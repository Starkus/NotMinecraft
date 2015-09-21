package block;

import data.WorldSettings;
import item.ItemBlock;
import item.ItemStack;
import world.World;

public class BlockGrass extends Block{

	public BlockGrass(int id, int index)
	{
		super(id, index);
	}
	public int getBlockIndexInTexture(int f, int meta) {
		if (f == 4) {
			return 0;
		} else if (f == 5) {
			return 2;
		} else {
			return 1;
		}
	}
	
	public ItemStack getItemDrop(int meta) {
		ItemBlock i = new ItemBlock(Block.dirt.id);
		return new ItemStack(i, 1);
	}
	
	public void onUpdate(World world, int x, int y, int z) {
		
		if (world.getBlockId(x, y, z+1) == Block.snow.id) {
			world.setBlock(x, y, z, Block.snowyGrass);
		}
		else if (z+1 < WorldSettings.z && world.getBlock(x, y, z+1).isEntireBlock()) {
			world.setBlock(x, y, z, Block.dirt);
		}
		
	}	
}

