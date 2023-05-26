package monster;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;



public class MON_GreenSlime extends Entity
{

  private GamePanel gp = null;

  public MON_GreenSlime(GamePanel gp)
  {
    super(gp);

    this.gp = gp;

    //name = "Green Slime";
    setSpeed(1);
    setMaxLife(4);
    setLife(getMaxLife());

    setSolidArea(new Rectangle(3, 18, 42, 30));

    getImage();
  }
  public void getImage()
  {
    try {
      setUp1(ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png")));
      setUp2(ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png")));
      setDown1(ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png")));
      setDown2(ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png")));
      setRight1(ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png")));
      setRight2(ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png")));
      setLeft1(ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_1.png")));
      setLeft2(ImageIO.read(getClass().getResourceAsStream("/res/monster/greenslime_down_2.png")));
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    }
  }
  
  public void setAction()
  {
    setActionLockCounter(getActionLockCounter()+1);

    if(getActionLockCounter() == 60)
    {
      int difX = Math.abs(this.getX() - gp.getPlayer().getX());
      int difY = Math.abs(this.getY() - gp.getPlayer().getY());
      //think about the case if they are in the same position but that might not be possible 
      //due to collison soon to be implemented
      if(difX > difY)
      {
        if((this.getX() - gp.getPlayer().getX()) < 0)
        {
          this.setDirection("right");
        }
        if((this.getX() - gp.getPlayer().getX()) > 0)
        {
          this.setDirection("left");
        }
      }
      else if(difX < difY)
      {
        if((this.getY() - gp.getPlayer().getY()) < 0)
        {
          this.setDirection("down");
        }
        if((this.getY() - gp.getPlayer().getY()) > 0)
        {
          this.setDirection("up");
        }
      }
      
    //   int i = (int)(Math.random()*100) + 1;
    //   if(i > 75) 
    //   { 
    //     setDirection("up");
    //   }
    //   else if(i > 50)
    //   {
    //     setDirection("down");
    //   }
    //   else if(i > 25)
    //   {
    //     setDirection("left");
    //   }
    //   else if(i > 0)
    //   {
    //     setDirection("right");
    //   }

      setActionLockCounter(0);
    }

  }
}
