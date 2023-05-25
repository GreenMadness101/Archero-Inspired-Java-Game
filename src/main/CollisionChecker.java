package main;

import entity.Entity;

public class CollisionChecker 
{
  GamePanel gp;

  public CollisionChecker(GamePanel gp)
  {
    this.gp = gp;
  }

  public void checkTile(Entity entity)
  {
    int entityLeftX = entity.getX() + entity.getSolidArea().x;
    int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
    int entityTopY = entity.getY() + entity.getSolidArea().y;
    int entityBottomY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

    int entityLeftCol = entityLeftX/gp.getTileSize();
    int entityRightCol = entityRightX/gp.getTileSize();
    int entityTopRow = entityTopY/gp.getTileSize();
    int entityBottomRow = entityBottomY/gp.getTileSize();
    if(entityBottomRow >= gp.getMaxScreenRow())
    {
      entityBottomRow = gp.getMaxScreenRow() - 1;
    }


    int tileNum1, tileNum2;

    switch(entity.getDirection())
    {
      case "up":
        entityTopRow = ((entityTopY - entity.getSpeed())/gp.getTileSize());
        System.out.println(entity.getX());
        System.out.println(entity.getSolidArea().getLocation());
        System.out.println(entityLeftCol);
        tileNum1 = gp.getTileM().mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = gp.getTileM().mapTileNum[entityRightCol][entityTopRow];
        if(gp.getTileM().tile[tileNum1].collision || gp.getTileM().tile[tileNum2].collision || entity.getY() <= 0)
        {
          entity.setCollisionOn(true);
        }
        break;
      case "down":
        entityBottomRow = ((entityBottomY + entity.getSpeed())/gp.getTileSize());
        if(entityBottomRow >= gp.getMaxScreenRow())
        {
          entityBottomRow = gp.getMaxScreenRow() - 1;
        }
        System.out.println(entity.getX());
        System.out.println(entity.getSolidArea().x);
        System.out.println(entity.getSolidArea().y);
        tileNum1 = gp.getTileM().mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.getTileM().mapTileNum[entityRightCol][entityBottomRow];
        if(gp.getTileM().tile[tileNum1].collision || gp.getTileM().tile[tileNum2].collision || entity.getY() + gp.getTileSize() >= gp.getScreenHeight() - 1)
        {
          entity.setCollisionOn(true);
        }
        break;
      case "right":
        entityRightCol = ((entityRightX + entity.getSpeed())/gp.getTileSize());
        tileNum1 = gp.getTileM().mapTileNum[entityRightCol][entityBottomRow];
        tileNum2 = gp.getTileM().mapTileNum[entityRightCol][entityTopRow];
        if(gp.getTileM().tile[tileNum1].collision || gp.getTileM().tile[tileNum2].collision || entity.getX() + gp.getTileSize() >= gp.getScreenWidth())
        {
          entity.setCollisionOn(true);
        }
        break;
      case "left":
        entityLeftCol = ((entityLeftX - entity.getSpeed())/gp.getTileSize());
        tileNum1 = gp.getTileM().mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.getTileM().mapTileNum[entityLeftCol][entityTopRow];
        if(gp.getTileM().tile[tileNum1].collision || gp.getTileM().tile[tileNum2].collision || entity.getX() <= 0)
        {
          entity.setCollisionOn(true);
        }
        break;
    }
  }

    //make the checkObject method if needed
    // public int checkObject(Entity entity, boolean player)
    // {

    // }

  public int checkEntity(Entity entity, Entity[] target)
  {
    int index = 999;

    for(int i = 0; i < target.length; i++)
    {
      if(target[i] != null)
      {
        entity.getSolidArea().x = entity.getX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getY() + entity.getSolidArea().y;
        //get entity solid area
        target[i].getSolidArea().x = target[i].getX() + target[i].getSolidArea().x;
        target[i].getSolidArea().y = target[i].getY() + target[i].getSolidArea().y;

        switch(entity.getDirection())
        {
          case "up":
            entity.getSolidArea().y -= entity.getSpeed();
            if(entity.getSolidArea().intersects(target[i].getSolidArea()))
            {
              entity.setCollisionOn(true);
              index = i;
            }
            break;
          case "down":
            entity.getSolidArea().y += entity.getSpeed();
            if(entity.getSolidArea().intersects(target[i].getSolidArea()))
            {
              entity.setCollisionOn(true);
              index = i;
            }
            break;
          case "left":
            entity.getSolidArea().x -= entity.getSpeed();
            if(entity.getSolidArea().intersects(target[i].getSolidArea()))
            {
              entity.setCollisionOn(false);
              index = i;
            }
            break;
          case "right":
            entity.getSolidArea().x += entity.getSpeed();
            if(entity.getSolidArea().intersects(target[i].getSolidArea()))
            {
              entity.setCollisionOn(false);
              index = i;
            }
            break;
        }
        //watch the video on objects to find what these values are defined as
        //also check if these statements are placed right
        // entity.solidArea.x = entity.solidAreaDefaultX;
        // entity.solidArea.y = entity.solideAreaDefaultY;
        // target[i].solidArea.x = target[i].solidAreaDefaultX;
        // target[i].solidArea.y = target[i].solidAreaDefaultY;
      }
    }
    return index;

  }

  public void checkPlayer(Entity entity)
  {
    //these statemnts caused the previous error
    entity.getSolidArea().x = entity.getX() + entity.getSolidArea().x;
    entity.getSolidArea().y = entity.getY() + entity.getSolidArea().y;    //get entity solid area
    gp.getPlayer().getSolidArea().x = gp.getPlayer().getX() + gp.getPlayer().getSolidArea().x;
    gp.getPlayer().getSolidArea().y = gp.getPlayer().getY() + gp.getPlayer().getSolidArea().y;

    switch(entity.getDirection())
    {
      case "up":
        entity.getSolidArea().y -= entity.getSpeed();
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          entity.setCollisionOn(true);
        }
        break;
      case "down":
        entity.getSolidArea().y += entity.getSpeed();
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          entity.setCollisionOn(true);
        }
        break;
      case "left":
        entity.getSolidArea().x -= entity.getSpeed();
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          entity.setCollisionOn(false);
        }
        break;
      case "right":
        entity.getSolidArea().x += entity.getSpeed();
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          entity.setCollisionOn(false);
        }
        break;
    }
    //watch the video on objects to find what these values are defined as
    //also check if these statements are placed right
    // entity.solidArea.x = entity.solidAreaDefaultX;
    // entity.solidArea.y = entity.solidAreaDefaultY;
    // gp.player.solidArea.x = gp.player.solidAreaDefaultX;
    // gp.player.solidArea.y = gp.player.solidAreaDefaultY;
  }
  
}
