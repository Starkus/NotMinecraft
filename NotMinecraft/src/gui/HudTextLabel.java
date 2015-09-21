package gui;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import util.Font;

public class HudTextLabel extends HudObject{

	public HudTextLabel(float x, float y) {
		super(x, y);
		font = Font.sen_10;
		scale = 1f;
		
		texture = font.texture;
	}
	
	public HudTextLabel setScale(float s) {
		scale = s;
		return this;
	}
	public HudTextLabel setAlignment(int m, int n) {
		align = m;
		align_y = n;
		return this;
	}
	
	public void write(String str) {
		text = str;
	}
	
	public String read() {
		return text;
	}

	@Override
	public void tick() {}

	@Override
	public void draw() {

		
		texture.bind();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glColor3f(1f, 1f, 1f);
		
		float shift[] = new float[]{0f, 0f};
		
		if (align > 0) shift[0] = -getTotalWidth(0, 0f);
		if (align == 2) shift[0] /= 2f;
		
		if (align_y > 0) shift[1] = getTotalHeight();
		if (align_y == 2) shift[1] /= 2f;
		
		float cursor[] = new float[]{0, 0};
		
		for (int i = 0; i < text.length(); i++) {
			cursor = drawLetter(text.charAt(i), cursor, shift);
		}
		
	}
	
	private float[] drawLetter(char c, float[] cursor, float[] shift) {
		
		if (c == '\n') {
			return new float[]{0, cursor[1] + scale*font.getChar(' ')[5]};
		}
		
		if (c > 125)
			c = '?';
		if (c < 32)
			return cursor;
		
		float[] chr = font.getChar(c);
		
		float x = getPosition().x;
		float y = getPosition().y;
		
		GL11.glBegin(GL11.GL_QUADS);
		
			GL11.glTexCoord2f(chr[0], chr[3]);
			GL11.glVertex2f(shift[0] + cursor[0] + x, 				(-shift[1]) - cursor[1] + y);

			GL11.glTexCoord2f(chr[2], chr[3]);
			GL11.glVertex2f(shift[0] + cursor[0] + x+scale*chr[4], 	(-shift[1]) - cursor[1] + y);

			GL11.glTexCoord2f(chr[2], chr[1]);
			GL11.glVertex2f(shift[0] + cursor[0] + x+scale*chr[4],  (-shift[1]) - cursor[1] + y+scale*chr[5]);

			GL11.glTexCoord2f(chr[0], chr[1]);
			GL11.glVertex2f(shift[0] + cursor[0] + x, 				(-shift[1]) - cursor[1] + y+scale*chr[5]);
			
		GL11.glEnd();
		
		cursor[0] += scale*chr[4];
		return cursor;
		
	}
	
	private float getTotalWidth(int i, float w) {
		if (i >= text.length()) return w;
		else {
			w += scale * font.getChar(text.charAt(i))[4];
			return getTotalWidth(i+1, w);
		}
	}
	private float getTotalHeight() {
		return text.split("\n").length * font.getChar(' ')[5] * scale / 2f;
	}
	
	public String getText() {
		return text;
	}
	
	Texture texture;
	
	Font font;
	float scale;
	String text = "";
	int align = 0;
	int align_y = 0;

}
