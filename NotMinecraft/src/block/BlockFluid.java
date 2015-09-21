package block;

import engine.UVVertex;
import world.World;

public class BlockFluid extends Block {

	public BlockFluid(int id, int index) {
		super(id, index);
	}
	
	@Override
	public float[] getBounds(int meta) {
		if (meta == 0) {
			return new float[] {0f, 0f, 0f, 1f, 1f, 7/8f};
		} else {
			return new float[] {0f, 0f, -1/8f, 1f, 1f, 7/8f};
		}
	}
	
	@Override
	public void onUpdate(World world, int x, int y, int z) {
		super.onUpdate(world, x, y, z);
		
		Block b = world.getBlock(x, y, z-1);
		if (b != null && b.getClass() == this.getClass()) {
			world.setMetadata(x, y, z, 1);
		} else {
			world.setMetadata(x, y, z, 0);
		}
	}
	
	@Override
	public boolean onTick(World world, int x, int y, int z) {
		
		Block b = world.getBlock(x, y, z-1);
		if (b == Block.air  || b == Block.water) {
			return world.setBlockOn(x, y, z-1, Block.water, Block.air);
		}
		boolean f = false;
		f = world.setBlockOn(x+1, y, z, Block.water, Block.air) || f;
		f = world.setBlockOn(x-1, y, z, Block.water, Block.air) || f;
		f = world.setBlockOn(x, y+1, z, Block.water, Block.air) || f;
		f = world.setBlockOn(x, y-1, z, Block.water, Block.air) || f;
		//if (f) Game.getVBO(x/16, y/16).setForUpdate();
		return f;
	}
	
	@Override
	public boolean isFluid() {
		return true;
	}
	@Override
	public boolean hasAlpha() {
		return true;
	}
	@Override
	public boolean hasCollision() {
		return false;
	}
	
	public void buildUV(int meta)
	{
		
		uv = new UVVertex[6*4];
		
		float x0 = getBounds(meta)[0];
		float y0 = getBounds(meta)[1];
		float z0 = getBounds(meta)[2]+1/8f;
		float x1 = getBounds(meta)[3];
		float y1 = getBounds(meta)[4];
		float z1 = getBounds(meta)[5]+1/8f;
		
		int index;
		
		int I, J;
		float i, j;
		
		for (int f = 0; f < uv.length/4; f++) {
			index = getBlockIndexInTexture(f, meta);
		
			I = (int) (index) % 16;
			i = (float) I / 16f;
			J = (int) (index) / 16;
			j = (float) J / 16f;
		
			int shift = f*4;
			
			if (f != 4 && f != 5) {
				uv[0+shift] = new UVVertex((0f+x0)/16+i, (1f-z1)/16+j);
				uv[1+shift] = new UVVertex((0f+x1)/16+i, (1f-z1)/16+j);
				uv[2+shift] = new UVVertex((0f+x0)/16+i, (1f-z0)/16+j);
				uv[3+shift] = new UVVertex((0f+x1)/16+i, (1f-z0)/16+j);
			} else {
				uv[0+shift] = new UVVertex((0f+x0)/16+i, (1f-y1)/16+j);
				uv[1+shift] = new UVVertex((0f+x1)/16+i, (1f-y1)/16+j);
				uv[2+shift] = new UVVertex((0f+x0)/16+i, (1f-y0)/16+j);
				uv[3+shift] = new UVVertex((0f+x1)/16+i, (1f-y0)/16+j);		
			}
		}
	}

}
