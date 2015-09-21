package array;

import java.util.Random;

import org.lwjgl.opengl.Display;

import client.Game;
import util.RNG;
import world.WorldGenerator;

public class GenTexture3D {
	
	public GenTexture3D(int size, int jump, String seed) {
		
		this.size = size;
		this.jump = jump;
		this.seed = seed;
		
	}
	
	public Array3D simplexNoise(Random random, int largest, float persistence) {
		SimplexNoise simplexNoise = new SimplexNoise(largest, persistence, seed.hashCode());

		
		if (WorldGenerator.scrn != null)
			WorldGenerator.scrn.setLabel("Making noise");



		double x_start = 0;
		double x_end = size;
		double y_start = 0;
		double y_end = size;
		double z_start = 0;
		double z_end = size;

		int x_size = size;
		int y_size = size;
		int z_size = size;

		float[][][] result = new float[x_size][y_size][z_size];

		for (int i=0; i < x_size; i++){
			for (int j=0; j < y_size; j++){
				for (int k=0; k < z_size; k++) {
					int x = (int) (x_start + i*((x_end-x_start)/x_size));
					int y = (int) (y_start + j*((y_end-y_start)/y_size));
					int z = (int) (z_start + k*((z_end-z_start)/z_size));
					result[i][j][k] = (float) (simplexNoise.getNoise(x, y, z));
				}
			}
			
			if (i%2 == 0 && WorldGenerator.scrn != null){
				WorldGenerator.scrn.setProgress(i / (float) x_size);
				WorldGenerator.scrn.draw();
				Display.update();
			}
		}

		return new Array3D(result);
	}
	
	public Array3D genNoise(Random random) {
		
		float[][][] result = new float[size][size][size];

		
		int seedcode = (Game.getSeed() + seed).hashCode();
		
		
		for (int x = 0; x < size; x += jump) {
			for (int y = 0; y < size; y += jump) {
				for (int z = 0; z < size/2f; z += jump) {
					
					random.setSeed(seedcode * (x*3) * (y*3 +1) * (z*3 +2));
					
					int r = RNG.nextInt(random, 0, 2);
					
					int k = 1;
					int j = 2;
					
					result[x][y][z] = r;
					if (inBounds(x+k, y, z)) result[x+k][y][z] = r;
					if (inBounds(x-k, y, z)) result[x-k][y][z] = r;
					if (inBounds(x, y+k, z)) result[x][y+k][z] = r;
					if (inBounds(x, y-k, z)) result[x][y-k][z] = r;
					if (inBounds(x, y, z+k)) result[x][y][z+k] = r;
					if (inBounds(x, y, z-k)) result[x][y][z-k] = r;

					if (inBounds(x+j, y+j, z+j)) result[x+j][y+j][z+j] = r;
					if (inBounds(x+j, y-j, z+j)) result[x+j][y-j][z+j] = r;
					if (inBounds(x-j, y+j, z+j)) result[x-j][y+j][z+j] = r;
					if (inBounds(x-j, y-j, z+j)) result[x-j][y-j][z+j] = r;
					if (inBounds(x+j, y+j, z-j)) result[x+j][y+j][z-j] = r;
					if (inBounds(x+j, y-j, z-j)) result[x+j][y-j][z-j] = r;
					if (inBounds(x-j, y+j, z-j)) result[x-j][y+j][z-j] = r;
					if (inBounds(x-j, y-j, z-j)) result[x-j][y-j][z-j] = r;
					
				}
			}
		}
		
		
		return new Array3D(result);
		
	}
	
	boolean inBounds(int x, int y, int z) {
		if (x < 0 || x >= size) return false;
		if (y < 0 || y >= size) return false;
		if (z < 0 || z >= size) return false;
		return true;
	}
	
	int size, jump;
	String seed;

}
