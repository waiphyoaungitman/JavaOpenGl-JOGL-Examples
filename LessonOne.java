package com.lesson;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

public class LessonOne extends Frame implements GLEventListener {
	 static int HEIGHT = 400, WIDTH = 400; 

	 static GLCanvas canvas; 
	static GLCapabilities capabilities; 
	 LessonOne()
	 {
		 final GLProfile profile = GLProfile.get(GLProfile.GL2); 
		 capabilities = new GLCapabilities(profile); 
		 canvas = new GLCanvas(capabilities); 
		 canvas.addGLEventListener(this);
		 add(canvas, BorderLayout.CENTER); 
		 


	 }

	@Override
	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2(); 
		gl.glBegin(GL2.GL_POINTS); 
		gl.glVertex2i(WIDTH/2 , HEIGHT/2); 
		gl.glEnd();

	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2(); 
		gl.glColor3f(0.0f, 1.0f, 0.0f);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int width,
			int height) {
		final GL2 gl = drawable.getGL().getGL2(); 

		gl.glMatrixMode(GL2.GL_PROJECTION); 
		gl.glLoadIdentity();
		gl.glOrtho(0, width, 0, height, -1.0, 1.0);

		
	}
	public static void main(String[]args)
	{
		LessonOne one = new LessonOne();
		one.setSize(WIDTH,HEIGHT);
		one.addWindowListener(new WindowAdapter() {      
			public void windowClosing(WindowEvent e) {      
			       System.exit(0);      
			       }
			});
			
		
		one.setVisible(true);
	}

}
