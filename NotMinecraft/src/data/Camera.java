package data;

import util.Vector3d;

public abstract class Camera {


	public boolean update(String string) {
		return false;
	}
	
	public float getPitch() {
		return pitch;
	}
	public void setPitch(float pitch) {
		CameraFirstPerson.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}
	public void setYaw(float yaw) {
		CameraFirstPerson.yaw = yaw;
	}

	public Vector3d getPosition() {
		//return position;
		return new Vector3d(position.x, position.y, position.z);
	}
	public void setPosition(Vector3d position) {
		this.position = position;
	}
	
	public void yaw(float i) {
		setYaw(getYaw() + i);
	}
	public void pitch(float j) {
		setPitch(getPitch() + j);
	}
	
	Vector3d position;
	static float yaw = 0F;
	static float pitch = 0F;

}
