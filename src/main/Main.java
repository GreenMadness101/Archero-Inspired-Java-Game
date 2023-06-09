package main;
import javax.swing.JFrame;

/** Creates JFrame and Timer, and sets up Game
 * 
 * @author Ishan Voleti
 * @author Samarth Vysyraju
 * 
 */
public class Main 
{
    public static void main(String[] args) 
    {
        //create JFrame and do default stuff
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        //basically extra functions
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //sizes the window to fit the size of the sub components (GamePanel)
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

        //not sure if this is the right place
        //SET UP GAME
        gamePanel.setupGame();

        
    }
}
