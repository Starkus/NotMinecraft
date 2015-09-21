package world;

public class WorldGenStructure extends WorldGenBase{
	
	public WorldGenStructure(int i, int j, int k, 
			World w, Structure s) {

		x = i;
		y = j;
		z = k;
		world = w;
		struct = s;
	}

	public boolean generate() {
		if (!struct.canPlaceAt(x, y, z, world, 0, 0)) {
			return false;
		}
		
		struct.place(x, y, z, world, 0, 0);
		return true;
	}
	
	int x, y, z;
	World world;
	Structure struct;
	
}
