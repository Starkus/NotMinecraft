package block;

import java.util.Random;

import item.ItemBlock;
import item.ItemStack;

public class BlockLeaves extends Block{

	public BlockLeaves(int id, int index) {
		super(id, index);
	}
	
	public ItemStack getItemDrop(int meta) {
		ItemBlock i = new ItemBlock(Block.sapling.id);
		int r = new Random().nextInt(16);
		if (r == 0) return new ItemStack(i, 1, meta);
		return null;
	}
	@Override
	public int getBlockIndexInTexture(int f, int meta) {
		if (meta == 0) return 28;
		else if (meta == 1) return 44;
		else return 60;
	}
	
	public boolean isEntireBlock() {
		return false;
	}
	@Override
	public float getLightStop() {
		return 0f;
	}
	
}
