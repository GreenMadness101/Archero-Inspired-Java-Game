package entity;

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
    public int spriteNum = 1;
    public boolean spriteBool = false;
    
}
