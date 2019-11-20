package com.cg.pta;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.event.KeyEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

import com.cg.pta.object.Sound;
import static com.jogamp.opengl.GL.GL_NEAREST;
import static com.jogamp.opengl.GL.GL_TEXTURE_2D;
import static com.jogamp.opengl.GL.GL_TEXTURE_MAG_FILTER;
import static com.jogamp.opengl.GL.GL_TEXTURE_MIN_FILTER;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;

public class MenuScene implements IScene {
	private GL2 gl;
	private GLU glu;
	private MainGLCanvas mainGLCanvas;
	private Texture[] textures;
	private float textureTop;
	private float textureBottom;
	private float textureLeft;
	private float textureRight;
	private float angleCar;
	private TextRenderer textCredit;
	private TextRenderer textInfo;
	private static String musicURL = "/fx/music.wav";
	private static String startURL = "/fx/start.wav";
	private AudioClip music;

	public MenuScene() {

	}

	public void init(GL2 gl, GLU glu, MainGLCanvas mainGLCanvas) {
		this.gl = gl;
		this.glu = glu;
		this.mainGLCanvas = mainGLCanvas;
		textures = mainGLCanvas.textures;
		TextureCoords textureCoords = textures[0].getImageTexCoords();
		textureTop = textureCoords.top();
		textureBottom = textureCoords.bottom();
		textureLeft = textureCoords.left();
		textureRight = textureCoords.right();

		angleCar = 0f;
		textInfo = new TextRenderer(new Font("SansSerif", Font.BOLD, 40));
		textCredit = new TextRenderer(new Font("SansSerif", Font.BOLD, 20));
		
		music = Applet.newAudioClip(this.getClass().getResource(musicURL));
		music.loop();

	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {

	}

	public void display(GLAutoDrawable drawable) {
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0, 1.55f, 0.1, 100.0);
		gl.glMatrixMode(GL_MODELVIEW);

		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		gl.glColor3f(1f, 1f, 1f);

		drawLogo();

		drawBigCar();
		angleCar += 0.8f;
                int tamanho = drawable.getSurfaceHeight();
                int largura = drawable.getSurfaceWidth();
                
		// draw info
                float size = 0.6f;
		float radio = 29f / 168f;
		gl.glLoadIdentity();
		gl.glTranslatef(0f, -0.6f, -2.4f);
		textures[20].enable(gl);
		textures[20].bind(gl);
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		gl.glBegin(GL_QUADS);
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-size, -radio * size, 0f);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(size, -radio * size, 0f);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(size, 0.25f * size, 0f);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-size, 0.25f * size, 0f);
		gl.glEnd();
                 
		gl.glLoadIdentity();
		gl.glTranslatef(0f, -0.9f, -2.4f);
		textures[21].enable(gl);
		textures[21].bind(gl);
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		gl.glBegin(GL_QUADS);
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-size, -radio * size, 0f);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(size, -radio * size, 0f);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(size, 0.25f * size, 0f);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-size, 0.25f * size, 0f);
		gl.glEnd();
                textInfo.beginRendering(largura,tamanho);
		textInfo.setColor(1f, 1f, 1f, 0.4f);
		textInfo.draw("[Esc]", 20, 700);
		textInfo.endRendering();
		
		

	}

	public void dispose(GLAutoDrawable drawable) {

	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			new Sound(startURL).play(); 
			mainGLCanvas.setScene(1);
		}
                 if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {

	}

	@Override
	public void keyTyped(KeyEvent event) {

	}

	private void drawLogo() {
		float size = 1f;
		float radio = 29f / 168f;
		gl.glLoadIdentity();
		gl.glTranslatef(0f, 0.6f, -2.4f);
		textures[19].enable(gl);
		textures[19].bind(gl);
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		gl.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		gl.glBegin(GL_QUADS);
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-size, -radio * size, 0f);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(size, -radio * size, 0f);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(size, 0.25f * size, 0f);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-size, 0.25f * size, 0f);
		gl.glEnd();
	}

	private void drawBigCar() {
		float size = 1f;
		// ----- Render the Color Cube -----
		gl.glLoadIdentity(); // reset the current model-view matrix
		// gl.glTranslatef(pX, 0, 0); // translate right and into the
		// screen

		gl.glTranslatef(0f, 0.0f, -5.0f);
		gl.glRotatef(angleCar, 0f, 1f, 0f);
		// side car (for front face)
		textures[11].enable(gl);
		textures[11].bind(gl);
		gl.glBegin(GL_QUADS);
		// Front Face
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-size, -0.5f * size, 0.5f * size);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(size, -0.5f * size, 0.5f * size);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(size, 0.5f * size, 0.5f * size);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-size, 0.5f * size, 0.5f * size);
		gl.glEnd();

		// inverse front face
		textures[11].enable(gl);
		textures[11].bind(gl);
		gl.glBegin(GL_QUADS);
		// Back Face
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-size, -0.5f * size, -0.5f * size);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-size, 0.5f * size, -0.5f * size);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(size, 0.5f * size, -0.5f * size);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(size, -0.5f * size, -0.5f * size);
		gl.glEnd();

		// top car (for Top face)
		textures[14].enable(gl);
		textures[14].bind(gl);
		gl.glBegin(GL_QUADS);
		// Top Face
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-size, 0.5f * size, -0.5f * size);
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-size, 0.5f * size, 0.5f * size);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(size, 0.5f * size, 0.5f * size);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(size, 0.5f * size, -0.5f * size);
		gl.glEnd();

		// carBack
		textures[13].enable(gl);
		textures[13].bind(gl);
		gl.glBegin(GL_QUADS);
		// Right face
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(size, -0.5f * size, -0.5f * size);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(size, 0.5f * size, -0.5f * size);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(size, 0.5f * size, 0.5f * size);
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(size, -0.5f * size, 0.5f * size);
		gl.glEnd();

		// carFront
		textures[12].enable(gl);
		textures[12].bind(gl);
		gl.glBegin(GL_QUADS);
		// Left Face
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-size, -0.5f * size, -0.5f * size);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(-size, -0.5f * size, 0.5f * size);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(-size, 0.5f * size, 0.5f * size);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-size, 0.5f * size, -0.5f * size);
		gl.glEnd();
	}

	@Override
	public void refresh() {
		
	}

}
