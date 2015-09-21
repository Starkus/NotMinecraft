package gui;

import org.lwjgl.input.Keyboard;

public class GuiHandler {

	public static void checkEvents(int w, int h) {
		if (current_gui == null) {
			while (Keyboard.next()) {
			
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) && Keyboard.getEventKeyState()) {
					current_gui = new GuiPauseMenu(w, h);
				}
				
				if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && Keyboard.getEventKeyState()) {
					current_gui = new GuiConsoleInput(w, h);
				}
				
				if (Keyboard.isKeyDown(Keyboard.KEY_E) && Keyboard.getEventKeyState()) {
					current_gui = new GuiInventory(w, h);
				}
				
			}
		}
			
		if (current_gui != null && current_gui.shouldBeDestroyed()) {
			killCurrentGui();
		}
	}
	
	public static Gui getCurrentGui() {
		return current_gui;
	}
	public static void killCurrentGui() {
		current_gui.onDestroy();
		current_gui = null;
	}
	
	public static void tick() {
		if (current_gui == null) return;
		current_gui.tick();
	}
	
	public static void draw() {
		if (current_gui == null) return;
		current_gui.draw();
	}

	public static boolean isGamePaused() {
		if (current_gui == null) return false;
		return current_gui.pausesGame();
	}
	public static boolean isGuiOnKeyboardControl() {
		if (current_gui == null) return false;
		return current_gui.getsKeyboardControl();
	}
	public static boolean isGuiOnMouseControl() {
		if (current_gui == null) return false;
		return current_gui.getsMouseControl();
	}
	
	private static Gui current_gui;

}
