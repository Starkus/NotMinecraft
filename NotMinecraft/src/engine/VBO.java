package engine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.newdawn.slick.opengl.Texture;

import block.Block;
import client.Game;
import data.WorldSettings;
import entity.Entity;
import entity.RenderEntity;
import world.World;

public class VBO {

	public VBO(Texture tex, World world, int x, int y) {

		IntBuffer buffer = BufferUtils.createIntBuffer(1);
		GL15.glGenBuffers(buffer);
		id =  buffer.get(0);
		
		x_pos = x;
		y_pos = y;
		texture = tex;
		
		//Lighting.updateLightMass(world, this);
	}
	
	public void initializeLighting(World world) {
		Lighting.updateLightMass(world, this);
	}
	
	private void getBlocksToRender(World world) {
		
		blocks.clear();
		alpha_blocks.clear();
		entities.clear();
		render_list.clear();
		
		for (int x = x_pos*16; x < (x_pos+1)*16; x++) {
			for (int y = y_pos*16; y < (y_pos+1)*16; y++) {
				for (int z = 0; z < WorldSettings.z; z++) {
					GameObject ob = world.objects[x][y][z];
					Block b = Block.blockList[ob.block];
					
					b.onUpdate(world, x, y, z);
					b.onRender(world, ob);
					
					if (ob.block != 0 && ob.isVisible()) {
						if (b.getEntity(x, y, z) != null) {
							entities.add(b.getEntity(x, y, z));
						} else if (b.hasAlpha()) {
							alpha_blocks.add(ob);
						} else {
							blocks.add(ob);
						}
					}
				}
			}
		}
		
		render_list.addAll(blocks);
		render_list.addAll(alpha_blocks);
	}
	
	public void save(FloatBuffer float_buffer, boolean update) {
		
		World world = Game.getCurrentWorld();
		
		getBlocksToRender(world);
		
		if (update) Lighting.updateLightMass(world, this);
		
		float_buffer.clear();
		
		setAlphaOffset(0);

		for (int obi = 0; obi < render_list.size(); obi++) {
			GameObject ob = render_list.get(obi);
			Block b = Block.blockList[ob.block];
			
			Mesh mesh = b.getMesh(ob.meta);
				
			if (ob.block!=0 && ob.isVisible() && mesh.polygons != null) {
				
				if (b.hasAlpha() && getAlphaOffset() == 0) {
					setAlphaOffset(float_buffer.position() / 12);
				}
						
				int t_length = mesh.polygons.length;
				float[] pos = ob.position;
			
				for (int l_index=0; l_index<t_length; l_index++) {
					if (ob.culling[l_index%12]==true || b.isEntireBlock() == false) {
								
						Polygon poly = mesh.polygons[l_index];
						Vertex[] verts = poly.verts;
						UVVertex[] uv = poly.uv;
						
						for (int i = 0; i < 3; i++) {
						
							float_buffer.put(verts[i].x + pos[0]);
							float_buffer.put(verts[i].y + pos[1]);
							float_buffer.put(verts[i].z + pos[2]);
						
							float_buffer.put(poly.normal.x);
							float_buffer.put(poly.normal.y);
							float_buffer.put(poly.normal.z);
						
							//float t = Game.getCurrentWorld().getTemperature((int)(verts[i].x + pos[0]), (int)(verts[i].y + pos[1]));
							float l = Lighting.getLightingAtPoint(verts[i].x + pos[0], verts[i].y + pos[1], verts[i].z + pos[2]);
							//float l = 1f;
							
							float_buffer.put(ob.getColor()[0] * l);// * t);
							float_buffer.put(ob.getColor()[1] * l);// * 0.75f);
							float_buffer.put(ob.getColor()[2] * l);// * 1f-t);
							float_buffer.put(1f);

							float_buffer.put(uv[i].u);
							float_buffer.put(uv[i].v);
					
						}
					}
				}
			}
		}
		
		limit = float_buffer.position() / 12;
		
		if (alpha_offset == 0) alpha_offset = limit;
		
		float_buffer.rewind();
		
		if (!update) {
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, getID());
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, float_buffer, GL15.GL_DYNAMIC_DRAW);
		}
		else {
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, getID());
			GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, float_buffer);
		}

		float_buffer.flip();
	}
	
	public void render(boolean alpha) {
		
		texture.bind();
		
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		
		//GL11.glDisable(GL11.GL_TEXTURE_2D);
				
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, id);
					
		int stride = (3 + 3 + 4 + 2) * 4; // 3 for vertex, 3 for normal, 4 for colour and 2 for texture coordinates. * 4 for bytes
					 
		// vertices
		int offset = 0 * 4; // 0 as its the first in the chunk, i.e. no offset. * 4 to convert to bytes.
		GL11.glVertexPointer(3, GL11.GL_FLOAT, stride, offset);
		 
		// normals
		offset = 3 * 4; // 3 components is the initial offset from 0, then convert to bytes
		GL11.glNormalPointer(GL11.GL_FLOAT, stride, offset);
		 
		// colours
		offset = (3 + 3) * 4; // (6*4) is the number of byte to skip to get to the colour chunk
		GL11.glColorPointer(4, GL11.GL_FLOAT, stride, offset);
		 
		// texture coordinates
		offset = (3 + 3 + 4) * 4;
		GL11.glTexCoordPointer(2, GL11.GL_FLOAT, stride, offset);
		
		for (int j = 0; j < entities.size(); j++) {
			Entity e = entities.get(j);
			e.update();
			RenderEntity.render(e);
		}
		
		texture.bind();
		
		//GL11.glDisable(GL11.GL_TEXTURE_2D);

		if (!alpha) {
	
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_ALPHA);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, alpha_offset);
		}
					
		else if (limit - alpha_offset > 0) {
						
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDrawArrays(GL11.GL_TRIANGLES, alpha_offset, limit - alpha_offset);
		}
	}
	
	public int getID() {
		return id;
	}
	public int[] getLocation() {
		return new int[]{x_pos, y_pos};
	}
	
	public int getAlphaOffset() {
		return alpha_offset;
	}
	public void setAlphaOffset(int k) {
		alpha_offset = k;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int k) {
		limit = k;
	}
	
	public void setForUpdate() {
		update = true;
	}
	public boolean shouldUpdate() {
		if (update) {
			update = false;
			return true;
		}
		return false;
	}
	
	public ArrayList<Entity> getEntityList() {
		return entities;
	}
	
	private int id;
	private int x_pos, y_pos;
	private int alpha_offset;
	private int limit;
	
	private boolean update = false;

	private ArrayList<GameObject> blocks = new ArrayList<GameObject>();
	private ArrayList<GameObject> alpha_blocks = new ArrayList<GameObject>();
	private ArrayList<GameObject> render_list = new ArrayList<GameObject>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	Texture texture;

}
