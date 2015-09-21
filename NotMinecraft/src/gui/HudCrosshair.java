package gui;

import org.lwjgl.opengl.GL11;

public class HudCrosshair extends HudObject {
	
	public HudCrosshair(float x, float y) {
		
		super(x, y);
		
	}

	@Override
	public void tick() {}

	@Override
	public void draw()  {
		
		float x = getPosition().x;
		float y = getPosition().y;
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glColor4f(1f, 1f, 1f, 1f);
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x-3.5f, y-.4f);
			GL11.glVertex2f(x+3.5f, y-.4f);
			GL11.glVertex2f(x+3.5f, y+.4f);
			GL11.glVertex2f(x-3.5f, y+.4f);
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x-.4f, y-3.5f);
			GL11.glVertex2f(x+.4f, y-3.5f);
			GL11.glVertex2f(x+.4f, y+3.5f);
			GL11.glVertex2f(x-.4f, y+3.5f);
		GL11.glEnd();
		
	}

}
