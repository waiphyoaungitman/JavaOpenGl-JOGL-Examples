package com.lesson;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.gl2.GLUT;

public class LessonFive extends LessonFour
{
	 GLUT glut = new GLUT();
	 public void display(GLAutoDrawable drawable) {
		 final GL2 gl = drawable.getGL().getGL2();
		 int x0 = (int)(Math.random()*WIDTH); 
		 int y0 = (int)(Math.random()*HEIGHT);  
		 int xn = (int)((Math.random()*WIDTH));
		 int yn = (int)(Math.random()*HEIGHT);
		 gl.glColor3f(1, 1, 1); 
		 bresenhamLine(x0, y0, xn, yn,gl);
		 gl.glRasterPos3f(x0, y0, 0); 
		 glut.glutBitmapCharacter(GLUT.BITMAP_HELVETICA_12, 's');   
		 glut.glutBitmapString(GLUT.BITMAP_HELVETICA_12, "tart");
		    gl.glPushMatrix();  
		    gl.glTranslatef(xn, yn, 0);
		    gl.glScalef(0.2f, 0.2f, 0.2f);  
		    glut.glutStrokeCharacter(GLUT.STROKE_ROMAN, 'e'); 
		    glut.glutStrokeString(GLUT.STROKE_ROMAN, "nd");  
		    gl.glPopMatrix();
		    try {    
		    	Thread.sleep(100);   
		    	} catch (Exception ignore) {} 
		 
		 
		 
		 
	 }
	 public static void main(String[] args) {
		LessonFive five = new LessonFive();
		five.setSize(WIDTH,HEIGHT);
		five.setVisible(true);
	}

}
