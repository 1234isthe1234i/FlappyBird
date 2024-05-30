import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GameRunner extends JPanel {
    private FlappyBird fb;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private Random r = new Random();
    private int points = 0;
    private boolean gameStarted = false;

    /**
     * Creates the initial parts of the game --bird & pipes--, links the w, space, and up_arrow key to a function which moves the bird.
     * Also makes it so that the first time these keys are pressed is when the game starts. 
     * @param f The instance of the window
     */
    public GameRunner(FlappyBird f) {
        fb = f;
        setBounds(0, 0, fb.getWidth(), fb.getHeight());
        setFocusable(true);
        bird = new Bird(this, 100, (int) (getHeight() * 0.5 - 20));
        pipes = new ArrayList<Pipe>();
        getPipes();
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {if (e.getKeyCode() == 38 || e.getKeyCode() == 32 || e.getKeyCode() == 87) gameStarted = true;}
            @Override
            public void keyReleased(KeyEvent e){if (e.getKeyCode() == 38 || e.getKeyCode() == 32 || e.getKeyCode() == 87) bird.keyReleased();}
        });
    }

    /**
     * A function that is called automatically. It is overriden to display the background, bird, pipes, and points. 
     * Also displays an end screen at the end of the game. 
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Times New Roman", 1, 50));
        if (bird.getBounds().y + bird.getBounds().width >= getHeight() || bird.getBounds().y <= 0 ||
            ((pipes.get(0).getBounds().intersects(bird.getBounds()) || pipes.get(1).getBounds().intersects(bird.getBounds())) && (bird.getBounds().getMaxX() - 25 >= pipes.get(0).getX()))) {
              setBackground(Color.red);
              g.drawString(String.valueOf(points), getWidth() / 2 - 50, getHeight() / 2);
        } 
        else {
            try {g.drawImage(ImageIO.read(new File("images\\background.jpg")), 0, 0, getWidth(), (int) (getHeight() * 0.85), this);} 
            catch (IOException e) {}
            try {g.drawImage(ImageIO.read(new File("images\\base.png")), 0, (int) (getHeight() * 0.8), getWidth(), (int) (getHeight() * 0.15), this);} 
            catch (IOException e) {}
            g.setColor(Color.orange);
            g.fillOval(-100, -100, 200, 200);

            bird.paint(g);
            try {for (Pipe pipe : pipes) pipe.paint(g);} 
            catch (ConcurrentModificationException e) {}
            g.drawString(String.valueOf(points), 10, 40);
        }

    }

    /**
     * Adds two new pipes to the game and randomly generates them in a certain height threshold. 
     */
    public void getPipes() {
        int upHeight = r.nextInt(-400, -200);
        int downHeight = upHeight + 500 + 250;
        pipes.add(new Pipe(this, getWidth() - 10, (int) upHeight, "UP"));
        pipes.add(new Pipe(this, getWidth() - 10, (int) downHeight, "DOWN"));
    }

    /**
     * Makes sure that the bird and pipes move on the screen.
     * Also checks when more pipes should be added or removed and perfoms these actions. 
     */
    public void move() {
        if (gameStarted) {
            for (int i = 0; i < pipes.size(); i++) {
                if (pipes.get(i).getX() <= -80) {
                    pipes.remove(i);
                    break;
                }
                if (pipes.get(i).getX() == 494) {
                    getPipes();
                    break;
                }
            }
            if (pipes.get(0).getX() == bird.getBounds().getCenterX()) points++;
            for (Pipe p : pipes) p.move();
            bird.move();
        }
    }

    /**
     * Checks to see if the bird has hit the top or bottom borders, or the pipes. 
     * This check determines if the game should keep running or end. 
     * @return Returns true or false based on the condition mentioned above. 
     */
    public boolean gameEnd() {
        if (bird.getBounds().y + bird.getBounds().width >= getHeight() || bird.getBounds().y <= 0 ||
            ((pipes.get(0).getBounds().intersects(bird.getBounds()) || pipes.get(1).getBounds().intersects(bird.getBounds())) && (bird.getBounds().getMaxX() - 25 >= pipes.get(0).getX())))
              return false;
        return true;
    }
}
