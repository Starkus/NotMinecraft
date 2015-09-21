package block;

import entity.Entity;
import model.ModelBase;

public class BlockEntity extends Block {

	public BlockEntity(int id, int index) {
		super(id, index);
	}
	
	@Override
	public void buildPolygons() {}
	
	@Override
	public void buildVert(int meta) {}
	
	@Override
	public void buildUV(int meta) {}

	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Entity getEntity(int x, int y, int z) {
		return entity;
	}
	
	public ModelBase getModel() {
		return entity.getModel();
	}
	
	
	private Entity entity;
}
