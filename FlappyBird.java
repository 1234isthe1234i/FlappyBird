import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

@SuppressWarnings("unused")
public class FlappyBird extends JFrame {
    private GameRunner gr;
    private boolean gameRunning = true;
    /**
     * Initializes the window and sets its attributes. 
     * Creates an instance of the game, adds it to the window, and starts the inital game loop.
     */
    public FlappyBird(){
        setSize(800, 800);
        setTitle("FlappyBird");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        gr = new GameRunner(this);
        add(gr);
        setVisible(true);
        runGame();
    }
    /**
     * Creates an instance of the window, which holds the game. 
     * @param args A list of strings that is passed in by the compiler when the code is run
     */
    public static void main(String[] args) {
        FlappyBird fb = new FlappyBird();
    }
    /**
     * Holds the game loop and runs it. 
     * When the game ends, repaints the screen to display an end screen.
     */
    public void runGame() {
        while (gameRunning){
            gr.repaint();
            
            try {Thread.sleep(45);}
            catch (InterruptedException e) {e.printStackTrace();}
            gr.move();
            gameRunning = gr.gameEnd();
        }
        gr.repaint();
    }
}

