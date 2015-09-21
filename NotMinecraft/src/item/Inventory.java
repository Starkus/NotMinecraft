package item;


public class Inventory {
	
	public Inventory(int size) {
		
		item_list = new ItemStack[size];
		
	}
	
	public ItemStack getSlot(int i) {
		return item_list[i];
	}
	
	public ItemStack putInSlot(ItemStack items, int i) {
		
		if (items == null) {
			ItemStack out = item_list[i];
			item_list[i] = null;
			return out;
		}
		
		if (item_list[i] == null) {
			if (items.size <= max_stack_size) {
				item_list[i] = items.clone();
				return null;
			}
			item_list[i] = new ItemStack(items.getItem(), max_stack_size);
			return new ItemStack(items.getItem(), items.size - max_stack_size);
		}
		
		if (item_list[i].getItem().equalTo(items.getItem())) {
			int excedent = item_list[i].add(items.size, max_stack_size);
			if (excedent == 0)
				return null;
			return new ItemStack(items.getItem(), excedent, items.getMetadata());
		}
		
		ItemStack out = item_list[i];
		item_list[i] = items.clone();
		
		return out;
	}
	
	public ItemStack getItemsOnSlot(int i) {
		if (item_list[i] == null) return null;
		return item_list[i].clone();
	}
	
	public void addItem(ItemStack items) {
		if (items == null || items.getItem() == null) {
			return;
		}
		
		int x = search(items.getItem(), items.getMetadata());
		if (x == -1) {
			for (int i=0; i<item_list.length; i++) {
				if (item_list[i] == null) {
					//item_list[i] = items;
					addItem(  putInSlot(items, i));
					return;
				}
			}
		} else {
			//item_list[x].size += items.size;
			addItem(  putInSlot(items, x));
			return;
		}
		System.out.println("Full inventory!");
	}
	
	public void takeItem(ItemStack items) {

		if (items == null || items.getItem() == null) {
			return;
		}
		
		items.size = -items.size;
		
		int x = search(items.getItem(), items.getMetadata());
		
		if (x == -1) {
			return;
		} else {
			addItem(  putInSlot(items, x));
			return;
		}
	}
	
	public int search(Item item, int meta) {
		
		for (int i = 0; i < item_list.length; i++) {
			if (item_list[i] != null) {
				if (item_list[i].getItem() != null) {
					if (item_list[i].getItem().equalTo(item)) {
						if (item_list[i].getMetadata() == meta) {
							if (item_list[i].size < max_stack_size)
								return i;
						}
					}
				}
			}
		}
		
		return -1;
	}
	
	public void clean() {
		
		for (int i = 0; i < item_list.length; i++) {
			if (item_list[i] != null) {
				if (item_list[i].size <= 0) {
					item_list[i] = null;
				}
			}
		}
		
	}
	
	public int size() {
		return item_list.length;
	}

	private ItemStack[] item_list;
	
	final int max_stack_size = 200;
	
}
