package engine;


public class Mesh {
	
	public Mesh(Polygon[] polygon) {//, boolean[] culling) {
		this.polygons = polygon;
		//this.culling = culling;
	}
	
	public Polygon[] polygons = new Polygon[128];
	//public boolean[] culling;
	
}
