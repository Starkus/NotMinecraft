package gui;

import org.lwjgl.opengl.GL11;

import data.EnumCenter;

public class GuiWorldProgressBar extends Gui{


	public GuiWorldProgressBar(int w, int h) {
		super(w, h);
		
		title = (HudTextLabel) new HudTextLabel(0, -20).setScale(0.4f).setAlignment(2, 0).setAnchorPoint(EnumCenter.TOP);
		title.write("Generating world...");
		
		bar = (HudProgressBar) new HudProgressBar(0f, 0f).setSize(180f, 10f).setLabel("Creating world").setCenter(EnumCenter.BOTTOM).setAnchorPoint(EnumCenter.CENTER);
	}

	@Override
	public void tick() {
		
		bar.tick();
		
	}

	@Override
	public void draw() {
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glColor4f(.1f, .1f, .1f, 1f);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(0f, 	0f);
			GL11.glVertex2f(width, 	0f);
			GL11.glVertex2f(width, 	height);
			GL11.glVertex2f(0f, 	height);
		GL11.glEnd();
		
		title.draw();
		
		bar.draw();

	}
	
	public void setProgress(float arg) {
		bar.setProgress(arg);
	}
	public void setLabel(String arg) {
		bar.setLabel(arg);
	}

	private HudTextLabel title;
	private HudProgressBar bar;

}
