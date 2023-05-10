package entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SpinnerDateModel;

import main.GamePanel;
import main.KeyHandler;


public class Player extends Entity
{

  GamePanel gp;
  KeyHandler keyH;



  public Player(GamePanel gp, KeyHandler keyH)
  {
    this.gp =  gp;
    this.keyH = keyH;

    setDefaultValues();
    getPlayerImage();

  }

  public void setDefaultValues()
  {
    x = 100;
    y = 100;
    speed = 4;
    direction = "down";
  }

  public void getPlayerImage()
  {
    try 
    {
      //Update Image Files HERE (USE SIMILAR NAMING)
      // the /res/player/ is there due to the images being in a separate package
      up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));

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
        y -= speed;
        direction = "up";
      }
      else if(keyH.leftPressed)
      {
        x -= speed;
        direction = "left";
      }
      else if(keyH.downPressed)
      {
        y += speed;
        direction = "down";
      }
      else if(keyH.rightPressed)
      {
        x += speed;
        direction = "right";
      }

      spriteCounter++;
      //change this value to decide how fast it animates
      if(spriteCounter > 12)
      {
        spriteBool = !spriteBool;
        spriteCounter = 0;
      }
    }
    

  }

  public void draw(Graphics2D g2)
  {
    BufferedImage image = null;

    switch(direction)
    {
      case "up":
        if(spriteBool)
          image = up1;
        else
          image = up2;
        break;

      case "down":
        if(spriteBool)
          image = down1;
        else
          image = down2;
        break;

      case "right":
        if(spriteBool)
         image = right1;
        else
          image = right2;
        break;

      case "left":
        if(spriteBool)
          image = left1;
        else
          image = left2;
        break;
    }

    g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
  }

}
