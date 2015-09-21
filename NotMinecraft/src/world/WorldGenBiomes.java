package world;

import java.util.Random;

import array.GenTexture;
import data.WorldSettings;

public class WorldGenBiomes extends WorldGenBase {

	public WorldGenBiomes(World w, Random r) {
		
		world = w;
		random = r;
		
	}
	
	public boolean generate() {
		
		GenTexture noisegen = new GenTexture(length, 64, "temperature");
		
		float[][] m = noisegen.genNoise(random).smooth(1024).map().getArray();
		
		
		apply(m);
		
		return true;
		
	}
	void apply(float[][]m) {
		
		for (int x = 0; x < WorldSettings.x; x++) {
		for (int y = 0; y < WorldSettings.y; y++) {
				
			BiomeBase biome;
			
			double t = m[x][y];
			
			world.setTemperature(x, y, (float)m[x][y]);
			
			if (t > 0.9) {
				biome = BiomeBase.desert;
			} else if (t < 0.1) {
				biome = BiomeBase.snowyForest;
			} else {
				biome = BiomeBase.forest;
			}
			world.setBiome(x, y, biome);
				
		}
		}
		
	}

	
	int length = 128;
	int jump = 96;
	float high = 3000f;//18000f;
	int h_smooth = 900;

}
