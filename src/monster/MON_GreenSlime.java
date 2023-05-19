package monster;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class MON_GreenSlime extends Entity
{
  public MON_GreenSlime(GamePanel gp)
  {
    super(gp);

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
    //might not be declared here
    int actionLockCounter = 0;
    actionLockCounter++;

    if(actionLockCounter == 120)
    {
      int i = (int)(Math.random()*100) + 1;
      if(i > 75) 
      { 
        setDirection("up");
      }
      else if(i > 50)
      {
        setDirection("down");
      }
      else if(i > 50)
      {
        setDirection("left");
      }
      else if(i > 50)
      {
        setDirection("right");
      }

      actionLockCounter= 0;
    }

  }
}
