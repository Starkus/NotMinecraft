package entity;

import model.ModelBase;
import model.ModelBox;
import model.ModelChest;

public class EntityChest extends Entity {

	public EntityChest(float i, float j, float k) {
		super(i, j, k);
		setScale(1f);
		float v[] = {0f, 0f, 0f, 1f, 1f, 1f};
		setBounds(v);
	}
	
	@Override
	public boolean update() {

		ModelBox top, locker;
		
		top = getModel().getBoxes().get("top");
		locker = getModel().getBoxes().get("locker");

		if (top.getRotation()[0] > 65) speed = -1;
		if (top.getRotation()[0] < 0) speed = 1;

		top.setRotation(0, top.getRotation()[0]+speed);
		locker.setRotation(0, locker.getRotation()[0]+speed);
		
		return true;
	}

	@Override
	public ModelBase getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	private int speed = 2;
	
	ModelBase model = new ModelChest("mob/chest.png");
}
