package entity;

import main.GamePanel;

public class Projectile extends Entity
{
    private Entity user;
    private int dX = 7;
    private int dY = 7;

    public Projectile(GamePanel gp)
    {
        super(gp);
    }

  public void set(int x, int y, String direction, boolean alive, Entity user)
  {
    setX(x);
    setY(y);
    setDirection(direction);
    this.user = user;
    setLife(getMaxLife());
    setAlive(true);
  }

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
            gp.monster[monsterIndex].setLife(gp.monster[monsterIndex].getLife() - gp.getPlayer().getDamage());
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
