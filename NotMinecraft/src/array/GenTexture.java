package array;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.opengl.Display;

import client.Game;
import util.RNG;
import world.WorldGenerator;

public class GenTexture {
	
	public GenTexture(int _size, int _jump, String _seed) {
		
		size = _size;
		jump = _jump;
		seed = _seed;
		
	}
	
	public Array2D simplexNoise(Random random, int largest, float persistence) {
		SimplexNoise simplexNoise = new SimplexNoise(largest, persistence, seed.hashCode());

		
		if (WorldGenerator.scrn != null)
			WorldGenerator.scrn.setLabel("Making noise");


		double x_start = 0;
		double x_end = size;
		double y_start = 0;
		double y_end = size;

		int x_size = size;
		int y_size = size;

		float[][] result = new float[x_size][y_size];

		for (int i=0; i < x_size; i++){
			for (int j=0; j < y_size; j++){
				int x = (int) (x_start + i*((x_end-x_start)/x_size));
		        int y = (int) (y_start + j*((y_end-y_start)/y_size));
		        result[i][j] = (float) (simplexNoise.getNoise(x,y));
		    }
			
			if (i%4 == 0 && WorldGenerator.scrn != null){
				WorldGenerator.scrn.setProgress(i / (float) x_size);
				WorldGenerator.scrn.draw();
				Display.update();
			}
		}

		return new Array2D(result);
	}
	
	public Array2D genNoise(Random random) {
		return genNoise(random, 0, 0);
	}
	
	public Array2D genNoise(Random random, int xpos, int ypos) {
		
		float[][] m = new float[size][size];
		
		int seedcode = (Game.getSeed() + seed).hashCode();
		
		for (int x = 0; x < size; x+= jump) {
			
			for (int y = 0; y < size; y+= jump) {
				
				random.setSeed(seedcode * (x+(xpos*size)*2 + 1) * (y+(ypos*size)*2));
				
				int r = (RNG.nextInt(random, 0, 2));
				
				
				m[x][y] = r;
				
			}
		}
		
		int iterations = 0;
		
		for (int i = 0; i < iterations; i++) {
		
			jump /= 2f;
			
			for (int x = 0; x < size; x+= jump) {
				
				for (int y = 0; y < size; y+= jump) {
					
					float r = 0;
					
					if ((x/jump)%2 == 1 || (y/jump)%2 == 1) {
						if (x > 0 && y > 0  &&  x < size-jump && y < size-jump) {
					
							if ((x/jump)%2 == 1 && (y/jump)%2 == 1) {
								float a = m[x-jump][y-jump];
								float b = m[x+jump][y-jump];
								float c = m[x-jump][y+jump];
								float d = m[x+jump][y+jump];
								
								r = (a+b+c+d)*0.707f/4f;
							}
							else {
	
								float a = m[x-jump][y];
								float b = m[x+jump][y];
								float c = m[x][y-jump];
								float d = m[x][y+jump];
								
								r = (a+b+c+d)/4f;
							}
						
							m[x][y] = r;
						}
					}
				}
			}
		}
		
		
		int blend_amount = h_smooth;
		
		for (int i = 0; i < blend_amount; i++) {
			vert_blend(m);
		}
		
		return new Array2D(m);
		
	}
	
	int[] point(int a, int b, Random random) {
		int p1 = a + RNG.nextInt(random, 0, 32);
		int p2 = b + RNG.nextInt(random, 0, 32);
		
		return new int[]{p1, p2};
	}
	public Array2D genCrack(Random random, int iterations) {
		
		float[][] result = new float[size][size];
		
		for (int c = 0; c < iterations; c++) {
		
		int max = 32;
		int points = 0;
		ArrayList<int[]> point_list = new ArrayList<int[]>();
		
		int[] a_point = new int[2];
		
		for (int p = 0; p < max; p++) {
			if (p == 0) {
				a_point = new int[]{0, RNG.nextInt(random, 0, 32)};
				point_list.add(a_point);
			}
			else {
				a_point = point(a_point[0], a_point[1], random);
				point_list.add(a_point);
			}
			points++;
		}
		
		if (random.nextBoolean()) {
			ArrayList<int[]> new_list = new ArrayList<int[]>();
			
			for (int i = 0; i < point_list.size(); i++) {
				int newa = size - point_list.get(i)[0];
				int newb = point_list.get(i)[1];
				new_list.add(new int[]{newa, newb});
			}
			point_list = new_list;
		}
		
		ArrayList<int[]> r_list = new ArrayList<int[]>();
		
		int count = 0;
		int[] r_point = point_list.get(0);
		
		int rand_gen = 1;
		
		int error = 0;
		
		while (count < points) {
			if (error > 12000) {
				
				count = 100;
				System.err.println("Error at river maker!");
				
			} else if (r_point[0] == point_list.get(count)[0] &&
						r_point[1] == point_list.get(count)[1]) {
				
				count++;
				
			} else {

				if (r_point[0] > point_list.get(count)[0])
					r_point[0] = r_point[0] - rand_gen;
				
				if (r_point[0] < point_list.get(count)[0])
					r_point[0] = r_point[0] + rand_gen;
				
				
				if (r_point[1] > point_list.get(count)[1])
					r_point[1] = r_point[1] - rand_gen;
				
				if (r_point[1] < point_list.get(count)[1])
					r_point[1] = r_point[1] + rand_gen;
				
				r_list.add(new int[]{r_point[0], r_point[1]});
				error++;
				
			}
		}
		
		for (int i = 0; i < r_list.size(); i++) {
			if (r_list.get(i)[0] < size && r_list.get(i)[1] < size) {
				if (r_list.get(i)[0] > 0 && r_list.get(i)[1] > 0) {
					float b;
					if (iterations == 1)
						b = 1.0f;
					else
						b = 0.5f;
					
					result[r_list.get(i)[0]][r_list.get(i)[1]] = b;
				}
			}
		}
		
		}
		
		return new Array2D(result);
	}
	
	
	int wrap(int v, int max) {
		if (v < max - 1)	v += 1;
		return v;
	}
	
	void vert_blend(float[][] m) {
		// Fills an array with all surrounding verts and averages it
		
		float[][] n = new float[size][size];
		
		for (int x = 0; x < size; x += 1) {
			for (int y = 0; y < size; y += 1) {
				
				float[] list = new float[9];
				
				list[0] = m[x][y];
				
				list[1] = m[x][wrap(y, size)];
				
				list[2] = m[wrap(x, size)][y];
				
				list[3] = m[wrap(x, size)][wrap(y, size)];
				
				if (x == 0)	list[4] = m[size-1][y];
				else list[4] = m[x-1][y];
				
				if (y == 0) list[5] = m[wrap(x, size)][size-1];
				else list[5] = m[wrap(x, size)][y-1];
				
				if (x == 0) list[6] = m[size-1][wrap(y, size)];
				else list[6] = m[x-1][wrap(y, size)];
				
				if (y == 0) list [7] = m[x][size-1];
				else list[7] = m[x][y-1];
				
				if (x == 0 && y == 0) list[8] = m[size-1][size-1];
				else if (x == 0) list[8] = m[size-1][y-1];
				else if (y == 0) list[8] = m[x-1][size-1];
				else list[8] = m[x-1][y-1];
				
				float total = 0;
				
				for (int i = 0; i <= 8; i += 1) {
					total += list[i];
				}
				
				n[x][y] = total / 9f;
				
			}
		}
		
		for (int x = 0; x < size; x+= 1) {
			for (int y = 0; y < size; y += 1) {
				m[x][y] = n[x][y];
			}
		}
		
	}
	
	
	public GenTexture setHillSmooth(int k) {
		h_smooth = k;
		return this;
	}
	
	public String seed;
	
	public int size;
	public int jump;
	public float high = 1f;
	
	public int h_smooth = 0;

}
