package entity;

import org.lwjgl.input.Keyboard;

import model.ModelBase;
import model.ModelBox;

public class EntityCow extends EntityLiving{
	public EntityCow(float i, float j, float k) {
		super(i, j, k);
		
		setScale(0.9f);
	}
	
	public boolean update() {
		ModelBox body;
		body = getModel().getBoxes().get("body");
		if (Keyboard.isKeyDown(Keyboard.KEY_C)) body.setRotation(0, body.getRotation()[0]+10);
		return true;
	}
	
	public ModelBase getModel() {
		return ModelBase.cow;
	}
}
