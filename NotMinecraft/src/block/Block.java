package block;

import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

import data.EnumBlockItemDrawMethod;
import engine.*;
import entity.Entity;
import item.ItemBlock;
import item.ItemStack;
import model.ModelBase;
import util.Vector3d;
import world.World;

public class Block {

	public Block(int id, int index)
	{
		texture_index = (short) index;
		this.id = (short) id;
		
		bounds = new float[] {0f, 0f, 0f, 1f, 1f, 1f};
		
		//buildVert(0);
		//buildUV(0);
		//buildPolygons();
		
		if(blockList[id] == null) blockList[id] = this;
		else System.out.println(new StringBuilder().append("Block ID ").append(id).append(" has been used"));
	}
	
	public float[] getBounds(int meta) {
		return bounds;
	}
	
	public boolean onActivation(World world, Vector3d pos, Vector3d shift, Block block, int meta) {
		
		int x = (int) (pos.x + shift.x);
		int y = (int) (pos.y + shift.y);
		int z = (int) (pos.z + shift.z);
		
		if (block != null && block.getClass() == BlockSlab.class) {
			
			boolean upper = (pos.z % 1f > 0.5f && shift.z == 0) || shift.z < 0;
			
			if (!upper && world.setBlockAndMetadataOn(x, y, z, block, 0, air)) {
				//world.updateVBOs(x, y, z);
				return true;
			}
			if (upper && world.setBlockAndMetadataOn(x, y, z, block, 1, air)) {
				//world.updateVBOs(x, y, z);
				return true;
			}
			
		}
		
		if (block != null && world.setBlockAndMetadataOn(x, y, z, block, meta, air)) {
			
			//world.updateVBOs(x, y, z);
			return true;
		}
		
		return false;
		
	}
	
	public ItemStack getItemDrop(int meta) {
		ItemBlock i = new ItemBlock(id);
		return new ItemStack(i, 1, meta);
	}
	public int getBlockIndexInTexture(int f, int meta) {
		return texture_index;
	}
	
	public Mesh getMesh(int meta) {
		buildVert(meta);
		buildUV(meta);
		buildPolygons();
		return new Mesh(p);
	}
	
	public float[] getColor() {
		return new float[] {1f, 1f, 1f, 1f};
	}
	
	public Entity getEntity(int x, int y, int z) {
		return null;
	}
	
	public EnumBlockItemDrawMethod getItemDrawMethod() {
		return EnumBlockItemDrawMethod.BLOCK;
	}
	
	public boolean isVisible() {
		return true;
	}
	public boolean isEntireBlock() {
		return entire;
	}
	public boolean isTransparent() {
		return transp;
	}
	public boolean isLit() {
		return lit;
	}
	public boolean hasAlpha() {
		return alpha;
	}
	public boolean isFluid() {
		return fluid;
	}
	public boolean hasCollision() {
		return collision;
	}
	
	public float getLightStop() {
		return light_stop;
	}
	
	public Block isEntireBlock(boolean flag) {
		entire = flag;
		return this;
	}
	public Block isTransparent(boolean flag) {
		transp = flag;
		return this;
	}
	public Block isLit(boolean flag) {
		lit = flag;
		return this;
	}
	public Block hasAlpha(boolean flag) {
		alpha = flag;
		return this;
	}
	public Block isFluid(boolean flag) {
		fluid = flag;
		return this;
	}
	public Block hasCollision(boolean flag) {
		collision = flag;
		return this;
	}
	public Block setLightStop(float f) {
		light_stop = f;
		return this;
	}
	public Block setName(String n) {
		name = n;
		return this;
	}
	public Block setBounds(float x0, float y0, float z0, float x1, float y1, float z1) {
		bounds = new float[] {
			x0,
			y0,
			z0,
			x1,
			y1,
			z1
		};
		buildVert(0);
		buildUV(0);
		buildPolygons();
		return this;
	}
	
	public void onLeftClick() {
		return;
	}
	public void onRightClick() {
		return;
	}
	public void onUpdate(World world, int x, int y, int z) {
		return;
	}
	public boolean onTick(World world, int x, int y, int z) {
		return false;
	}
	public void onRender(World world, GameObject ob) {
		return;
	}
	
	public ModelBase getModel() { return null; }
	
	

	
	public void buildVert(int meta)
	{	
		float x0 = getBounds(meta)[0];
		float y0 = getBounds(meta)[1];
		float z0 = getBounds(meta)[2];
		float x1 = getBounds(meta)[3];
		float y1 = getBounds(meta)[4];
		float z1 = getBounds(meta)[5];
		
		v = new Vertex[8];
		v[0] = new Vertex(x1, y0, z0);
		v[1] = new Vertex(x0, y0, z0);
		v[2] = new Vertex(x0, y0, z1);
		v[3] = new Vertex(x1, y0, z1);
		v[4] = new Vertex(x1, y1, z0);
		v[5] = new Vertex(x0, y1, z0);
		v[6] = new Vertex(x0, y1, z1);
		v[7] = new Vertex(x1, y1, z1);
	}
	public void buildUV(int meta)
	{
		
		uv = new UVVertex[6*4];
		
		float x0 = getBounds(meta)[0];
		float y0 = getBounds(meta)[1];
		float z0 = getBounds(meta)[2];
		float x1 = getBounds(meta)[3];
		float y1 = getBounds(meta)[4];
		float z1 = getBounds(meta)[5];
		
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
	public void buildPolygons()
	{	
		p = new Polygon[12];

		p[0] = new Polygon(new Vertex[] {v[0], v[1], v[4]}, new UVVertex[] {uv[22], uv[23], uv[20]}, new Vector3f( 0f,  0f, -1f));
		p[1] = new Polygon(new Vertex[] {v[1], v[5], v[4]}, new UVVertex[] {uv[23], uv[21], uv[20]}, new Vector3f( 0f,  0f, -1f));
		p[2] = new Polygon(new Vertex[] {v[1], v[2], v[5]}, new UVVertex[] {uv[7],  uv[5],  uv[6]},  new Vector3f(-1f,  0f,  0f));
		p[3] = new Polygon(new Vertex[] {v[2], v[6], v[5]}, new UVVertex[] {uv[5],  uv[4],  uv[6]},  new Vector3f(-1f,  0f,  0f));
		p[4] = new Polygon(new Vertex[] {v[6], v[2], v[3]}, new UVVertex[] {uv[19], uv[17], uv[16]}, new Vector3f( 0f,  0f,  1f));
		p[5] = new Polygon(new Vertex[] {v[7], v[6], v[3]}, new UVVertex[] {uv[18], uv[19], uv[16]}, new Vector3f( 0f,  0f,  1f));
		p[6] = new Polygon(new Vertex[] {v[3], v[0], v[7]}, new UVVertex[] {uv[12], uv[14], uv[13]}, new Vector3f( 1f,  0f,  0f));
		p[7] = new Polygon(new Vertex[] {v[0], v[4], v[7]}, new UVVertex[] {uv[14], uv[15], uv[13]}, new Vector3f( 1f,  0f,  0f));
		p[8] = new Polygon(new Vertex[] {v[4], v[5], v[7]}, new UVVertex[] {uv[10],  uv[11],  uv[8]},  new Vector3f( 0f,  1f,  0f));
		p[9] = new Polygon(new Vertex[] {v[5], v[6], v[7]}, new UVVertex[] {uv[11],  uv[9],  uv[8]},  new Vector3f( 0f,  1f,  0f));
		p[10]= new Polygon(new Vertex[] {v[3], v[2], v[0]}, new UVVertex[] {uv[1],  uv[0],  uv[3]},  new Vector3f( 0f, -1f,  0f));
		p[11]= new Polygon(new Vertex[] {v[2], v[1], v[0]}, new UVVertex[] {uv[0],  uv[2],  uv[3]},  new Vector3f( 0f, -1f,  0f));
		polygons = p;
	}
	
	
	@Override
	public int hashCode() {
		return id;
	}
	
	
	public short id;
	public String name = "Unknown";
	
	Polygon polygons[];
	public Texture texture;
	
	public short texture_index;
	float[] bounds;
	//public int meta;
	
	boolean entire = true;
	boolean transp = false;
	boolean lit = true;
	boolean alpha = false;
	boolean fluid = false;
	boolean collision = true;
	
	float light_stop = 1f;
	
	protected Vertex[] v;
	protected UVVertex[] uv;
	protected Polygon[] p;
	protected Mesh[] m;
	
	
	public static Block[] blockList = new Block[256];
	
	public static final Block air = new BlockAir(0, 0).setName("Air").setBounds(0f, 0f, 0f, 0f, 0f, 0f);
	public static final Block stone = new BlockStone(1, 3).setName("Stone");
	public static final Block grass = new BlockGrass(2, 0).setName("Grass");
	public static final Block dirt = new Block(3, 2).setName("Dirt");
	public static final Block bedrock = new Block(4, 20).setName("Bedrock");
	
	public static final Block water = new BlockFluid(70, 95).setName("Water").setLightStop(0f);
	public static final Block ice = new Block(72, 53).setName("Ice").hasAlpha(true);
	
	public static final Block coalOre = new BlockOre(5, 32).setName("Coal Ore");
	public static final Block ironOre = new BlockOre(6, 33).setName("Iron Ore");
	public static final Block copperOre = new BlockOre(7, 34).setName("Copper Ore");
	public static final Block silverOre = new BlockOre(8, 35).setName("Silver Ore");
	public static final Block emeraldOre = new BlockOre(9, 36).setName("Emerald Ore");
	public static final Block diamondOre = new BlockOre(10, 37).setName("Diamond Ore");
	
	public static final Block planks = new Block(11, 45).setName("Wooden Planks");
	public static final Block cobble = new Block(12, 4).setName("Cobblestone");
	public static final Block brick = new Block(13, 14).setName("Bricks");
	public static final Block glass = new Block(14, 54).isTransparent(true).setName("Glass");
	public static final Block chest = new BlockChest(15, 7).setName("Chest");
	public static final Block workbench = new BlockWorkbench(16, 0).setName("Workbench");
	public static final Block furnace = new BlockFurnace(17, 69).setName("Furnace");
	public static final Block table = new BlockTable(18, 85).setName("Wooden Table");
	
	public static final Block cobweb = new BlockCross(20, 30);
	public static final Block tallGrass = new BlockTallGrass(21, 46).setBounds(1f/16, 1f/16, 0f, 15f/16, 15f/16, 14f/16);
	public static final Block tallRoses = new BlockCross(22, 47).setBounds(1f/16, 1f/16, 0f, 15f/16, 15f/16, 14f/16);
	public static final Block tallFlower= new BlockCross(23, 31).setBounds(1f/16, 1f/16, 0f, 15f/16, 15f/16, 14f/16);
	public static final Block sapling = new BlockSapling(24, 0);
	
	public static final Block pumpkin = new BlockPumpkin(30, 0).setName("Pumpkin");
	public static final Block melon = new BlockPumpkin(31, 0).setName("Watermelon");
	public static final Block wood = new BlockLog(32, 0).setName("Wood");
	public static final Block woodBirch = new BlockLog(33, 0).setName("Wood");
	public static final Block woodPine = new BlockLog(34, 0).setName("Wood");
	public static final Block leaves = new BlockLeaves(35, 28).setName("Leaves");
	public static final Block leavesBirch = new BlockLeaves(36, 28+16).setName("Leaves");
	public static final Block leavesPine = new BlockLeaves(37, 28+32).setName("Leaves");
	
	public static final Block torch = new BlockCactus(40, 16).setName("Torch").setBounds(7f/16, 7f/16, 0f, 9f/16, 9f/16, 10f/16).isEntireBlock(false).isLit(false).hasCollision(false);
	public static final Block redTorch = new Block(41, 17).setBounds(7f/16, 7f/16, 0f, 9f/16, 9f/16, 10f/16).isEntireBlock(false).isLit(false).hasCollision(false);
	
	public static final Block sand = new Block(45, 64).setName("Sand");
	public static final Block cactus = new BlockCactus(47, 65).setName("Cactus").isEntireBlock(false);
	public static final Block deadPlant = new BlockTallGrass(48, 78).setName("Dead plant");
	
	public static final Block snowyGrass = new BlockGrassSnowy(50, 0).setName("Snowy Grass");

	public static final Block slab = new BlockSlab(60, -1).setName("Slab").isEntireBlock(false);
	public static final Block doubleSlab = new BlockSlab(61, -1).setName("Double Slab");
	
	public static final Block snow = new BlockSnow(65, 18).setName("Snow").isTransparent(true).hasCollision(false);
	public static final Block flint = new BlockFlint(67, 3).setName("Flint").hasCollision(true);
	
	public static final Block hellstone = new Block(69, 90).setName("Hellstone");
	
	public static final Block unknown = new Block(255, 255);
}