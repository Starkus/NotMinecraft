package world;

import java.util.Random;

import block.Block;
import data.WorldSettings;

public class BiomeSnowyForest extends BiomeBase {

	public Block topBlock() {
		return Block.grass;
	}

	public Block fillBlock() {
		return Block.dirt;
	}

	public boolean genOres() {
		return true;
	}

	public WorldGenBase floraGen(World world, Random random) {
		return new WorldGenSnow(0, 0, 64, WorldSettings.x, WorldSettings.y, 65, world, random);
	}

	public WorldGenBase treesGen(World world, Random random) {
		return new WorldGenPines(4f, world, random);
	}
}
