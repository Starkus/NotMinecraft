package entity;

import java.util.Enumeration;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import engine.Polygon;
import engine.UVVertex;
import engine.Vertex;
import model.ModelBase;
import model.ModelBox;
import util.Vector3d;

public class RenderEntity {
	public static void render(Entity entity) {
		
		ModelBase model = entity.getModel();
		
		if (model.texture != null) model.texture.bind();
		
		Enumeration<String> keys = model.getBoxes().keys();
		for (int b = 0; b < model.getBoxes().size(); b++) {
			ModelBox box = model.getBoxes().get(keys.nextElement());
			
			if (entity.isTextured()) {
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				//GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);
			} else {
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				//GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_LINE);
			}
			
			GL11.glEnable(GL11.GL_LIGHTING);
			
			if (box.isClipEnabled()) {
				GL11.glEnable(GL11.GL_CULL_FACE);
			} else {
				GL11.glDisable(GL11.GL_CULL_FACE);
			}
			
			GL11.glBegin(GL11.GL_TRIANGLES);

			box = rotateBox(box);
			box = scaleBox(box);
			
			int pl = box.p.length;
		
			for(int pi = 0; pi < pl; pi++) {
			
				Polygon p = box.p[pi];
				Vertex[] v = p.verts;
				UVVertex[] uv = p.uv;
				
				Vector3d ps = entity.getPosition();
				float[] be = entity.getBounds();
				float[] bm = model.bounds;
				float s = entity.getScale();
				Vector3f rot = entity.getRotation();
				
				if (pi%2 == 0) {
					GL11.glNormal3f(p.normal.x, p.normal.y, p.normal.z);
				}
				
				double x, y, z;
						
				for (int vi = 0; vi < 3; vi++) {
					x = v[vi].x * bm[0] * s * (be[3]-be[0]);
					y = v[vi].y * bm[1] * s * (be[4]-be[1]);
					z = v[vi].z * bm[2] * s * (be[5]-be[2]);
					float[] xyz = rotateVert(x, y, z, rot.x, rot.y, rot.z);
					x = xyz[0] + ps.x;
					y = xyz[1] + ps.y;
					z = xyz[2] + ps.z;

					x += (be[0] - (1f - be[3])) / 2;
					y += (be[1] - (1f - be[4])) / 2;
					z += (be[2] - (1f - be[5])) / 2;
			
					GL11.glTexCoord2f(uv[vi].u, uv[vi].v);
					GL11.glNormal3f(p.normal.x, p.normal.y, p.normal.z);
					GL11.glVertex3f((float) x, (float) y, (float) z);
				}
			}
			GL11.glEnd();
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glPolygonMode(GL11.GL_FRONT, GL11.GL_FILL);
		}
	}
	
	static float[] rotateVert(double i, double j, double k, double degx, double degy, double degz) {
		double radx = Math.toRadians(degx+90);
		double rady = Math.toRadians(degy);
		double radz = Math.toRadians(degz);
		float x, y, z;
		float x1, y1, z1;
		
		// X axis
		y1 = (float) (Math.sin(radx)*j + Math.cos(radx)*k);
		z1 = (float) (Math.cos(radx)*j - Math.sin(radx)*k);
		// Y axis
		x1 = (float) (Math.cos(rady)*i + Math.sin(rady)*z1);
		z = (float) (Math.sin(rady)*i - Math.cos(rady)*z1);
		// Z axis
		x = (float) (Math.cos(radz)*x1 - Math.sin(radz)*y1);
		y = (float) (Math.sin(radz)*x1 + Math.cos(radz)*y1);
		
		return new float[] {x, y, z};
	}
	static ModelBox rotateBox(ModelBox box) {
		
		Polygon[] p = box.p;
		float[] rot = box.getRotation();
		float[] pivot = box.getPivotOffset();
		float scale = box.getScale();
		
		Polygon[] np = new Polygon[box.p.length];
		
		int pl = box.p.length;
		
		for(int pi = 0; pi < pl; pi++) {
		
			Polygon p1 = p[pi];
			Vertex[] v = p1.verts;
			
			Vertex[] nv = new Vertex[v.length];
			
			float x, y, z;
			
			for (int vi = 0; vi < 3; vi++) {
				x = v[vi].x;
				y = v[vi].y;
				z = v[vi].z;
				float[] xyz = rotateVert(x + pivot[0], y + pivot[1], z + pivot[2], rot[0], rot[1], rot[2]);
				x = xyz[0] - pivot[0];
				y = xyz[1] - pivot[1];
				z = xyz[2] - pivot[2];

				nv[vi] = new Vertex(x, y, z);
			}
			np[pi] = new Polygon(nv, p1.uv, p1.normal);
		}
		
		ModelBox nbox = new ModelBox(np).setRotation(rot[0], rot[1], rot[2]).setPivotOffset(pivot[0]*10, pivot[1]*10, pivot[2]*10).setScale(scale);
		return nbox;
	}
	static ModelBox scaleBox(ModelBox box) {
		
		Polygon[] p = box.p;
		float[] rot = box.getRotation();
		float[] pivot = box.getPivotOffset();
		float scale = box.getScale();
		
		Polygon[] new_polys = new Polygon[box.p.length];
		
		int pl = box.p.length;
		
		for(int pi = 0; pi < pl; pi++) {
		
			Polygon p1 = p[pi];
			Vertex[] verts = p1.verts;
			
			Vertex[] new_verts = new Vertex[verts.length];
			
			float x, y, z;
			
			for (int vi = 0; vi < 3; vi++) {
				x = (verts[vi].x + pivot[0]) * scale;
				y = (verts[vi].y + pivot[1]) * scale;
				z = (verts[vi].z + pivot[2]) * scale;
				x = x - pivot[0];
				y = y - pivot[1];
				z = z - pivot[2];

				new_verts[vi] = new Vertex(x, y, z);
			}
			new_polys[pi] = new Polygon(new_verts, p1.uv, p1.normal);
		}
		
		return new ModelBox(new_polys).setRotation(rot[0], rot[1], rot[2]).setPivotOffset(pivot[0], pivot[1], pivot[2]);
	}
}
