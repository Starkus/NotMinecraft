package block;

public class BlockSapling extends BlockCross {

	public BlockSapling(int id, int index) {
		super(id, index);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getBlockIndexInTexture(int f, int meta) {

		if (meta == 0) return 62;
		if (meta == 1) return 63;
		else return 79;
	}

}
