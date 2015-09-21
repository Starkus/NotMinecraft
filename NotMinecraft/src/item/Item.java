package item;

import org.newdawn.slick.opengl.Texture;

import block.Block;
import engine.UVVertex;
import util.Vector3d;
import world.World;

public class Item {

	public Item(int id, int index)
	{
		texture_index = (short) index;
		this.id = (short) id;
		
		if(this.getClass() != ItemBlock.class) {
			if (itemList[id] == null) itemList[id] = this;
			else System.out.println(new StringBuilder().append("Item ID ").append(id).append(" has been used"));
		}
	}
	
	public boolean onActivation(World world, Vector3d pos, Vector3d shift, Block block, int meta) {
		
		return false;
	}

	public void buildUV(int meta)
	{
		
		uv = new UVVertex[4];
		
		int index;
		
		int I, J;
		float i, j;
		
		index = getItemIndexInTexture(meta);
	
		I = (int) (index) % 16;
		i = (float) I * 0.0625F;
		J = (int) (index) / 16;
		j = (float) J * 0.0625F;
		
		uv[0] = new UVVertex(i, j);
		uv[1] = new UVVertex(i+0.0625f, j);
		uv[2] = new UVVertex(i, j+0.0625f);
		uv[3] = new UVVertex(i+0.0625f, j+0.0625f);
	}
	
	
	public Item setName(String s) {
		name = s;
		return this;
	}
	
	
	public int getItemIndexInTexture(int meta) {
		return texture_index;
	}
	
	public boolean equalTo(Item item) {
		if (this.getClass() == ItemBlock.class && item.getClass() == ItemBlock.class)
			return this.id == item.id;
		if (this.getClass() != ItemBlock.class && item.getClass() != ItemBlock.class)
			return this.id == item.id;
		
		return false;
	}


	private UVVertex[] uv;
	
	public short id;
	public String name;
	
	public Texture texture;
	public short texture_index;
	
	
	public static Item[] itemList = new Item[256];
	
	
	public static final Item nothing = new Item(0, 0).setName("");
	
	public static final Item flint = new Item(1, 128).setName("Flint");
	
	public static final Item bucket = new ItemBucket(2, -1).setName("Iron Bucket");
	
}
