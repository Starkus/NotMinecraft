package client;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import block.Block;
import data.Camera;
import data.CameraFirstPerson;
import data.LightSource;
import data.Material;
import data.WorldSettings;
import engine.DrawingTools;
import engine.GameObject;
import engine.OpenGLWorldSettings;
import engine.VBO;
import entity.Entity;
import entity.EntityMarker;
import entity.EntityPlayer;
import entity.RenderEntity;
import gui.GuiHandler;
import gui.Hud;
import item.Item;
import item.ItemBlock;
import item.ItemStack;
import util.RNG;
import util.TextureLoad;
import util.Vector3d;
import world.World;
import world.WorldGenerator;

public class Game {

	public Game(String seed_) {
		
		seed = seed_;
		
		try {
			Display.setDisplayMode(new DisplayMode(screen_width, screen_height));
			Display.setVSyncEnabled(true);
			Display.setTitle("Definitely not Minecraft");
			Display.setFullscreen(true);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		init();
		while(!Display.isCloseRequested() &&
				!quit_request &&
				!Keyboard.isKeyDown(Keyboard.KEY_F2)) {
			display();
			Display.update();
		}
		Display.destroy();
		
	}
	
	protected void init() {
		
		OpenGLWorldSettings.apply(screen_width, screen_height);
		
		
		
		if (seed == "") {
			seed = RNG.nextString(new Random(), 8);
			System.out.println(seed);
		}
		
		

		DrawingTools.DrawIn2D(screen_width, screen_height);

		
		
		player_camera = new CameraFirstPerson(0.5F, 0.5F, 70F);
		
		world_texture = TextureLoad.LoadPNG("terrain.png");
		world_texture.hasAlpha();
		
		// Initialize all VBOs
		VBO_array = new VBO[WorldSettings.x/16][WorldSettings.y/16];
		for (int xvbo = 0; xvbo < WorldSettings.x/16; xvbo++) {
			for (int yvbo = 0; yvbo < WorldSettings.y/16; yvbo++) {
				VBO_array[xvbo][yvbo] = new VBO(world_texture, world, xvbo, yvbo);
			}
		}
		
		world = WorldGenerator.generate(world, seed);
		
		// Init lighting
		for (int xvbo = 0; xvbo < WorldSettings.x/16; xvbo++) {
			for (int yvbo = 0; yvbo < WorldSettings.y/16; yvbo++) {
				VBO_array[xvbo][yvbo].initializeLighting(world);
			}
		}
		
		light = new LightSource(0.5F, 0.75F, 1F, 0F).setAmbient(new float[] {0.2F, 0.2F, 0.2F, 0F}).setDiffuse(new float[] {.8F, .8F, .8F, 1F}).setSpecular(new float[] {0F, 0F, 0F, 1F});
		light.load(GL11.GL_LIGHT0);
		
		Material mat = new Material(new float[] {1f, 1f, 1f, 1f}).setAmbient(new float[] {0f, 0f, 0f, 1f});
		mat.activate();
		
		float_buffer = BufferUtils.createFloatBuffer((int) Math.pow(2, 23));
		
		for (int x = 0; x < VBO_array.length; x++) {
			for (int y = 0; y < VBO_array[0].length; y++) {
				VBO_array[x][y].save(float_buffer, false);
			}
		}
		
		hud = new Hud(screen_width, screen_height);
		
		player = new EntityPlayer(63.5f, 63.5f, 127f);
		entities.add(player);
		player.getInventory().addItem(new ItemStack(Item.bucket, 8, 1));
		player.getInventory().addItem(new ItemStack(new ItemBlock(Block.glass.id), 16));
		player.getInventory().addItem(new ItemStack(new ItemBlock(Block.slab.id), 8));
		player.getInventory().addItem(new ItemStack(new ItemBlock(Block.workbench.id), 1));
		player.getInventory().addItem(new ItemStack(new ItemBlock(Block.furnace.id), 1));
		player.getInventory().addItem(new ItemStack(new ItemBlock(Block.torch.id), 24));
		
		marker = (EntityMarker) new EntityMarker(0f, 0f, 0f).isTextured(false);
		
		framerate_count = 0;
		framerate_timestamp = new Date().getTime();

		Mouse.setGrabbed(true);
		DrawingTools.DrawIn3D();
	}
	
	protected void display() {
		
		// FRAMERATE AND THAT THINGS
		tick_counter++;
		
		framerate_count++;
		Date d = new Date();
		long this_timestamp = d.getTime();
		
		tick_time = (int) (this_timestamp - tick_timestamp);
		if (tick_time > 40) tick_time = 40;
		if (tick_time < 0) tick_time = 0;
		
		if ((this_timestamp - framerate_timestamp) >= 1000) {
			fps = framerate_count;
			//hud.println(new StringBuilder().append("FPS: ").append(fps).toString()); /// Print FPS on console
			
			framerate_count = 0;
			framerate_timestamp = this_timestamp;
		}
		tick_timestamp = this_timestamp;
		
		// GUI HANDLING AND PAUSE
		GuiHandler.checkEvents(screen_width, screen_height);
		pause = GuiHandler.isGamePaused();
		keyboard_control = GuiHandler.isGuiOnKeyboardControl();
		mouse_control = GuiHandler.isGuiOnMouseControl();

		if (Mouse.isGrabbed() == (pause || mouse_control)) Mouse.setGrabbed(!(pause || mouse_control));
		
		hud.tick();
		
		GuiHandler.tick();
		
		
		if (!pause) {
			
			
			// CURSOR THINGS	
			marker.update(float_buffer);
			
			player.getInventory().clean();
			
			
			// ENTITIES STUFF
			for(int ei = 0; ei < entities.size(); ei++) {
				Entity e = entities.get(ei);
				e.update();
			}
			
			/*for (int i = 0; i < 128; i++) {
				Random random = new Random();
				
				int x = RNG.nextInt(random, 0, WorldSettings.x);
				int y = RNG.nextInt(random, 0, WorldSettings.y);
				int z = RNG.nextInt(random, 0, WorldSettings.z);
				
				world.getBlock(x, y, z).onTick(world, x, y, z);
			}*/
			
			short jump = 64;
			short start = (short) (tick_counter%jump);
			
			for (int x = start; x < WorldSettings.x; x+= jump) {
				for (int y = 0; y < WorldSettings.y; y += 1) {
					for (int z = 0; z < WorldSettings.z; z += 1) {
						Block b = world.getBlock(x, y, z);
						if (b != null && b.onTick(world, x, y, z)) {
							y++;
						}
					}
				}
			}
			
			for (int x = 0; x < VBO_array.length; x++) {
				for (int y = 0; y < VBO_array[0].length; y++) {
					if (VBO_array[x][y].shouldUpdate())
						VBO_array[x][y].save(float_buffer, true);
				}
			}
		
		}
		
		// UPDATE CAMERA
		player_camera.update(screen_width, screen_height);
		
		// GET READY FOR 3D
		GL11.glPushMatrix();
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		
		// MOVE THE CAMERA
		GL11.glRotatef(player_camera.getPitch(), 1F, 0F, 0F);
		GL11.glRotatef(-player_camera.getYaw(), 0F, 0F, 1F);
		GL11.glTranslated(-player_camera.getPosition().x, -player_camera.getPosition().y, -player_camera.getPosition().z);
		GL11.glScalef(1f, 1f, 1f);
		
		// RENDER THE WORLD
		renderWorld();

		for(int ei = 0; ei < entities.size(); ei++) {
			Entity e = entities.get(ei);
			if (e.isVisible())
				RenderEntity.render(e);
		}
		RenderEntity.render(marker);
		
		light.load(GL11.GL_LIGHT0);
		
		GL11.glPopMatrix();
		
		
		DrawingTools.DrawIn2D(screen_width, screen_height);
		
		hud.draw();
		
		GuiHandler.draw();
		
		DrawingTools.DrawIn3D();
		
	}
	
	
	private void renderWorld() {
		
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		
		for (int xvbo = 0; xvbo < WorldSettings.x/16; xvbo++) {
			for (int yvbo = 0; yvbo < WorldSettings.y/16; yvbo++) {
				
				int r = 5;
				
				Vector3d playerpos = Game.getPlayer().getPosition();
				if ((int) playerpos.x/16 - xvbo > -r &&
					(int) playerpos.x/16 - xvbo <  r &&
					(int) playerpos.y/16 - yvbo > -r &&
					(int) playerpos.y/16 - yvbo <  r) {
					
						VBO vbo = VBO_array[xvbo][yvbo];
						
						vbo.render(false);
					
				}
			}
		}
		
		for (int xvbo = 0; xvbo < WorldSettings.x/16; xvbo++) {
			for (int yvbo = 0; yvbo < WorldSettings.y/16; yvbo++) {
				
				Vector3d playerpos = Game.getPlayer().getPosition();
				if ((int) playerpos.x/16 - xvbo > -4 &&
					(int) playerpos.x/16 - xvbo <  4 &&
					(int) playerpos.y/16 - yvbo > -4 &&
					(int) playerpos.y/16 - yvbo <  4) {
					
						VBO vbo = VBO_array[xvbo][yvbo];
						
						vbo.render(true);
					
				}
			}
		}
	}
	
	
	public static float getScreenSize(int i) {
		
		if (i==0) return screen_width;
		if (i==1) return screen_height;
		else return -1;
		
	}
	
	public static Camera getCurrentCamera() {
		return player_camera;
	}
	
	public static String getSeed() {
		return seed;
	}
	
	public static World getCurrentWorld() {
		return world;
	}

	public static VBO getVBO(int x, int y) {
		return VBO_array[x][y];
	}
	public static void setVBO(int x, int y, VBO vbo) {
		VBO_array[x][y] = vbo;
	}
	
	public static FloatBuffer getVBOFloatBuffer() {
		return float_buffer;
	}
	
	public static Hud getHud() {
		return hud;
	}
	
	public static int getTickTime() {
		return tick_time;
	}

	public static Entity getPlayer() {
		return player;
	}

	public void setPlayer(Entity player) {
		Game.player = player;
	}
	
	public static Texture getWorldTexture() {
		return world_texture;
	}

	public static boolean isPaused() {
		return pause;
	}
	public static boolean isGuiOnKeyboardControl() {
		return keyboard_control;
	}
	public static boolean isGuiOnMouseControl() {
		return mouse_control;
	}
	public static void requestQuit() {
		quit_request = true;
	}

	private static int screen_width = 1440;
	private static int screen_height = 900;
	
	protected static boolean pause = false;
	protected static boolean keyboard_control = false;
	protected static boolean mouse_control = false;
	
	protected static boolean quit_request = false;
	
	public static float hud_scale = 7f;
	
	private static String seed = "Lighting";
	
	private static VBO VBO_array[][];
	private static FloatBuffer float_buffer;
	
	private int framerate_count;
	private long framerate_timestamp;
	public static float fps;

	private long tick_timestamp; 
	private static int tick_time;
	
	private long tick_counter = 0;
	
	int filling = 1;
	
	static CameraFirstPerson player_camera;
	
	private static Texture world_texture;
	
	static World world = new World(new GameObject[WorldSettings.x][WorldSettings.y][WorldSettings.z]);
	
	public static float gravity = 9.8f;
	
	int objLength;
	
	static Hud hud;
	
	private static Entity player;
	private EntityMarker marker;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private LightSource light;
}