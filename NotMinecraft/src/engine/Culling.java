package engine;

import world.World;

public class Culling {
	
	public static void BlockFaceCulling(World world) {
		int x, y, z, xl, yl, zl;
		xl = world.objects.length;
		yl = world.objects[0].length;
		zl = world.objects[0][0].length;
		
		for (x=0; x<xl; x++) {
			for (y=0; y<yl; y++) {
				for (z=0; z<zl; z++) {
					GameObject ob = world.objects[x][y][z];
					
					if (x >= 0 && x < xl-1) {
						if (cullFace(world, x, y, z, x+1, y, z)) {
							ob.culling[6] = ob.culling[7] = true;
						}
					}
					if (x > 0 && x <= xl-1) {
						if (cullFace(world, x, y, z, x-1, y, z)) {
							ob.culling[2] = ob.culling[3] = true;
						}
					}
					if (y >= 0 && y < yl-1) {
						if (cullFace(world, x, y, z, x, y+1, z)) {
							ob.culling[8] = ob.culling[9] = true;
						}
					}
					if (y > 0 && y <= yl-1) {
						if (cullFace(world, x, y, z, x, y-1, z)) {
							ob.culling[10] = ob.culling[11] = true;
						}
					}
					if (z >= 0 && z < zl-1) {
						if (cullFace(world, x, y, z, x, y, z+1)) {
							ob.culling[4] = ob.culling[5] = true;
						}
					}
					if (z > 0 && z <= zl-1) {
						if (cullFace(world, x, y, z, x, y, z-1)) {
							ob.culling[0] = ob.culling[1] = true;
						}
					}
					
					world.objects[x][y][z] = ob;
				}
			}
		}
	}
	
	public static void UpdateFaceCulling(World world, int j, int k, int l) {
		int x, y, z, xl, yl, zl;
		xl = world.objects.length;
		yl = world.objects[0].length;
		zl = world.objects[0][0].length;
		
		for (x=j-1; x<=j+1; x++) {
			for (y=k-1; y<=k+1; y++) {
				for (z=l-1; z<=l+1; z++) {
					
					if (x >= 0 && x < xl && y >= 0 && y < yl && z >= 0 && z < zl) {
					
						GameObject ob = world.objects[x][y][z];
						
						ob.culling = new boolean[12];
						
						if (x >= 0 && x < xl-1) {
							if (cullFace(world, x, y, z, x+1, y, z)) {
								ob.culling[6] = ob.culling[7] = true;
							}
						}
						if (x > 0 && x <= xl-1) {
							if (cullFace(world, x, y, z, x-1, y, z)) {
								ob.culling[2] = ob.culling[3] = true;
							}
						}
						if (y >= 0 && y < yl-1) {
							if (cullFace(world, x, y, z, x, y+1, z)) {
								ob.culling[8] = ob.culling[9] = true;
							}
						}
						if (y > 0 && y <= yl-1) {
							if (cullFace(world, x, y, z, x, y-1, z)) {
								ob.culling[10] = ob.culling[11] = true;
							}
						}
						if (z >= 0 && z < zl-1) {
							if (cullFace(world, x, y, z, x, y, z+1)) {
								ob.culling[4] = ob.culling[5] = true;
							}
						}
						if (z > 0 && z <= zl-1) {
							if (cullFace(world, x, y, z, x, y, z-1)) {
								ob.culling[0] = ob.culling[1] = true;
							}
						}
						
						world.objects[x][y][z] = ob;
					
					}
				}
			}
		}
	}
	
	private static boolean cullFace(World world, int x, int y, int z, int x1, int y1, int z1) {
		
		return (world.getBlock(x1, y1, z1).isEntireBlock() == false || 
				(world.getBlock(x1, y1, z1).hasAlpha() == true && world.getBlock(x, y, z).hasAlpha() == false) || 
				(world.getBlock(x1, y1, z1).hasAlpha() == false && world.getBlock(x, y, z).hasAlpha() == true) || 
				(world.getBlock(x1, y1, z1).isTransparent() == true && world.getBlock(x, y, z).isTransparent() == false) ||  
				(world.getBlock(x1, y1, z1).isFluid() == true && world.getBlock(x, y, z).isFluid() == false));
		
	}
	
}
