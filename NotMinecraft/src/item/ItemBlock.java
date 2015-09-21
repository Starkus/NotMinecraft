package item;

import block.Block;
import util.Vector3d;
import world.World;

public class ItemBlock extends Item{

	public ItemBlock(int id) {
		
		super(id, -1);
		name = Block.blockList[id].name;
	}
	
	@Override
	public boolean onActivation(World world, Vector3d pos, Vector3d shift, Block block, int meta) {
		
		return block.onActivation(world, pos, shift, getBlock(), meta);
	}

	public int getItemIndexInTexture(int meta) {
		return getBlock().getBlockIndexInTexture(0, meta);
	}
	
	public Block getBlock() {
		return Block.blockList[id];
	}

	

}
