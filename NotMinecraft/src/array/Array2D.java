package array;

import org.lwjgl.opengl.Display;

import world.WorldGenerator;

public class Array2D {

	public Array2D(float k[][]) {
		array = k;
	}
	
	public Array2D mix(Array2D k, float factor) {
		return mix(k.array, factor);
	}
	
	public Array2D mix(float[][] k, float factor) {
		float r[][] = new float[array.length][array[0].length];
		
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				r[x][y] = (array[x][y] * (1-factor) + k[x][y] * factor)/1f;
			}
		}
		
		return new Array2D(r);
	}
	
	public Array2D add(float factor) {
		float r[][] = new float[array.length][array[0].length];
		
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				r[x][y] = array[x][y] + factor;
			}
		}
		
		return new Array2D(r);
	}
	
	public Array2D multiply(float factor) {
		float r[][] = new float[array.length][array[0].length];
		
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				r[x][y] = array[x][y] * factor;
			}
		}
		
		return new Array2D(r);
	}
	
	public Array2D smooth(int iterations) {
		
		for (int z = 0; z < iterations; z++) {
		
			int xsize = array.length;
			int ysize = array[0].length;
			
			float[][] n = new float[array.length][array[0].length];
			
			for (int x = 0; x < xsize; x += 1) {
				for (int y = 0; y < ysize; y += 1) {
					
					float[] list = new float[9];
					
					list[0] = array[x][y];
					
					list[1] = array[x][wrap(y, ysize)];
					
					list[2] = array[wrap(x, ysize)][y];
					
					list[3] = array[wrap(x, ysize)][wrap(y, ysize)];
					
					if (x == 0)	list[4] = array[xsize-1][y];
					else list[4] = array[x-1][y];
					
					if (y == 0) list[5] = array[wrap(x, xsize)][ysize-1];
					else list[5] = array[wrap(x, xsize)][y-1];
					
					if (x == 0) list[6] = array[xsize-1][wrap(y, ysize)];
					else list[6] = array[x-1][wrap(y, ysize)];
					
					if (y == 0) list [7] = array[x][ysize-1];
					else list[7] = array[x][y-1];
					
					if (x == 0 && y == 0) list[8] = array[xsize-1][ysize-1];
					else if (x == 0) list[8] = array[xsize-1][y-1];
					else if (y == 0) list[8] = array[x-1][ysize-1];
					else list[8] = array[x-1][y-1];
					
					float total = 0;
					
					for (int i = 0; i <= 8; i += 1) {
						total += list[i];
					}
					
					n[x][y] = total / 9f;
					
				}
			}
			
			if (z%16 == 0 && WorldGenerator.scrn != null){
				WorldGenerator.scrn.setProgress(z / (float) iterations);
				WorldGenerator.scrn.draw();
				Display.update();
			}
			
			for (int x = 0; x < xsize; x+= 1) {
				for (int y = 0; y < ysize; y += 1) {
					array[x][y] = n[x][y];
				}
			}
		}
		
		return this;
	}
	
	public Array2D map() {
		float r[][] = new float[array.length][array[0].length];
		
		float min = array[0][0];
		float max = array[0][0];

		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				float l = array[x][y];
				if (l > max) max = l;
				if (l < min) min = l;
			}
		}

		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				r[x][y] = (array[x][y] - min) / (max - min);
			}
		}
		
		array = r;
		
		return this;
	}

	public Array2D levelize(int levels) {
		float r[][] = new float[array.length][array[0].length];
		
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				r[x][y] = (int) (array[x][y]*levels) / (float) levels;
			}
		}
		
		array = r;
		
		return this;
	}
	

	public float[][] getArray() {
		return array;
	}
	
	
	int wrap(int v, int max) {
		if (v < max - 1)	v += 1;
		return v;
	}
	
	float[][] array;

}
