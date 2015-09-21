package util;

import org.lwjgl.util.vector.Vector3f;

public class Vector2Point {
	public Vector2Point(Vector3f sta, Vector3f end) {
		x = end.x - sta.x;
		y = end.y - sta.y;
		z = end.z - sta.z;
		this.start = sta;
		this.end = end;
		normalize();
	}
	public Vector2Point(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public float lenght() {
		return (float) (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2)));
	}
	public void normalize() {
		float lenght = lenght();
		
		if (lenght == 0) lenght = 1;
		x /= lenght;
		y /= lenght;
		z /= lenght;
	}
	
	public static Vector2Point sum(Vector2Point A, Vector2Point B) {
		float x = A.x + B.x;
		float y = A.y + B.y;
		float z = A.z + B.z;
		Vector2Point C = new Vector2Point(x, y, z);
		return C;
	}
	public static float scalar(Vector2Point A, Vector2Point B) {
		return (A.x * B.x + 
				A.y * B.y + 
				A.z * B.z);
	}
	public static Vector2Point dot(Vector2Point A, Vector2Point B) {
		float x = (A.y * B.z) - (A.z * B.y);
		float y = (A.z * B.x) - (A.x * B.z);
		float z = (A.x * B.y) - (A.y * B.x);
		Vector2Point C = new Vector2Point(x, y, z);
		return C;
	}
	
	public Vector3f start, end;
	public float x, y, z;
}
