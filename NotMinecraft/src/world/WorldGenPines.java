package world;

import java.util.Random;

import block.Block;
import data.WorldSettings;
import util.RNG;

public class WorldGenPines extends WorldGenBase {

	public WorldGenPines(float d, World w, Random r) {

		minx = 0;
		miny = 0;
		maxx = WorldSettings.x;
		maxy = WorldSettings.y;
		world = w;
		density = d;
		random = r;
	}

	public boolean generate() {

		int x, y, z;
		
		for (x=minx; x<maxx; x++)
		{
		for (y=miny; y<maxy; y++)
		{
		for (z=80; z>42; z--)
		{
			
		if (world.getBiome(x, y) == BiomeBase.snowyForest) {
		if (world.getBlockId(x, y, z) == 0 && (world.getBlockId(x, y, z-1) == Block.grass.id || world.getBlockId(x, y, z-1) == Block.dirt.id || world.getBlockId(x, y, z-1) == Block.snowyGrass.id)) {
					
			int r = RNG.nextInt(random, 0, 20);
			int l = RNG.nextInt(random, 4, 5);
					
			if (r == 0)
			{
				Block log, leaves;
				log = Block.woodPine;
				leaves = Block.leavesPine;
				
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
					
					for (int z5 = 0; z5 < 3; z5++) {
						for (int z2 = 0; z2 <= 3; z2++)
						{
							int z3 = (4-z2)/2;
							for (int x1 = x-z3; x1 <= x+z3; x1++)
							{
								for (int y1 = y-z3; y1 <= y+z3; y1++)
								{
									world.setBlockAndMetadataOn(x1, y1, z+l+z2-2+(z5*2), leaves, 2, Block.air);
								}
							}
						}
					}
				
					int m = 4;
				
					for (int z1 = 0; z1 < l+m; z1++)
					{
						world.setBlockAndMetadata(x, y, z+z1, log, 2);
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
	
	int minx, miny, maxx, maxy;
	float density;

}