package main;

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
        gp.monster.add(new MON_GreenSlime(gp));
        gp.monster.get(0).setX(200);
        gp.monster.get(0).setY(200);
        gp.monster.add(new MON_GreenSlime(gp));
        gp.monster.get(1).setX(300);
        gp.monster.get(1).setY(200);

    }
    
}
