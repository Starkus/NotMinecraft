package world;

import java.util.Random;

import block.Block;
import data.WorldSettings;

public class BiomeDesert extends BiomeBase {

	public Block topBlock() {
		return Block.sand;
	}

	public Block fillBlock() {
		return Block.sand;
	}

	public boolean genOres() {
		return true;
	}

	public WorldGenBase floraGen(World world, Random random) {
		return new WorldGenDesertFlora(0, 0, 64, WorldSettings.x, WorldSettings.y, 65, world, random);
	}

	public WorldGenBase treesGen(World world, Random random) {
		return new WorldGenCactus(0, 0, 64, WorldSettings.x, WorldSettings.y, 65, world, random);
	}
}
