package model;

public class ModelChest extends ModelBase {

	public ModelChest(String tex) {
		super(tex);
		@SuppressWarnings("unused")
		ModelBox chest, top, locker;
		
		chest = addBox("chest", 14f, 14f, 9f, 1f, 1f, 0f, 0, 32);
		
		top = addBox("top", 14f, 14f, 5f, 1f, 1f, 9f, 0, 0);
		top.setPivotOffset(-1f, -1f, -9f);
		
		locker = addBox("locker", 2f, 1f, 4f, 7f, 15f, 7f, 48, 0);
		locker.setPivotOffset(-1f, -1f, -9f);
	}
	
	public void getBounds() {
		bounds = new float[] {1F/16*10, 1F/16*10, 1F/16*10};
	}

}
