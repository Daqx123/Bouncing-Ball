package Ball;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;


 
public class BallThread implements Runnable {
	// Setting up Variables.
    int x, y, radius, yVelocity; 

    Color c;
 
    
    public BallThread(int x, int y, Color c, int Radius, int yspeed) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.radius = Radius;
        this.yVelocity = yspeed;
    }

    public void paintBall(Graphics g) {
        g.setColor(c);
        g.fillOval(x, y, radius, radius);
    }
    

    public void moveBall() {   
            if (y < 20 || y > 380)
		yVelocity = -yVelocity;
		y = y + yVelocity;
        }
     
    public void run() {
        try {
            while (true) {
                moveBall();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
