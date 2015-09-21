package world;

import java.util.Random;

import org.lwjgl.opengl.Display;

import block.Block;
import data.WorldSettings;
import util.RNG;

public class WorldGenTrees extends WorldGenBase {

	public WorldGenTrees(float d, World w, Random r) {

		minx = 0;
		miny = 0;
		maxx = WorldSettings.x;
		maxy = WorldSettings.y;
		world = w;
		random = r;
		density = d;
		zmin = 42;
		zmax = 80;
	}
	
	public WorldGenTrees(float d, int zmin, int zmax, World w) {
		
		minx = 0;
		miny = 0;
		maxx = WorldSettings.x;
		maxy = WorldSettings.y;
		world = w;
		density = d;
		this.zmin = zmin;
		this.zmax = zmax;		
	}

	public boolean generate() {

		int x, y, z;
		
		for (x = minx; x < maxx; x++) {
			for (y = miny; y < maxy; y++) {
				for (z = zmax; z > zmin; z--) {
					
				if (world.getBiome(x, y) == BiomeBase.forest) {
				if (world.getBlockId(x, y, z) == 0 && (world.getBlockId(x, y, z-1) == Block.grass.id || world.getBlockId(x, y, z-1) == Block.dirt.id)) {
							
					int r = RNG.nextInt(random, 0, (int) (30/density));
					int l = RNG.nextInt(random, 5, 6);
							
					if (r == 0)
					{
						int type = RNG.nextInt(random, 0, 3);
						Block log, leaves;
						
						log = Block.wood;
						leaves = Block.leaves;
						int meta;
						
						if (type!=0) {
							meta = 0;
						} else {
							meta = 1;
						}
						
						boolean enough_room = true;
						for (int xx = x-3; xx <= x+3; xx++) {
						for (int yy = y-3; yy <= y+3; yy++) {
						for (int zz = z-0; zz <= z+6; zz++) {
							
							if (world.getBlockId(xx, yy, zz) != Block.air.id) {
								enough_room = false;
								break;
							}
							
						}
						}
						}
						
						if (enough_room) {
							
							for (int z2 = 0; z2 < 4; z2++)
							{
								int i = RNG.nextInt(random, 7, 8);
								int z3 = (i-z2)/3;
								for (int x1 = x-z3; x1 <= x+z3; x1++)
								{
									for (int y1 = y-z3; y1 <= y+z3; y1++)
									{
										if (z2 % 2 == 0)
											world.setBlockAndMetadataOn(x1, y1, z+l+z2-3, leaves, meta, Block.air);
										else if (z2 == 1){
											if (!(Math.abs(x1-x) == 2 && Math.abs(y1-y) == 2))
												world.setBlockAndMetadataOn(x1, y1, z+l+z2-3, leaves, meta, Block.air);
										} else {

											if (!(Math.abs(x1-x) == 1 && Math.abs(y1-y) == 1))
												world.setBlockAndMetadataOn(x1, y1, z+l+z2-3, leaves, meta, Block.air);
										}
									}
								}
							}
						
							for (int z1 = 0; z1 < l; z1++)
							{
								world.setBlockAndMetadata(x, y, z+z1, log, meta);
							}
						}
					}
				}
				}
					
				}
			}
			
			if (x%16 == 0){
				WorldGenerator.scrn.setProgress(x / (float) maxx);
				WorldGenerator.scrn.draw();
				Display.update();
			}
		}
		
		return true;
	}
	
	int minx, miny, maxx, maxy;
	int zmin, zmax;
	float density;

}