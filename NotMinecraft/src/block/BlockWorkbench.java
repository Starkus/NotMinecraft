package block;

import data.EnumBlockItemDrawMethod;

public class BlockWorkbench extends Block {

	public BlockWorkbench(int id, int index) {
		super(id, index);
		
	}
	
	public float[] getBounds(int meta) {
		return new float[] {1/16f, 1/16f, 0f, 15/16f, 15/16f, 10/16f};
	}
	public int getBlockIndexInTexture(int f, int meta) {
		if (f == 4) {
			return 10;
		} else if (f == 5) {
			return 12;
		} else {
			return 11;
		}
	}
	
	public boolean isEntireBlock() {
		return false;
	}
	
	public EnumBlockItemDrawMethod getItemDrawMethod() {
		return EnumBlockItemDrawMethod.BLOCK;
	}

}
