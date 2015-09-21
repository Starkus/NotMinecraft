package model;

public class ModelMarker extends ModelBase {
	public ModelMarker(String tex) {
		super(tex);
		ModelBox box;
		
		box = addBox("box", 32f, 32f, 32f, -16f, -16f, -16f, 0, 0);
		box.setScale(1f);
	}
	
}
