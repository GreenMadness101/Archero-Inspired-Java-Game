import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) 
    {
        
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
    }
}
