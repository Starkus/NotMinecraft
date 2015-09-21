package engine;

import org.lwjgl.util.vector.Vector3f;

public class Polygon {

	public Polygon(Vertex verts[], UVVertex uv[]) {
		this.verts = verts;
		this.uv = uv;
		calcNormal();
	}
	public Polygon(Vertex verts[], UVVertex uv[], Vector3f normal) {
		this.verts = verts;
		this.uv = uv;
		this.normal = normal;
	}
	public void calcNormal() {
		
		normal = new Vector3f(0f, 0f, 0f);
		
		for (int vi = 0; vi < 3; vi++) {
			
			Vertex current = verts[vi];
			Vertex next;
			if (vi == 2) next = verts[0];
			else next = verts[vi + 1];
			
			normal.x = normal.x + ((current.y - next.y) * (current.z - next.z));
			normal.y = normal.y + ((current.z - next.z) * (current.x - next.x));
			normal.z = normal.z + ((current.x - next.x) * (current.y - next.y));
			
		}
		
	}
	
	
	/*public void calcNormal() {
		Vector a = new Vector(new Vector3f(verts[0].x, verts[0].y, verts[0].z), 
							new Vector3f(verts[1].x, verts[1].y, verts[1].z));
		Vector b = new Vector(new Vector3f(verts[1].x, verts[1].y, verts[1].z), 
				new Vector3f(verts[2].x, verts[2].y, verts[2].z));
		Vector c = Vector.dot(a, b);
		
		normal = c;
	}*/
	
	public Vertex verts[] = new Vertex[3];
	public UVVertex uv[] = new UVVertex[3];
	public Vector3f normal;
	
}
