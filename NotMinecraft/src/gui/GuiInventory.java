package gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import client.Game;
import item.ItemStack;

public class GuiInventory extends Gui {

	public GuiInventory(int w, int h) {
		super(w, h);
		
		item_array = new HudItemArray(0, 0, Game.getPlayer().getInventory(), 9);
		cursor = new HudItemOnCursor(0, 0);
	}

	@Override
	public void tick() {
		
		while (Keyboard.next()) {
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || 
				Keyboard.isKeyDown(Keyboard.KEY_E)) {
				destroy = true;
				return;
			}
		}
		
		item_array.giveInput(cursor.getItem());
		
		item_array.tick();
		
		ItemStack out = item_array.grabOutput();
		if (item_array.anythingNew()) cursor.setItem(out);
		
		cursor.tick();
		
	}

	@Override
	public void draw() {
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor4f(0f, 0f, 0f, 0.6f);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(0f, 	0f);
			GL11.glVertex2f(width, 	0f);
			GL11.glVertex2f(width, 	height);
			GL11.glVertex2f(0f, 	height);
		GL11.glEnd();
		
		item_array.draw();
		
		cursor.draw();

	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		item_array.getInventory().addItem(cursor.getItem());
	}
	
	@Override
	public boolean getsKeyboardControl() {
		return true;
	}
	
	@Override
	public boolean getsMouseControl() {
		return true;
	}
	
	HudItemArray item_array;
	HudItemOnCursor cursor;

}
