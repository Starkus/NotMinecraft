package gui;

import org.lwjgl.input.Mouse;

import client.Game;

public class HudItemOnCursor extends HudItem{

	public HudItemOnCursor(float x, float y) {
		super(x, y);
	}
	
	@Override
	public void tick() {
		
		float x =  4f + Mouse.getX() / Game.hud_scale;
		float y = -4f + Mouse.getY() / Game.hud_scale;
		
		setPosition(x, y);
		
		super.tick();
	}

}
