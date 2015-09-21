package world;

import java.util.Random;

import block.Block;
import data.WorldSettings;

public class BiomeHell extends BiomeBase {

	public Block topBlock() {
		return Block.hellstone;
	}

	public Block fillBlock() {
		return Block.hellstone;
	}

	public boolean genOres() {
		return false;
	}

	public WorldGenBase floraGen(World world, Random random) {
		return new WorldGenDesertFlora(0, 64, 0, WorldSettings.x, 65, WorldSettings.z, world, random);
	}

	public WorldGenBase treesGen(World world, Random random) {
		return new WorldGenCactus(0, 64, 0, WorldSettings.x, 65, WorldSettings.z, world, random);
	}
}
