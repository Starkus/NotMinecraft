package world;

import java.util.Random;

import block.Block;
import util.RNG;

public class WorldGenDesertFlora extends WorldGenBase {
	public WorldGenDesertFlora(int li, int lj, int lk,
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
					
					if (world.getBlockId(x, y, z) == 0 && (world.getBlockId(x, y, z-1) == Block.sand.id)) {					
					
						int r = RNG.nextInt(0, 100);
						if(r == 0) {
							world.setBlockOn(x, y, z, Block.deadPlant, Block.air);
						}
					}
				}
			}
		}
		
		return true;
		
	}
	
	int minx, miny, minz, maxx, maxy, maxz;
	
}