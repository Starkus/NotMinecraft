package data;

import java.util.ArrayList;

public abstract class ActionBase {
	
	public final String name; 
	protected ArrayList<ActionFrame> frames = new ArrayList<ActionFrame>();
	
	public ActionBase(String name) {
		this.name = name;
		addFrames();
	}
	
	protected abstract void addFrames();
	
	public void getBoxRotationInFrame(int f) {
		
	}
}