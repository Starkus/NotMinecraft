package block;

import item.ItemBlock;
import item.ItemStack;
import util.Vector3d;
import world.World;

public class BlockSlab extends Block{

	public BlockSlab(int id, int index)
	{
		super(id, index);
	}
	public int getBlockIndexInTexture(int f, int meta)
	{
		if (texture_index == -1) {
			if (f==4 || f==5) {
				return 6;
			} else {
				return 5;
			}
		} else {
			return texture_index;
		}
	}
	
	@Override
	public float[] getBounds(int meta) {
		if (meta == 0) {
			return new float[] {0f, 0f, 0f, 1f, 1f, 0.5f};
		} else if (meta == 1) {
			return new float[] {0f, 0f, 0.5f, 1f, 1f, 1f};
		} else {
			return new float[] {0f, 0f, 0f, 1f, 1f, 1f};
		}
	}
	
	public boolean onActivation(World world, Vector3d pos, Vector3d shift, Block block, int m) {
		
		int meta = world.getBlockMetadata((int)pos.x, (int)pos.y, (int)pos.z);
		
		if (shift.z > 0 && meta == 0 && block != null && block.id == id) {
			
			if (world.setBlockAndMetadata((int) (pos.x), (int) (pos.y), (int) (pos.z), this, 2)) {
				world.updateVBOs((int) (pos.x), (int) (pos.y), (int) (pos.z));
				return true;
			}
		}
		if (shift.z < 0 && meta == 1 && block != null && block.id == id) {
			
			if (world.setBlockAndMetadata((int) (pos.x), (int) (pos.y), (int) (pos.z), this, 2)) {
				world.updateVBOs((int) (pos.x), (int) (pos.y), (int) (pos.z));
				return true;
			}
		}
		
		return super.onActivation(world, pos, shift, block, meta);
		
	}
	
	public ItemStack getItemDrop(int meta) {
		
		ItemBlock i = new ItemBlock(id);
		
		if (meta == 2) {
			return new ItemStack(i, 2);
		}
		return new ItemStack(i, 1);
	}

}
