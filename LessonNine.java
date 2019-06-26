package com.lesson;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLDrawable;

public class LessonNine extends LessonEight implements MouseMotionListener {
	private static float P1[] = { -WIDTH / 4, -HEIGHT / 4 };
	private float sx = 1, sy = 1;

	@Override
	public void mouseDragged(MouseEvent e) {
		float wd1 = WIDTH / 2;
		float ht1 = HEIGHT / 2;
		P1[0] = e.getX() - WIDTH / 2;
		P1[1] = HEIGHT / 2 - e.getY();
		float wd2 = WIDTH / 4 - P1[0];
		float ht2 = HEIGHT / 4 - P1[1];
		sx = wd2 / wd1;
		sy = ht2 / ht1;

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		((Component) drawable).addMouseMotionListener(this);
	}

	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		float c[] = { 0, 0, 1 };
		float h[] = { 0, WIDTH / 6, 1 };
		float v0[] = { -WIDTH / 4, -HEIGHT / 4 };
		float v1[] = { WIDTH / 4, HEIGHT / 4 };
		my2dLoadIdentity();
		my2dTranslatef(P1[0], P1[1]);
		my2dScalef(sx, sy);
		my2dTranslatef(-v0[0], -v0[1]);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		gl.glColor3f(1, 1, 1);
		float v00[] = new float[2], v11[] = new float[2];
		my2dTransformf(v0, v00);
		my2dTransformf(v1, v11);
		gl.glBegin(GL2.GL_LINE_LOOP);
		gl.glVertex3f(v00[0], v00[1], 0);
		gl.glVertex3f(v11[0], v00[1], 0);
		gl.glVertex3f(v11[0], v11[1], 0);
		gl.glVertex3f(v00[0], v11[1], 0);
		gl.glEnd();
		long curTime;
		float ang, hsecond, hminute, hhour,hAngle;
		curTime = System.currentTimeMillis() / 1000;
		hsecond = curTime % 60;
		curTime = curTime / 60;
		hminute = curTime % 60 + hsecond / 60;
		curTime = curTime / 60;
		hhour = (curTime % 12) + 8 + hminute / 60;
		hAngle = PI * hsecond / 30; // arc angle
		gl.glColor3f(1, 0, 0);
		my2dTranslatef(c[0], c[1]);
		my2dRotatef(-hAngle);
		my2dTranslatef(-c[0], -c[1]);
		gl.glLineWidth(3);
		transDrawClock(c, h, gl);
		gl.glColor3f(0, 1, 0);
		my2dLoadIdentity();
		my2dTranslatef(P1[0], P1[1]);
		my2dScalef(sx, sy);
		my2dTranslatef(-v0[0], -v0[1]);
		hAngle = PI * hminute / 30;
		my2dTranslatef(c[0], c[1]);
		my2dScalef(0.8f, 0.8f);
		my2dRotatef(-hAngle);
		my2dTranslatef(-c[0], -c[1]);
		gl.glLineWidth(5);
		transDrawClock(c, h, gl);
		gl.glColor3f(0, 0, 1);
		my2dLoadIdentity();
		my2dTranslatef(P1[0], P1[1]);
		my2dScalef(sx, sy);
		my2dTranslatef(-v0[0], -v0[1]);
		hAngle = PI * hhour / 6;
		my2dTranslatef(c[0], c[1]);
		my2dScalef(0.5f, 0.5f);
		my2dRotatef(-hAngle);
		my2dTranslatef(-c[0], -c[1]);
		gl.glLineWidth(7);
		transDrawClock(c, h, gl);

	}
	public static void main(String[] args) {
		LessonNine nine = new LessonNine();
		nine.setSize(500,500);
		nine.setTitle("JOGL LESSON");
		nine.setVisible(true);
		
	}

}
