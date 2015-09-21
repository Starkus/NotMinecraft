package block;

public class BlockTable extends Block {

	public BlockTable(int id, int index) {
		super(id, index);
		
	}
	
	public int getBlockIndexInTexture(int f, int meta) {
		if (f == 4) {
			return texture_index;
		} else if (f == 5) {
			return texture_index+2;
		} else {
			return texture_index+1;
		}
	}
	
	public boolean isEntireBlock() {
		return false;
	}

}
