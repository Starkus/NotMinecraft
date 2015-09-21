package gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import client.Game;
import data.EnumCenter;
import util.TextureLoad;

public class HudHotbar extends HudObject {

	public HudHotbar(float x, float y) {
		super(x, y);
		
		for (int i = 0; i < items.length; i++) {
			items[i] = (HudItem) new HudItem(x_pos + (i-4) * 10,  y_pos + 5).setAnchorPoint(EnumCenter.BOTTOM);
		}
		
		texture = TextureLoad.LoadPNG("sprites/gui.png");
		
		hand = (HudHotbarHand) new HudHotbarHand(x, y).setAnchorPoint(EnumCenter.BOTTOM);
	}

	@Override
	public void tick() {
		for (int i = 0; i < items.length; i++) {
			items[i].x_pos = x_pos + (i-4) * 10;
			items[i].y_pos = y_pos + 7;
			items[i].tick();
		}
	}
	
	@Override
	public void draw() {

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ZERO);
		
		texture.bind();
		
		float x = getPosition().x;
		float y = getPosition().y;
		
		int x_size = 94;
		int y_size = 14;
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0f, y_size/128f);
			GL11.glVertex2f(x-x_size/2f, y);
			
			GL11.glTexCoord2f(x_size/128f, y_size/128f);
			GL11.glVertex2f(x+x_size/2f, y);
			
			GL11.glTexCoord2f(x_size/128f, 0f);
			GL11.glVertex2f(x+x_size/2f, y+y_size);
			
			GL11.glTexCoord2f(0f, 0f);
			GL11.glVertex2f(x-x_size/2f, y+y_size);
		GL11.glEnd();
		
		
		for (int i = 0; i < items.length; i++) {
			items[i].setItem(Game.getPlayer().getInventory().getItemsOnSlot(i));
			items[i].draw();
		}
		
		
		int dWheel = Mouse.getDWheel();
		if (dWheel < 0) selection++;
		if (dWheel > 0) selection--;
		selection = (selection +9) % 9;
		hand.draw(selection);
		
	}
	
	public int getSlotInHand() {
		return selection;
	}
	
	private Texture texture;
	
	private HudItem items[] = new HudItem[9];
	private int selection = 0;
	private HudHotbarHand hand;

}
