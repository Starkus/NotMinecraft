package gui;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import client.Game;
import data.EnumButtonState;
import data.EnumCenter;

public class HudButton extends HudObject {

	public HudButton(float x, float y) {
		super(x, y);

		x_size = 10f;
		y_size = 10f;
		center = EnumCenter.LEFTBOT;
		
		label = (HudTextLabel) new HudTextLabel(x, y).setScale(.5f).setAnchorPoint(EnumCenter.CENTER);
		label.write("unnamed button");
	}
	
	public HudButton setSize(float x, float y) {
		x_size = x;
		y_size = y;
		return this;
	}
	public HudButton setCenter(EnumCenter arg) {
		center = arg;
		
		if (center == EnumCenter.RIGHT || center == EnumCenter.RIGHTTOP || center == EnumCenter.RIGHTBOT) {
			label.setAlignment(1, 0);
		} else if (center == EnumCenter.CENTER || center == EnumCenter.TOP || center == EnumCenter.BOTTOM) {
			label.setAlignment(2, 0);
		}
		
		if (center == EnumCenter.TOP || center == EnumCenter.LEFTTOP || center == EnumCenter.RIGHTTOP) {
			label.y_pos -= y_size;
		} else if (center == EnumCenter.CENTER || center == EnumCenter.LEFT || center == EnumCenter.RIGHT) {
			label.y_pos -= y_size / 1f;
		}
		
		return this; 
	}
	public HudButton setLabel(String arg) {
		label.write(arg);
		return this;
	}

	@Override
	public void tick() {

		x = getPosition().x;
		y = getPosition().y;
		
		if (center == EnumCenter.RIGHT || center == EnumCenter.RIGHTTOP || center == EnumCenter.RIGHTBOT) {
			x -= x_size;
		} else if (center == EnumCenter.CENTER || center == EnumCenter.TOP || center == EnumCenter.BOTTOM) {
			x -= x_size / 2f;
		}
		
		if (center == EnumCenter.TOP || center == EnumCenter.LEFTTOP || center == EnumCenter.RIGHTTOP) {
			y -= y_size;
		} else if (center == EnumCenter.CENTER || center == EnumCenter.LEFT || center == EnumCenter.RIGHT) {
			y -= y_size / 2f;
		}
		
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
		
		label.tick();
	}
	
	@Override
	public void draw() {

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
		
		label.draw();

	}
	
	public EnumButtonState getCurrentState() {
		return state;
	}
	
	public boolean isMouseOver() {
		
		float xm = Mouse.getX() / Game.hud_scale;
		float ym = Mouse.getY() / Game.hud_scale;
		
		
		if (xm > x && ym > y) {
			if (xm < x + x_size && ym < y + y_size) {
				return true;
			}
		}
		
		return false;
	}
	
	float x_size, y_size;
	float x, y;
	
	EnumCenter center;
	
	EnumButtonState state = EnumButtonState.NORMAL;
	
	HudTextLabel label;

}
