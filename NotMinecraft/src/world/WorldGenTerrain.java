package world;

import java.util.Random;

import array.GenClouds;
import array.GenTexture;
import block.Block;
import data.WorldSettings;

public class WorldGenTerrain extends WorldGenBase {

	public WorldGenTerrain(World w, Random r) {
		
		world = w;
		random = r;
		
	}
	
	public boolean generate(int chunk_x, int chunk_y) {
		
		GenTexture noisegen = new GenTexture(length, 32, "terrain").setHillSmooth(256);
		
		float[][] m = noisegen.genNoise(random).map().multiply(16f).getArray();
		
		
		
		apply(m);
		
		return true;
		
	}
	
	public boolean generate() {
		
		GenClouds noisegen = new GenClouds(length, 4, "terrain");
		
		float[][] m = noisegen.generate(random).smooth(0).map().multiply(24f).getArray();
		
		
		
		apply(m);
		
		return true;
	}
	
	
	void apply(float[][]m) {
		
		BiomeBase biome;
		
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < length; y++) {
				
				int z = (int) m[x][y]+38;
				
				biome = world.getBiome(x, y);
				
				for (int i = 0; z >= 0; i++) {
							
					Block block;
					
					if (i == 0) block = biome.topBlock();
					else if (i < 3) block = biome.fillBlock();
					else block = Block.stone;
					
					world.setBlock(x, y, z, block);
					z--;
					
				}
				
			}
		}
		
	}
	
	World world;
	Random random;
	
	int length = WorldSettings.x;
	int jump = 8;
	float high = 175.0f*6;
	int h_smooth = 18*4;
	int v_smooth = 4;
	int valley = 0;
	int make_roads = 20;
	float deep = -4f;

}
