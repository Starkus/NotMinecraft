package gui;

import java.util.Enumeration;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import block.Block;
import client.Game;
import data.EnumBlockItemDrawMethod;
import engine.Mesh;
import engine.Polygon;
import engine.UVVertex;
import engine.Vertex;
import item.Item;
import item.ItemBlock;
import item.ItemStack;
import model.ModelBase;
import model.ModelBox;
import util.Vector3d;

public class HudItem extends HudObject {

	public HudItem(float x, float y) {
		super(x, y);
		texture = Game.getWorldTexture();
		quant = (HudTextLabel) new HudTextLabel(x, y).setScale(.3f).setAlignment(1, 0);
	}
	
	public void setItem(ItemStack itemstack) {
		this.itemstack = itemstack;
	}
	public ItemStack getItem() {
		return itemstack;
	}

	@Override
	public void tick() {
		quant.x_pos = getPosition().x + 3.5f;
		quant.y_pos = getPosition().y - 4f;
	}

	public void draw() {
		
		if (itemstack == null) return;
		
		if (itemstack.size > 1)
			quant.write(Integer.toString(itemstack.size));
		else
			quant.write("");
		
		texture.bind();
		
		Item item = itemstack.getItem();
		
		if (itemstack.getItem().getClass() == ItemBlock.class) {
		
			Block block = Block.blockList[item.id];
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ZERO);
			
			if (block.getItemDrawMethod() == EnumBlockItemDrawMethod.BLOCK) {
				drawBlock(block, itemstack.getMetadata());
			} else if (block.getItemDrawMethod() == EnumBlockItemDrawMethod.PLANE) {
				drawPlane(itemstack.getItem(), itemstack.getMetadata());
			} else if (block.getItemDrawMethod() == EnumBlockItemDrawMethod.MODEL) {
				drawEntity(block);
			}
		} else {
			
			drawPlane(itemstack.getItem(), itemstack.getMetadata());
			
		}
			
		quant.draw();

	}
	private Vector3d rotate(Vector3d v, float pitch, float roll, float yaw) {
		
		Vector3d r = new Vector3d(v.x, v.y, v.z);

		double radx = Math.toRadians(pitch+90f);
		double rady = Math.toRadians(roll);
		double radz = Math.toRadians(yaw);
		float x1, y1, z1;
		
		// X axis
		y1 = (float) (Math.sin(radx)*(v.y - .5f) + Math.cos(radx)*(v.z - .5f));
		z1 = (float) (Math.cos(radx)*(v.y - .5f) - Math.sin(radx)*(v.z - .5f));
		// Y axis
		x1 = (float) (Math.cos(rady)*(v.x - .5f) + Math.sin(rady)*z1);
		r.z = (float) (Math.sin(rady)*(v.x - .5f) - Math.cos(rady)*z1);
		// Z axis
		r.x = (float) (Math.cos(radz)*x1 - Math.sin(radz)*y1);
		r.y = (float) (Math.sin(radz)*x1 + Math.cos(radz)*y1);
		
		r.x += .5f;
		r.y += .5f;
		r.z += .5f;
		
		return r;
		
	}
	
	private void drawEntity(Block block) {
		ModelBase model = block.getModel();
		
		float scale = 5f;

		float xp = getPosition().x;
		float yp = getPosition().y;
		
		
		if (model.texture != null) model.texture.bind();
		
		Enumeration<String> keys = model.getBoxes().keys();
		for (int b = 0; b < model.getBoxes().size(); b++) {
			ModelBox box = model.getBoxes().get(keys.nextElement());

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			
			GL11.glBegin(GL11.GL_TRIANGLES);
			
			int pl = box.p.length;
			
			float[] bm = model.bounds;
		
			for(int pi = 0; pi < pl; pi++) {
			
				Polygon p = box.p[pi];
				Vertex[] v = p.verts;
				UVVertex[] uv = p.uv;
				
				if (pi%2 == 0) {
					GL11.glNormal3f(p.normal.x, p.normal.y, p.normal.z);
				}
				
				float x, y, z;
				
				Vector3f n = p.normal;
				
				float lum = (Math.abs(n.x) + Math.abs(n.y)*.5f + Math.abs(n.z)*1.5f)/1f;
						
				for (int vi = 0; vi < 3; vi++) {
					x = (v[vi].x) * bm[0];
					y = (v[vi].y) * bm[1];
					z = (v[vi].z) * bm[2];

					Vector3d j = rotate(new Vector3d(x, y, z), 0f, 0f, 135f);
					Vector3d r = rotate(new Vector3d(j.x, j.y, j.z), 20f, 0f, 0f);

					GL11.glColor3f(lum, lum, lum);
					GL11.glTexCoord2f(uv[vi].u, uv[vi].v);
					GL11.glNormal3f(p.normal.x, p.normal.y, p.normal.z);
					GL11.glVertex3d(xp+(r.x-.5f) * scale, yp+(r.z-.5f) * scale, -(r.y) / 4f);
				}
			}
			GL11.glEnd();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);
		}
	}
	
	private void drawBlock(Block block, int meta) {
		float scale = 5f;
		
		float x = getPosition().x;
		float y = getPosition().y;
		
		Mesh mesh = block.getMesh(meta);
		
		
		for (int i = 0; i < mesh.polygons.length; i++) {
			
			GL11.glBegin(GL11.GL_TRIANGLES);
			
				Vector3f n = mesh.polygons[i].normal;
				
				float lum = (Math.abs(n.x) + Math.abs(n.y)*.5f + Math.abs(n.z)*1.5f)/1f;
				
				for (int k = 0; k < 3; k++) {
					Vertex v = mesh.polygons[i].verts[k];
					
					Vector3d j = rotate(new Vector3d(v.x, v.y, v.z), 0f, 0f, -45f);
					Vector3d r = rotate(new Vector3d(j.x, j.y, j.z), 20f, 0f, 0f);
					
					GL11.glColor3f(lum, lum, lum);
					GL11.glTexCoord2f(mesh.polygons[i].uv[k].u, mesh.polygons[i].uv[k].v);
					GL11.glVertex2d(x+(r.x-.5f)*scale, y+(r.z-.5f)*scale);
				}
			GL11.glEnd();
			
		}
		
	}
	
	
	private void drawPlane(Item item, int meta) {

		int tex_index = item.getItemIndexInTexture(meta);
		int ix = tex_index%16;
		int iy = tex_index/16;
		
		float scale = .9f;
		float size = 8f;
		
		float x = getPosition().x;
		float y = getPosition().y;
		
		float k = size/2f * scale;
		
		GL11.glBegin(GL11.GL_TRIANGLES);
			GL11.glColor3f(.8f, .8f, .8f);
			GL11.glTexCoord2f(ix/16f, (iy+1)/16f);
			GL11.glVertex2f(x-k, y-k);

			GL11.glTexCoord2f((ix+1)/16f, (iy+1)/16f);
			GL11.glVertex2f(x+k, y-k);

			GL11.glTexCoord2f(ix/16f, iy/16f);
			GL11.glVertex2f(x-k, y+k);
		GL11.glEnd(); 
		
		GL11.glBegin(GL11.GL_TRIANGLES);
			GL11.glTexCoord2f((ix+1)/16f, (iy+1)/16f);
			GL11.glVertex2f(x+k, y-k);
	
			GL11.glTexCoord2f((ix+1)/16f, iy/16f);
			GL11.glVertex2f(x+k, y+k);
	
			GL11.glTexCoord2f(ix/16f, iy/16f);
			GL11.glVertex2f(x-k, y+k);
		GL11.glEnd();
		
	}
	
	ItemStack itemstack;
	Texture texture;
	HudTextLabel quant;

}
