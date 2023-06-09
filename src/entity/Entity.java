package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

/** Class creates the entity object which makes up all of the characters in the game including: player and monsters and projectiles
 * @author Ishan Voleti
 * @author Samarth Vysyaraju
 */
public class Entity 
{
    /** Game Panel object, reference to JPanel which everything is on */
    GamePanel gp;
    
    /** Image objects for movement sprites of entities */
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    
    /** counter and boolean to switch sprites every few frames while moving */
    private int spriteCounter = 0;
    private boolean spriteBool = false;
    
    /** more specific rectangle in which collision occurs */
    private Rectangle solidArea;
    /** keeps track of original solid area locations */
    private int solidAreaDefaultX;
    private int solidAreaDefaultY;
    
    /** tracks if entity is colliding with something */
    private boolean collisionOn = false;
    
    /** multiple counters used to space out time between methods that occur */
    private int actionLockCounter = 0;
    private int dyingCounter = 0;
    private int damageCounter = 0;
    private int shootCounter = 0;
    private int teleportCounter = 100;
    
    /** categorizes entity */
    private String name;

    /** checks if entity is colliding with something that causes damage */
    private boolean collisionDamage = false;
    
    /** starting life and current life of entity */
    private int maxLife;
    private int life;

    /** location of entity */
    private int x,y;

    /** how fast entity moves */
    private int speed;

    /** direction entity is facing */
    private String direction = "down";

    /** amount of damage entity does */
    private int damage;

    /** tracks if entity is alive */
    private boolean alive = true;
    /** tracks if entity is dying */
    private boolean dying = false;

    /** projectile created by entity */
    private Projectile projectile;

    

    /** constructor for entity, assigns param to game panel reference, sets default solid area
     * 
     * @param gp game panel reference 
     */
    public Entity(GamePanel gp)
    {
        this.gp = gp;
        solidArea = new Rectangle(0, 0, gp.getTileSize(), gp.getTileSize());
    }

    /** Placeholder method for entity actions */
    public void setAction(){}

    /** updates method checks lives and collision to decide if movement can occur  */
    public void update()
    {
        //runs set action
        setAction();

        //checks if entity is dead
        if(getLife() <= 0)
        {
            dying = true;
        }

        //sets entity collision to false and checks collision
        collisionOn = false;
        gp.getCollisionChecker().checkTile(this);
        gp.getCollisionChecker().checkBorder(this);
        gp.getCollisionChecker().checkPlayer(this);

        //checks if players is losing damage
        damageCounter++;
        if(collisionDamage && damageCounter > 40)
        {
            gp.getPlayer().setLife(gp.getPlayer().getLife() - 10);
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
        }

        spriteCounter++;
        //change this value to decide how fast it animates
        if(spriteCounter > 12)
        {
            spriteBool = !spriteBool;
            spriteCounter = 0;
        }

    }

    /** draws entity depending on direction
     * 
     * @param g2 graphics variable 
     */
    public void draw(Graphics2D g2)
    {
        //sets image to null
        BufferedImage image = null;

        //checks direction variable which was set by KeyHandler
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
            // check if x and y are right variables
            double healthRatio = ((double)getLife())/getMaxLife();
            g2.fillRect(x, y - 15, (int) (gp.getTileSize() * healthRatio), 10);
        }


        //checks if dying variable is true
        if(dying)
        {
            dyingAnimation(g2);
        }
        
        //draw the entity
        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }

    /** changes the opacity of dying entity to represent death
     * 
     * @param g2 graphics variable
     */
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
