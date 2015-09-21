package gui;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import util.TextureLoad;

public class HudHotbarHand extends HudObject {

	public HudHotbarHand(float x, float y) {
		super(x, y);
		texture = TextureLoad.LoadPNG("sprites/gui.png");
	}

	@Override
	public void tick() {}

	@Override
	public void draw() {}
	
	public void draw(int pos) {

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ZERO);
		
		texture.bind();
		
		float x = getPosition().x;
		float y = getPosition().y;
		
		float x_shift = x + (pos-4) * 10f;
		
		float scale = 1f;
		
		float size = 14f;
		
		float k = size * scale;
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(95f/128f, 14f/128f);
			GL11.glVertex2f(x_shift - k/2f, y);
			
			GL11.glTexCoord2f(109f/128f, 14f/128f);
			GL11.glVertex2f(x_shift + k/2f, y);
			
			GL11.glTexCoord2f(109f/128f, 0f);
			GL11.glVertex2f(x_shift + k/2f, y + k);
			
			GL11.glTexCoord2f(95f/128f, 0f);
			GL11.glVertex2f(x_shift - k/2f, y + k);
		GL11.glEnd();
		
	}
	
	private Texture texture;

}
