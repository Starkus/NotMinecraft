import client.Game;


public class Start {

	public static void main(String[] args) {
		String seed = "";
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("/seed-")) {
				seed = args[i].substring(6, args[i].length());
			}
		}
		
		@SuppressWarnings("unused")
		Game game = new Game(seed);
		
	}
	
}
