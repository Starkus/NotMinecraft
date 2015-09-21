


package engine;

public class Vertex {
	public Vertex(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.color = new float[] {x, y, z};
	}
	
	public Vertex(double x2, double y2, double z2) {
		this.x = (float) x2;
		this.y = (float) y2;
		this.z = (float) z2;
		
		this.color = new float[] {x, y, z};
	}

	public float x, y, z;
	public float u, v;
	public float color[] = new float[3];
	
	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
