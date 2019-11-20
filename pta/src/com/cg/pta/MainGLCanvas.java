package com.cg.pta;

import static com.jogamp.opengl.GL.GL_DEPTH_TEST;
import static com.jogamp.opengl.GL.GL_LEQUAL;
import static com.jogamp.opengl.GL.GL_NICEST;
import static com.jogamp.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

@SuppressWarnings("serial")
public class MainGLCanvas extends GLCanvas implements GLEventListener,
		KeyListener {
	private List<IScene> scenes;
	private IScene currentScene;
	public Texture[] textures;

	private static String TITLE = "Pobre Theft Auto"; 
															
	private static final int CANVAS_WIDTH = 1600;
	private static final int CANVAS_HEIGHT = 900; 
	private static final int FPS = 60; 

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GLCanvas canvas = new MainGLCanvas();
				canvas.setPreferredSize(new Dimension(CANVAS_WIDTH,
						CANVAS_HEIGHT));

				final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

				final JFrame frame = new JFrame(); 
                                frame.setSize(CANVAS_WIDTH,CANVAS_HEIGHT);
	                        frame.getContentPane().add(canvas);
                                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);	
                                frame.setUndecorated(true);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				frame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						
						new Thread() {
							@Override
							public void run() {
								if (animator.isStarted())
									animator.stop();
								System.exit(0);
							}
						}.start();
					}
				});
				frame.setTitle(TITLE);
				frame.pack();
                                
                                frame.setVisible(true);
				animator.start(); 
			}
		});
	}

	

	private GLU glu;

	public MainGLCanvas() {
		this.addGLEventListener(this);
		this.addKeyListener(this);
	}

	@Override
	public void init(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2(); 
		glu = new GLU(); 

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); 
		gl.glClearDepth(1.0f); 
		gl.glEnable(GL_DEPTH_TEST); 
		gl.glEnable(GL2.GL_POLYGON_SMOOTH);
		gl.glDepthFunc(GL_LEQUAL); 
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 
																
		gl.glShadeModel(GL_SMOOTH); 
		float[] fogColor = { 0.0f, 0.0f, 0.4f, 1.0f };
		gl.glFogfv(GL2.GL_FOG_COLOR, fogColor, 0); 
		gl.glFogf(GL2.GL_FOG_DENSITY, 0.04f); 
		gl.glHint(GL2.GL_FOG_HINT, GL2.GL_DONT_CARE); 
		gl.glFogf(GL2.GL_FOG_START, 0.0f); 
		gl.glFogf(GL2.GL_FOG_END, 100.0f);
		gl.glEnable(GL2.GL_FOG); 
		gl.glFogi(GL2.GL_FOG_MODE, GL2.GL_LINEAR);

		try {
			textures = new Texture[22];
			
			textures[0] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/casa.png"), false, ".png");
			textures[1] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/predio.png"), false, ".png");
			textures[2] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/casa3.png"), false, ".png");
			textures[3] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/casa4.png"), false, ".png");
			textures[4] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/casa5.png"), false, ".png");
			
			textures[5] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/teto1.png"), false, ".png");
			textures[6] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/tetop.png"), false, ".png");
			textures[7] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/teto3.png"), false, ".png");
			textures[8] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/teto4.png"), false, ".png");
			textures[9] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/teto5.png"), false, ".png");
			
			textures[10] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/chao6.png"), false, ".png");
			
			textures[11] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/carSide.png"), false, ".png");
			textures[12] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/carFront.png"), false, ".png");
			textures[13] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/carBack.png"), false, ".png");
			textures[14] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/carTop.png"), false, ".png");
			
			textures[15] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/money.png"), false, ".png");
			textures[16] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/money2.png"), false, ".png");
			textures[17] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/money3.png"), false, ".png");
			textures[18] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/money4.png"), false, ".png");
			
			
			textures[19] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/logo2.png"), false, ".png");
                        
                       textures[20] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/enter.png"), false, ".png");
                       textures[21] = TextureIO.newTexture(getClass().getClassLoader()
					.getResource("img/inst.png"), false, ".png");

		} catch (GLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		scenes = new ArrayList<IScene>();
		scenes.add(new MenuScene());
		scenes.add(new GameScene());
                scenes.add(new GameScene2());
	        scenes.add(new LeaderboardScene());
                scenes.add(new LeaderboardScene2());
		currentScene = scenes.get(0);

		for (IScene scene : scenes) {
			scene.init(gl, glu, this);
		}
	}

	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
		currentScene.reshape(drawable, x, y, width, height);
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		currentScene.display(drawable);
	}

	
	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void keyPressed(KeyEvent event) {
           currentScene.keyPressed(event);
	}

	@Override
	public void keyReleased(KeyEvent event) {
		currentScene.keyReleased(event);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		currentScene.keyTyped(event);
	}

	public void setScene(IScene scene) {
		currentScene = scene;
	}

	public void setScene(int number) {
		currentScene = scenes.get(number);
		currentScene.refresh();
	}

}