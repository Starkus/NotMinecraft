package entity;

import model.ModelBase;

public class EntityPig extends Entity{
	public EntityPig(float i, float j, float k) {
		super(i, j, k);
		
		setScale(0.9f);
	}
	
	public ModelBase getModel() {
		return ModelBase.pig;
	}
}
