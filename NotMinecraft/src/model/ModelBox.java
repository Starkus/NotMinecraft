package model;

import engine.Polygon;

public class ModelBox {
	
	public Polygon[] p;
	
	private float scale;
	private float[] pivot;
	private float[] rot;
	
	private boolean clip;
	
	public ModelBox(Polygon[] poly) {
		p = poly;
		pivot = new float[] {0f, 0f, 0f};
		rot = new float[] {0f, 0f, 0f};
		scale = 1.0f;
		clip = true;
	}
	
	
	public ModelBox isClipEnabled(boolean flag) {
		clip = flag;
		return this;
	}
	
	public boolean isClipEnabled() {
		return clip;
	}
	
	
	public ModelBox setPivotOffset(float i, float j, float k) {
		pivot = new float[] {i/10, j/10, k/10};
		return this;
	}
	public ModelBox setRotation(float i, float j, float k) {
		rot = new float[] {i, j, k};
		return this;
	}
	
	public ModelBox setPivotOffset(int i, float j) {
		pivot[i] = j/10;
		return this;
	}
	public ModelBox setRotation(int i, float j) {
		rot[i] = j;
		return this;
	}
	
	public ModelBox setRotation(float[] r) {
		if(r.length == 3) rot = r;
		return this;
	}
	public ModelBox setScale(float s) {
		scale = s;
		return this;
	}
	
	public float[] getRotation() {
		return rot;
	}
	public float[] getPivotOffset() {
		return pivot;
	}
	public float getScale() {
		return scale;
	}
	
}
