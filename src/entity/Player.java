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
    solidArea = new Rectangle(8 , 16 , 32, 32);

    setDefaultValues();
    getPlayerImage();

  }

  public void setDefaultValues()
  {
    x = 240;
    y = 600;
    speed = 4;
    direction = "down";
  }

  public void getPlayerImage()
  {
    try 
    {
      //Update Image Files HERE (USE SIMILAR NAMING)
      // the /res/player/ is there due to the images being in a separate package
      up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/player_up_1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/player_up_2.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/player_down_1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/player_down_2.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/player_right_1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/player_right_2.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/player_left_1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/player_left_2.png"));

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
        direction = "up";
      }
      else if(keyH.leftPressed)
      {
        direction = "left";
      }
      else if(keyH.downPressed)
      {
        direction = "down";
      }
      else if(keyH.rightPressed)
      {
        direction = "right";
      }

      // CHECK TILE COLLISION
      collisionOn = false;
      gp.cChecker.checkTile(this);

      

     //STOP PLAYER MOVEMENT IF COLLISION
      if(!collisionOn)
      {
        switch(direction)
        {
          case "up":
            y -= speed;
            break;
          case "down":
            y += speed;
            break;
          case "right":
            x += speed;
            break;
          case "left":
            x -= speed;
            break;
        }
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
