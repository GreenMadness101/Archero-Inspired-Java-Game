package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Entity 
{
    GamePanel gp;
    
    //IMAGES
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    //private BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    
    //IMAGE SWITCHER
    private int spriteCounter = 0;
    private boolean spriteBool = false;
    
    //COLLISION
    private Rectangle solidArea;
    private int solidAreaDefaultX;
    private int solidAreaDefaultY;
    
    private boolean collisionOn = false;
    
    //COUNTERS
    //USED IN SETACTION for Monster
    private int actionLockCounter = 0;
    private int dyingCounter = 0;
    private int damageCounter = 0;
    private int shootCounter = 0;
    private int teleportCounter = 100;
    
    private BufferedImage image1, image2, image3;
    private String name;
    private boolean collision = false;
    private boolean collisionDamage = false;
    
    //CHARACTER STATUS
    private int maxLife;
    private int life;
    private int x,y;
    private int speed;
    private String direction = "down";
    private int damage;

    private boolean alive = true;
    private boolean dying = false;

    //FOR PROJECTILE
    private Projectile projectile;
    private int shotAvailableCounter;

    


    public Entity(GamePanel gp)
    {
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.getTileSize(), gp.getTileSize());
    }

    public void setAction(){}

    public void update()
    {
        setAction();

        if(getLife() <= 0)
        {
            dying = true;
        }

        collisionOn = false;
        gp.getCollisionChecker().checkTile(this);
        gp.getCollisionChecker().checkBorder(this);
        //use when using objects
        //gp.cChecker.checkObject(this, false);
        gp.getCollisionChecker().checkPlayer(this);

        damageCounter++;
        if(collisionDamage && damageCounter > 40)
        {
            System.out.println("hi");
            gp.getPlayer().setLife(gp.getPlayer().getLife() - 5);
            collisionDamage = false;
            damageCounter = 0;
        }

        //STOP PLAYER MOVEMENT IF COLLISION
        if(!collisionOn)
        {
            if(direction == "up")
                y -= speed;
            if(direction == "down")
                y+= speed;
            if(direction == "right")
                x += speed;
            if(direction == "left")
                x -= speed;
            // switch(direction)
            // {
            // case "up":
            //     y -= speed;
            //     break;
            // case "down":
            //     y += speed;
            //     break;
            // case "right":
            //     x += speed;
            //     break;
            // case "left":
            //     x -= speed;
            //     break;
            // }
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

        //HP BAR MONSTER

        if(name.equals("monster"))
        {
            g2.setColor(Color.black);
            g2.fillRect(getX() - 1, getY() - 16, gp.getTileSize() + 2, 12);
            
            g2.setColor(new Color(255, 0, 30));
            //CHekc if x and y are right varaibles
            double healthRatio = ((double)getLife())/getMaxLife();
            g2.fillRect(x, y - 15, (int) (gp.getTileSize() * healthRatio), 10);
        }
        // if(name.equals("player"))
        // {
        //     createPlayerHealthBar(g2);
        // }

        if(dying)
        {
            dyingAnimation(g2);
        }
        
        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }

    public void dyingAnimation(Graphics2D g2)
    {
        dyingCounter++;
        if(dyingCounter <= 5)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > 5 && dyingCounter <= 10)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter > 10 && dyingCounter <= 15)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > 15 && dyingCounter <= 20)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter > 20 && dyingCounter <= 25)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }        
        if(dyingCounter > 30 && dyingCounter <= 25)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if(dyingCounter > 25 && dyingCounter <= 40)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if(dyingCounter > 40)
        {
            dying = false;
            alive = false;
        }
        
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
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public int getSolidAreaDefaultX() 
    {
        return solidAreaDefaultX;
    }
    public int getSolidAreaDefaultY() 
    {
        return solidAreaDefaultY;
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

    //LIFE
    public int getMaxLife() 
    {
        return maxLife;
    }
    public void setMaxLife(int maxLife) 
    {
        this.maxLife = maxLife;
    }

    public int getLife() 
    {
        return life;
    }
    public void setLife(int life) 
    {
        this.life = life;
    }

    //ACTION LOCK COUNTER
    public int getActionLockCounter() 
    {
      return actionLockCounter;
    }
    public void setActionLockCounter(int actionLockCounter) 
    {
      this.actionLockCounter = actionLockCounter;
    }

    //OBJECT STUFF
    public void setCollision(boolean collision) 
    {
      this.collision = collision;
    }
    public boolean getCollision()
    {
        return collision;
    }
    public BufferedImage getImage1() {
      return image1;
    }
    public BufferedImage getImage2() {
      return image2;
    }
    public BufferedImage getImage3() {
      return image3;
    }
    public void setImage1(BufferedImage image1) 
    {
      this.image1 = image1;
    }
    public void setImage2(BufferedImage image2) 
    {
      this.image2 = image2;
    }
    public void setImage3(BufferedImage image3) 
    {
      this.image3 = image3;
    }

    public String getName() 
    {
      return name;
    }
    public void setName(String name) 
    {
      this.name = name;
    }

    public Projectile getProjectile()
    {
        return projectile;
    }
    public void setProjectile(Projectile projectile)
    {
        this.projectile = projectile;
    }

    //ALIVE and DEAD
    public boolean getAlive()
    {
        return alive;
    }
    public void setAlive(boolean alive) 
    {
      this.alive = alive;
    }
    public boolean getDying()
    {
        return dying;
    }
    public void setDying(boolean dying) 
    {
      this.dying = dying;
    }
    public void setCollisionDamage(boolean collisionDamage) 
    {
        this.collisionDamage = collisionDamage;
    }
    public boolean getCollisionDamage()
    {
        return collisionDamage;
    }
    public int getShotAvailableCounter()
    {
        return shotAvailableCounter;
    }
    public void setShotAvailableCounter(int shotAvailableCounter)
    {
        this.shotAvailableCounter += shotAvailableCounter;
    }

    //DAMAGE COUNTER
    public void setDamageCounter(int damageCounter) 
    {
        this.damageCounter = damageCounter;
    }
    public int getDamageCounter() {
        return damageCounter;
    }

    //SHOOT COUNTER
    public int getShootCounter() 
    {
      return shootCounter;
    }
    public void setShootCounter(int shootCounter) 
    {
      this.shootCounter = shootCounter;
    }

    public void setDamage(int damage) 
    {
        this.damage = damage;
    }
    public int getDamage() 
    {
        return damage;
    }
    
    public void setTeleportCounter(int teleportCounter) 
    {
        this.teleportCounter = teleportCounter;
    }
    public int getTeleportCounter() 
    {
        return teleportCounter;
    }

 
    





}
