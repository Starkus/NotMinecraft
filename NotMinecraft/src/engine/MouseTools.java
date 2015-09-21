package engine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class MouseTools {

	static public FloatBuffer getMousePosition(float mouseX, float mouseY, float z_offset) { 
		
		IntBuffer viewport = BufferUtils.createIntBuffer(16); 
		
		FloatBuffer modelview = BufferUtils.createFloatBuffer(16); 
		FloatBuffer projection = BufferUtils.createFloatBuffer(16); 
		FloatBuffer winZ = BufferUtils.createFloatBuffer(1); 
		
		float winX, winY; 
		
		FloatBuffer position = BufferUtils.createFloatBuffer(3); 
		
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelview); 
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, projection); 
		GL11.glGetInteger(GL11.GL_VIEWPORT, viewport); 
		
		winX = (float)mouseX; 
		winY = (float)viewport.get(3) - (float)mouseY; 
		
		GL11.glReadPixels((int)mouseX, (int)winY, 1, 1, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, winZ);
		float k = winZ.get();
		k += z_offset;
		if (k > .9777f) return null;
		GLU.gluUnProject(winX, winY, k, modelview, projection, viewport, position); 
		
		return position;
		
	}
}