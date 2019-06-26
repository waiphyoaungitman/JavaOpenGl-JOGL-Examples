package com.lesson;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;

public class LessonSix extends LessonFive {
	private float r, g, b;

	public void display(GLAutoDrawable drawable) {
		final GL2 gl = drawable.getGL().getGL2();
		int x0 = (int) (Math.random() * WIDTH);
		int y0 = (int) (Math.random() * HEIGHT);
		int xn = (int) ((Math.random() * WIDTH));
		int yn = (int) (Math.random() * HEIGHT);
		r = (float) ((Math.random() * 9)) / 8;
		g = (float) ((Math.random() * 9)) / 8;
		b = (float) ((Math.random() * 9)) / 8;
		gl.glColor3f(r, g, b);
		antialiasedLine(x0, y0, xn, yn, gl);
		try {
			Thread.sleep(500);
		} catch (Exception ignore) {
		}
	}

	void antialiasedLine(int x0, int y0, int xn, int yn,GL2 gl) {
		int dx, dy, incrE, incrNE, d, x, y, flag = 0;
		float D = 0, sin_a, cos_a, sin_cos_a, Denom;
		if (xn < x0) {
			int temp = x0;
			x0 = xn;
			xn = temp;
			temp = y0;
			y0 = yn;
			yn = temp;
		}
		if (yn < y0) {
			y0 = -y0;
			yn = -yn;
			flag = 10;
		}
		dy = yn - y0;
		dx = xn - x0;
		if (dx < dy) {
			int temp = x0;
			x0 = y0;
			y0 = temp;
			temp = xn;
			xn = yn;
			yn = temp;
			temp = dy;
			dy = dx;
			dx = temp;
			flag++;
		}
		x = x0;
		y = y0;
		d = 2 * dy - dx;
		incrE = 2 * dy;
		incrNE = 2 * (dy - dx);
		Denom = (float) Math.sqrt((double) (dx * dx + dy * dy));
		sin_a = dy / Denom;
		cos_a = dx / Denom;
		sin_cos_a = sin_a - cos_a;
		while (x < xn + 1) {
			IntensifyPixel(x, y, D, flag, gl);
			IntensifyPixel(x, y + 1, D - cos_a, flag, gl);
			IntensifyPixel(x, y - 1, D + cos_a, flag, gl);

			x++;
			if (d <= 0) {
				D += sin_a;
				d += incrE;
			} else {
				D += sin_cos_a;
				y++;
				d += incrNE;

			}

		}
	}

	void IntensifyPixel(int x, int y, float D, int flag,GL2 gl) {
		float d, r1, g1, b1;
		if (D < 0) {
			d = -D;
		} else {
			d = D;
		}
		r1 = (float) (r * (1 - d / 1.5));
		g1 = (float) (g * (1 - d / 1.5));
		b1 = (float) (b * (1 - d / 1.5));
		gl.glColor3f(r1, g1, b1);
		writepixel(x, y, flag, gl);
	}
	public static void main(String[] args) {
		LessonSix six = new LessonSix();
		six.setSize(WIDTH,HEIGHT);
		six.setVisible(true);
	}

}
