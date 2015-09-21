package util;

import java.util.Random;

public class RNG {
	public static int nextInt(int min, int max) {
		
		double rand = Math.random();
		int result = ((int) (rand*(max-min+1))+min);
		
		return result;
	}
	public static int nextInt(Random rng, int min, int max) {
		
		int rand = rng.nextInt(max-min);
		int result = rand+min;
		
		return result;
	}
	public static String nextString(Random rng, int lenght) {
		String out = "";
		
		for (int i = 0; i < lenght; i++) {
			out += (char) nextInt(rng, 33, 125);
		}
		
		return out;
	}
}
