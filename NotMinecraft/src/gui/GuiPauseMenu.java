package gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import client.Game;
import data.EnumButtonState;
import data.EnumCenter;

public class GuiPauseMenu extends Gui {

	public GuiPauseMenu(int w, int h) {
		super(w, h);
		
		button_1 = (HudButton) new HudButton(0f, 15f).setLabel("Resume").setCenter(EnumCenter.BOTTOM).setSize(40f, 9f).setAnchorPoint(EnumCenter.CENTER);
		button_2 = (HudButton) new HudButton(0f, 5f).setLabel("Donate").setCenter(EnumCenter.BOTTOM).setSize(40f, 9f).setAnchorPoint(EnumCenter.CENTER);
		button_3 = (HudButton) new HudButton(0f, -5f).setLabel("Options").setCenter(EnumCenter.BOTTOM).setSize(40f, 9f).setAnchorPoint(EnumCenter.CENTER);
		button_4 = (HudButton) new HudButton(0f, -15f).setLabel("Quit").setCenter(EnumCenter.BOTTOM).setSize(40f, 9f).setAnchorPoint(EnumCenter.CENTER);
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

		button_1.draw();
		button_2.draw();
		button_3.draw();
		button_4.draw();

	}
	
	@Override
	public boolean pausesGame() {
		return true;
	}

	@Override
	public void tick() {
		
		while (Keyboard.next()) {
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				destroy = true;
				return;
			}
		}

		button_1.tick();
		button_2.tick();
		button_3.tick();
		button_4.tick();
		
		if (button_1.getCurrentState() == EnumButtonState.RELEASED) {
			destroy = true;
			return;
		}
		
		if (button_4.getCurrentState() == EnumButtonState.RELEASED) {
			Game.requestQuit();
			return;
		}
		
	}
	
	
	HudButton button_1, button_2, button_3, button_4;

}
