package entity;

import org.lwjgl.util.vector.Vector3f;

import block.Block;
import client.Game;
import data.Bound;
import data.WorldSettings;
import item.Inventory;
import model.ModelBase;
import util.Vector3d;

public abstract class Entity {
	
	public Entity(float x, float y, float z) {
		setPosition(x, y, z);
	}

	public void setPosition(Vector3d v) {
		position.x = v.x;
		position.y = v.y;
		position.z = v.z;
	}
	
	public void setBounds(float[] v) {
		bounds[0] = v[0];
		bounds[1] = v[1];
		bounds[2] = v[2];
		bounds[3] = v[3];
		bounds[4] = v[4];
		bounds[5] = v[5];
	}
	
	public Vector3d getPosition() {
		return position;
	}
	public Vector3f getRotation() {
		return rotation;
	}
	public float[] getBounds() {
		return bounds;
	}
	
	public void setScale(float j) {
		scale = j;
	}
	public float getScale() {
		return scale;
	}
	
	public void setPosition(double x, double y, double z) {
		position = new Vector3d(x, y, z);
	}
	public void setRotation(float x, float y, float z) {
		rotation = new Vector3f(x, y, z);
	}
	
	public boolean isVisible() {
		return true;
	}
	
	public boolean collidesIn(Vector3d pos) {
		return collidesIn(pos.x, pos.y, pos.z);
	}
	public boolean collidesIn(double x, double y, double z) {
		
		float[] b = getBounds();
		
		if ((int) (x - b[3] / 2) < 0) return false;
		if ((int) (x + b[3] / 2) >= WorldSettings.x) return false;
		if ((int) (y - b[4] / 2) < 0) return false;
		if ((int) (y + b[4] / 2) >= WorldSettings.y) return false;
		if ((int) (z - b[5] / 2) < 0) return false;
		if ((int) (z + b[5] / 2) >= WorldSettings.z) return false;
		
		float step = .05f; // Presicion
		
		
		for (double x1 = x+b[0]; x1 < x+b[3]; x1+=step) {
			for (double y1 = y+b[1]; y1 < y+b[4]; y1+=step) {
				for (double z1 = z+b[2]; z1 < z+b[5]; z1+=step) {
					Block picked_block = Game.getCurrentWorld().getBlock((int) (x1), (int) (y1), (int) (z1));
					int meta = Game.getCurrentWorld().getBlockMetadata((int) (x1), (int) (y1), (int) (z1));
					if (picked_block != null && picked_block.hasCollision() == true) {
						
						float[] bb = picked_block.getBounds(meta);
						if (picked_block.id != 0 &&
								x1%1 > bb[0] &&
								y1%1 > bb[1] &&
								z1%1 > bb[2] &&
								x1%1 < bb[3] &&
								y1%1 < bb[4] &&
								z1%1 < bb[5])
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	
	public boolean move(double x, double y, double z) {

		int tick_time = Game.getTickTime();
		double tick_mult = tick_time / 1000f;

		double new_x = position.x + x * tick_mult;
		double new_y = position.y + y * tick_mult;
		double new_z = position.z + z * tick_mult;
		
		if (!this.collidesIn(new_x, new_y, new_z)) {
			position.x = new_x;
			position.y = new_y;
			position.z = new_z;
			return true;
		}
		
		return false;
	}
	
	public boolean moveWithStepping(double x, double y, double z, float max_step) {

		int tick_time = Game.getTickTime();
		double tick_mult = tick_time / 1000f;
		
		for (double z1 = 0; z1 <= max_step; z1 += 0.03125f) {
			if (move(x, y, z1/tick_mult))
				return true;
		}
		return false;
	}
	
	
	public boolean isTextured() {
		return textured;
	}
	public Entity isTextured(boolean flag) {
		textured = flag;
		return this;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	
	public Bound getCollisionBounds() {
		return new Bound();
	}
	
	public abstract ModelBase getModel();
	
	public boolean update() {
		return false;
	}
	public boolean update(float f, int i, Block b) {
		return false;
	}

	boolean textured = true;

	
	Inventory inventory = new Inventory(36);
	
	protected Vector3d position = new Vector3d();
	private float scale;
	private Vector3f rotation = new Vector3f();
	private float[] bounds = new float[6];
}
