package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SpinnerDateModel;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Projectile;


public class Player extends Entity
{
  KeyHandler keyH;

  public Player(GamePanel gp, KeyHandler keyH)
  {
    super(gp);
    this.keyH = keyH;

    setName("player");

    //can adjust values to change collision
    setSolidArea(new Rectangle(8 , 16 , 32, 32));

    setDefaultValues();
    getPlayerImage();

  }

  public void setDefaultValues()
  {
    this.setX(240);
    this.setY(600);
    setSpeed(4);
    setDirection("down");

    //LIVES
    setMaxLife(100);
    setLife(getMaxLife());

    //CHANGE
    setProjectile(new OBJ_Projectile(gp));
  }

  public void getPlayerImage()
  {
    try 
    {
      //Update Image Files HERE (USE SIMILAR NAMING)
      // the /res/player/ is there due to the images being in a separate package
      setUp1(ImageIO.read(getClass().getResourceAsStream("/res/player/player_up_1.png")));
      setUp2(ImageIO.read(getClass().getResourceAsStream("/res/player/player_up_2.png")));
      setDown1(ImageIO.read(getClass().getResourceAsStream("/res/player/player_down_1.png")));
      setDown2(ImageIO.read(getClass().getResourceAsStream("/res/player/player_down_2.png")));
      setRight1(ImageIO.read(getClass().getResourceAsStream("/res/player/player_right_1.png")));
      setRight2(ImageIO.read(getClass().getResourceAsStream("/res/player/player_right_2.png")));
      setLeft1(ImageIO.read(getClass().getResourceAsStream("/res/player/player_left_1.png")));
      setLeft2(ImageIO.read(getClass().getResourceAsStream("/res/player/player_left_2.png")));
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }
  //delete
  public void getPlayerAttackImage()
  {
    try 
    {
      //Update Image Files HERE (USE SIMILAR NAMING)
      // the /res/player/ is there due to the images being in a separate package
      //use Utility Tool if scaling is needed
      setUp1(ImageIO.read(getClass().getResourceAsStream("/res/player-attack/player_attack_up_1.png")));
      setUp2(ImageIO.read(getClass().getResourceAsStream("/res/player-attack/player_attack_up_2.png")));
      setDown1(ImageIO.read(getClass().getResourceAsStream("/res/player-attack/player_attack_down_1.png")));
      setDown2(ImageIO.read(getClass().getResourceAsStream("/res/player-attack/player_attack_down_2.png")));
      setRight1(ImageIO.read(getClass().getResourceAsStream("/res/player-attack/player_attack_right_1.png")));
      setRight2(ImageIO.read(getClass().getResourceAsStream("/res/player-attack/player_attack_right_2.png")));
      setLeft1(ImageIO.read(getClass().getResourceAsStream("/res/player-attack/player_attack_left_1.png")));
      setLeft2(ImageIO.read(getClass().getResourceAsStream("/res/player-attack/player_attack_left_2.png")));
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }
  }



  public void update()
  {
    //delete
    if(keyH.getEnterPressed())
    {
      //setAttacking(true);
      //temp
      gp.monster[0].setDying(true);
      gp.getPlayer().setDying(false);
      System.out.println(gp.getPlayer().getDying());
    }

    if(keyH.getDownPressed() || keyH.getUpPressed() || keyH.getLeftPressed() || keyH.getRightPressed())
    {
      if(keyH.getUpPressed())
      {
        setDirection("up");
      }
      if(keyH.getLeftPressed())
      {
        setDirection("left");
      }
      if(keyH.getDownPressed())
      {setDirection("down");
      }
      if(keyH.getRightPressed())
      {
        setDirection("right");
      }

      // CHECK TILE COLLISION
      setCollisionOn(false);
      setCollisionDamage(false);
      gp.getCollisionChecker().checkTile(this);
      
      gp.getCollisionChecker().checkEntity(this, gp.monster);
      if(getCollisionDamage())
      {
        int count = 0;
        count++;
        System.out.println("blud");
        if(count > 10)
        {
          setLife(getLife() - 10);
          System.out.println(getLife());
          count =0;
        }
      }


      

     //STOP PLAYER MOVEMENT IF COLLISION
      if(!getCollisionOn())
      {
        switch(getDirection())
        {
          case "up":
            setY(getY() - getSpeed());
            //y -= speed;
            break;
          case "down":
            setY(getY() + getSpeed());
            //y += speed;
            break;
          case "right":
            setX(getX() + getSpeed());
            //x += speed;
            break;
          case "left":
            setX(getX() - getSpeed());
            //x -= speed;
            break;
        }
    }

      setSpriteCounter(getSpriteCounter() + 1);
      //change this value to decide how fast it animates
      if(getSpriteCounter() > 12)
      {
        setSpriteBool(!getSpriteBool());
        setSpriteCounter(0);
      }
    }

    if(keyH.getShotKeyPressed() && !getProjectile().getAlive() && getShotAvailableCounter() == 30)
    {
      //SET DEFAULT COORDINATED, DIRECTION, AND USER
      getProjectile().set(getX(), getY(), getDirection(), true, this);
      gp.getProjectileList().add(getProjectile());
      setShotAvailableCounter(0);
    }

    //If you shoot a fireball you cannot shoot another one for another 30 frames
    if(getShotAvailableCounter() < 30)
    {
      setShotAvailableCounter(getShotAvailableCounter() + 1);
    }
  }

  //delete
  public void attacking()
  {
    setSpriteCounter(getSpriteCounter() + 1);

    // if(getSpriteBool() <= 5)
    // {
     
    // }
  }

  public void draw(Graphics2D g2)
  {
    BufferedImage image = null;

    switch(getDirection())
    {
      case "up":
        if(getSpriteBool())
          image = getUp1();
        else
          image = getUp2();
        break;

      case "down":
        if(getSpriteBool())
          image = getDown1();
        else
          image = getDown2();
        break;

      case "right":
        if(getSpriteBool())
         image = getRight1();
        else
          image = getRight2();
        break;

      case "left":
        if(getSpriteBool())
          image = getLeft1();
        else
          image = getLeft2();
        break;
    }

    createPlayerHealthBar(g2);


    g2.drawImage(image, this.getX(), this.getY(), gp.getTileSize(), gp.getTileSize(), null);
  }

  public void createPlayerHealthBar(Graphics2D g2)
  {

    g2.setColor(Color.black);
    g2.fillRect(getX() - 6, getY() - 16, gp.getTileSize() + 12, 12);

    g2.setColor(new Color(31, 160, 22));
    double healthRatio = getLife()/getMaxLife();
    g2.fillRect(getX() - 5, getY() - 15, (int)((gp.getTileSize() + 10) * healthRatio), 10);
  }


}
