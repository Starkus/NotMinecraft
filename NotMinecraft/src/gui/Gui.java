package gui;

import client.Game;

public abstract class Gui {
	
	public Gui(int w, int h) {
		
		width = (int) (w / Game.hud_scale);
		height = (int) (h / Game.hud_scale);
	}

	
	public abstract void draw();
	public abstract void tick();

	
	public boolean pausesGame() {
		return false;
	}
	public boolean getsKeyboardControl() {
		return false;
	}
	public boolean getsMouseControl() {
		return false;
	}
	
	public boolean shouldBeDestroyed() {
		return destroy;
	}
	
	public void onDestroy() {}
	
	protected int width, height;
	boolean destroy = false;

}
