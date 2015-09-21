package item;

import block.Block;
import client.Game;
import data.Camera;
import util.Vector3d;
import world.World;

public class ItemBucket extends Item {

	public ItemBucket(int id, int index) {
		super(id, index);
	}
	
	@Override
	public int getItemIndexInTexture(int meta) {
		return 129 + meta;
	}
	
	@Override
	public boolean onActivation(World world, Vector3d pos, Vector3d shift, Block block, int meta) {
		
		if (meta == 0) {
		
			Camera cam = Game.getCurrentCamera();
			
			double yaw = Math.toRadians(cam.getYaw());
			double pitch = Math.toRadians(cam.getPitch());
			
			Vector3d dir = new Vector3d(-Math.sin(yaw) *	-Math.sin(pitch),
										Math.cos(yaw) *		-Math.sin(pitch),
															-Math.cos(pitch));
			
			
			Vector3d newv = binaryProjection(cam.getPosition(), dir, world);
	
			if (newv != null) {
			
				int x = (int) newv.x;
				int y = (int) newv.y;
				int z = (int) newv.z;
				
				if (world.setBlockOn(x, y, z, Block.air, Block.water))
					world.updateVBOs(x, y, z);
				
					Game.getPlayer().getInventory().addItem(new ItemStack(bucket, 1, 1));
					
					return true;
				
			}
			
			return false;
		
		} else if (meta == 1) {
			
			int x = (int) (pos.x + shift.x);
			int y = (int) (pos.y + shift.y);
			int z = (int) (pos.z + shift.z);
			
			if (world.setBlockOn(x, y, z, Block.water, Block.air)) {
				world.updateVBOs(x, y, z);

				Game.getPlayer().getInventory().addItem(new ItemStack(bucket, 1, 0));
				
				return true;
			}
			
		}
			
		return false;
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
				return null;
			}
			
			int meta = world.getBlockMetadata((int) temp.x, (int) temp.y, (int) temp.z);
			float[] bounds = picked_block.getBounds(meta);
			
			if (picked_block != Block.air && picked_block.isFluid() &&
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

}
