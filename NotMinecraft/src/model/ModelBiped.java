package model;

public class ModelBiped extends ModelBase {
	public ModelBiped(String tex) {
		super(tex);
		@SuppressWarnings("unused")
		ModelBox head, body, hair, left_arm, right_arm, left_leg, right_leg;
		
		head = addBox("head", 8f, 8f, 8f, -4f, -4f, 24f, 0, 0);
		head.setPivotOffset(0f, 0f, -28f);
		
		hair = addBox("hair", 8f, 8f, 8f, -4f, -4f, 24f, 32, 0);
		hair.setPivotOffset(0f, 0f, -28f);
		hair.setScale(1.1f);
		
		body = addBox("body", 8f, 4f, 12f, -4f, -2f, 12f, 16, 16);
		
		left_arm = addBox("left_arm", 4f, 4f, 12f, -8f, -2f, 12f, 40, 16);
		left_arm.setPivotOffset(6f, 0f, -22f);
		
		right_arm = addBox("right_arm", 4f, 4f, 12f, 4f, -2f, 12f, 40, 16);
		right_arm.setPivotOffset(-6f, 0f, -22f);
		
		left_leg = addBox("left_leg", 4f, 4f, 12f, -4f, -2f, 0f, 0, 16);
		left_leg.setPivotOffset(0f, 0f, -12f);
		
		right_leg = addBox("right_leg", 4f, 4f, 12f, 0f, -2f, 0f, 0, 16);
		right_leg.setPivotOffset(0f, 0f, -12f);
	}
	
	public void getBounds() {
		bounds = new float[] {1F/16*10, 1F/16*10, 1F/16*10};
	}
}
