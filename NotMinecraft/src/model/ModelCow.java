package model;

public class ModelCow extends ModelBase {
	public ModelCow(String tex) {
		super(tex);
		@SuppressWarnings("unused")
		ModelBox head, body, left_arm, right_arm, left_leg, right_leg, left_horn, right_horn, udders;
		
		head = addBox("head", 8f, 8f, 6f, -4f, 13f, -13f, 0, 0);
		
		body = addBox("body", 10f, 16f, 12f, -5f, 6f, -6f, 16, 4);
		body.setPivotOffset(0f, -14f, 0f);
		body.setRotation(0, 90);
		
		left_arm = addBox("left_arm", 4f, 8f, 4f, -5f, 0f, 4f, 0, 16);
		
		right_arm = addBox("right_arm", 4f, 8f, 4f, 1f, 0f, 4f, 0, 16);
		
		left_leg = addBox("left_leg", 4f, 8f, 4f, -5f, 0f, -8f, 0, 16);
		
		right_leg = addBox("right_leg", 4f, 8f, 4f, 1f, 0f, -8f, 0, 16);
		
		left_horn = addBox("left_horn", 1f, 2f, 1f, -3f, 21f, -12f, 0, 0);
		
		left_horn = addBox("right_horn", 1f, 2f, 1f, 2f, 21f, -12f, 0, 0);
		
		udders = addBox("udders", 4f, 1f, 4f, -2f, 7f, 0f, 48, 0);
	}
	
	public void getBounds() {
		bounds = new float[] {1F/16*10, 1F/32*20, 1F/16*10};
	}
}
