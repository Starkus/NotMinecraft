package block;

import data.EnumBlockItemDrawMethod;
import entity.Entity;
import entity.EntityChest;
import model.ModelBase;

public class BlockChest extends BlockEntity {

	public BlockChest(int id, int index)
	{
		super(id, index);
	}
	public void getBounds()
	{
		this.bounds = new float[] {
				(float) 1/16*1, (float) 1/16*1, (float) 1/16*0, // 1st Block
				(float) 1/16*15, (float) 1/16*15, (float) 1/16*14,
				(float) 1/16*7, (float) 1/16*0, (float) 1/16*6, // 2nd Block
				(float) 1/16*9, (float) 1/16*1, (float) 1/16*10
				};
	}
	
	@Override
	public Entity getEntity(int x, int y, int z) {
		return new EntityChest(x, y, z);
	}
	
	public boolean isEntireBlock() {
		return false;
	}
	
	public ModelBase getModel() {
		return ModelBase.chest;
	}
	
	@Override
	public EnumBlockItemDrawMethod getItemDrawMethod() {
		return EnumBlockItemDrawMethod.MODEL;
	}

}
