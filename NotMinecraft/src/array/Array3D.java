package array;

import org.lwjgl.opengl.Display;

import world.WorldGenerator;

public class Array3D {

	public Array3D(float k[][][]) {
		array = k;
	}

	
	public float[][][] getArray() {
		return array;
	}
	
	public Array3D multiply(float factor) {
		float r[][][] = new float[array.length][array[0].length][array[0][0].length];
		
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				for (int z = 0; z < array[0][0].length; z++) {
					r[x][y][z] = array[x][y][z] * factor;
				}
			}
		}
		
		return new Array3D(r);
	}
	
	public Array3D map() {
		float r[][][] = new float[array.length][array[0].length][array[0][0].length];
		
		float min = array[0][0][0];
		float max = array[0][0][0];

		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				for (int z = 0; z < array[0][0].length; z++) {
					float l = array[x][y][z];
					if (l > max) max = l;
					if (l < min) min = l;
				}
			}
		}

		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[0].length; y++) {
				for (int z = 0; z < array[0][0].length; z++) {
					r[x][y][z] = (array[x][y][z] - min) / (max - min);
				}
			}
		}
		
		array = r;
		
		return this;
	}

	
	public Array3D smooth(int iterations) {
		
		for (int i = 0; i < iterations; i++) {
		
			int xsize = array.length;
			int ysize = array[0].length;
			int zsize = array[0][0].length;
			
			float[][][] n = new float[array.length][array[0].length][array[0][0].length];
			
			
			for (int x = 0; x < xsize; x++) {
				for (int y = 0; y < ysize; y++) {
					for (int z = 0; z < zsize; z++) {
						
						int k = 0;
					
						float[] list = new float[27];
						
						for (int x1 = x-1; x1 < x+1; x1++) {
							if (x1 < 0) x1 = xsize-1;
							else if (x1 >= xsize) x1 = xsize-1;
							for (int y1 = y-1; y1 < y+1; y1++) {
								if (y1 < 0) y1 = ysize-1;
								else if (y1 >= ysize) y1 = ysize-1;
								for (int z1 = z-1; z1 < z+1; z1++) {
									if (z1 < 0) z1 = zsize-1;
									else if (z1 >= zsize) z1 = zsize-1;
									
									list[k] = array[x1][y1][z1];
									
									k++;
									
								}
							}
						}
						
						float total = 0;
						
						for (int j = 0; j <= 8; j += 1) {
							total += list[j];
						}
						
						n[x][y][z] = total / 27f;
					
					}
				}
			}
			
			if (/*i%1 == 0 && */WorldGenerator.scrn != null){
				WorldGenerator.scrn.setProgress(i / (float) iterations);
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
	
	
	int wrap(int v, int max) {
		if (v < max - 1)	v += 1;
		return v;
	}
	
	float[][][] array;
}
