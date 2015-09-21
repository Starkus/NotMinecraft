package data;

import org.lwjgl.util.vector.Vector3f;

import client.Game;
import util.Vector3d;

public class BoundCylinder extends Bound {

	public BoundCylinder(Vector3d pos, float h, float r) {
		height = h;
		radius = r;
	}
	
	public boolean collidesWithBlock(Vector3f pos, int x, int y, int z) {
		
		//System.out.println(x);System.out.println(y);System.out.println(z);
		
		boolean bcoll = Game.getCurrentWorld().getBlock(x, y, z).hasCollision();
		boolean zcoll = false;
		
		if (pos.z < z && height > z) zcoll = true;
		if ((Math.abs(pos.x - x)   + Math.abs(pos.y - y)) < radius);
		if ((Math.abs(pos.x - x+1) + Math.abs(pos.y - y)) < radius);
		if ((Math.abs(pos.x - x)   + Math.abs(pos.y - y+1)) < radius);
		if ((Math.abs(pos.x - x+1) + Math.abs(pos.y - y+1)) < radius);
		
		return bcoll && zcoll;// && xzcoll;
		
	}
	
	float height, radius;
	
}
