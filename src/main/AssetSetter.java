package main;

import entity.Entity;
import monster.MON_Bat;
import monster.MON_GreenSlime;
import monster.MON_Wizard;

public class AssetSetter 
{
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }
    public void setMonster()
    {
        gp.monster.add(new MON_GreenSlime(gp));
        gp.monster.get(0).setX(220);
        gp.monster.get(0).setY(220);
        gp.monster.add(new MON_GreenSlime(gp));
        gp.monster.get(1).setX(300);
        gp.monster.get(1).setY(200);
        gp.monster.add(new MON_Bat(gp));
        gp.monster.get(2).setX(250);
        gp.monster.get(2).setY(250);
        gp.monster.add(new MON_Wizard(gp));
        gp.monster.get(3).setX(200);
        gp.monster.get(3).setY(200);
    
       
    }
    
}
