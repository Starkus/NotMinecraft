package gui;

import java.util.ArrayList;

import data.EnumCenter;

public class Hud {
	
	public Hud(int w, int h) {
		
		width = (int) (w);
		height = (int) (h);

		objects.add(new HudCrosshair(0f, 0f).setAnchorPoint(EnumCenter.CENTER));
		hotbar = (HudHotbar) new HudHotbar(0f, 0f).setAnchorPoint(EnumCenter.BOTTOM);
		console = (HudTextLabel) new HudTextLabel(4f, 30f).setScale(0.4f).setAlignment(0, 1).setAnchorPoint(EnumCenter.LEFT);
		console.write("\n\n\n\n\n\n\n\n");
		
	}
	
	public void tick() {
		hotbar.tick();
	}
	
	public void draw() {
		
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw();
		}
		
		hotbar.draw();
		console.draw();
	}
	
	public int getSlotInHand() {
		return hotbar.getSlotInHand();
	}
	
	public void println(String arg) {
		
		String s = console.read();
		console.write(s.substring(s.indexOf('\n')+1, s.length()) + arg + '\n');
		
	}
	
	int width, height;
	//float hud_scale = 2.8f;
	
	ArrayList<HudObject> objects = new ArrayList<HudObject>();
	HudHotbar hotbar;
	HudTextLabel console;
	
}
