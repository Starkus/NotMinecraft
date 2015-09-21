package world;

import java.util.Random;

import block.Block;
import data.WorldSettings;

public class BiomeForest extends BiomeBase {

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
		return new WorldGenFlora(0, 0, 64, WorldSettings.x, WorldSettings.y, 65, world, random);
	}

	public WorldGenBase treesGen(World world, Random random) {
		return new WorldGenTrees(1f, world, random);
	}
	
	public void genExtras(World world, Random random) {
		//WorldGenBase pumpkins = new WorldGenPumpkins(0, 0, WorldSettings.x, WorldSettings.y, world, random, Block.pumpkin);
		//WorldGenBase melons = new WorldGenPumpkins(0, 0, WorldSettings.x, WorldSettings.y, world, random, Block.melon);
		//WorldGenBase house = new WorldGenHouses(2, 2, 48, WorldSettings.x, WorldSettings.y, 48, world, random);
		//pumpkins.generate();
		//melons.generate();
		//house.generate();
	}
}
