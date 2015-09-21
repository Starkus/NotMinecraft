package world;

import block.Block;

public class StructureHouse1 extends Structure {

	private int wallHeight = 4;

	public boolean place(int x, int y, int z, World world, int width, int depth) {
		
		// Clean sector
		/*for (int y1 = 0; y1 < wallHeight; y1++) {
			for (int x1 = 0; x1 < width; x1++) {
				for (int z1 = 0; z1 < proof; z1++) {
					world.setBlock(x1, y1, z1, Block.air);
				}
			}
		}*/
		
		// Wall rings
		for (int z1 = 0; z1 <= wallHeight; z1++) {
			for (int x1 = 0; x1 < width; x1++) {
				for (int y1 = 0; y1 < depth; y1++) {
					if (x1 == 0 || x1 == width-1 ||
							y1 == 0 || y1 == depth-1) {
						//Windows
						if (x1 >= 2 && x1 < width-2 &&
									z1 > 1 && z1 < wallHeight) {
							world.setBlock(x+x1, y+y1, z+z1, Block.glass);
						} else if (y1 >= 2 && y1 < depth-2 &&
									z1 > 1 && z1 < wallHeight) {
							world.setBlock(x+x1, y+y1, z+z1, Block.glass);
						
						} else {
							world.setBlock(x+x1, y+y1, z+z1, Block.planks);
						}
						
						if ((x1 == 0 && y1 == 0)		||	(x1 == width-1 && y1 == 0) ||
							(x1 == 0 && y1 == depth-1)	||	(x1 == width-1 && y1 == depth-1)) {
							
							world.setBlock(x+x1, y+y1, z+z1, Block.wood);
						}
					}
				}
			}
		}
		
		// Roof
		boolean stop = false;
		
		for (int z1 = 0; z1 < 10; z1++) {
			stop = true;
			for (int x1 = -1; x1 <= width; x1++) {
				for (int y1 = -1; y1 <= depth; y1++) {
					if ((x1 == z1-1 || x1 == width-z1 ||
							y1 == z1-1 || y1 == depth-z1) &&
							x1 >= z1-1 && x1 < width-z1+1 &&
							y1 >= z1-1 && y1 < depth-z1+1) {
						world.setBlock(x+x1, y+y1, z+z1+4, Block.brick);
						stop = false;
					}
				}
			}
			if (stop) break;
		}
		for (int x1 = 1; x1 < width-1; x1++) {
			for (int y1 = 1; y1 < depth-1; y1++) {
				world.setBlock(x+x1, y+y1, z+wallHeight, Block.doubleSlab);
			}
		}
		
		// Floor
		for (int x1 = 0; x1 < width; x1++) {
			for (int y1 = 0; y1 < depth; y1++) {
				world.setBlock(x+x1, y+y1, z, Block.cobble);
			}
		}
		
		// Stuff
		world.setBlock(x+1, y+1, z+1, Block.torch);
		world.setBlock(x+width-2, y+1, z+1, Block.torch);
		world.setBlock(x+1, y+depth-2, z+1, Block.torch);
		world.setBlock(x+width-2, y+depth-2, z+1, Block.torch);
		
		world.setBlock(x+2, y+depth-2, z+1, Block.chest);
		world.setBlock(x+3, y+depth-2, z+1, Block.table);
		world.setBlock(x+4, y+depth-2, z+1, Block.furnace);
		
		return true;
	}

	public boolean canPlaceAt(int x, int y, int z, World world, int width, int proof) {
		for (int z1 = 1; z1 < wallHeight; z1++) {
			for (int x1 = 0; x1 < width; x1++) {
				for (int y1 = 0; y1 < proof; y1++) {
					int b = world.getBlockId(x+x1, y+y1, z+z1);
					if (b != 0) return false;
					if (b == -1) return false;
				}
			}
		}
		
		return true;
	}

}
