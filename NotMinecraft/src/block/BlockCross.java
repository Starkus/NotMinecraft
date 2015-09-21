package block;

import org.lwjgl.util.vector.Vector3f;

import data.EnumBlockItemDrawMethod;
import engine.Polygon;
import engine.UVVertex;
import engine.Vertex;

public class BlockCross extends Block {

	public BlockCross(int id, int index) {
		super(id, index);
	}
	
	public void buildPolygons()
	{	
		Vector3f n = new Vector3f( 0f,  0f,  1f);
		p = new Polygon[8];
		p[0] = new Polygon(new Vertex[] {v[0], v[2], v[4]}, new UVVertex[] {uv[2], uv[3], uv[0]}, n);
		p[1] = new Polygon(new Vertex[] {v[2], v[6], v[4]}, new UVVertex[] {uv[3], uv[1], uv[0]}, n);
		p[2] = new Polygon(new Vertex[] {v[3], v[1], v[7]}, new UVVertex[] {uv[2], uv[3], uv[0]}, n);
		p[3] = new Polygon(new Vertex[] {v[1], v[5], v[7]}, new UVVertex[] {uv[3], uv[1], uv[0]}, n);
		p[4] = new Polygon(new Vertex[] {v[2], v[4], v[6]}, new UVVertex[] {uv[3], uv[0], uv[1]}, n);
		p[5] = new Polygon(new Vertex[] {v[2], v[0], v[4]}, new UVVertex[] {uv[3], uv[2], uv[0]}, n);
		p[6] = new Polygon(new Vertex[] {v[1], v[7], v[5]}, new UVVertex[] {uv[3], uv[0], uv[1]}, n);
		p[7] = new Polygon(new Vertex[] {v[1], v[3], v[7]}, new UVVertex[] {uv[3], uv[2], uv[0]}, n);

		p[0] = new Polygon(new Vertex[] {v[0], v[5], v[3]}, new UVVertex[] {uv[2], uv[3], uv[0]}, n);
		p[1] = new Polygon(new Vertex[] {v[5], v[6], v[3]}, new UVVertex[] {uv[3], uv[1], uv[0]}, n);
		p[2] = new Polygon(new Vertex[] {v[4], v[1], v[7]}, new UVVertex[] {uv[2], uv[3], uv[0]}, n);
		p[3] = new Polygon(new Vertex[] {v[1], v[2], v[7]}, new UVVertex[] {uv[3], uv[1], uv[0]}, n);
		p[4] = new Polygon(new Vertex[] {v[5], v[3], v[6]}, new UVVertex[] {uv[3], uv[0], uv[1]}, n);
		p[5] = new Polygon(new Vertex[] {v[5], v[0], v[3]}, new UVVertex[] {uv[3], uv[2], uv[0]}, n);
		p[6] = new Polygon(new Vertex[] {v[1], v[7], v[2]}, new UVVertex[] {uv[3], uv[0], uv[1]}, n);
		p[7] = new Polygon(new Vertex[] {v[1], v[4], v[7]}, new UVVertex[] {uv[3], uv[2], uv[0]}, n);
		polygons = p;
	}
	
	public EnumBlockItemDrawMethod getItemDrawMethod() {
		return EnumBlockItemDrawMethod.PLANE;
	}
	
	public boolean isEntireBlock() {
		return false;
	}
	public boolean hasCollision() {
		return false;
	}
	@Override
	public float getLightStop() {
		return 0f;
	}

}
