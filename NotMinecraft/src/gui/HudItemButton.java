package gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import client.Game;
import data.EnumButtonState;

public class HudItemButton extends HudItem {

	public HudItemButton(float x, float y) {
		super(x, y);
	}
	
	@Override
	public void tick() {
		super.tick();

		
		if (isMouseOver()) {
			if (Mouse.isButtonDown(0))
				state = EnumButtonState.PRESSED;
			else {
				if (state == EnumButtonState.PRESSED)
					state = EnumButtonState.RELEASED;
				else
				state = EnumButtonState.HOVER;
			}
		} else {
			state = EnumButtonState.NORMAL;
		}
	}
	
	@Override
	public void draw() {
		
		float x = getPosition().x - 4f;
		float y = getPosition().y - 4f;
		
		float x_size = 8f;
		float y_size = 8f;
		
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		
		float lum = 0f;

		if (state == EnumButtonState.HOVER)
			lum = .2f;
		if (state == EnumButtonState.PRESSED)
			lum = .1f;

		GL11.glColor4f(lum, lum, lum, 1f);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x, 			y);
			GL11.glVertex2f(x + x_size, y);
			GL11.glVertex2f(x + x_size, y + y_size);
			GL11.glVertex2f(x, 			y + y_size);
		GL11.glEnd();
		
		
		
		super.draw();
	}

	
	public boolean isMouseOver() {
		
		float xm = Mouse.getX() / Game.hud_scale;
		float ym = Mouse.getY() / Game.hud_scale;
		
		
		float x = getPosition().x - 4f;
		float y = getPosition().y - 4f;
		
		float x_size = 8f;
		float y_size = 8f;
		
		
		if (xm > x && ym > y) {
			if (xm < x + x_size && ym < y + y_size) {
				return true;
			}
		}
		
		return false;
	}
	
	
	EnumButtonState state = EnumButtonState.NORMAL;

}
