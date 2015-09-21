package world;

import block.Block;
import util.RNG;

public class WorldGenLayer extends WorldGenBase {

	public WorldGenLayer(int li, int lj, int lk,
			int mi, int mj, int mk, 
			World w) {

		minx = li;
		miny = lj;
		minz = lk;
		maxx = mi;
		maxy = mj;
		maxz = mk;
		world = w;;
	}

	public boolean generate() {
		
		int scale = 64;
		for (int times = 0; times <= 1; times++) {
			int x, y, z;
			int s = scale / (times+1);
			for (x = 0; x <= maxx; x += s) {
				for (z = 0; z <= maxz; z += s) {
					y = RNG.nextInt(miny, maxy);
					world.setBlock(x, y, z, Block.grass);
				}
			}
		}
		
		return true;
	}
	
	int minx, miny, minz, maxx, maxy, maxz;
	World world;

}
