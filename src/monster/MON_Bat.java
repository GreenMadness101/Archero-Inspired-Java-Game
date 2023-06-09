package monster;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;
/** class for the bat 
 * 
 * @author Ishan
 * @author Samarth
 */
public class MON_Bat extends Entity {
  
  /** field for the game panel  */
  private GamePanel gp = null;

    /** constructor for the bat which sets the speed, life, name, calls the super constructor in entity, and sets the field gp to the parameter gp
     * 
     * @param gp  the game panel
     */
    public MON_Bat(GamePanel gp)
    {
        super(gp);
        this.gp = gp;
        setName("monster");
        setSpeed(3);
        setMaxLife(30);
        setLife(getMaxLife());

        setSolidArea(new Rectangle(3, 18, 42, 30));

        getImage();
    }
  /**sets the image for the bats for all directions
   * 
   * @exception IOExceptions   causes an error if the image does not work 
   */
    public void getImage()
    {
        try {
            setUp1(ImageIO.read(getClass().getResourceAsStream("/res/monster/bat_down_1.png")));
            setUp2(ImageIO.read(getClass().getResourceAsStream("/res/monster/bat_down_2.png")));
            setDown1(ImageIO.read(getClass().getResourceAsStream("/res/monster/bat_down_1.png")));
            setDown2(ImageIO.read(getClass().getResourceAsStream("/res/monster/bat_down_2.png")));
            setRight1(ImageIO.read(getClass().getResourceAsStream("/res/monster/bat_down_1.png")));
            setRight2(ImageIO.read(getClass().getResourceAsStream("/res/monster/bat_down_2.png")));
            setLeft1(ImageIO.read(getClass().getResourceAsStream("/res/monster/bat_down_1.png")));
            setLeft2(ImageIO.read(getClass().getResourceAsStream("/res/monster/bat_down_2.png")));
          } 
          catch (IOException e) 
          {
            e.printStackTrace();
          }

    }

    /** pathfinding for the bat to follow the player depending on the difference in their x and y and then determining how the bat should move depending on those values  */
    public void setAction()
    {
      setActionLockCounter(getActionLockCounter()+1);
      
      if(getActionLockCounter() == 10)
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
      
  
        setActionLockCounter(0);
      }
  
    }

    
}
