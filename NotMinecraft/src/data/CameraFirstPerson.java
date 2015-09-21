package data;



import org.lwjgl.input.Mouse;

import client.Game;
import util.Vector3d;

public class CameraFirstPerson extends Camera{
	
	public CameraFirstPerson(float x, float y, float z) {
		setPosition(new Vector3d(x, y, z));
	}
	public void update(int w, int h) {
		double x = (float) (Game.getPlayer().getPosition().x + 0 * Math.sin(Math.toRadians(Game.getPlayer().getRotation().z)));
		double y = (float) (Game.getPlayer().getPosition().y - 0 * Math.cos(Math.toRadians(Game.getPlayer().getRotation().z)));
		double z = (float) Game.getPlayer().getPosition().z + 1.65f;
		
		setPosition(new Vector3d(x, y, z));

		if (Mouse.isGrabbed())	look(w, h);
	}
	
	public void look(int width, int height) {
		dx = -Mouse.getDX();
		dy = -Mouse.getDY();
		
		yaw(dx * mouseSen);
		pitch(dy * mouseSen);
		
		Mouse.setCursorPosition(width/2, height/2);
	}

	private float dx = 0F;
	private float dy = 0F;
	
	private float mouseSen = 0.04F;
	
}
