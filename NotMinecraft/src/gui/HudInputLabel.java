package gui;

import org.lwjgl.input.Keyboard;

public class HudInputLabel extends HudTextLabel {

	public HudInputLabel(float x, float y) {
		super(x, y);
	}
	
	@Override
	public void tick() {
		super.tick();

		while (Keyboard.next()){
			if(Keyboard.getEventKeyState()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
					try {
	                    text = text.substring(0, text.length() - 1);
	                } catch (StringIndexOutOfBoundsException e) {}
				} else {
					char c = Keyboard.getEventCharacter();
					if ((c >= 32 && c <= 125) || c == '\r')
						text += String.valueOf(c);
				}
			}
		}
	}
	
	public void draw() {
		int k = (int) (System.currentTimeMillis() % 1000);
		if (k < 500)
			text += "_";
		
		
		super.draw();
		
		
		if (k < 500)
			text = text.substring(0, text.length()-1);
	}

}
