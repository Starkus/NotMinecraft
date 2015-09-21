package world;

import engine.*;
import gui.GuiWorldProgressBar;

import java.util.Date;
import java.util.Random;

import org.lwjgl.opengl.Display;

import block.Block;
import client.Game;
import data.EnumWorldType;

public class WorldGenerator {
	public static World generate(World world, int chunk_x, int chunk_y, String seed) {

		return world;
		
	}
	
	public static World generate(World world, String seed) {
		

		
		scrn = new GuiWorldProgressBar((int) Game.getScreenSize(0), (int) Game.getScreenSize(1));
		
		scrn.tick();
		scrn.draw();
		
		Display.update();
		
		
		// Generation time
		Date d = new Date();
		long start_timestamp = d.getTime();
		
		int x, y, z, xl, yl, zl;
		xl = world.objects.length;
		yl = world.objects[0].length;
		zl = world.objects[0][0].length;
		
		for (x=0; x<xl; x++) {
			for (y=0; y<yl; y++) {
				for (z=0; z<zl; z++) {
					world.objects[x][y][z] = new GameObject(Block.air).setPosition(x, y, z);
				}
			}

			if (x%4 == 0){
				scrn.setProgress(x / (float) xl);
				scrn.draw();
				Display.update();
			}
		}
		
		Random random = new Random(seed.hashCode());
		
		if (world_type==EnumWorldType.NORMAL) {
	
			WorldGenBase trees, pines, deadplants, cactus, flora, snow, terrain, biomes;
			
			terrain = new WorldGenTerrain2(world, random);
			biomes = new WorldGenBiomes(world, random);
			
			flora = BiomeBase.forest.floraGen(world, random);
			trees = BiomeBase.forest.treesGen(world, random);
			
			snow = BiomeBase.snowyForest.floraGen(world, random);
			pines = BiomeBase.snowyForest.treesGen(world, random);

			deadplants = BiomeBase.desert.floraGen(world, random);
			cactus = BiomeBase.desert.treesGen(world, random);
	
			scrn.setLabel("Generating terrain");
			scrn.setProgress(0f);
			scrn.draw();
			Display.update();
			
			biomes.generate();
			terrain.generate();
	
			scrn.setLabel("Making oceans");
			scrn.setProgress(0f);
			scrn.draw();
			Display.update();
			
			genCube(0, 0, 0, xl, yl, 48, world, Block.water, Block.air);
			
			genOres(0, 0, 0, xl, yl, zl, world);
			

			scrn.setLabel("Planting stuff");
			scrn.setProgress(0f);
			scrn.draw();
			Display.update();
	
			trees.generate();
			pines.generate();
			
			deadplants.generate();
			cactus.generate();
			
			snow.generate();
			flora.generate();
			

			scrn.setLabel("Getting ready...");
			scrn.setProgress(0f);
			scrn.draw();
			Display.update();
			
			
			
		} else if (world_type == EnumWorldType.HELL) {
			for (int x1 = 0; x1 < xl; x1++) {
			for (int y1 = 0; y1 < yl; y1++) {
				world.setBiome(x1, y1, BiomeBase.hell);
			}
			}
			WorldGenBase terrain = new WorldGenTerrain(world, random);
			terrain.generate();
			
		} else if (world_type == EnumWorldType.FLATLAND) {
			
			genCube(0, 0, 0, xl, yl, 1, world, Block.bedrock);
			genCube(0, 0, 1, 16, 16, 64, world, Block.leaves);
			
		} else if (world_type == EnumWorldType.TESTS) {
			
			for (int x1 = 0; x1 < xl; x1++) {
			for (int y1 = 0; y1 < yl; y1++) {
				world.setBiome(x1, y1, BiomeBase.forest);
			}
			}
			WorldGenBase terrain = new WorldGenTerrain2(world, random);
			terrain.generate();
			
			//genCube(0, 0, 32, xl, yl, 48, world, Block.water, Block.air);
			genCube(0, 0, 0, xl, yl, 1, world, Block.bedrock);
			
		}
		
		world.finalizeGeneration();
		
		d = new Date();
		long end_timestamp = d.getTime();
		System.out.println(String.format("Generation time: %ds", (end_timestamp-start_timestamp)/1000));
		
		return world;
		
	}
	
	public static void genCube(int minx, int miny, int minz,
						int maxx, int maxy, int maxz, 
						World world, Block block) {
		
		int x, y, z;
		
		for (x=minx; x<maxx; x++) {
			for (y=miny; y<maxy; y++) {
				for (z=minz; z<maxz; z++) {
					world.setBlock(x, y, z, block);
				}
			}
		}
	}
	public static void genCube(int minx, int miny, int minz,
						int maxx, int maxy, int maxz, 
						World world, Block block, Block block2) {
		
		int x, y, z;
		
		for (x=minx; x<maxx; x++) {
			for (y=miny; y<maxy; y++) {
				for (z=minz; z<maxz; z++) {
					if (world.getBlockId(x, y, z) == Block.air.id) world.setBlock(x, y, z, block);
				}
			}
		}
	}
	public static void genOres(int minx, int miny, int minz,
			int maxx, int maxy, int maxz, 
			World world) {
				
		WorldGenBase coal, copper, iron, silver, emerald, diamond;
		coal =    new WorldGenMinable(minx, miny, minz, maxx, maxy, 64, world, Block.coalOre,    Block.stone, 600,  8);
		copper =  new WorldGenMinable(minx, miny, minz, maxx, maxy, 48, world, Block.copperOre,  Block.stone, 1100, 8);
		iron =    new WorldGenMinable(minx, miny, minz, maxx, maxy, 48, world, Block.ironOre,    Block.stone, 1000, 6);
		silver =  new WorldGenMinable(minx, miny, minz, maxx, maxy, 32, world, Block.silverOre,  Block.stone, 1500, 6);
		emerald = new WorldGenMinable(minx, miny, minz, maxx, maxy, 16, world, Block.emeraldOre, Block.stone, 3000, 6);
		diamond = new WorldGenMinable(minx, miny, minz, maxx, maxy, 16, world, Block.diamondOre, Block.stone, 4000, 6);
		
		coal.generate();
		copper.generate();
		iron.generate();
		silver.generate();
		emerald.generate();
		diamond.generate();
	}

	
	public static EnumWorldType world_type = EnumWorldType.NORMAL;
	
	public static GuiWorldProgressBar scrn; 
	
}
