package model;

public class ModelPig extends ModelBase {
	public ModelPig(String tex) {
		super(tex);
		@SuppressWarnings("unused")
		ModelBox head, body, left_arm, right_arm, left_leg, right_leg;
		
		head = addBox("head", 8f, 8f, 8f, -4f, 8f, -13f, 0, 0);
		
		body = addBox("body", 10f, 8f, 12f, -5f, 6f, -6f, 20, 12);
		
		left_arm = addBox("left_arm", 4f, 6f, 4f, -5f, 0f, 2f, 0, 22);
		
		right_arm = addBox("right_arm", 4f, 6f, 4f, 1f, 0f, 2f, 0, 22);
		
		left_leg = addBox("left_leg", 4f, 6f, 4f, -5f, 0f, -6f, 0, 22);
		
		right_leg = addBox("right_leg", 4f, 6f, 4f, 1f, 0f, -6f, 0, 22);
	}
	
	public void getBounds() {
		bounds = new float[] {1F/16*10, 1F/32*20, 1F/16*10};
	}
}
