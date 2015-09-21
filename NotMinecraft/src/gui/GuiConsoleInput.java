package gui;

import data.EnumCenter;

public class GuiConsoleInput extends Gui{

	public GuiConsoleInput(int w, int h) {
		super(w, h);
		
		input_label = (HudInputLabel) new HudInputLabel(4f, 16f).setScale(0.4f);
		input_label.setAnchorPoint(EnumCenter.LEFTBOT);
		//input_label.write("-");
	}

	@Override
	public void tick() {
		
		input_label.tick();
		
		char last_char = '\0';
		try {
			last_char = input_label.getText().charAt(input_label.getText().length()-1);
		} catch (Exception e) {}
		
		if (last_char == 13) {
			
			ConsoleCommandHandler.runCommand(input_label.text);
			
			destroy = true;
		}
		
		if (last_char == 27) {
			
			destroy = true;
		}
		
	}
	
	@Override
	public void draw() {
		
		input_label.draw();
		
	}
	
	@Override
	public boolean getsKeyboardControl() {
		return true;
	}

	HudInputLabel input_label;
}
