package block;

public class BlockAir extends Block{

	public BlockAir(int id, int index) {
		super(id, index);
	}
	public void getVert() {
		return;
	}
	public void getUV() {
		return;
	}
	public void buildPolygons() {
		return;
	}
	
	public boolean isVisible() {
		return false;
	}
	public boolean isEntireBlock() {
		return false;
	}
	public boolean hasCollision() {
		return false;
	}
	
}
