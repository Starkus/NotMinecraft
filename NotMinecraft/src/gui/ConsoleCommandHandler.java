package gui;

import client.Game;

public class ConsoleCommandHandler {
	
	public static void runCommand(String arg) {
		arg = arg.substring(0, arg.length()-1);
		String words[] = arg.split(" ");
		
		if (words[0].equals("/set")) {
			
			if (words[1].equals("hudscale")) {
				Game.hud_scale = Float.parseFloat(words[2]);
				Game.getHud().println("Hud scale changed to: " + words[2]);
			} else {
				Game.getHud().println("Variable not found or not available");
			}
			
		} else if (words[0].equals("/get")) {
			
			if (words[1].equals("seed")) {
				Game.getHud().println("Current seed is:  " + Game.getSeed());
			} else {
				Game.getHud().println("Variable not found or not available");
			}
			
		} else if (words[0].equals("/quit")) {
			
			Game.getHud().println("Closing game");
			Game.requestQuit();
			
		} else if (!words[0].equals("")) {
			
			Game.getHud().println("Player: " + arg);
			
		}
	}

}
