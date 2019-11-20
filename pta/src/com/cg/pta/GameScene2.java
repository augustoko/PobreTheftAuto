package com.cg.pta;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_LIGHT1;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.List;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.glu.GLU;

import com.cg.pta.object.Car;
import com.cg.pta.object.Car2;
import com.cg.pta.object.Maze;
import com.cg.pta.object.Sound;
import com.cg.pta.object.Money;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_LIGHT1;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_POSITION;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;

public class GameScene2 implements IScene {
	private CameraController2 cameraController;
	private Maze maze;
	private Car2 car;
	private List<Money> money;
	private Texture[] textures;
	private CountDownTimer timer;
        
	// private GL2 gl;
	private GLU glu;
	private MainGLCanvas mainGLCanvas;
        private GameScene2 gamescene2;
	private TextRenderer textInfo;
	private TextRenderer textUpdate;
        private Score score;

	private int frame_counter = 0;
	private int FPS = 60;

	private static String crashURL = "/fx/crash.wav";
	private static String clickURL = "/fx/click.wav";

	public GameScene2() {

	}

	@Override
	public void refresh() {
		initComponent();
	}

	public void init(GL2 gl, GLU glu, MainGLCanvas mainGLCanvas) {
		// this.gl = gl;
		this.glu = glu;
		this.mainGLCanvas = mainGLCanvas;
		textures = mainGLCanvas.textures;

		textInfo = new TextRenderer(new Font("SansSerif", Font.BOLD, 30));
		textUpdate = new TextRenderer(new Font("SansSerif", Font.BOLD, 50));
              
		initComponent();

		gl.glEnable(GL2.GL_LIGHT0);
		gl.glEnable(GL2.GL_LIGHT1);
		gl.glEnable(GL2.GL_LIGHT2);
		gl.glEnable(GL2.GL_LIGHT3);
		gl.glEnable(GL2.GL_LIGHT3);
	}

	public void initComponent() {
		cameraController = new CameraController2();
		maze = MazeGenerator.createMaze(19, 19, 0.4f, textures);
		money = MoneyGenerator.create(maze.getGrid(), textures);
		car = new Car2(maze.getGrid(), textures, money);
		timer = new CountDownTimer(30);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
	}

	public void display(GLAutoDrawable drawable) {
                 
		GL2 gl = drawable.getGL().getGL2(); 
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		float lightPercent = 4f * (1 - (0.7f * timer.getPercent()));
		float[] LightAmbient = { lightPercent, lightPercent, lightPercent, 1.0f };
		gl.glLightfv(GL_LIGHT1, GL_AMBIENT, LightAmbient, 0);
                
                float luzDifusa[] = {0.7f, 0.7f, 0.7f, 1.0f};
                float posicaoLuz[] = {50.0f, 100.0f, 0.0f, 0.0f};
                gl.glLightfv(GL_LIGHT1, GL_POSITION, posicaoLuz, 0);
                // define os parametros de luz de numero 0 (zero)
                gl.glLightfv(GL_LIGHT1, GL_DIFFUSE, luzDifusa, 0);

		gl.glEnable(GL_LIGHT1);
		gl.glEnable(GL_LIGHTING);

		maze.update(gl, null);
		car.update(gl, null);

		for (Money t : money) {
			t.update(gl, null);
		}

		float[] t = cameraController.getTranslation();
		float[] r = cameraController.getRotation();

		cameraController.update();
		cameraController.setDestination(-car.getX(), -14.2f, -8f + car.getZ());

		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0, 1.55f, 0.1, 100.0);
		
		gl.glRotatef(-1 * r[0], -1 * r[1], -1 * r[2], -1 * r[3]);
		gl.glTranslatef(t[0], t[1], t[2]);

		gl.glTranslatef(-1 * t[0], -1 * t[1], -1 * t[2]);
		gl.glRotatef(r[0], r[1], r[2], r[3]);
		gl.glTranslatef(t[0], t[1], t[2]);
		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();

                int tamanho = drawable.getSurfaceHeight();
                int largura = drawable.getSurfaceWidth();
		
                textInfo.beginRendering(largura,tamanho);
		textInfo.setColor(1f, 1f, 0f, 0.6f);
		textInfo.draw("Fase 2", 150, 115);
		textInfo.endRendering();
		textInfo.beginRendering(largura,tamanho);
		textInfo.setColor(1f, 1f, 0f, 0.6f);
		textInfo.draw("Dinheiro", 150, 86);
		textInfo.endRendering();

		textUpdate.beginRendering(largura,tamanho);
		textUpdate.setColor(1f, 1f, 0f, 0.6f);
		textUpdate.draw(String.format("%04d", car.getScore()), 150, 40);
		textUpdate.endRendering();
               
		textInfo.beginRendering(largura,tamanho);
		textInfo.setColor(1f, 1f, 0f, 0.6f);
		textInfo.draw("Tempo", 50, 86);
		textInfo.endRendering();
              
		textUpdate.beginRendering(largura,tamanho);
		textUpdate.setColor(1f, 1f, 0f, 0.6f);
		textUpdate.draw(String.format("%02d", timer.getTime()), 50, 40);
		textUpdate.endRendering();

		textInfo.beginRendering(largura,tamanho);
		textInfo.setColor(1f, 1f, 1f, 0.4f);
		textInfo.draw("[Esc]", 20, 700);
		textInfo.endRendering();
                
                //if(score.getScore()==1000){
                  //  new Sound(crashURL).play();
		//	mainGLCanvas.setScene(2);
                //}
		
                if(car.getScore()>=5000){
                            new Sound(crashURL).play();
			mainGLCanvas.setScene(4);   
                }
		if (timer.getTime() <= 0) {
			new Sound(crashURL).play();
			mainGLCanvas.setScene(4);
		}

		frame_counter++;
		if (frame_counter == FPS) {
			frame_counter = 0;
			money.add(MoneyGenerator.newMoney(maze.grid, money, textures));
		}
	}

	public void dispose(GLAutoDrawable drawable) {

	}

	@Override
	public void keyPressed(KeyEvent event) {
		cameraController.keyPressed(event);
		car.keyPressed(event);
		// quick exit
		if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			new Sound(clickURL).play();
			mainGLCanvas.setScene(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		cameraController.keyReleased(event);
		car.keyReleased(event);
	}

	@Override
	public void keyTyped(KeyEvent event) {
		cameraController.keyTyped(event);
		car.keyTyped(event);
	}

}
