package block;


public class BlockLog extends Block {

	public BlockLog(int id, int index) {
		super(id, index);
	}
	
	public int getBlockIndexInTexture(int f, int meta) {
		if (f == 4 || f == 5) {
			return 26 + 16*meta;
		} else {
			return 25 + 16*meta;
		}
	}

}
