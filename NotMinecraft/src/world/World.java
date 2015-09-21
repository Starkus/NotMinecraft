package world;

import java.nio.FloatBuffer;

import block.Block;
import client.Game;
import data.WorldSettings;
import engine.Culling;
import engine.GameObject;

public class World {
	public World(GameObject[][][] obs) {
		objects = obs;
	}
	public boolean inBounds(int x, int y, int z) {
		if (x >= 0 && x < objects.length) {
		if (y >= 0 && y < objects[0].length) {
		if (z >= 0 && z < objects[0][0].length) {
			return true;
		}
		}
		}
		return false;
	}
	
	public void updateVBOs(int x, int y, int z) {

		FloatBuffer float_buffer = Game.getVBOFloatBuffer();

		Culling.UpdateFaceCulling(this, x, y, z);
		
		Game.getVBO(x/16, y/16).save(float_buffer, true);
		if (x%16 == 0)
			if (x/16 -1 >= 0)
				Game.getVBO(x/16 -1, 	y/16).save(float_buffer, true);
		if (x%16 == 15)
			if (x/16 +1 <= 7)
				Game.getVBO(x/16 +1, 	y/16).save(float_buffer, true);
		if (y%16 == 0)
			if (y/16 -1 >= 0)
				Game.getVBO(x/16, 		y/16 -1).save(float_buffer, true);
		if (y%16 == 15)
			if (y/16 +1 <= 7)
				Game.getVBO(x/16, 		y/16 +1).save(float_buffer, true);
		
	}
	
	public void setVBOsForUpdate(int x, int y, int z) {

		Culling.UpdateFaceCulling(this, x, y, z);
		
		Game.getVBO(x/16, y/16).setForUpdate();
		if (x%16 == 0)
			if (x/16 -1 >= 0)
				Game.getVBO(x/16 -1, 	y/16).setForUpdate();
		if (x%16 == 15)
			if (x/16 +1 <= 7)
				Game.getVBO(x/16 +1, 	y/16).setForUpdate();
		if (y%16 == 0)
			if (y/16 -1 >= 0)
				Game.getVBO(x/16, 		y/16 -1).setForUpdate();
		if (y%16 == 15)
			if (y/16 +1 <= 7)
				Game.getVBO(x/16, 		y/16 +1).setForUpdate();
		
	}
	
	public boolean setBlock(int x, int y, int z, Block block) {
		
		if (inBounds(x, y, z)) {
			objects[x][y][z].block = block.id;
			setVBOsForUpdate(x, y, z);
			return true;
		}
		
		return false;
		
	}
	public boolean setBlockOn(int x, int y, int z, Block block, Block block2) {
		if (inBounds(x, y, z) && objects[x][y][z].block == block2.id) {
			if (setBlock(x, y, z, block))
				return true;
		}
		return false;
	}
	
	public boolean setBlockAndMetadata(int x, int y, int z, Block block, int m) {
		if (setBlock(x, y, z, block)) {
			objects[x][y][z].meta = m;
			return true;
		}
		return false;
	}
	public boolean setBlockAndMetadataOn(int x, int y, int z, Block block, int m, Block block2) {
		if (setBlockOn(x, y, z, block, block2)) {
			objects[x][y][z].meta = m;
			return true;
		}
		return false;
	}
	
	public boolean setMetadata(int x, int y, int z, int meta) {
		if (inBounds(x, y, z)) {
			objects[x][y][z].meta = meta;
			return true;
		}
		return false;
	}
	public void setColor(int x, int y, int z, float r, float g, float b) {
		if (inBounds(x, y, z)) {
			float[] col = new float[] {r, g, b};
			objects[x][y][z].color = col;
		}
	}
	public void setTemperature(int x, int y, float t) {
		if (inBounds(x, y, 1)) {
			temperature[x][y] = t;
		}
	}
	public float getTemperature(int x, int y) {
		if (inBounds(x, y, 1)) {
			return temperature[x][y];
		}
		return -1f;
	}

	public void setBiome(int x, int y, BiomeBase b) {
		if (inBounds(x, y, 1)) {
			biomes[x][y] = b;
		}
	}
	public BiomeBase getBiome(int x, int y) {
		if (inBounds(x, y, 1)) {
			return biomes[x][y];
		}
		return null;
	}
	
	public Block getBlock(int x, int y, int z) {
		if (inBounds(x, y, z)) {
			return Block.blockList[objects[x][y][z].block];
		}
		return null;
	}
	
	public int getBlockId(int x, int y, int z) {
		if (inBounds(x, y, z)) {
			return objects[x][y][z].block;
		}
		return -1;
	}
	
	public int getBlockMetadata(int x, int y, int z) {
		if (inBounds(x, y, z)) {
			return objects[x][y][z].meta;
		}
		return 0;
	}
	
	public float getSkyColor(int i) {
		return skycolor[i];
	}
	public World setSkyColor(int i, float j) {
		skycolor[i] = j;
		return this;
	}
	
	public void finalizeGeneration() {
		generated = true;
		Culling.BlockFaceCulling(this);
	}
	public void faceCulling() {
		Culling.BlockFaceCulling(this);
	}
	
	public int length(int a) {
		if (a == 0) {
			return objects.length;
		} else if (a == 1) {
			return objects[0].length;
		} else if (a == 2) {
			return objects[0][0].length;
		} else {
			Exception ex = new Exception("Algo");
			ex.printStackTrace();
		}
		return -1;
	}
	
	public void tick() {
		for (int x = 0; x < objects.length; x++) {
			for (int y = 0; y < objects[0].length; y++) {
				for (int z = 0; z < objects[0][0].length; z++) {
					getBlock(x, y, z).onTick(this, x, y, z);
				}
			}
		}
	}
	
	public GameObject[][][] objects;
	public GameObject[] visibleObjects;
	public float[][] temperature = new float[WorldSettings.x][WorldSettings.y];
	public BiomeBase[][] biomes = new BiomeBase[WorldSettings.x][WorldSettings.y];
	
	private float[] skycolor = new float[] {0F, 0.8F, 1F};
	
	public boolean generated = false;
	
}
