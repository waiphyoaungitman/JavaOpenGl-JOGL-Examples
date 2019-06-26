package com.lesson;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLDrawable;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.Animator;
import com.one.One;

public class LessonTwo extends com.lesson.LessonOne{
	 static Animator animator; 
	 LessonTwo()
	 {
		 
		 capabilities.setDoubleBuffered(false);
		 addWindowListener(new WindowAdapter()
		 {      
			 public void windowClosing(WindowEvent e) {       
				 animator.stop(); // stop animation   
				 System.exit(0);     
				 }   
			 }); 
			 }
	 public void init(GLAutoDrawable drawable) {   
		 final GL2 gl = drawable.getGL().getGL2(); 
		 gl.glColor3f(0.0f, 1.0f, 0.0f);
	  gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);    
	 gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
	 animator = new Animator(canvas);   
	 animator.start();
		 
}
	 public void display(com.jogamp.opengl.GLAutoDrawable drawable) {
		 final GL2 gl = drawable.getGL().getGL2(); 
		       double x = Math.random()*WIDTH;   
		       double y = Math.random()*HEIGHT;
		      gl.glBegin(GL2.GL_POINTS);    gl.glVertex2d(x, y);    gl.glEnd(); 
		      }
	 public static void main(String[] args) {
		LessonTwo two = new LessonTwo();
		two.setSize(WIDTH, HEIGHT);
		two.setVisible(true);
	}
		 
}
