package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Projectile extends Projectile 
{
    public OBJ_Projectile(GamePanel gp) {
        super(gp);
        setName("projectile");
        setSpeed(7);
        setMaxLife(80);
        setLife(getMaxLife());
        //attack = 2;
        setAlive(false);
        getImage();
        setSolidArea(new Rectangle(21 , 9 , 12, 36));
    }




    //update image files herew
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
