package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Projectile;
import main.GamePanel;

/**class for the monster's projectile
 * 
 * @author Samarth Vysyraju
 * @author Ishan Voleti
 */
public class OBJ_MonsterProjectile extends Projectile
{
    /** constructor for the monster's projectile which sets the max life, area, and speed 
     * 
     * @param gp   the game panel 
     */
    public OBJ_MonsterProjectile(GamePanel gp) {
        super(gp);
        setName("projectile");
        setSpeed(7);
        setMaxLife(300);
        setLife(getMaxLife());
        setAlive(false);
        getImage();
        setSolidArea(new Rectangle(21 , 9 , 12, 36));
    }



    /** the images for all dierctions for the monster's projectiles
     * 
     * @exception IOException  causes and error if image does not work 
     */
    public void getImage()
    {
     try {
        setUp1(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_up_1.png")));
        setUp2(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_up_2.png")));
        setDown1(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_down_1.png")));
        setDown2(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_down_2.png")));
        setRight1(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_right_1.png")));
        setRight2(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_right_2.png")));
        setLeft1(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_left_1.png")));
        setLeft2(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/fireball_left_2.png")));
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    }
}
