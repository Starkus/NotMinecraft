package array;

import java.util.Random;

public class GenClouds {

	public GenClouds(int size, int iterations, String seed) {
		
		this.size = size;
		this.iterations = iterations;
		this.seed = seed;
		
	}
	
	public Array2D generate(Random random) {

		Array2D result = null;
		
		for (int i = 0; i < iterations; i++) {
			int jump = (int)Math.pow(2, i);
			int smooth = (int)Math.pow(2, jump);
			Array2D b = new GenTexture(size, jump, seed).genNoise(random).smooth(smooth).map();
			
			if (i == 0) {
				result = b;
			} else {
				result = result.mix(b, 0.4f + (0.1f * i));
			}
		}
		
		return result;
		
	}
	
	int size, iterations;
	String seed;

}
