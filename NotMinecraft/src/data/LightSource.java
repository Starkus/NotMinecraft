package data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;

public class LightSource {
	public LightSource(float x, float y, float z, float w) {
		position = new float[] {x, y, z, w};
		ambient = diffuse = specular = new float[] {0f, 0f, 0f, 0f};
	}
	public LightSource setAmbient(float color[]) {
		ambient = color;
		return this;
	}
	public LightSource setDiffuse(float color[]) {
		diffuse = color;
		return this;
	}
	public LightSource setSpecular(float color[]) {
		specular = color;
		return this;
	}
	public void load(int slot) {
		ByteBuffer tmp = ByteBuffer.allocateDirect(16);
		tmp.order(ByteOrder.nativeOrder());
		
		GL11.glLight(slot, GL11.GL_AMBIENT, (FloatBuffer) tmp.asFloatBuffer().put(ambient).flip());
		GL11.glLight(slot, GL11.GL_DIFFUSE, (FloatBuffer) tmp.asFloatBuffer().put(diffuse).flip());
		GL11.glLight(slot, GL11.GL_SPECULAR, (FloatBuffer) tmp.asFloatBuffer().put(specular).flip());
		GL11.glLight(slot, GL11.GL_POSITION, (FloatBuffer) tmp.asFloatBuffer().put(position).flip());
		GL11.glEnable(slot);
	}
	
	float[] position;
	float[] ambient;
	float[] diffuse;
	float[] specular;
	
}
