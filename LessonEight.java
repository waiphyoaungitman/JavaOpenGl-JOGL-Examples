package com.lesson;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class LessonEight extends Lesson7 {
	static float vdata[][] = { { 1.0f, 0.0f, 0.0f }, { 0.0f, 1.0f, 0.0f },
			{ -1.0f, 0.0f, 0.0f }, { 0.0f, -1.0f, 0.0f } };
	static int cnt = 1;
	private static float my2dMatStack[][][] = new float[24][3][3];
	private static int stackPtr = 0;
	static final float PI = 3.1415926f;

	public LessonEight() {
		capabilities.setDoubleBuffered(true);
	}

	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		float c[] = { 0, 0, 1 };
		float h[] = { 0, WIDTH / 6, 1 };
		long curTime;
		float ang, second, minute, hour;
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		curTime = System.currentTimeMillis() / 1000;
		second = curTime % 60;
		curTime = curTime / 60;
		minute = curTime % 60 + second / 60;
		curTime = curTime / 60;
		hour = (curTime % 12) + 8 + minute / 60;
		ang = PI * second / 30; //
		gl.glColor3f(1, 0, 0);
		my2dLoadIdentity();
		my2dTranslatef(c[0], c[1]);
		my2dRotatef(-ang);
		my2dTranslatef(-c[0], -c[1]);
		gl.glLineWidth(1);
		transDrawClock(c, h, gl);

		gl.glColor3f(0, 1, 0);
		my2dLoadIdentity();
		ang = PI * minute / 30;
		my2dTranslatef(c[0], c[1]);
		my2dScalef(0.8f, 0.8f);
		my2dRotatef(-ang);
		my2dTranslatef(-c[0], -c[1]);
		gl.glLineWidth(2);
		transDrawClock(c, h, gl);

		gl.glColor3f(0, 0, 1);
		my2dLoadIdentity();
		ang = PI * hour / 6;
		my2dTranslatef(c[0], c[1]);
		my2dScalef(0.5f, 0.5f);
		my2dRotatef(-ang);
		my2dTranslatef(-c[0], -c[1]);
		gl.glLineWidth(3);
		transDrawClock(c, h, gl);

	}

	public void transDrawTriangle(float[] v1, float[] v2, float[] v3, GL2 gl) {
		float v[][] = new float[3][3];
		my2dTransformf(v1, v[0]);
		my2dTransformf(v2, v[1]);
		my2dTransformf(v3, v[2]);
		gl.glBegin(GL.GL_TRIANGLES);
		gl.glVertex3f(v1[0], v1[1], v1[2]);
		gl.glVertex3f(v2[0], v2[1], v2[2]);
		gl.glVertex3f(v3[0], v3[1], v3[2]);
		gl.glEnd();
	}

	public void my2dTransformf(float v[], float v1[]) {
		float vertex[] = new float[3];
		vertex[0] = v[0];
		vertex[1] = v[1];
		vertex[2] = 1;
		my2dTransHomoVertex(vertex);
		v1[0] = vertex[0] / vertex[2];
		v1[1] = vertex[1] / vertex[2];
	}

	public void my2dTransHomoVertex(float vertex[]) {
		float vertex1[] = new float[3];
		my2dTransHomoVertex(vertex, vertex1);
		for (int i = 0; i < 3; i++) {
			vertex[i] = vertex1[i];
		}
	}

	public void my2dTransHomoVertex(float v[], float v1[]) {
		int i, j;
		for (i = 0; i < 3; i++) {
			v1[i] = 0.0f;
		}
		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				v1[i] += my2dMatStack[stackPtr][i][j] * v[j];
			}
		}
	}

	public void my2dLoadIdentity() {
		my2dIdentity(my2dMatStack[stackPtr]);
	}

	public void my2dScalef(float x, float y) {
		float S[][] = new float[3][3];
		my2dIdentity(S);
		S[0][0] = x;
		S[1][1] = y;
		my2dMultMatrix(S);
	}

	public void my2dRotatef(float angle) {
		float R[][] = new float[3][3];
		my2dIdentity(R);
		R[0][0] = (float) Math.cos(angle);
		R[0][1] = (float) -Math.sin(angle);
		R[1][0] = (float) Math.sin(angle);
		R[1][1] = (float) Math.cos(angle);
		my2dMultMatrix(R);
	}

	public void my2dTranslatef(float x, float y) {
		float T[][] = new float[3][3];
		my2dIdentity(T);
		T[0][2] = x;
		T[1][2] = y;
		my2dMultMatrix(T);

	}

	private void my2dIdentity(float mat[][]) {
		my2dClearMatrix(mat);
		for (int i = 0; i < 3; i++) {
			mat[i][i] = 1.0f;
		}
	}

	public void my2dMultMatrix(float mat[][]) {
		float matTmp[][] = new float[3][3];
		my2dClearMatrix(matTmp);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					matTmp[i][j] += my2dMatStack[stackPtr][i][k] * mat[k][j];
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				my2dMatStack[stackPtr][i][j] = matTmp[i][j];
			}
		}

	}

	private void my2dClearMatrix(float mat[][]) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				mat[i][j] = 0.0f;
			}
		}

	}

	public static void main(String[] args) {
		LessonEight eight = new LessonEight();
		eight.setTitle("Wai");
		eight.setSize(WIDTH, HEIGHT);
		eight.setVisible(true);
	}

	public void transDrawClock(float C[], float H[],GL2 gl) {
		float End1[] = new float[3];
		float End2[] = new float[3];
		my2dTransHomoVertex(C, End1);
		my2dTransHomoVertex(H, End2);
		gl.glBegin(GL.GL_LINES);
		gl.glVertex3f(End1[0],End1[1],End1[2]);
		gl.glVertex3f(End2[0],End2[1],End2[2]);
		gl.glEnd();
	}
}
