package com.robotarm;

import javax.swing.JFrame;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLDrawable;
import com.jogamp.opengl.GLEventListener;

public class Arm extends JFrame implements GLEventListener
{
	 static int HEIGHT = 400, WIDTH = 400; 
	 static int cRadius = 2, flip = 1;
	 static int depth = 0; 
	 static float cVdata[][] = { {1.0f, 0.0f, 0.0f}, {0.0f, 1.0f, 0.0f}, {-1.0f, 0.0f, 0.0f}, {0.0f, -1.0f, 0.0f}  };
	 
	@Override
	public void display(GLAutoDrawable nn) {
		final GL2 gl = nn.getGL().getGL2();
		 if ((cRadius>(WIDTH/2))|| (cRadius==1)) 
		 {      flip = -flip;    
		 depth++;    
		 depth = depth%5;    }
		  cRadius += flip;
		  gl.glClear(GL.GL_COLOR_BUFFER_BIT|GL.GL_DEPTH_BUFFER_BIT);
		  gl.glRotatef(1, 1, 1, 1); 
		  gl.glPushMatrix();
		  gl.glScaled(cRadius, cRadius, cRadius); 
		  drawCone(gl);  
		  gl.glPopMatrix();
		    try { 
		    	Thread.sleep(10);    } 
		    catch (Exception ignore) {} 
		  
		
	}
	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void init(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void reshape(GLAutoDrawable glDrawable,int x, int y, int w, int h)  {
		 WIDTH = w; 
		 HEIGHT = h;
		 final GL2 gl = glDrawable.getGL().getGL2(); 
		 gl.glEnable(GL2.GL_DEPTH_TEST);
		 gl.glMatrixMode(GL2.GL_PROJECTION);   
	     gl.glLoadIdentity();
	     gl.glOrtho(-w/2, w/2, -h/2, h/2, -w, w);
	     gl.glMatrixMode(GL2.GL_MODELVIEW); 
	     gl.glLoadIdentity(); 
		 
		 
		
	}
	public void drawCone(GL2 gl) {
		subdivideCone(cVdata[0], cVdata[1], depth, gl);   
		subdivideCone(cVdata[1], cVdata[2], depth, gl);  
		subdivideCone(cVdata[2], cVdata[3], depth, gl);    
		subdivideCone(cVdata[3], cVdata[0], depth, gl);
		}
	  private void subdivideCone(float v1[],float v2[], int depth,GL2 gl) {  
		  float v0[] = {0, 0, 0};    float v12[] = new float[3];
	  
	    if (depth==0) {     
	    	gl.glColor3f(v1[0]*v1[0], v1[1]*v1[1], v1[2]*v1[2]);
	    
	      drawtriangle(v1, v2, v0,gl); 
	      v0[2] = 1;
	      drawtriangle(v1, v2, v0, gl);
	      return;
	    }
	    for (int i = 0; i<3; i++) {     
	    	v12[i] = v1[i]+v2[i];    }   
	    normalize(v12);
	    subdivideCone(v1, v12, depth-1, gl);   
	    subdivideCone(v12, v2, depth-1, gl); 
	  }
	  public void drawtriangle(float[] v1,float[] v2, float[] v3,GL2 gl) 
	  {    gl.glBegin(GL.GL_TRIANGLES);    
	 gl.glVertex3f(v1[0], v1[1], v1[2]);
	 gl.glVertex3f(v2[0], v2[1], v2[2]);
	 gl.glVertex3f(v3[0], v3[1], v3[2]);
	  gl.glEnd();  }
	  public void normalize(float vector[])
	  {    float d = (float)Math.sqrt(vector[0]*vector[0] +vector[1]*vector[1] + vector[2]*vector[2]);
	    if (d==0) 
	    {     
	    	System.out.println("0 length vector: normalize().");     
	    	return;   
	    	}    vector[0] /= d;   
	    	vector[1] /= d; 
	    	vector[2] /= d; 
	   }
	 
	
}