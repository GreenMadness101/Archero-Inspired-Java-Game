package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity 
{
    public int x,y;

    //update this value to make faster
    public int speed;

    //basically used to store image files
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public boolean spriteBool = false;

    public Rectangle solidArea;
    public boolean collisionOn = false;

    ////maybe use for changing the image when stopped
    //public boolean spriteStop = true;
    
}
