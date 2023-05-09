import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
  // SCREEN SETTINGS
  final int originalTileSize = 16; //16x16 tile, default size
  final int scale = 3;

  final int tileSize = originalTileSize * scale; //48x48 tile

  //Change this ratio - rn 3 x 4
  final int maxScreenCol = 16;
  final int maxScreenRow = 12;
  final int screenWidth = tileSize * maxScreenCol; // 768 pixels
  final int screenHeight = tileSize * maxScreenRow; // 576 pixels

  //Similar to a timer
  Thread gameThread;

  //CONSTRUCTOR
  public GamePanel()
  {
    //maybe change this to this.setBounds if Dimension class isn't offering anything
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);

    // basically allows all the drawing in the component to be done in an offscreen painting buffer
    //basically improves the rendering performance so for our game we might not need but might as well keep
    this.setDoubleBuffered(true);

  }

  //THREAD METHODS
  public void startGameThread()
  {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() 
  {
    
  }
}
