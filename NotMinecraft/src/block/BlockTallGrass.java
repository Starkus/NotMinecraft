package block;

import util.Vector3d;
import world.World;

public class BlockTallGrass extends BlockCross{

	public BlockTallGrass(int id, int index) {
		super(id, index);
	}
	
	public boolean onActivation(World world, Vector3d pos, Vector3d shift, Block block, int meta) {
		
		if (block != null && world.setBlock((int) (pos.x), (int) (pos.y), (int) (pos.z), block)) {
			world.updateVBOs((int) (pos.x), (int) (pos.y), (int) (pos.z));
			return true;
		}
		
		return false;
		
	}

}
