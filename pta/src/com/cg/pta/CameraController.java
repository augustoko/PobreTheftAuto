package com.cg.pta;

import java.awt.event.KeyEvent;

public class CameraController {
	private float pX;
	private float pY;
	private float pZ;
	private float pSpeed = 0.199000000000000000000000f;
	private float r;
	private float rX;
	private float rY;
	private float rZ;

	private float desX;
	private float desY;
	private float desZ;

	private static final float START_CAMERA_X = -0.0f;
	private static final float START_CAMERA_Y = -14.2f;
	private static final float START_CAMERA_Z = -8.0f;
	private static final float START_ROT = 32.6f;
	private static final float START_ROT_X = 1.0f;
	private static final float START_ROT_Y = 0.0f;
	private static final float START_ROT_Z = 0.0f;

	public CameraController() {
		pX = START_CAMERA_X;
		pY = START_CAMERA_Y;
		pZ = START_CAMERA_Z;
		r = START_ROT;
		rX = START_ROT_X;
		rY = START_ROT_Y;
		rZ = START_ROT_Z;
		desX = pX;
		desY = pY;
		desZ = pZ;
	}

	public float[] getTranslation() {
		return new float[] { pX, pY, pZ };
	}

	public float[] getRotation() {
		return new float[] { r, rX, rY, rZ };
	}

	public void setDestination(float x, float y, float z) {
		desX = x;
		desY = y;
		desZ = z;

	}

	public void update() {

		if (this.desX == pX && this.desY == pY && this.desZ == pZ) {
			return;
		}

		if (Math.abs((this.pX - this.desX) * 1000f) / 1000f <= pSpeed) {
			this.pX = this.desX;
		} else {
			this.pX += Math.signum(desX - pX) * pSpeed;
		}
		if (Math.abs((this.pY - this.desY) * 1000f) / 1000f <= pSpeed) {
			this.pY = this.desY;
		} else {
			this.pY += Math.signum(desY - pY) * pSpeed;
		}
		if (Math.abs((this.pZ - this.desZ) * 1000f) / 1000f <= pSpeed) {
			this.pZ = this.desZ;
		} else {
			this.pZ += Math.signum(desZ - pZ) * pSpeed;
		}

	}

	public void keyPressed(KeyEvent event) {

	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {

	}
}
