package test;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.*;

import array.Array2D;
import util.ImageTools;

public class Chunk2D {

	public Chunk2D(int x, int y, Array2D a) {
		x_pos = x;
		y_pos = y;
		array = a;
        
		ImageTools.load2dArray(a.getArray(), x*2 * (1+y*2));
	}
	
	public void render() {
		
		glBindTexture(GL_TEXTURE_2D, x_pos*2 * (1+y_pos*2));

		glBegin(GL_TRIANGLES);
			glColor3f(1f, 1f, 1f);
			glTexCoord2f(0f, 1f);
			glVertex2f(x_pos + 0f, y_pos + 0f);

			glTexCoord2f(1f, 1f);
			glVertex2f(x_pos + 1f, y_pos + 0f);

			glTexCoord2f(0f, 0f);
			glVertex2f(x_pos + 0f, y_pos + 1f);
		glEnd(); 
		
		glBegin(GL_TRIANGLES);
			glTexCoord2f(1f, 1f);
			glVertex2f(x_pos + 1f, y_pos + 0f);
	
			glTexCoord2f(1f, 0f);
			glVertex2f(x_pos + 1f, y_pos + 1f);
	
			glTexCoord2f(0f, 0f);
			glVertex2f(x_pos + 0f, y_pos + 1f);
		glEnd();
		
	}
	
	int x_pos, y_pos;
	Array2D array;

}
