package world;

import java.util.Random;

import util.RNG;

public class WorldGenHouses extends WorldGenBase {
	
	public WorldGenHouses(int li, int lj, int lk,
			int mi, int mj, int mk, 
			World w, Random r) {

		minx = li;
		miny = lj;
		minz = lk;
		maxx = mi;
		maxy = mj;
		maxz = mk;
		world = w;
		struct = new StructureHouse1();
	}

	public boolean generate() {
		
		for (int t = 0; t < 10; t++) {
		
			int i = RNG.nextInt(7, 8);
			int j = RNG.nextInt(5, 8);
			
			int x = RNG.nextInt(minx, maxx);
			int y = RNG.nextInt(miny, maxy);
			int z = RNG.nextInt(minz, maxz);
		
			if (struct.canPlaceAt(x, y, z, world, i, j)) {
				struct.place(x, y, z, world, i, j);
			}
		}
		
		return true;
	}
	
	int minx, miny, minz, maxx, maxy, maxz;
	World world;
	Structure struct;
	
}
