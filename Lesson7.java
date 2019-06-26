package com.lesson;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class Lesson7 extends LessonSix {
	static int depth = 0;
	static int cRadius = 2, flip = 1;
	static float cVdata[][] = { { 1.0f, 0.0f, 0.0f }, { 0.0f, 1.0f, 0.0f },
			{ -1.0f, 0.0f, 0.0f }, { 0.0f, -1.0f, 0.0f } };
	 
	 

	public Lesson7() {
		capabilities.setDoubleBuffered(true);
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
		WIDTH = w;
		HEIGHT = h;
		final GL2 gl = drawable.getGL().getGL2();
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(-w / 2, w / 2, -h / 2, h / 2, -1, 1);
	}

	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		if (cRadius >= (HEIGHT / 2) || cRadius == 1) {
			flip = -flip;
			depth++;
			depth = depth % 7;

		}
		cRadius += flip;
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		drawCircle(cRadius, depth, gl);

		try {
			Thread.sleep(15);
		} catch (Exception ignore) {
		}
	}

	public void drawCircle(int cRadius, int depth, GL2 gl) {
		subdivideCircle(cRadius, cVdata[0], cVdata[1], depth, gl);
		subdivideCircle(cRadius, cVdata[1], cVdata[2], depth, gl);
		subdivideCircle(cRadius, cVdata[2], cVdata[3], depth, gl);
		subdivideCircle(cRadius, cVdata[3], cVdata[0], depth, gl);
	}

	private void subdivideCircle(int radius, float[] v1, float[] v2, int depth,
			GL2 gl) {
		float v11[] = new float[3];
		float v22[] = new float[3];
		float v00[] = { 0, 0, 0 };
		float v12[] = new float[3];
		if (depth == 0) {
			gl.glColor3f(v1[0] * v1[0], v1[1] * v1[1], v1[2] * v1[2]);
			for (int i = 0; i < 3; i++) {
				v11[i] = v1[i] * radius;
				v22[i] = v2[i] * radius;
			}
			drawtriangle(v11, v22, v00, gl);
			return;
		}
		v12[0] = v1[0] + v2[0];
		v12[1] = v1[1] + v2[1];
		v12[2] = v1[2] + v2[2];
		normalize(v12);
		subdivideCircle(radius, v1, v12, depth - 1, gl);
		subdivideCircle(radius, v12, v2, depth - 1, gl);
	}

	public void normalize(float vector[]) {
		float d = (float) Math.sqrt(vector[0] * vector[0] + vector[1]
				* vector[1] + vector[2] * vector[2]);
		if (d == 0) {
			System.out.println("0 length vector: normalize().");
			return;
		}
		vector[0] /= d;
		vector[1] /= d;
		vector[2] /= d;
	}

	public void drawtriangle(float[] v1, float[] v2, float[] v3,GL2 gl) {
		gl.glBegin(GL2.GL_TRIANGLES);
		 gl.glVertex3f(v1[0], v1[1], v1[2]);
		 gl.glVertex3f(v2[0], v2[1], v2[2]);
		 gl.glVertex3f(v3[0], v3[1], v3[2]);
		gl.glEnd();
	}
	public static void main(String[] args) {
		Lesson7 seve = new Lesson7();
		seve.setSize(WIDTH,HEIGHT);
		seve.setTitle("JOGL LESSON");
		seve.setVisible(true);
	}

}
