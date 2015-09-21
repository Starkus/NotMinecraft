package data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

public class Material {
	public Material(float[] diff) {
		diffuse = diff;
		ambient = specular = shininess = new float[] {0f, 0f, 0f, 0f};
	}
	public Material setAmbient(float[] color) {
		ambient = color;
		return this;
	}
	public Material setDiffuse(float[] color) {
		diffuse = color;
		return this;
	}
	public Material setSpecular(float[] color) {
		specular = color;
		return this;
	}
	public Material setShininess(float[] color) {
		shininess = color;
		return this;
	}
	public void activate() {
		ByteBuffer tmp = ByteBuffer.allocateDirect(16);
		tmp.order(ByteOrder.nativeOrder());
		
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, (FloatBuffer) tmp.asFloatBuffer().put(ambient).flip());
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, (FloatBuffer) tmp.asFloatBuffer().put(diffuse).flip());
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, (FloatBuffer) tmp.asFloatBuffer().put(specular).flip());
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SHININESS, (FloatBuffer) tmp.asFloatBuffer().put(shininess).flip());
	}
	
	float[] ambient, diffuse, specular, shininess;
	
}
