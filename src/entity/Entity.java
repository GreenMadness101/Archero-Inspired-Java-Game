package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity 
{
    GamePanel gp;
    private int x,y;

    //update this value to make faster
    private int speed;

    //basically used to store image files
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private String direction;

    private int spriteCounter = 0;
    private boolean spriteBool = false;

    private Rectangle solidArea;
    private boolean collisionOn = false;

    ////maybe use for changing the image when stopped
    //public boolean spriteStop = true;

    public Entity(GamePanel gp)
    {
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.getTileSize(), gp.getTileSize());
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

        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }

    //GETTER AND SETTER FUNCTIONS
    //x and y
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }

    //speed
    public int getSpeed()
    {
        return speed;
    }
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    //BUFFERED IMAGE
    public BufferedImage getUp1()
    {
        return up1;
    }
    public void setUp1(BufferedImage image)
    {
        up1 = image;
    }
    public BufferedImage getUp2()
    {
        return up2;
    }
    public void setUp2(BufferedImage image)
    {
        up2 = image;
    }
    public BufferedImage getDown1()
    {
        return down1;
    }
    public void setDown1(BufferedImage image)
    {
        down1 = image;
    }
    public BufferedImage getDown2()
    {
        return down2;
    }
    public void setDown2(BufferedImage image)
    {
        down2 = image;
    }
    public BufferedImage getLeft1()
    {
        return left1;
    }
    public void setLeft1(BufferedImage image)
    {
        left1 = image;
    }
    public BufferedImage getLeft2()
    {
        return left2;
    }
    public void setLeft2(BufferedImage image)
    {
        left2 = image;
    }
    public BufferedImage getRight1()
    {
        return right1;
    }
    public void setRight1(BufferedImage image)
    {
        right1 = image;
    }
    public BufferedImage getRight2()
    {
        return right2;
    }
    public void setRight2(BufferedImage image)
    {
        right2 = image;
    }

    //DIRECTION
    public String getDirection() 
    {
        return direction;
    }
    public void setDirection(String direction) 
    {
        this.direction = direction;
    }

    //SPRITE COUNTER
    public int getSpriteCounter() 
    {
        return spriteCounter;
    }
    public void setSpriteCounter(int spriteCounter) 
    {
        this.spriteCounter = spriteCounter;
    }

    //SPRITE BOOL
    public boolean getSpriteBool()
    {
        return spriteBool;
    }
    public void setSpriteBool(boolean spriteBool) 
    {
        this.spriteBool = spriteBool;
    }

    //SOLID AREA
    public Rectangle getSolidArea() 
    {
        return solidArea;
    }
    public void setSolidArea(Rectangle solidArea) 
    {
        this.solidArea = solidArea;
    }

    //COLLISION ON
    public void setCollisionOn(boolean collisionOn) 
    {
        this.collisionOn = collisionOn;
    }
    public boolean getCollisionOn()
    {
        return collisionOn;
    }

    
}
