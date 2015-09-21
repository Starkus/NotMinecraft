package world;

import java.util.Random;

import block.Block;
import util.RNG;

public class WorldGenCactus extends WorldGenBase {

	public WorldGenCactus(int li, int lj, int lk,
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
		
		for (x=minx; x<maxx; x++)
		{
		for (y=miny; y<maxy; y++)
		{
		for (z=128; z>0; z--)
		{
			
		if (world.getBlockId(x, y, z) == 0 && world.getBlockId(x, y, z-1) == Block.sand.id) {
					
			int r = RNG.nextInt(0, 50);
			int l = RNG.nextInt(1, 4);
					
			if (r == 0)
			{
				boolean s = true;
				for (int xx = x-2; xx <= x+2; xx++) {
				for (int yy = y-2; yy <= y+2; yy++) {
					if (world.getBlockId(xx, yy, z) != Block.air.id) {
						s = false;
						break;
					}
				}
				}
				
				if (s) {
					
					for (int z1 = 0; z1 < l; z1++)
					{
						world.setBlock(x, y, z+z1, Block.cactus);
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
	
}
