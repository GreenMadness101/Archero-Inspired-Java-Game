package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.event.SwingPropertyChangeSupport;

import entity.Entity;
import entity.Player;

public class GamePanel extends JPanel implements Runnable
{
  // SCREEN SETTINGS
  final int originalTileSize = 16; //16x16 tile, default size
  final int scale = 3;

  //instead of making public maybe change to a method
  public final int tileSize = originalTileSize * scale; //48x48 tile

  //Change this ratio - rn 3 x 4
  final int maxScreenCol = 12;
  final int maxScreenRow = 16;
  final int screenWidth = tileSize * maxScreenCol; // 768 pixels
  final int screenHeight = tileSize * maxScreenRow; // 576 
  
  //FPS
  int fps = 60;

  //KEY HANDLER
  KeyHandler keyH = new KeyHandler();

  //TIMER
  //Similar to a timer
  Thread gameThread;

  //PLAYER
  Player player = new Player(this, keyH);

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

    player.draw(g2);

    //this line just helps save memory, not needed
    g2.dispose();
  }
}
