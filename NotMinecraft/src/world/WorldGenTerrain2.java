package world;

import java.util.Random;

import org.lwjgl.opengl.Display;

import array.GenTexture3D;
import block.Block;
import client.Game;
import data.WorldSettings;

public class WorldGenTerrain2 extends WorldGenBase{

	public WorldGenTerrain2(World w, Random r) {
		
		world = w;
		random = r;
		
	}
	
	@Override
	public boolean generate() {
		
		GenTexture3D noisegen = new GenTexture3D(length, jump, Game.getSeed());
		
		float[][][] m = noisegen.simplexNoise(random, 128, 0.4f).map().getArray();
		
		
		
		apply(m);
		
		return true;
	}
	
	
	void apply(float[][][]m) {
		
		if (WorldGenerator.scrn != null)
			WorldGenerator.scrn.setLabel("Applying");
		
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < length; y++) {
				for (int z = 0; z < length; z++) {
					
					if (z < 127) {
					
						float i = m[x][y][z];
						
						if (i > 0.45f)
							world.setBlock(x, y, z/2, Block.grass);
					} else {
						
						float i = m[x][y][127];

						if (i > 0.45f)
							for (int z1 = 64; z1 < (int) 64+(i-0.45f)*16; z1++)
								world.setBlock(x, y, z1, Block.grass);
					}
				}				
			}
			
			if (x%4 == 0 && WorldGenerator.scrn != null){
				WorldGenerator.scrn.setProgress(x / (float) length);
				WorldGenerator.scrn.draw();
				Display.update();
			}
		}
		
	}
	
	int length = WorldSettings.x;
	int jump = 10;

}
