import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

@SuppressWarnings("unused")

public class Pipe {
    private GameRunner gr;
    private int xPos;
    private int yPos;
    private final int WIDTH = 80;
    private final int HEIGHT = 500;
    private final int xSpeed = 8;
    private String orientation;
    private Color color = Color.getColor("dark green", Color.getHSBColor((float) 1.36, (float) 0.91, (float) 0.51));

    /**
     * Creates a Pipe using the paramaters passed in.
     * @param g The game logic instance that the pipe is displayed on.
     * @param x The X-Position of the Pipe.
     * @param y The Y-Position of the Pipe. 
     * @param o The Orientation of the Pipe. 
     */
    public Pipe(GameRunner g, int x, int y, String o) {
        gr = g;
        xPos = x;
        yPos = y;
        orientation = o;
    }

    /**
     * Paints the pipe onto the screen after determining its orientation. 
     * @param g The Graphics context in which to paint. 
     */
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(xPos+10, yPos, WIDTH-20, HEIGHT);

        if (orientation == "UP"){
            g.fillRect(xPos, yPos + HEIGHT - 20, WIDTH, 20);
            g.setColor(Color.black);
            g.drawRect(xPos, yPos + HEIGHT - 20, WIDTH, 20);
        }
        if (orientation == "DOWN"){
            g.fillRect(xPos, yPos, WIDTH, 20);
            g.setColor(Color.black);
            g.drawRect(xPos, yPos, WIDTH, 20);
        }
    }
    
    /**
     * Moves the Pipe's X-Position to the left using a 
     */
    public void move() {
        xPos -= xSpeed;
    }

    public int getX() {
        return xPos;
    }

    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }
}
