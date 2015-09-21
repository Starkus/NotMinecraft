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
import client.Game;

import static org.lwjgl.opengl.GL11.*;

import engine.DrawingTools;
import engine.OpenGLWorldSettings;
import util.ImageTools;

@SuppressWarnings("unused")
public class InfiniteTest {
	
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
		
		Game.hud_scale = 64f;
		
		OpenGLWorldSettings.apply(screen_width, screen_height);
		
		
		Random random = new Random();
		
		//Array res = new GenClouds(256, 4, "eminem").generate(random);
		GenTexture g = new GenTexture(64, 4, "eminem");
		

		chunks[0][0] = new Chunk2D(0, 0, g.genNoise(random, 0, 0).smooth(0).map());
		chunks[1][1] = new Chunk2D(1, 1, g.genNoise(random, 1, 1).smooth(0).map());
		
		//glBindTexture(GL_TEXTURE_2D, 0);
        
        //Setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
	}
	
	static void loop() {

		float w = screen_width;
		float h = screen_height;

		DrawingTools.DrawIn2D(screen_width, screen_height);
		

		glEnable(GL_TEXTURE_2D);
		
		for (int x = 0; x < chunks.length; x++) {
			for (int y = 0; y < chunks[0].length; y++) {
				if (chunks[x][y] != null) chunks[x][y].render();
			}
		}
		
	}
	
	static Chunk2D chunks[][] = new Chunk2D[8][8];
	
	static int screen_width = 1024;
	static int screen_height = 1024;
}
