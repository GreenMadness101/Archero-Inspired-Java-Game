package main;

import entity.Entity;
import monster.MON_GreenSlime;

public class AssetSetter 
{
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }
    public void setMonster()
    {
<<<<<<< HEAD
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].setX(200);
        gp.monster[0].setY(200);
        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].setX(300);
        gp.monster[1].setY(200);
        gp.monster[2] = new Entity(gp);
        gp.monster[2].setX(250);
        gp.monster[2].setY(250);
=======
        gp.monster.add(new MON_GreenSlime(gp));
        gp.monster.get(0).setX(200);
        gp.monster.get(0).setY(200);
        gp.monster.add(new MON_GreenSlime(gp));
        gp.monster.get(1).setX(300);
        gp.monster.get(1).setY(200);
>>>>>>> 6bcb4b21c8fce77584fa67a2ccb851b3af07f197

    }
    
}
