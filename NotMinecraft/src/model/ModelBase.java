package model;

import java.util.Dictionary;
import java.util.Hashtable;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import engine.Polygon;
import engine.UVVertex;
import engine.Vertex;
import util.TextureLoad;

public class ModelBase {
	
	public ModelBase(String tex) {
		getBounds();
		setTexture(tex);
	}
	
	public ModelBase setTexture(String tex)
	{
		texture = TextureLoad.LoadPNG(tex);
		return this;
	}
	public void getBounds()
	{
		bounds = new float[] {1F, 1F, 1F};
	}
	protected ModelBox addBox(String name, float x, float y, float z, float posx, float posy, float posz, int u, int v)
	{
		Vertex[] bv = new Vertex[8];
		UVVertex[] buv = new UVVertex[16];
		Polygon[] bp = new Polygon[12];
		
		float pix = 1f/texture.getImageWidth();
		float piy = 1f/texture.getImageHeight();
		
		bv[0] = new Vertex((posx+x)/10,	posy/10,		posz/10);
		bv[1] = new Vertex(posx/10,		posy/10,		posz/10);
		bv[2] = new Vertex(posx/10,		posy/10,		(posz+z)/10);
		bv[3] = new Vertex((posx+x)/10,	posy/10,		(posz+z)/10);
		bv[4] = new Vertex((posx+x)/10,	(posy+y)/10,	posz/10);
		bv[5] = new Vertex(posx/10,		(posy+y)/10,	posz/10);
		bv[6] = new Vertex(posx/10,		(posy+y)/10,	(posz+z)/10);
		bv[7] = new Vertex((posx+x)/10,	(posy+y)/10,	(posz+z)/10);
		
		
		buv[0] = new UVVertex(pix*u,			piy*(v+y+z));
		buv[1] = new UVVertex(pix*(u+y),		piy*(v+y+z));
		buv[2] = new UVVertex(pix*(u+y+x),		piy*(v+y+z));
		buv[3] = new UVVertex(pix*(u+y+x+y),	piy*(v+y+z));
		buv[4] = new UVVertex(pix*(u+y+x+y+x),	piy*(v+y+z));
		
		buv[5] = new UVVertex(pix*u,			piy*(v+y));
		buv[6] = new UVVertex(pix*(u+y),		piy*(v+y));
		buv[7] = new UVVertex(pix*(u+y+x),		piy*(v+y));
		buv[8] = new UVVertex(pix*(u+y+x+y),	piy*(v+y));
		buv[9] = new UVVertex(pix*(u+y+x+y+x),	piy*(v+y));
		
		buv[10] = new UVVertex(pix*(u+y),		piy*(v+y));
		buv[11] = new UVVertex(pix*(u+y+x),		piy*(v+y));
		buv[12] = new UVVertex(pix*(u+y+x+x),	piy*(v+y));
		buv[13] = new UVVertex(pix*(u+y),		piy*v);
		buv[14] = new UVVertex(pix*(u+y+x),		piy*v);
		buv[15] = new UVVertex(pix*(u+y+x+x),	piy*v);
		
		
		bp[0]  = new Polygon(new Vertex[] {bv[0], bv[1], bv[4]}, new UVVertex[] {buv[11], buv[14], buv[12]}, new Vector3f( 0f,  0f, -1f));
		bp[1]  = new Polygon(new Vertex[] {bv[1], bv[5], bv[4]}, new UVVertex[] {buv[14], buv[15], buv[12]}, new Vector3f( 0f,  0f, -1f));
		bp[2]  = new Polygon(new Vertex[] {bv[1], bv[2], bv[5]}, new UVVertex[] {buv[3],  buv[8],  buv[2]},  new Vector3f(-1f,  0f,  0f));
		bp[3]  = new Polygon(new Vertex[] {bv[2], bv[6], bv[5]}, new UVVertex[] {buv[8],  buv[11], buv[2]},  new Vector3f(-1f,  0f,  0f));
		bp[4]  = new Polygon(new Vertex[] {bv[2], bv[3], bv[6]}, new UVVertex[] {buv[14], buv[13], buv[11]}, new Vector3f( 0f,  0f,  1f));
		bp[5]  = new Polygon(new Vertex[] {bv[3], bv[7], bv[6]}, new UVVertex[] {buv[13], buv[10], buv[11]}, new Vector3f( 0f,  0f,  1f));
		bp[6]  = new Polygon(new Vertex[] {bv[3], bv[0], bv[7]}, new UVVertex[] {buv[5],  buv[0],  buv[6]},  new Vector3f( 1f,  0f,  0f));
		bp[7]  = new Polygon(new Vertex[] {bv[0], bv[4], bv[7]}, new UVVertex[] {buv[0],  buv[1],  buv[6]},  new Vector3f( 1f,  0f,  0f));
		bp[8]  = new Polygon(new Vertex[] {bv[4], bv[5], bv[7]}, new UVVertex[] {buv[1],  buv[2],  buv[6]},  new Vector3f( 0f,  1f,  0f));
		bp[9]  = new Polygon(new Vertex[] {bv[5], bv[6], bv[7]}, new UVVertex[] {buv[2],  buv[7],  buv[6]},  new Vector3f( 0f,  1f,  0f));
		bp[10] = new Polygon(new Vertex[] {bv[3], bv[2], bv[0]}, new UVVertex[] {buv[9],  buv[8],  buv[4]},  new Vector3f( 0f, -1f,  0f));
		bp[11] = new Polygon(new Vertex[] {bv[2], bv[1], bv[0]}, new UVVertex[] {buv[8],  buv[3],  buv[4]},  new Vector3f( 0f, -1f,  0f));
		
		ModelBox box = new ModelBox(bp);
		
		boxes.put(name, box);
		return boxes.get(name);
	}
	protected ModelBox addBoxQuads(String name, float x, float y, float z, float px, float py, float pz, int u, int v)
	{
		Vertex[] bv = new Vertex[8];
		UVVertex[] buv = new UVVertex[16];
		Polygon[] bp = new Polygon[12];
		
		float pix = 1f/texture.getImageWidth();
		float piy = 1f/texture.getImageHeight();
		
		bv[0] = new Vertex((px+x)/10,	py/10,		pz/10);
		bv[1] = new Vertex(px/10,		py/10,		pz/10);
		bv[2] = new Vertex(px/10,		py/10,		(pz+z)/10);
		bv[3] = new Vertex((px+x)/10,	py/10,		(pz+z)/10);
		bv[4] = new Vertex((px+x)/10,	(py+y)/10,	pz/10);
		bv[5] = new Vertex(px/10,		(py+y)/10,	pz/10);
		bv[6] = new Vertex(px/10,		(py+y)/10,	(pz+z)/10);
		bv[7] = new Vertex((px+x)/10,	(py+y)/10,	(pz+z)/10);
		
		
		buv[0] = new UVVertex(pix*u,			piy*(v+z+y));
		buv[1] = new UVVertex(pix*(u+z),		piy*(v+z+y));
		buv[2] = new UVVertex(pix*(u+z+x),		piy*(v+z+y));
		buv[3] = new UVVertex(pix*(u+z+x+z),	piy*(v+z+y));
		buv[4] = new UVVertex(pix*(u+z+x+z+x),	piy*(v+z+y));
		
		buv[5] = new UVVertex(pix*u,			piy*(v+z));
		buv[6] = new UVVertex(pix*(u+z),		piy*(v+z));
		buv[7] = new UVVertex(pix*(u+z+x),		piy*(v+z));
		buv[8] = new UVVertex(pix*(u+z+x+z),	piy*(v+z));
		buv[9] = new UVVertex(pix*(u+z+x+z+x),	piy*(v+z));
		
		buv[10] = new UVVertex(pix*(u+z),		piy*(v+z));
		buv[11] = new UVVertex(pix*(u+z+x),		piy*(v+z));
		buv[12] = new UVVertex(pix*(u+z+x+x),	piy*(v+z));
		buv[13] = new UVVertex(pix*(u+z),		piy*v);
		buv[14] = new UVVertex(pix*(u+z+x),		piy*v);
		buv[15] = new UVVertex(pix*(u+z+x+x),	piy*v);
		
		
		bp[0] = new Polygon(new Vertex[] {bv[0], bv[1], bv[2], bv[3]}, new UVVertex[] {buv[1], buv[2], buv[6]});
		bp[1] = new Polygon(new Vertex[] {bv[0], bv[3], bv[7], bv[4]}, new UVVertex[] {buv[2], buv[7], buv[6]});
		bp[2] = new Polygon(new Vertex[] {bv[4], bv[5], bv[6], bv[7]}, new UVVertex[] {buv[2], buv[3], buv[7]});
		bp[3] = new Polygon(new Vertex[] {bv[1], bv[2], bv[5], bv[6]}, new UVVertex[] {buv[3], buv[8], buv[7]});
		bp[4] = new Polygon(new Vertex[] {bv[2], bv[3], bv[6], bv[7]}, new UVVertex[] {buv[3], buv[4], buv[8]});
		bp[5] = new Polygon(new Vertex[] {bv[0], bv[1], bv[5], bv[4]}, new UVVertex[] {buv[4], buv[9], buv[8]});
		
		ModelBox box = new ModelBox(bp);
		
		boxes.put(name, box);
		return boxes.get(name);
	}
	
	
	public Dictionary<String, ModelBox> getBoxes() {
		return boxes;
	}
	
	
	public float[] bounds;
	
	Vertex[] v;
	UVVertex[] uv;
	Polygon[] p;
	
	Dictionary<String, ModelBox> boxes = new Hashtable<String, ModelBox>();
	
	public Polygon[] mesh;

	public Texture texture;
	
	public static final ModelBase human = new ModelBiped("mob/char.png");
	public static final ModelBase zombie = new ModelBiped("mob/zombie.png");
	public static final ModelBase skel = new ModelSkeleton("mob/skel.png");
	public static final ModelBase pig = new ModelPig("mob/pig.png");
	public static final ModelBase cow = new ModelCow("mob/cow.png");
	public static final ModelBase marker = new ModelMarker("mob/marker.png");
	public static final ModelBase chest = new ModelChest("mob/chest.png");
}
