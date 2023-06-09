package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

/** the JPanel where everything occurs
 *
 * @author Ishan Voleti
 * @author Samarth Vysyaraju
 * 
 */
public class GamePanel extends JPanel implements Runnable
{
  // SCREEN SETTINGS
  /** original tile size  */
  final int originalTileSize = 16; //16x16 tile, default size
  /** for scaling the tiles */
  final int scale = 3;

  /** the tileSize */
  private final int tileSize = originalTileSize * scale; //48x48 tile

  /** maximum columns of tiles  */
  private final int maxScreenCol = 11;
  /** maximum rows of tiles */
  private final int maxScreenRow = 15;
  /** the width of the screen  */
  private final int screenWidth = tileSize * maxScreenCol; // 768 pixels // diff now due to change in num cols
  /** the height of the screen */
  private final int screenHeight = tileSize * maxScreenRow; // 576 // diff now due to change in num rows
  
  /** the frames per second  */
  int fps = 60;

  //SETUP
  /** creates the keyhandler */
  KeyHandler keyH = new KeyHandler();
  /** similar to a timer */
  Thread gameThread;
  /** the sound for the game  */
  Sound sound = new Sound();
  /** creates the assetsetter */
  AssetSetter aSetter = new AssetSetter(this);

  //COLLISION
  /** creates the collision checker */
  private CollisionChecker cChecker = new CollisionChecker(this);

 
  //** creates the player */
  private Player player = new Player(this, keyH);
  //** creates the arraylist for the monsters */
  public ArrayList<Entity> monster = new ArrayList<Entity>();

  //** creates an arraylist of entities  */
  private ArrayList<Entity> entityList = new ArrayList<Entity>();
  //** creates an arraylist of projectiles */
  private ArrayList<Entity> projectileList = new ArrayList<Entity>();


  //** creates the tilemanager for the tiles  */
  private TileManager tileM = new TileManager(this);

  /** constructor for the game panel which sets the size of the JPanel, sets the background color, and adds keyListener */
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

  /** sets up the game by playing music and adding the monsters  */
  public void setupGame()
  {
    //playMusic(0);
    aSetter.setMonster();

  }

  /** starts the timer for the game  */
  public void startGameThread()
  {
    gameThread = new Thread(this);
    gameThread.start();
  }

  /** determines how often it calls update and repaint and prints the fps */
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

  /** goes through the arraylist of monster and projectiles an updating them if they are still alive or removing them if they are dead */
  public void update()
  {
    player.update();

    for(int i  = 0; i < monster.size(); i++)
    {
      if(monster.get(i) != null)
      {
        if(monster.get(i).getAlive() && !monster.get(i).getDying())
        {
          monster.get(i).update();
        }
        if(!monster.get(i).getAlive())
        {
          monster.remove(i);
        }

      }
    }

    for(int i  = 0; i < projectileList.size(); i++)
    {
      if(projectileList.get(i) != null)
      {
        if(projectileList.get(i).getAlive())
        {
          projectileList.get(i).update();
        }
        if(!projectileList.get(i).getAlive())
        {
          projectileList.remove(i);
        }

      }
    }
  }

  /** adds characters and projectiles to entityList and draws the tiles and entites from the entityList
   * 
   * @param g
   */
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    //graphics 2D has more functions
    Graphics2D g2 = (Graphics2D)g;

    //make sure this is ahead because they are layered
    //tile should be on a layer below player
    tileM.draw(g2);

    entityList.add(player);

    for(int i = 0; i < monster.size(); i++)
    {
      if(monster.get(i) != null)
      {
        entityList.add(monster.get(i));
      }
    }

    for(int i = 0; i <projectileList.size(); i++)
    {
      if(projectileList.get(i) != null)
      {
        entityList.add(projectileList.get(i));
      }
    }

    //SORT
    Collections.sort(entityList, new Comparator<Entity>() 
    {

      @Override
      public int compare(Entity e1, Entity e2) {
        
        int result = Integer.compare(e1.getY(), e2.getY());

        return result;
      }
      
    });

    for(Entity e : entityList)
    {
      e.draw(g2);
    }
    entityList.clear();

    //this line just helps save memory, not needed
    g2.dispose();
  }

  /** plays the music for the game and loops it 
   * 
   * @param i  an integer for which music should be played
   */
  // public void playMusic(int i)
  // {
  //   sound.setFile(i);
  //   sound.play();
  //   sound.loop();
  // }
  
  /** stops the music */
  public void stopMusic()
  {
    sound.stop();
  }
  /**plays the sound effects */
  public void playerSE(int i)
  {
    sound.setFile(i);
    sound.play();
  }

  //GETTERS AND SETTERS

  //TILE SIZE
  public int getTileSize() 
  {
      return tileSize;
  }

  //MAX SCREENCOL ADN ROW
  public int getMaxScreenCol() 
  {
      return maxScreenCol;
  }
  public int getMaxScreenRow() 
  {
      return maxScreenRow;
  }

  //SCREEN WIDTH AND HEIGHT
  public int getScreenHeight() 
  {
      return screenHeight;
  }
  public int getScreenWidth() 
  {
      return screenWidth;
  }

  //COLLISION CHECKER
  public CollisionChecker getCollisionChecker()
  {
    return cChecker;
  }
  public void setCollisionChecker(CollisionChecker cChecker)
  {
    this.cChecker = cChecker;
  }

  //PLAYER
  public Player getPlayer() 
  {
    return player;
  }
  public void setPlayer(Player player) 
  {
    this.player = player;
  }

  //TILE MANAGER
  public TileManager getTileM() 
  {
      return tileM;
  }

  public ArrayList<Entity> getProjectileList()
  {
    return projectileList;
  }

  // public Entity[] getMonsterArr()
  // {
  //   return monster;
  // }
  


}
