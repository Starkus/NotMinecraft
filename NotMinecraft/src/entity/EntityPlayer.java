package entity;

import org.lwjgl.input.Keyboard;
import client.Game;
import data.Bound;
import data.BoundCylinder;
import data.Camera;
import item.Inventory;
import model.ModelBase;
import model.ModelBox;
import util.Vector3d;

public class EntityPlayer extends EntityLiving{
	public EntityPlayer(float x, float y, float z) {
		super(x, y, z);
		setScale(0.9f);
		speed = 4f;
	}
	
	public boolean update() {
		
		movement(Game.getCurrentCamera());
		
		return false;
	}
	
	private void movement(Camera camera) {

		double new_x, new_y, new_z;
		
		running = false;
		
		int tick_time = Game.getTickTime();
		if (tick_time > 40) tick_time = 40;
		
		////// FORCE
		
		move(force.x, force.y, force.z - Game.gravity);
	    
		for (int i = 0; i < tick_time; i++) {
		    force.x *= .995f;
		    force.y *= .995f;
		    force.z *= .995f;
		}
		
		new_x = getPosition().x;
		new_y = getPosition().y;
	    new_z = getPosition().z - 0.2;
	    
	    
	    if (!Game.isGuiOnKeyboardControl()) {
		
		    /////// JUMP
		    
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && force.z <= 7 && this.collidesIn(new Vector3d(new_x, new_y, new_z))) {
				force.z = 23.0;
			}
			
			
			/////// MOVE
			
			int angle;
			angle = -1;
			
			if (Keyboard.isKeyDown(Keyboard.KEY_W) && Keyboard.isKeyDown(Keyboard.KEY_A)) {
				angle = 45;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_A) && Keyboard.isKeyDown(Keyboard.KEY_S)) {
				angle = 135;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_S) && Keyboard.isKeyDown(Keyboard.KEY_D)) {
				angle = -135;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_D) && Keyboard.isKeyDown(Keyboard.KEY_W)) {
				angle = -45;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				angle = 0;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				angle = 90;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				angle = 180;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				angle = -90;
			} else {
				angle = -1;
			}
			
			if (angle != -1) {
				
				float max_step = .5625f;
			    
			    running = moveWithStepping(-speed * Math.sin(Math.toRadians(getRotation().z + angle)), 0, 0, max_step);
			    running = (moveWithStepping(0, speed * Math.cos(Math.toRadians(getRotation().z + angle)), 0, max_step) || running);
			}
	    }
		
		
		ModelBox leftLeg, rightLeg, leftArm, rightArm, head;

		head = getModel().getBoxes().get("head");
		leftLeg = getModel().getBoxes().get("left_leg");
		rightLeg = getModel().getBoxes().get("right_leg");
		leftArm = getModel().getBoxes().get("left_arm");
		rightArm = getModel().getBoxes().get("right_arm");
		
		if (running) {
		
			if (direction == 0) {
				leftLeg.setRotation(0, leftLeg.getRotation()[0]+5);
				rightLeg.setRotation(0, rightLeg.getRotation()[0]-5);
				leftArm.setRotation(0, leftArm.getRotation()[0]-5);
				rightArm.setRotation(0, rightArm.getRotation()[0]+5);
			}
			if (direction == 1) {
				leftLeg.setRotation(0, leftLeg.getRotation()[0]-5);
				rightLeg.setRotation(0, rightLeg.getRotation()[0]+5);
				leftArm.setRotation(0, leftArm.getRotation()[0]+5);
				rightArm.setRotation(0, rightArm.getRotation()[0]-5);
			}

			if (leftLeg.getRotation()[0] > 45) direction = 1;
			if (leftLeg.getRotation()[0] < -45) direction = 0;
		
		} else {

			leftLeg.getRotation()[0] = 0;
			rightLeg.getRotation()[0] = 0;
			leftArm.getRotation()[0] = 0;
			rightArm.getRotation()[0] = 0;
			direction = 0;
			
		}
		
		getRotation().z = Game.getCurrentCamera().getYaw();
		
		head.setRotation(0, -Game.getCurrentCamera().getPitch());
	}
	
	public boolean isVisible() {
		return false;
	}
	
	
	public Inventory getInventory() {
		return inventory;
	}
	
	
	public ModelBase getModel() {
		return ModelBase.human;
	}
	
	public float[] getBounds() {
		float[] v = new float[6];
		v[0] = -.45f;
		v[1] = -.45f;
		v[3] = 0.45f;
		v[4] = 0.45f;
		v[5] = 1.8f;
		return v;
	}
	
	public Bound getCollisionBounds() {
		return new BoundCylinder(position, 2f, 0.5f);
	}
	
	
	Vector3d force = new Vector3d(0, 0, 0);
	
	boolean running = false;
	
	int direction = 0;
}
