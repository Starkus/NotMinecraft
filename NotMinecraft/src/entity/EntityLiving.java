package entity;

import model.ModelBase;

public abstract class EntityLiving extends Entity{
	public EntityLiving(float x, float y, float z) {
		super(x, y, z);
	}

	public abstract ModelBase getModel();
	
	protected float speed = 1f;

}
