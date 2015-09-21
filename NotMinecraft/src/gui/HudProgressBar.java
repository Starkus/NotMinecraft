package gui;

import org.lwjgl.opengl.GL11;

import data.EnumCenter;

public class HudProgressBar extends HudObject {

	public HudProgressBar(float x, float y) {
		super(x, y);

		x_size = 10f;
		y_size = 10f;
		center = EnumCenter.LEFTBOT;
		
		label = (HudTextLabel) new HudTextLabel(x, y).setScale(.5f);
		label.write("unnamed progress bar");
	}
	
	public HudProgressBar setSize(float x, float y) {
		x_size = x;
		y_size = y;
		return this;
	}
	public HudProgressBar setCenter(EnumCenter arg) {
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
	
	public HudProgressBar setProgress(float arg) {
		progress = arg;
		return this;
	}
	
	public HudProgressBar setLabel(String arg) {
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
		
		label.setAnchorPoint(anchor);
		label.tick();
	}
	
	@Override
	public void draw() {

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);

		GL11.glColor4f(0.2f, 0.2f, 0.2f, 1f);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x, 			y);
			GL11.glVertex2f(x + x_size, y);
			GL11.glVertex2f(x + x_size, y + y_size);
			GL11.glVertex2f(x, 			y + y_size);
		GL11.glEnd();

		GL11.glColor4f(0.2f, 1.0f, 0.2f, 1f);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x, 			y);
			GL11.glVertex2f(x + x_size * progress, y);
			GL11.glVertex2f(x + x_size * progress, y + y_size);
			GL11.glVertex2f(x, 			y + y_size);
		GL11.glEnd();
		
		label.draw();

	}

	
	float x_size, y_size;
	float x, y;
	
	float progress = 0.0f;
	
	EnumCenter center;
	
	HudTextLabel label;
}
