package world;

import java.util.Random;

import block.Block;
import util.RNG;

public class WorldGenFlora extends WorldGenBase {
	public WorldGenFlora(int li, int lj, int lk,
			int mi, int mj, int mk, 
			World w, Random r) {

		minx = li;
		miny = lj;
		minz = lk;
		maxx = mi;
		maxy = mj;
		maxz = mk;
		world = w;
		random = r;
	}
	
	public boolean generate() {
		
		int x, y, z;
		
		for (x=minx; x<maxx; x++) {
			for (y=miny; y<maxy; y++) {
				for (z=80; z>42; z--) {
					
					if (world.getBlockId(x, y, z) == 0 && (world.getBlockId(x, y, z-1) == Block.grass.id || world.getBlockId(x, y, z-1) == Block.dirt.id)) {					
					
						int r = RNG.nextInt(random, 0, 100);
						if(r <= 5) {
							world.setBlockOn(x, y, z, Block.tallRoses, Block.air);
						} else if (r <= 10) {
							world.setBlockOn(x, y, z, Block.tallFlower, Block.air);
						} else if (r == 11) {
							world.setBlockOn(x, y, z, Block.flint, Block.air);
						} else if (r <= 30) {
							world.setBlockOn(x, y, z, Block.tallGrass, Block.air);
						}
					}
				}
			}
		}
		
		return true;
		
	}
	
	int minx, miny, minz, maxx, maxy, maxz;
	World world;
	Random random;
	
}
