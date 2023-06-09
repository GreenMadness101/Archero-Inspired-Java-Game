package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Projectile;
import main.GamePanel;
/**class for the player projectile
 * 
 * @author Samarth Vysyraju
 * @author Ishan Voleti
 */
public class OBJ_Projectile extends Projectile 
{
     /** constructor for the player projectile which sets the max life, area, and speed 
     * 
     * @param gp   the game panel 
     */
    public OBJ_Projectile(GamePanel gp) {
        super(gp);
        setName("projectile");
        setSpeed(6);
        setMaxLife(80);
        setLife(getMaxLife());
        //attack = 2;
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
        setUp1(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/projectile_1.png")));
        setUp2(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/projectile_1.png")));
        setDown1(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/projectile_1.png")));
        setDown2(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/projectile_1.png")));
        setRight1(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/projectile_1.png")));
        setRight2(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/projectile_1.png")));
        setLeft1(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/projectile_1.png")));
        setLeft2(ImageIO.read(getClass().getResourceAsStream("/res/projectiles/projectile_1.png")));
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
