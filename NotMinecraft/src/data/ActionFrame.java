package data;

import java.util.Dictionary;
import java.util.Hashtable;

public class ActionFrame {
	
	public String name;
	Dictionary<String, float[]> values = new Hashtable<String, float[]>();
	
	public ActionFrame(String name) {
		this.name = name;
	}
	protected void addBox(String name, float x, float y, float z) {
		values.put(name, new float[] {x, y, z});
	}
}
