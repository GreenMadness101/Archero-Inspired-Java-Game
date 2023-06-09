package entity;

import main.GamePanel;
/** Class for a projectile
 * @author Samarth
 * @author Ishan 
 */
public class Projectile extends Entity
{
    /*the user of the projectile */
    private Entity user;
    /*the forward velocity */
    private int dX = 7;
    /*upward velocity */
    private int dY = 7;
    /*Constructor for the projectile calls the super constructor in class Entity
     * @param gp   the game panel
     */
    public Projectile(GamePanel gp)
    {
        super(gp);
    }


  /** Uses the parameters to set the position of the projectile, set the user, and shoot at the monster without needing to aim at it
   *
   * @param playerX   x value of projectile
   * @param playerY   y value of projectile 
   * @param monsterX  x value of monster 
   * @param monsterY  y value of monster
   * @param alive     if projectile is still alive
   * @param user      the user of the projectile 
   */
  public void set(int playerX, int playerY, int monsterX, int monsterY, boolean alive, Entity user)
  {
    setX(playerX);
    setY(playerY);
    this.user = user;
    setLife(getMaxLife());
    setAlive(true);

    //ANGLE CALC
    double lengthX = monsterX - playerX;
    double lengthY = monsterY - playerY;
    double angle = Math.atan(lengthY/lengthX);
    //System.out.println(angle);
    dX = (int) (7*(Math.cos(angle)));
    dY = (int) (7*(Math.sin(angle)));
    dX = Math.abs(dX);
    dY = Math.abs(dY);
    if(monsterX <= playerX)
    {
      dX = dX* -1;
    }
    if(monsterY <= playerY)
    {
      dY = dY* -1;
    }


  }


    /** does collision for projectile with tiles and monsters it also causes the projectile to dissapear from the screen after its life is over and finally also decides 
    how fast the projectile animates  
    */
    public void update()
    {
      //collision for projectile   
      if(user == gp.getPlayer())
      {
          int monsterIndex = gp.getCollisionChecker().checkEntity(this, gp.monster);
          setCollisionOn(false);
          setCollisionDamage(false);
          gp.getCollisionChecker().checkTile(this);
        if(monsterIndex != 99 )
        {
          monsterIndex = gp.getCollisionChecker().checkEntity(this, gp.monster);
          if(monsterIndex != 999)
          {
            gp.monster.get(monsterIndex).setLife(gp.monster.get(monsterIndex).getLife() - gp.getPlayer().getDamage());
            setAlive(false);
          }
         
        }
        //Projectile Collision with Tiles
        if(getCollisionOn())
        {
          setAlive(false);
        }
      }

      setY(getY() + dY);
      setX(getX() + dX);
        
      // switch(getDirection())
      // {
      //     case "up":
      //     setY(getY() - getSpeed());
      //     break;
      //   case "down":
      //     setY(getY() + getSpeed());
      //     break;
      //   case "right":
      //     setX(getX() + getSpeed());
      //     break;
      //   case "left":
      //     setX(getX() - getSpeed());
      //     break;
      // }

      //causes the projectile to dissapear
      setLife(getLife() - 1);
      if(getLife()  <= 0)
      {
        setAlive(false);
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
