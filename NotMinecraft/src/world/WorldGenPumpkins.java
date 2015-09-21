package world;

import java.util.Random;

import block.Block;
import util.RNG;

public class WorldGenPumpkins extends WorldGenBase {

	public WorldGenPumpkins(int lx, int ly,
			int mx, int my, 
			World w, Random r, Block b) {

		minx = lx;
		miny = ly;
		maxx = mx;
		maxy = my;
		world = w;
		random = r;
		block = b;
	}

	public boolean generate() {

		int x, y, z;
		
		for (x=minx; x<maxx; x++) {
			for (y=miny; y<maxy; y++) {
					
				int r = RNG.nextInt(random, 0, 5000);
				int radio = 3;
			
				if (r == 0) {
					for (int x1 = -radio; x1 <= radio; x1++) {
						for (int y1 = -radio; y1 <= radio; y1++) {
							int r1 = RNG.nextInt(random, 0, 8);
							if (r1 == 0) {
								for (z=80; z>42; z--) {
									if (world.getBlockId(x+x1, y+y1, z) == 0 && (world.getBlockId(x+x1, y+y1, z-1) == Block.grass.id || world.getBlockId(x+x1, y+y1, z-1) == Block.dirt.id)) {
										world.setBlock(x+x1, y+y1, z, block);
									}
								}
							}
						}
					}
				}
			}
		}
		
		return true;
	}
	
	int minx, miny, minz, maxx, maxy, maxz;
	Block block;

}
