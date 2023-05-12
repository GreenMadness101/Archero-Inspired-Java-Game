package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.event.SwingPropertyChangeSupport;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable
{
  // SCREEN SETTINGS
  final int originalTileSize = 16; //16x16 tile, default size
  final int scale = 3;

  //instead of making public maybe change to a method
  public final int tileSize = originalTileSize * scale; //48x48 tile

  //Change this ratio - rn 3 x 4
  public final int maxScreenCol = 11;
  public final int maxScreenRow = 15;
  public final int screenWidth = tileSize * maxScreenCol; // 768 pixels // diff now due to change in num cols
  public final int screenHeight = tileSize * maxScreenRow; // 576 // diff now due to change in num rows
  
  //FPS
  int fps = 60;

  //KEY HANDLER
  KeyHandler keyH = new KeyHandler();

  //TIMER
  //Similar to a timer
  Thread gameThread;

  //SOUND
  Sound sound = new Sound();

  //COLLISION
  public CollisionChecker cChecker = new CollisionChecker(this);

  //PLAYER
  public Player player = new Player(this, keyH);

  //TILES
  TileManager tileM = new TileManager(this);

  //CONSTRUCTOR
  public GamePanel()
  {
    //maybe change this to this.setBounds if Dimension class isn't offering anything
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);

    // basically allows all the drawing in the component to be done in an offscreen painting buffer
    //basically improves the rendering performance so for our game we might not need but might as well keep
    this.setDoubleBuffered(true);

    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  //not yet sure what this is for
  public void setupGame()
  {
    playMusic(0);

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
    //1 second
    //draws every 1/60 second
    double drawInterval = 1000000000/fps;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;
    long timer = 0;
    int drawCount = 0;

    //while the gamethread exists it repeats
    while(gameThread != null)
    {
      //the current time in nanoseconds
      //allows to keep track of how long the loops runs
      currentTime = System.nanoTime();

      delta += (currentTime - lastTime) / drawInterval;
      timer+= (currentTime - lastTime);
      lastTime = currentTime;

      if(delta >= 1)
      {
        //update information like charcter positions
        update();
        //draw the screen with the updated info 
        repaint();
        delta--;
        drawCount++;
      }

      if(timer >= 1000000000)
      {
        System.out.println("FPS:" + drawCount);
        drawCount = 0;
        timer = 0;
      }
    }
  }

  public void update()
  {
    player.update();
  }

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    //graphics 2D has more functions
    Graphics2D g2 = (Graphics2D)g;

    //make sure this is ahead because they are layered
    //tile should be on a layer below player
    tileM.draw(g2);

    player.draw(g2);

    //this line just helps save memory, not needed
    g2.dispose();
  }

  public void playMusic(int i)
  {
    sound.setFile(i);
    sound.play();
    sound.loop();
  }

  public void stopMusic()
  {
    sound.stop();
  }
  //play sound effect
  public void playerSE(int i)
  {
    sound.setFile(i);
    sound.play();
  }
}
