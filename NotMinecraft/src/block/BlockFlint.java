package block;

import item.Item;
import item.ItemStack;

public class BlockFlint extends Block {

	
	public BlockFlint(int id, int index) {
		super(id, index);
		
		bounds = new float[]{5/16f, 6/16f, 0f, 12/16f, 11/16f, 3/16f};
	}

	
	public ItemStack getItemDrop(int meta) {
		return new ItemStack(Item.flint, 1);
	}
	
	public boolean isEntireBlock() {
		return false;
	}
}
