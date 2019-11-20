package com.cg.pta;

import java.awt.event.KeyEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

public interface IScene {
	public void refresh();

	public void init(GL2 gl, GLU glu, MainGLCanvas mainGLCanvas);

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height);

	public void display(GLAutoDrawable drawable);

	public void dispose(GLAutoDrawable drawable);

	public void keyPressed(KeyEvent event);

	public void keyReleased(KeyEvent event);

	public void keyTyped(KeyEvent event);
}
