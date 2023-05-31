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

    public void update()
    {
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

        //Attempt to make it not alive if the fireball collides with walls or tiles
        setCollisionOn(false);
        gp.getCollisionChecker().checkTile(this);

        //causes the projectile to dissapear
        // setLife(getLife() - 1);
        // if(getLife()  <= 0)
        // {
        //   System.out.println("hiiii");
        //     setAlive(false);
        // }
        if(getCollisionOn())
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
