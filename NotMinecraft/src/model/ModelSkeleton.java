package model;

public class ModelSkeleton extends ModelBase {
	public ModelSkeleton(String tex) {
		super(tex);
		@SuppressWarnings("unused")
		ModelBox head, hair, body, left_arm, right_arm, left_leg, right_leg;
		
		head = addBox("head", 8f, 8f, 8f, -4f, 24f, -4f, 0, 0);
		head.setPivotOffset(0f, -28f, 0f);
		
		hair = addBox("hair", 8f, 8f, 8f, -4f, 24f, -4f, 32, 0);
		hair.setPivotOffset(0f, -28f, 0f);
		hair.setScale(1.1f);
		
		body = addBox("body", 8f, 12f, 4f, -4f, 12f, -2f, 16, 16);
		body.isClipEnabled(false);
		
		left_arm = addBox("left_arm", 2f, 12f, 2f, -6f, 12f, -1f, 40, 16);
		left_arm.setPivotOffset(6f, -22f, 0f);
		
		right_arm = addBox("right_arm", 2f, 12f, 2f, 4f, 12f, -1f, 40, 16);
		right_arm.setPivotOffset(-6f, -22f, 0f);
		
		left_leg = addBox("left_leg", 2f, 12f, 2f, -3f, 0f, -1f, 0, 16);
		
		right_leg = addBox("right_leg", 2f, 12f, 2f, 1f, 0f, -1f, 0, 16);
	}
	
	public void getBounds() {
		bounds = new float[] {1F/16*10, 1F/32*20, 1F/16*10};
	}
}
