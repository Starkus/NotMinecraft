package world;

public abstract class Structure {
	
	public Structure() {
	}
	
	public abstract boolean place(int x, int y, int z, World world, int i, int j);
	
	public abstract boolean canPlaceAt(int x, int y, int z, World world, int i, int j);
	
}
