package entity;

import java.nio.FloatBuffer;
import org.lwjgl.input.Mouse;

import block.Block;
import client.Game;
import data.Camera;
import engine.Culling;
import item.ItemStack;
import model.ModelBase;
import util.Vector3d;
import world.World;

public class EntityMarker extends Entity{
	public EntityMarker(float i, float j, float k) {
		super(i, j, k);
		setScale(0.315f);
	}
	
	public boolean update(FloatBuffer float_buffer) {
		
		setBounds(Block.glass.getBounds(0));
		
		Camera cam = Game.getCurrentCamera();
		
		double yaw = Math.toRadians(cam.getYaw());
		double pitch = Math.toRadians(cam.getPitch());
		
		World world = Game.getCurrentWorld();
		
		Block picked_block;
		
		Vector3d dir = new Vector3d(-Math.sin(yaw) *	-Math.sin(pitch),
									Math.cos(yaw) *		-Math.sin(pitch),
														-Math.cos(pitch));
		
		
		Vector3d newv = binaryProjection(cam.getPosition(), dir, world);
		
		
		if (newv != null) {

			picked_block = world.getBlock((int) newv.x, (int) newv.y, (int) newv.z);
			int meta = world.getBlockMetadata((int) newv.x, (int) newv.y, (int) newv.z);
			setBounds(picked_block.getBounds(meta));
			
			Vector3d shift = shiftNormally(newv, picked_block, meta);

			position.x = newv.x;
			position.y = newv.y;
			position.z = newv.z;
			
			boolean r = checkEvents(world, shift, picked_block, meta, float_buffer);

			position.x = (int) newv.x + .5;
			position.y = (int) newv.y + .5;
			position.z = (int) newv.z + .5;
			
			return r;
			
		}
		
		return false;
	}
	
	private boolean checkEvents(World world, Vector3d shift, Block picked_block, int meta, FloatBuffer float_buffer) {
		boolean update = false;
		
		if (Mouse.isButtonDown(0) && !lmb_last && picked_block.id != 0 && Mouse.isGrabbed()) {
			
			ItemStack drop = picked_block.getItemDrop(meta);
			if (drop != null)
				Game.getPlayer().getInventory().addItem(drop);
			
			world.setBlock((int) position.x, (int) position.y, (int) position.z, Block.air);
			
			updateVBOs(world, (int) position.x, (int) position.y, (int) position.z, float_buffer);
			
			update = true;
		}
		
		if (Mouse.isButtonDown(1) && !rmb_last && Mouse.isGrabbed()) {
			
			Vector3d shifted = new Vector3d(position.x + shift.x,
											position.y + shift.y,
											position.z + shift.z);
			
			Block block = world.getBlock((int) shifted.x, (int) shifted.y, (int) shifted.z);
			if (block != null) {
				
				ItemStack stack_in_hand = Game.getPlayer().getInventory().getSlot(Game.getHud().getSlotInHand());
				
				if (stack_in_hand != null) {
				
					if (stack_in_hand.getItem().onActivation(world, position, shift, picked_block, stack_in_hand.getMetadata())) {
					
						stack_in_hand.size--;
						update = true;
					}
				} else {
					
					if (picked_block.onActivation(world, position, shift, null, meta)) {
						update = true;
					}
				}
			}
		}
		
		lmb_last = Mouse.isButtonDown(0);
		rmb_last = Mouse.isButtonDown(1);
		
		return update;
	}
	
	private void updateVBOs(World world, int x, int y, int z, FloatBuffer float_buffer) {

		Culling.UpdateFaceCulling(world, x, y, z);
		
		Game.getVBO(x/16, y/16).save(float_buffer, true);
		if (x%16 == 0)
			Game.getVBO(x/16 -1, 	y/16).save(float_buffer, true);
		if (x%16 == 15)
			Game.getVBO(x/16 +1, 	y/16).save(float_buffer, true);
		if (y%16 == 0)
			Game.getVBO(x/16, 		y/16 -1).save(float_buffer, true);
		if (y%16 == 15)
			Game.getVBO(x/16, 		y/16 +1).save(float_buffer, true);
		
	}
	
	private Vector3d binaryProjection(Vector3d pos, Vector3d dir, World world) {
		
		float step = 0.02f;
		Vector3d temp = new Vector3d();
		Block picked_block;
		
		while (true) {
			
			temp.x = pos.x + (step * dir.x);
			temp.y = pos.y + (step * dir.y);
			temp.z = pos.z + (step * dir.z);
			
			picked_block = world.getBlock((int) temp.x, (int) temp.y, (int) temp.z);
			
			if (picked_block == null) {
				setBounds(new float[6]);
				return null;
			}
			
			int meta = world.getBlockMetadata((int) temp.x, (int) temp.y, (int) temp.z);
			float[] bounds = picked_block.getBounds(meta);
			
			if (picked_block != Block.air && !picked_block.isFluid() &&
					temp.x%1 > bounds[0] &&
					temp.y%1 > bounds[1] &&
					temp.z%1 > bounds[2] &&
					temp.x%1 < bounds[3] &&
					temp.y%1 < bounds[4] &&
					temp.z%1 < bounds[5]) {
				step /= 2f;
			}
			else {
				pos = temp;
			}
	
			if (step <= 0.001f) {
				pos = temp;
				break;
			}
		}
		return pos;
	}
	
	private Vector3d shiftNormally(Vector3d pos, Block block, int meta) {
		
		Vector3d newv = new Vector3d();
		
		float b[] = block.getBounds(meta);
		double differences[] = new double[]{
				Math.abs(pos.x %1 - b[0]),
				Math.abs(pos.y %1 - b[1]),
				Math.abs(pos.z %1 - b[2]),
				Math.abs(pos.x %1 - b[3]),
				Math.abs(pos.y %1 - b[4]),
				Math.abs(pos.z %1 - b[5])};
		double min = 2;
		int index = -1;
		
		for (int i = 0; i < 6; i++) {
			if (differences[i] < min) {
				min = differences[i];
				index = i;
			}
		}
		
		switch (index) {
		case 0:
			newv.x -= 1;
			break;

		case 1:
			newv.y -= 1;
			break;

		case 2:
			newv.z -= 1;
			break;

		case 3:
			newv.x += 1;
			break;

		case 4:
			newv.y += 1;
			break;

		case 5:
			newv.z += 1;
			break;
		}
	
		return newv;
	}
	
	
	public boolean isTextured() {
		return true;
	}
	
	public ModelBase getModel() {
		return ModelBase.marker;
	}

	private boolean lmb_last = false;
	private boolean rmb_last = false;
}
