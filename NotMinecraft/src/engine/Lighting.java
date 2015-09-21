package engine;

import block.Block;
import client.Game;
import data.WorldSettings;
import world.World;

public class Lighting {

	public static void updateLightMass(World world, VBO vbo) {
		for (int x = vbo.getLocation()[0]*16; x < (vbo.getLocation()[0]+1)*16; x++) {
			for (int y = vbo.getLocation()[1]*16; y < (vbo.getLocation()[1]+1)*16; y++) {
				
				light[x][y] = new int[WorldSettings.z];
				
				for (int z = WorldSettings.z-1; z > 0; z--) {
					Block b = world.getBlock(x, y, z);
					if (b != Block.air && b.getLightStop() > 0f) {
						z = -1;
					}
					
					else
						light[x][y][z] = 1;
				}
				
			}
		}
	}
	
	public static float getLightingAtPoint(float x, float y, float z) {
		return getLightingAtPoint((int)x, (int)y, (int)z);
	}
	public static float getLightingAtPoint(int x, int y, int z) {
		float res = 0f;
		
		int r = 2;
		
		for (int x1 = x-r; x1 < x+r; x1++) {
			for (int y1 = y-r; y1 < y+r; y1++) {
				for (int z1 = z; z1 < z+r; z1++) {
					if (inBounds(x1, y1, z1)) {
						
						if (!Game.getCurrentWorld().getBlock(x1, y1, z1).isLit())
							res += 32f;
						
						res += light[x1][y1][z1];
					}
				}
			}
		}
		
		return res/32f + 0.05f;
	}
	
	static boolean inBounds(int x, int y, int z) {
		return !(x < 0 || y < 0 || z < 0 ||
				x >= WorldSettings.x || y >= WorldSettings.y || z >= WorldSettings.z);
	}
	
	static int[][][] light = new int[WorldSettings.x][WorldSettings.y][WorldSettings.z];

}
