package block;

import item.ItemBlock;
import item.ItemStack;

public class BlockStone extends Block {

	public BlockStone(int id, int index) {
		super(id, index);
	}
	
	@Override
	public ItemStack getItemDrop(int meta) {
		ItemBlock i = new ItemBlock(Block.cobble.id);
		return new ItemStack(i, 1);
	}

}
