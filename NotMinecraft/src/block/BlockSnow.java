package block;

import util.Vector3d;
import world.World;

public class BlockSnow extends Block{

	public BlockSnow(int id, int index)
	{
		super(id, index);
	}
	public float[] getBounds(int meta) {
		return new float[] {0F, 0F, 0F, 1F, 1F, 2/16f};
	}
	
	public boolean onActivation(World world, Vector3d pos, Vector3d shift, Block block, int meta) {
		
		if (shift.z > 0) {
			if (block != null && world.setBlock((int) (pos.x), (int) (pos.y), (int) (pos.z), block)) {
				world.updateVBOs((int) (pos.x), (int) (pos.y), (int) (pos.z));
				return true;
			}
		}
		
		return super.onActivation(world, pos, shift, block, meta);
		
	}
}