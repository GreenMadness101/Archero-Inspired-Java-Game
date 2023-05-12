package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity 
{
    GamePanel gp;
    public int x,y;

    //update this value to make faster
    public int speed;

    //basically used to store image files
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public boolean spriteBool = false;

    public Rectangle solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
    public boolean collisionOn = false;

    ////maybe use for changing the image when stopped
    //public boolean spriteStop = true;

    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setAction(){}

    public void update()
    {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);

        //use when using objects
        //gp.cChecker.checkObject(this, false);

        gp.cChecker.checkPlayer(this);

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
