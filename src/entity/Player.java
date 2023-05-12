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


public class Player extends Entity
{

  KeyHandler keyH;

  public Player(GamePanel gp, KeyHandler keyH)
  {
    super(gp);
    this.keyH = keyH;

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

  public void update()
  {
    if(keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed)
    {
      if(keyH.upPressed)
      {
        setDirection("up");
      }
      else if(keyH.leftPressed)
      {
        setDirection("left");
      }
      else if(keyH.downPressed)
      {setDirection("down");
      }
      else if(keyH.rightPressed)
      {
        setDirection("right");
      }

      // CHECK TILE COLLISION
      setCollisionOn(false);
      gp.cChecker.checkTile(this);

      

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

    g2.drawImage(image, this.getX(), this.getY(), gp.getTileSize(), gp.getTileSize(), null);
  }

}
