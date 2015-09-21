package world;

import java.util.Random;

import block.Block;

public class BiomeBase {
	
	public Block topBlock() {
		return Block.unknown;
	}
	public Block fillBlock() {
		return Block.unknown;
	}
	public boolean genOres() {
		return false;
	}
	public WorldGenBase floraGen(World world, Random random) {
		return null;
	}
	public WorldGenBase treesGen(World world, Random random) {
		return null;
	}
	public void genExtras(World world, Random random) {
	}
	
	public static final BiomeBase forest = new BiomeForest();
	public static final BiomeBase desert = new BiomeDesert();
	public static final BiomeBase snowyForest = new BiomeSnowyForest();
	public static final BiomeBase hell = new BiomeHell();
	
}
