package com.lesson;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class LessonThree extends LessonTwo{
	
	 public void display(GLAutoDrawable drawable) {
		    int x0, y0, xn, yn, dx, dy;
		    final GL2 gl = drawable.getGL().getGL2();
		    do { 
		    	x0 = (int) (Math.random()*WIDTH);  
		    	y0 = (int) (Math.random()*HEIGHT);   
		    	xn = (int) (Math.random()*WIDTH);
		    	yn = (int) (Math.random()*HEIGHT);  
		    	dx = xn-x0;   
		    	dy = yn-y0;
		      if (x0>xn) {       
		    	  dx = -dx;   
		    	  }   
		      if (y0>yn)
		      {   
		    	  dy = -dy;    
		    	  }    
		      } while (dy>dx);
		    gl.glColor3f(0, 1, 0); 
		    line(x0, y0, xn, yn,gl); 
		 
	 }
	  void line(int x0, int y0, int xn, int yn,GL2 gl) {   
		  int x;  
		  float m, y;
	    m = (float) (yn-y0)/(xn-x0);
	    x = x0;    y = y0;
	    while (x<xn+1) {
	           gl.glBegin(GL2.GL_POINTS);     
	           gl.glVertex2i(x, (int) y);      gl.glEnd();
	      x++;      y += m; 
	      }  }
	  public static void main(String[] args) {
		LessonThree three = new LessonThree();
		three.setSize(WIDTH,HEIGHT);
		three.setVisible(true);
		
	}
	 


}
