package com.lesson;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class LessonFour extends LessonThree {
	  public void display(GLAutoDrawable drawable) {
		  final GL2 gl = drawable.getGL().getGL2();
    int x0 = (int) (Math.random()*WIDTH); 
    int y0 = (int) (Math.random()*HEIGHT); 
    int xn = (int) ((Math.random()*WIDTH));  
    int yn = (int) (Math.random()*HEIGHT);
		       gl.glColor3f(1, 1, 1);   
		       bresenhamLine(x0, y0, xn, yn,gl);  
		       }
	static  void bresenhamLine(int x0, int y0, int xn, int yn,GL2 gl) {  
		  int dx, dy, incrE, incrNE, d, x, y, flag = 0;
	  
	    if (xn<x0) {     
	    	int temp = x0;
	    	 x0 = xn;
	    	 xn = temp;
	    	 temp = y0; y0 = yn; yn = temp;    
	    	 }   
	    if (yn<y0) {    
	    	y0 = -y0; yn = -yn;
	    	flag = 10; 
	    	} 
	    dy = yn-y0; dx = xn-x0;
	    if (dx<dy) {      
	    	int temp = x0;    
	    	x0 = y0; y0 = temp;
	    temp = xn; xn = yn; yn = temp;
	     temp = dy; dy = dx; dx = temp;  
	     flag++;
	     }
	    x = x0; y = y0; d = 2*dy-dx;    incrE = 2*dy; incrNE = 2*(dy-dx);
	    while (x<xn+1) {    
	    	writepixel(x, y, flag,gl); 
	      x++; 
	      if (d<=0) {
	      d += incrE;
	      }
	      else
	      {
	    	  y++; d += incrNE;
	    	  }   
	      }
	   
	    }
	  static void writepixel(int x, int y, int flag,GL2 gl) {
		    gl.glBegin(GL2.GL_POINTS); 
		    if (flag==0) {    
		    gl.glVertex2i(x, y); 
		    } 
		    else if (flag==1) { 
		    	gl.glVertex2i(y, x); 
		    	} else if (flag==10) { 
		    		gl.glVertex2i(x, -y);  
		    		} else if (flag==11) {   
		    			gl.glVertex2i(y, -x);
		    }
		    gl.glEnd();
		    }
	  public static void main(String[]args)
	  {
		  LessonFour four = new LessonFour();
		  four.setTitle("Four");
		  four.setSize(WIDTH,HEIGHT);
		  four.setVisible(true);
	  }
		 

}
