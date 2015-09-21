package item;

public class ItemStack {

	public ItemStack(Item item, int ammount) {
		setItem(item);
		size = ammount;
		metadata = 0;
	}
	public ItemStack(Item item, int ammount, int meta) {
		setItem(item);
		size = ammount;
		metadata = meta;
	}
	
	public void add(int ammount) {
		size += ammount;
	}
	public int add(int ammount, int max) {
		size += ammount;
		
		if (size > max) {
			int out = size - max;
			size = max;
			return out;
		}
		
		return 0;
	}
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	public int getMetadata() {
		return metadata;
	}
	public void setMetadata(int x) {
		this.metadata = x;
	}
	
	public ItemStack clone() {
		return new ItemStack(item, size, metadata);
	}

	private Item item;
	private int metadata;
	public int size;
	
}
