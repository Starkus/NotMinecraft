package util;

import java.io.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureLoad {
	
	public static Texture LoadPNG(String filename) {
		
		try {
			l_texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(filename), GL11.GL_NEAREST);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return l_texture;
	}
	
	private static Texture l_texture;

}
