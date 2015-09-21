package gui;

import org.lwjgl.opengl.GL11;

import item.Inventory;
import item.ItemStack;

import static data.EnumButtonState.*;
import static data.EnumCenter.*;

public class HudItemArray extends HudObject{

	public HudItemArray(float x, float y, Inventory inventory, int ipr) {
		super(x, y);
		
		this.inventory = inventory;
		
		int size = inventory.size();
		
		items = new HudItemButton[size];
		
		for (int i = 0; i < size; i++) {
			items[i] = (HudItemButton) new HudItemButton(0, 0).setAnchorPoint(BOTTOM);
		}
		
		items_per_row = ipr;
	}
	
	public void giveInput(ItemStack in) {
		input_box = in;
	}
	
	public ItemStack grabOutput() {
		
		ItemStack out = output_box;
		output_box = null;
		return out;
	}
	
	public boolean anythingNew() {
		return update;
	}

	@Override
	public void tick() {
		
		update = false;
		
		for (int i = 0; i < items.length; i++) {
			
			float x = ((i % items_per_row) - 4) * 10f;
			float y = (int) (i / items_per_row) * 10f;
			
			items[i].setPosition(x, -y+100);
			items[i].tick();
			
			if (items[i].state == RELEASED) {
				
				update = true;
				
				//output_box = items[i].getItem();
				output_box = inventory.putInSlot(input_box, i);
			}
		}
		
	}

	@Override
	public void draw() {
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ZERO);
		
		
		for (int i = 0; i < items.length; i++) {
			items[i].setItem(inventory.getItemsOnSlot(i));
			items[i].draw();
		}
		
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	private Inventory inventory;
	
	private HudItemButton[] items;
	private int items_per_row;
	
	private ItemStack output_box, input_box;
	
	private boolean update;

}
