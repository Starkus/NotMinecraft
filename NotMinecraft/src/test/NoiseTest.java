package test;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL12;

import array.Array2D;
import array.GenClouds;
import array.GenTexture;
import array.SimplexNoise_octave;
import client.Game;

import static org.lwjgl.opengl.GL11.*;

import engine.DrawingTools;
import engine.OpenGLWorldSettings;
import util.ImageTools;

@SuppressWarnings("unused")
public class NoiseTest {
	
	public static void main(String[] args) {
		
		try {
			Display.setDisplayMode(new DisplayMode(screen_width, screen_height));
			Display.setVSyncEnabled(true);
			Display.setTitle("Noise Generator");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		init();
		while(!Display.isCloseRequested() &&
				!Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			loop();
			Display.update();
		}
		Display.destroy();
		
	}
	
	static void init() {
		
		Game.hud_scale = 1f;
		
		OpenGLWorldSettings.apply(screen_width, screen_height);
		
		
		Random random = new Random();
		
		//Array res = new GenClouds(256, 4, "eminem").generate(random);
		//Array res = new GenNoise(64, 1, "Algo").generate(random).smooth(0);
		Array2D res = new GenTexture(512, 32, "asd").simplexNoise(random, 512, 0.4f);
		
		res.map();
		//res.levelize(42);
		
		float[][] m = res.getArray();
		
		glBindTexture(GL_TEXTURE_2D, 0);
        
        //Setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        
		ImageTools.load2dArray(m, 0);
		
	}
	
	static void loop() {

		float w = screen_width;
		float h = screen_height;

		DrawingTools.DrawIn2D(screen_width, screen_height);
		

		glEnable(GL_TEXTURE_2D);
		
		glBegin(GL_TRIANGLES);
			glColor3f(1f, 1f, 1f);
			glTexCoord2f(0f, 1f);
			glVertex2f(0, 0);

			glTexCoord2f(1f, 1f);
			glVertex2f(w, 0f);

			glTexCoord2f(0f, 0f);
			glVertex2f(0f, h);
		glEnd(); 
		
		glBegin(GL_TRIANGLES);
			glTexCoord2f(1f, 1f);
			glVertex2f(w, 0f);
	
			glTexCoord2f(1f, 0f);
			glVertex2f(w, h);
	
			glTexCoord2f(0f, 0f);
			glVertex2f(0f, h);
		glEnd();
		
	}
	
	
	static int screen_width = 1024;
	static int screen_height = 1024;
}
