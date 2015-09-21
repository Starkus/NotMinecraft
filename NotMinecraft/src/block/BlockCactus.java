package block;

import org.lwjgl.util.vector.Vector3f;

import engine.Polygon;
import engine.UVVertex;
import engine.Vertex;

public class BlockCactus extends Block {

	public BlockCactus(int id, int index) {
		super(id, index);
		bounds = new float[] {1F/16, 1F/16, 0F, 15F/16, 15F/16, 1F};
	}
	public int getBlockIndexInTexture(int f, int meta)
	{
		if (f==4) return texture_index+1;
		else return texture_index;
	}
	
	public void buildVert(int meta)
	{			
		float x0 = getBounds(meta)[0];
		float y0 = getBounds(meta)[1];
		float x1 = getBounds(meta)[3];
		float y1 = getBounds(meta)[4];
		float z1 = getBounds(meta)[5];
		
		v = new Vertex[20];
		v[0]  = new Vertex(x1, 0f, 0f);
		v[1]  = new Vertex(x0, 0f, 0f);
		v[2]  = new Vertex(x0, 0f, 1f);
		v[3]  = new Vertex(x1, 0f, 1f);
		v[4]  = new Vertex(x1, 1f, 0f);
		v[5]  = new Vertex(x0, 1f, 0f);
		v[6]  = new Vertex(x0, 1f, 1f);
		v[7]  = new Vertex(x1, 1f, 1f);

		v[8]  = new Vertex(1f, y0, 0f);
		v[9]  = new Vertex(0f, y0, 0f);
		v[10] = new Vertex(0f, y0, 1f);
		v[11] = new Vertex(1f, y0, 1f);
		v[12] = new Vertex(1f, y1, 0f);
		v[13] = new Vertex(0f, y1, 0f);
		v[14] = new Vertex(0f, y1, 1f);
		v[15] = new Vertex(1f, y1, 1f);
		// Top
		v[16] = new Vertex(1f, 0f, z1);
		v[17] = new Vertex(0f, 0f, z1);
		v[18] = new Vertex(0f, 1f, z1);
		v[19] = new Vertex(1f, 1f, z1);
	}
	public void buildUV(int meta)
	{
		
		uv = new UVVertex[6*4];
		
		int index;
		
		int I, J;
		float i, j;
		
		for (int f = 0; f < uv.length/4; f++) {
			index = getBlockIndexInTexture(f, meta);
		
			I = (int) (index) % 16;
			i = (float) I * 0.0625F;
			J = (int) (index) / 16;
			j = (float) J * 0.0625F;
		
			int shift = f*4;
			
			if (f != 4 && f != 5) {
				uv[0+shift] = new UVVertex((0f)/16+i, (0f)/16+j);
				uv[1+shift] = new UVVertex((1f)/16+i, (0f)/16+j);
				uv[2+shift] = new UVVertex((0f)/16+i, (1f)/16+j);
				uv[3+shift] = new UVVertex((1f)/16+i, (1f)/16+j);
			} else {
				uv[0+shift] = new UVVertex((0f)/16+i, (0f)/16+j);
				uv[1+shift] = new UVVertex((1f)/16+i, (0f)/16+j);
				uv[2+shift] = new UVVertex((0f)/16+i, (1f)/16+j);
				uv[3+shift] = new UVVertex((1f)/16+i, (1f)/16+j);			}
		}
	}
	public void buildPolygons()
	{	
		p = new Polygon[10];

		p[0] = new Polygon(new Vertex[] {v[1],  v[2],  v[5]},  new UVVertex[] {uv[7],  uv[5],  uv[6]},  new Vector3f(-1f,  0f, 0f));
		p[1] = new Polygon(new Vertex[] {v[2],  v[6],  v[5]},  new UVVertex[] {uv[5],  uv[4],  uv[6]},  new Vector3f(-1f,  0f, 0f));
		p[2] = new Polygon(new Vertex[] {v[18], v[17], v[16]}, new UVVertex[] {uv[19], uv[17], uv[16]}, new Vector3f( 0f,  0f, 1f));
		p[3] = new Polygon(new Vertex[] {v[19], v[18], v[16]}, new UVVertex[] {uv[18], uv[19], uv[16]}, new Vector3f( 0f,  0f, 1f));
		p[4] = new Polygon(new Vertex[] {v[3],  v[0],  v[7]},  new UVVertex[] {uv[12], uv[14], uv[13]}, new Vector3f( 1f,  0f, 0f));
		p[5] = new Polygon(new Vertex[] {v[0],  v[4],  v[7]},  new UVVertex[] {uv[14], uv[15], uv[13]}, new Vector3f( 1f,  0f, 0f));
		p[6] = new Polygon(new Vertex[] {v[12], v[13], v[15]}, new UVVertex[] {uv[2],  uv[3],  uv[0]},  new Vector3f( 0f,  1f, 0f));
		p[7] = new Polygon(new Vertex[] {v[13], v[14], v[15]}, new UVVertex[] {uv[3],  uv[1],  uv[0]},  new Vector3f( 0f,  1f, 0f));
		p[8]= new Polygon(new Vertex[]  {v[11], v[10], v[8]},  new UVVertex[] {uv[1],  uv[0],  uv[3]},  new Vector3f( 0f, -1f, 0f));
		p[9]= new Polygon(new Vertex[]  {v[10], v[9],  v[8]},  new UVVertex[] {uv[0],  uv[2],  uv[3]},  new Vector3f( 0f, -1f, 0f));
		polygons = p;
	}

}
