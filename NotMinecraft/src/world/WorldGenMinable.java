package world;

import block.Block;
import util.RNG;

public class WorldGenMinable extends WorldGenBase {
	public WorldGenMinable(int li, int lj, int lk,
							int mi, int mj, int mk, 
							World w, Block b, Block v, int r, int cs) {
		
		minx = li;
		miny = lj;
		minz = lk;
		maxx = mi;
		maxy = mj;
		maxz = mk;
		world = w;
		block = b;
		on = v;
		rarity = r;
		chunksize = cs;
		
	}
	public boolean generate() {
		
		int x, y, z;
		
		for (x=minx; x<maxx; x++) {
			for (y=miny; y<maxy; y++) {
				for (z=minz; z<maxz; z++) {
					
					int s;
					int size = RNG.nextInt(chunksize/2, chunksize);
					
					int r = RNG.nextInt(0, rarity);
					
					if(r == 0) {
						
						int x1 = x;
						int y1 = y;
						int z1 = z;
						
						for (s=0; s<size; s++) {
							int dir = RNG.nextInt(0, 5);
							if (dir==0) {
								x1++;
							}
							if (dir==1) {
								x1--;
							}
							if (dir==2) {
								y1++;
							}
							if (dir==3) {
								y1--;
							}
							if (dir==4) {
								z1++;
							}
							if (dir==5) {
								z1--;
							}
							world.setBlockOn(x1, y1, z1, block, on);
						}
					}
				}
			}
		}
		
		return true;
	}
	
	int minx, miny, minz, maxx, maxy, maxz, rarity, chunksize;
	Block block, on;
	World world;
	
}
