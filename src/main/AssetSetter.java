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
        gp.monster[0] = new MON_GreenSlime(gp);
        gp.monster[0].setX(200);
        gp.monster[0].setY(200);
        gp.monster[1] = new MON_GreenSlime(gp);
        gp.monster[1].setX(300);
        gp.monster[1].setY(200);

    }
    
}
