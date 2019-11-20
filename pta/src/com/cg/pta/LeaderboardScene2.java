
package com.cg.pta;

import com.cg.pta.object.Sound;
import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import static com.jogamp.opengl.GL.GL_DEPTH_BUFFER_BIT;
import com.jogamp.opengl.GL2;
import static com.jogamp.opengl.GL2ES3.GL_QUADS;
import com.jogamp.opengl.GLAutoDrawable;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class LeaderboardScene2  implements IScene {
	private GL2 gl;
	private GLU glu;
	private MainGLCanvas mainGLCanvas;
	private Texture[] textures;
	private float textureTop;
	private float textureBottom;
	private float textureLeft;
	private float textureRight;
        private GameScene2 gamescene2;
	private float angleCar;
	private TextRenderer textTitle;
	private TextRenderer textInfo;
	private Score score;
	
	private static String clickURL = "/fx/click.wav";

	public LeaderboardScene2() {
	}

	@Override
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
		textTitle = new TextRenderer(new Font("SansSerif", Font.BOLD, 90));
		textInfo = new TextRenderer(new Font("SansSerif", Font.BOLD, 40));
		score = Score.getInstance();

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0, 1.55f, 0.1, 100.0);
		gl.glMatrixMode(GL_MODELVIEW);

		GL2 gl = drawable.getGL().getGL2(); // get the OpenGL 2 graphics context
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		gl.glColor3f(1f, 1f, 1f);

		drawTrash(15, 0.3f, -1.8f, -7f);
		drawTrash(16, 0.3f, -0.6f, -7f);
		drawTrash(17, 0.3f, 0.6f, -7f);
		drawTrash(18, 0.3f, 1.8f, -7f);
		angleCar += 0.4f;
                int tamanho = drawable.getSurfaceHeight();
                int largura = drawable.getSurfaceWidth();
               
                  if(score.getScore()<6000){
		String str = "Você foi preso :)";
		textTitle.beginRendering(largura,tamanho);
		textTitle.setColor(1f, 1f, 1f, 1f);
		Rectangle2D textBox = textTitle.getBounds(str);
		textTitle.draw(str, 350, 560);
		textTitle.endRendering();

		str = String.format("$%04d", score.getScore());
		textTitle.beginRendering(largura, tamanho);
		textTitle.setColor(1f, 1f, 0f, 1f);
		textBox = textTitle.getBounds(str);
		textTitle.draw(str,570 , 460);
		textTitle.endRendering();
                
		str = "Pressione ENTER pra continuar";
		textInfo.beginRendering(largura, tamanho);
		textInfo.setColor(1f, 1f, 1f, 1f);
		textBox = textInfo.getBounds(str);
		textInfo.draw(str,415,150);
		textInfo.endRendering();
                str = "Pressione ESC pra sair do jogo";
		textInfo.beginRendering(largura, tamanho);
		textInfo.setColor(1f, 1f, 1f, 1f);
		textBox = textInfo.getBounds(str);
		textInfo.draw(str,407,50);
		textInfo.endRendering();
                }else{
                String str = "Parabéns! Você ficou rico!";
		textTitle.beginRendering(largura,tamanho);
		textTitle.setColor(1f, 1f, 1f, 1f);
		Rectangle2D textBox = textTitle.getBounds(str);
		textTitle.draw(str, 120, 560);
		textTitle.endRendering();

		str = String.format("$%04d", score.getScore());
		textTitle.beginRendering(largura, tamanho);
		textTitle.setColor(1f, 1f, 0f, 1f);
		textBox = textTitle.getBounds(str);
		textTitle.draw(str, 570, 460);
		textTitle.endRendering();
                
		str = "Pressione ENTER pra continuar";
		textInfo.beginRendering(largura, tamanho);
		textInfo.setColor(1f, 1f, 1f, 1f);
		textBox = textInfo.getBounds(str);
		textInfo.draw(str, 415, 150);
		textInfo.endRendering();
                str = "Pressione ESC para sair do jogo ";
		textInfo.beginRendering(largura, tamanho);
		textInfo.setColor(1f, 1f, 1f, 1f);
		textBox = textInfo.getBounds(str);
		textInfo.draw(str,390,50);
		textInfo.endRendering();
              
                  }
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {

	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_ENTER) {
			new Sound(clickURL).play();
			mainGLCanvas.setScene(0);
			score.reset();
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

	private void drawTrash(int trashId, float size, float x, float z) {
		
		gl.glLoadIdentity(); 
		// gl.glTranslatef(pX, 0, 0); 

		gl.glTranslatef(x, -0.4f, z);
		gl.glRotatef(angleCar, 0f, 1f, 1f);

		textures[trashId].enable(gl);
		textures[trashId].bind(gl);

		gl.glBegin(GL_QUADS);

		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-size, -size, size); 
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(size, -size, size);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(size, size, size); 
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-size, size, size); 

		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(-size, -size, -size);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(-size, size, -size);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(size, size, -size);
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(size, -size, -size);

		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-size, size, -size);
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-size, size, size);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(size, size, size);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(size, size, -size);

		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(-size, -size, -size);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(size, -size, -size);
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(size, -size, size);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(-size, -size, size);

		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(size, -size, -size);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(size, size, -size);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(size, size, size);
		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(size, -size, size);

		gl.glTexCoord2f(textureLeft, textureBottom);
		gl.glVertex3f(-size, -size, -size);
		gl.glTexCoord2f(textureRight, textureBottom);
		gl.glVertex3f(-size, -size, size);
		gl.glTexCoord2f(textureRight, textureTop);
		gl.glVertex3f(-size, size, size);
		gl.glTexCoord2f(textureLeft, textureTop);
		gl.glVertex3f(-size, size, -size);

		gl.glEnd();
	}

	@Override
	public void refresh() {

	}

    
}
