package entity;

import model.ModelBase;

public class EntitySkeleton extends Entity{
	public EntitySkeleton(float i, float j, float k) {
		super(i, j, k);
		setScale(0.9f);
		getModel().getBoxes().get("left_arm").setRotation(90f, 5f, 5f);
		getModel().getBoxes().get("right_arm").setRotation(90f, -5f, -5f);
	}
	
	public ModelBase getModel() {
		return ModelBase.skel;
	}
}
