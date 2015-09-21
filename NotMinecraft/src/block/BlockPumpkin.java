package block;

public class BlockPumpkin extends Block {

	public BlockPumpkin(int id, int index) {
		super(id, index);
	}

	public int getBlockIndexInTexture(int f, int meta) {
		if (f == 4 || f == 5) {
			return 23 + meta*16;
		} else if (f == 0) {
			return 24 + meta*16;
		} else {
			return 22 + meta*16;
		}
	}

}
