package engine;

import org.newdawn.slick.opengl.Texture;

import block.Block;
import entity.Entity;

public class GameObject {
	public GameObject(Block block) {
		this.block = block.id;
		this.meta = 0;
		this.color = block.getColor();
	}
	public GameObject(int block) {
		this.block = block;
		this.meta = 0;
		this.color = Block.blockList[block].getColor();
	}
	public GameObject(Block block, int meta) {
		this.block = block.id;
		this.meta = meta;
		this.color = block.getColor();
	}
	public GameObject(int block, int meta) {
		this.block = block;
		this.meta = meta;
		this.color = Block.blockList[block].getColor();
	}
	
	public GameObject setPosition(float x, float y, float z) {
		position = new float[]{x, y, z};
		return this;
	}
	
	public boolean isVisible() {
		for (int i = 0; i < culling.length; i++) {
			if (culling[i]) {
				return true;
			}
		}
		return false;
	}
	public float[] getColor() {
		return Block.blockList[block].getColor();
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Entity getEntity() {
		return entity;
	}
	
	public int block;
	public int meta;
	public float[] color = new float[] {1f, 1f, 1f, 1f};
	public Texture texture;
	public float[] position;
	public boolean[] culling = new boolean[] {
		false, false,
		false, false,
		false, false,
		false, false,
		false, false,
		false, false
	};
	
	private Entity entity;
}
