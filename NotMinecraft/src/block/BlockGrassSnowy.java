package block;

import world.World;

public class BlockGrassSnowy extends Block{

	public BlockGrassSnowy(int id, int index)
	{
		super(id, index);
	}
	public int getBlockIndexInTexture(int f, int meta) {
		if (f == 4) {
			return 18;
		} else if (f == 5) {
			return 2;
		} else {
			return 19;
		}
	}
	
	public void onUpdate(World world, int x, int y, int z) {
		
		if (world.getBlockId(x, y, z+1) != Block.snow.id) {
			world.setBlock(x, y, z, Block.grass);
		}
		
	}
	
}

