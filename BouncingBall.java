package Ball;
import javax.swing.JFrame;
import java.util.Random;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Graphics;

 
public class BouncingBall extends JFrame implements Runnable, MouseListener{
	
	// Setting up variables
	Random rand = new Random();
	Color randomColor; // Variable to store random Colour.
	float r,g,b; // Variable to store Red, Green and Blue values of random colour. 
	BallThread ball; // Thread instance.
    Image ballImage; 
    BallThread balls[]; // Object array to store the object balls. 
    Graphics ballGraphics; 
    int mouseX, mouseY, ballNumber = 20, ballRadius = 20, ballVelocity = 2, currBall = 0; // This is self explanatory. 
    
    public static void main(String[] args) {
    	
        BouncingBall bal = new BouncingBall();
        bal.addMouseListener(bal);
        Thread d = new Thread(bal);
        d.start();
    }
    
    public void paintComponent(Graphics g) {
        for (int i = 0; i <= ballNumber-1; i++) {
            balls[i].paintBall(g);           
        }
        repaint();
    }
 
    public void paint(Graphics g) {
        ballImage = createImage(getWidth(), getHeight());
        ballGraphics = ballImage.getGraphics();
        paintComponent(ballGraphics);
        g.drawImage(ballImage, 0, 0, this);
        repaint();
    } 
    // This method Creates instance of JFrame components. 
    public BouncingBall() {
    	
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.PINK);

        ballUpdate();

    }
    //This method initialises the ball object array and populates it; with empty for now "ball slots"
    public void ballUpdate()
    {
    	balls = new BallThread[ballNumber];
    	for(int i=0; i<ballNumber; i++){
        balls[i] = new BallThread(0, 0, randomColor, 0, 0);
    	}


    }
    //This code calls BallThread method moveBall which moves the ball top-bottom. Look for moveBall() in ballThread class.
    public void move() {
        for (int i = 0; i <= ballNumber-1; i++) {
            balls[i].moveBall();    
        }
    }
    // Self Explanatory.
    public void run() {
        try {
 
            while (true) {
                move();
                Thread.sleep(5);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // This method generates random values for floats r,g,b which are later used in mouseClicked() for random colour.
    public void randomColor()
    {
    	r = rand.nextFloat();
    	g = rand.nextFloat();
    	b = rand.nextFloat();	
    }
    // This method deals with responding to user's mouse clicks. It takes mouse coordinates and 
    // passes them to currBall array.
    // This method also sets random colour for the ball.
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		randomColor();
		randomColor = new Color(r, g, b);
        balls[currBall] = new BallThread(mouseX, mouseY, randomColor, ballRadius, ballVelocity);
        currBall++;
		
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
}