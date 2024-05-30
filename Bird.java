import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
    private GameRunner gr;
    private int xPos;
    private int yPos;
    private int ySpeed = 0;
    private int yAcc = -3;
    private BufferedImage img;

    /**
     * Creates the bird by creating an image to display, and setting its position based on the parameters passed in.
     * @param g The game logic instance that the bird is displayed on. 
     * @param x The X-Position of the bird.
     * @param y The Y-Position of the bird.
     */
    public Bird(GameRunner g, int x, int y){
        gr = g;
        xPos = x;
        yPos = y;
        try {img = ImageIO.read(new File("images\\bird.png"));} catch (IOException e) {}
    }
    
    /**
     * Draws the bird image onto the screen.
     * @param g The Graphics context in which to paint. 
     */
    public void paint(Graphics g){
        g.drawImage(img, xPos, yPos, gr);
    }

    /**
     * Gives the bird an upward velocity when a key has released. 
     */
    public void keyReleased() {
        ySpeed = -24;
    }

    /**
     * Moves the bird regardless of when a key has been released. Creates gravity and upward movement. 
     */
    public void move() {
        yPos += ySpeed;
        ySpeed -= yAcc;        
    }

    /**
     * Defines the bounds of the bird. 
     * @return Rectangle holding the bounds of the bird. 
     */
    public Rectangle getBounds() {
        return new Rectangle(xPos, yPos, img.getWidth(), img.getHeight());
    }
}
