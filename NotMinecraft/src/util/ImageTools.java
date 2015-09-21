package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class ImageTools {
	
	public static void load2dArray(float[][] array, int level) {
		
		int x, y;
		
		int x_max = array.length;
		int y_max = array[0].length;
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(x_max * y_max * 3);
		
		float min = 0f;
		float max = 0f;
		
		for (x = 0; x < x_max; x++) {
			for (y = 0; y < y_max; y++) {
				double j = array[x][y];
				if (j > max) max = (float) j;
				if (j < min) min = (float) j;
			}
		}
		
		for (x = 0; x < x_max; x++) {
			for (y = 0; y < y_max; y++) {
				float k = map(array[x][y], min, max, 0f, 1f);
				k = (float) array[x][y];
				int rgb = new Color(k, k, k).getRGB();
				
				buffer.put((byte) ((rgb >> 16) & 0xFF));
				buffer.put((byte) ((rgb >> 8) & 0xFF));
				buffer.put((byte) (rgb & 0xFF));
			}
		}
		
		buffer.flip();
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, level, GL11.GL_RGB8, x_max, y_max, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buffer);
	}
	
	public static void loadWorldArray(float[][] array) {
		
		int x, y;
		
		int x_max = array.length;
		int y_max = array[0].length;
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(x_max * y_max * 3);
		
		float min = 0f;
		float max = 0f;
		
		for (x = 0; x < x_max; x++) {
			for (y = 0; y < y_max; y++) {
				float j = array[x][y];
				if (j > max) max = j;
				if (j < min) min = j;
			}
		}
		
		for (x = 0; x < x_max; x++) {
			for (y = 0; y < y_max; y++) {
				float k = map(array[x][y], min, max, 0f, 1f);
				k = array[x][y];
				int rgb;
				if (k > 0.95f) {
					rgb = new Color(k, k, k).getRGB();
				} else if (k > 0.85f) {
					rgb = new Color(k, k/1.5f, 0f).getRGB();
				} else if (k > 0.8f) {
					rgb = new Color(k, k, 0f).getRGB();
				} else if (k > 0.6f) {
					rgb = new Color(0f, k, 0f).getRGB();
				} else {
					rgb = new Color(k, .1f+k, .2f+k).getRGB();
				}
				
				buffer.put((byte) ((rgb >> 16) & 0xFF));
				buffer.put((byte) ((rgb >> 8) & 0xFF));
				buffer.put((byte) (rgb & 0xFF));
			}
		}
		
		buffer.flip();
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB8, x_max, y_max, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buffer);
	}
	
	public static void save2dArray(float[][] array, String filename) {
		
		int x, y;
		
		int x_max = array.length;
		int y_max = array[0].length;
		
		BufferedImage image = new BufferedImage(x_max, y_max, BufferedImage.TYPE_3BYTE_BGR);
		
		float min = 0f;
		float max = 0f;
		
		for (x = 0; x < x_max; x++) {
			for (y = 0; y < y_max; y++) {
				float j = array[x][y];
				if (j > max) max = j;
				if (j < min) min = j;
			}
		}
		
		for (x = 0; x < x_max; x++) {
			for (y = 0; y < y_max; y++) {
				float k = map(array[x][y], min, max, 0f, 1f);
				int rgb = new Color(k, k, k).getRGB();
				image.setRGB(x, y, rgb);
			}
		}
		
		try {
			File outputfile = new File(filename);
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private static float map(double x, float in_min, float in_max, float out_min, float out_max)
	{
		return (float) ((x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min);
	}

}
