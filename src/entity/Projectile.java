package entity;

import main.GamePanel;

public class Projectile extends Entity
{
    private Entity user;
    public Projectile(GamePanel gp)
    {
        super(gp);
    }

    public void set(int x, int y, String direction, boolean alive, Entity user){
        setX(x);
        setY(y);
        setDirection(direction);
        this.user = user;
        setLife(getMaxLife());
        setAlive(true);


    }

    public void update(){
      //collision for projectile   
      if(user == gp.getPlayer())
      {
          boolean monsterIndex = gp.getCollisionChecker().checkEntity(this, gp.monster);
          setCollisionOn(false);
          setCollisionDamage(false);
          gp.getCollisionChecker().checkTile(this);
        if(monsterIndex)
        {
          monsterIndex = gp.getCollisionChecker().checkEntity(this, gp.monster);
          if(monsterIndex)
          {
            //gp.getPlayer().damageMonster(monsterIndex, attack);
            setAlive(false);
          }
         
        }
        //Projectile Collision with Tiles
        if(!getCollisionOn())
        {
          setAlive(false);
        }
      }
        
        switch(getDirection()){
            case "up":
            setY(getY() - getSpeed());
            break;
          case "down":
            setY(getY() + getSpeed());
            break;
          case "right":
            setX(getX() + getSpeed());
            break;
          case "left":
            setX(getX() - getSpeed());
            break;
        }

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
