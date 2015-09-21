package block;

public class BlockFurnace extends Block {

	public BlockFurnace(int id, int index) {
		super(id, index);
	}
	
	public float[] getBounds(int meta) {
		return new float[] {1f/16, 1f/16, 0f, 15f/16, 15f/16, 14f/16};
	}
	public int getBlockIndexInTexture(int f, int meta) {
		if (f == 0) return texture_index+meta;
		else return texture_index+2;
	}
	
	public boolean isEntireBlock() {
		return false;
	}
}
