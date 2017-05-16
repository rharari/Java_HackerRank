/**
 * Technique TI Ltd - Brazil
 * HackerRank
 * Mandelbrot2.java
 *
 * @author Ricardo A Harari - ricardo.harari@gmail.com
 * @date Apr 20, 2017
 *
 */

package other;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Mandelbrot2 extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final int MAX_ITER = 1000;
    private static double ZOOM = -50;
    int width = 400;
    int height = 300;
    private static BufferedImage I;
    int black = 0;
    private double zx, zy, cX, cY, tmp;
    int[] colors = new int[MAX_ITER];
 
    public Mandelbrot2() {
        super("Mandelbrot Set");
        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void draw(double dx, double dy) {
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                zx = zy = 0;
                cX = (x - width/2) / ZOOM + dx;
                cY = (y - height/2) / ZOOM + dy;
                int iter = 0;
                while (zx * zx + zy * zy < 4 && iter < MAX_ITER) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter++;
                }

                if (iter < MAX_ITER) I.setRGB(x, y, colors[iter]);
                	else I.setRGB(x, y, black);
                
            }
        }
    }
    
 
    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }
 
    public static void main(String[] args) {
        Mandelbrot2 obj = new Mandelbrot2();
        obj.calcColors();
        double dx = 0;
        double dy = 0;
        
        while (ZOOM < 300000) {
        	obj.draw(dx, dy);
        	dx -= 0.05;
        	dy -= 0.05;
        	if (dy < -0.48) {
        		dx = -0.48; dy = -0.48;
        	}
        	obj.setVisible(true);
        	obj.getGraphics().drawImage(I,  0, 0, obj);
        	ZOOM += 50;
        }
    }

	private void calcColors() {
        for (int i = 0; i<MAX_ITER; i++) {
            colors[i] = Color.HSBtoRGB(i/256f, 1, i/(i+4f));
        }
		
	}
}


