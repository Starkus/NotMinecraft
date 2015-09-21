package gui;

import org.lwjgl.util.vector.Vector2f;

import client.Game;
import data.EnumCenter;

public abstract class HudObject {
	
	public HudObject(float x, float y) {
		x_pos = x;// / Game.hud_scale;
		y_pos = y;// / Game.hud_scale;
		anchor = EnumCenter.LEFTBOT;
	}
	public HudObject setAnchorPoint(EnumCenter arg) {
		anchor = arg;
		return this;
	}
	
	public void setPosition(float x, float y) {
		x_pos = x;
		y_pos = y;
	}

	public Vector2f getPosition() {
		//return new Vector2f(x_pos / Game.hud_scale, y_pos / Game.hud_scale);
		float shift[] = new float[2];
		
		float sx = Game.getScreenSize(0) / Game.hud_scale;
		float sy = Game.getScreenSize(1) / Game.hud_scale;
		
		switch (anchor) {
			case LEFTBOT:
				shift = new float[]{0f, 0f};
				break;
			case BOTTOM:
				shift = new float[]{sx/2f, 0f};
				break;
			case RIGHTBOT:
				shift = new float[]{sx, 0f};
				break;
			case LEFT:
				shift = new float[]{0f, sy/2f};
				break;
			case CENTER:
				shift = new float[]{sx/2f, sy/2f};
				break;
			case RIGHT:
				shift = new float[]{sx, sy/2f};
				break;
			case LEFTTOP:
				shift = new float[]{0f, sy};
				break;
			case TOP:
				shift = new float[]{sx/2f, sy};
				break;
			case RIGHTTOP:
				shift = new float[]{sx, sy};
				break;
	
			default:
				shift = new float[]{0f, 0f};
				break;
		}
		
		return new Vector2f(x_pos + shift[0], y_pos + shift[1]);
	}
	public abstract void tick();
	public abstract void draw();
	
	float x_pos, y_pos;
	EnumCenter anchor;

}
