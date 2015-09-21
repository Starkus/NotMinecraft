package world;

import java.util.Random;

public abstract class WorldGenBase {
	public WorldGenBase() {
	}
	
	public abstract boolean generate();
	
	World world;
	Random random;
}
