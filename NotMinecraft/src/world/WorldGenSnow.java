package world;

import java.util.Random;

import block.Block;

public class WorldGenSnow extends WorldGenBase {
	public WorldGenSnow(int li, int lj, int lk,
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
					if (world.getBiome(x, y) == BiomeBase.snowyForest) {
					if (world.getBlockId(x, y, z) == 0 && (world.getBlockId(x, y, z-1) == Block.grass.id || world.getBlockId(x, y, z-1) == Block.dirt.id || world.getBlockId(x, y, z-1) == Block.snowyGrass.id)) {					
					
						world.setBlockOn(x, y, z, Block.snow, Block.air);
						
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
