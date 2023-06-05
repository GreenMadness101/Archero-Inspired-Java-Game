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
        if(entityTopRow <= 0)
        {
          entityTopRow = 0;
        }
        tileNum1 = gp.getTileM().mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = gp.getTileM().mapTileNum[entityRightCol][entityTopRow];
        if(gp.getTileM().tile[tileNum1].collision || gp.getTileM().tile[tileNum2].collision)
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
        tileNum1 = gp.getTileM().mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.getTileM().mapTileNum[entityRightCol][entityBottomRow];
        if(gp.getTileM().tile[tileNum1].collision || gp.getTileM().tile[tileNum2].collision)
        {
          entity.setCollisionOn(true);
        }
        break;
      case "right":
        entityRightCol = ((entityRightX + entity.getSpeed())/gp.getTileSize());
        if(entityRightCol >= gp.getMaxScreenCol())
        {
          entityRightCol = gp.getMaxScreenCol() - 1;
        }
        tileNum1 = gp.getTileM().mapTileNum[entityRightCol][entityBottomRow];
        tileNum2 = gp.getTileM().mapTileNum[entityRightCol][entityTopRow];
        if(gp.getTileM().tile[tileNum1].collision || gp.getTileM().tile[tileNum2].collision)
        {
          entity.setCollisionOn(true);
        }
        break;
      case "left":
        entityLeftCol = ((entityLeftX - entity.getSpeed())/gp.getTileSize());
        if(entityLeftCol <= 0)
        {
          entityLeftCol = 0;
        }
        tileNum1 = gp.getTileM().mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.getTileM().mapTileNum[entityLeftCol][entityTopRow];
        if(gp.getTileM().tile[tileNum1].collision || gp.getTileM().tile[tileNum2].collision)
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

  public boolean checkEntity(Entity entity, Entity[] target)
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
              entity.setCollisionDamage(true);
              index = i;
            }
            break;
          case "down":
            entity.getSolidArea().y += entity.getSpeed();
            if(entity.getSolidArea().intersects(target[i].getSolidArea()))
            {
              entity.setCollisionOn(true);
              entity.setCollisionDamage(true);
              index = i;
            }
            break;
          case "left":
            entity.getSolidArea().x -= entity.getSpeed();
            if(entity.getSolidArea().intersects(target[i].getSolidArea()))
            {
              entity.setCollisionOn(true);
              entity.setCollisionDamage(true);
              index = i;
            }
            break;
          case "right":
            entity.getSolidArea().x += entity.getSpeed();
            if(entity.getSolidArea().intersects(target[i].getSolidArea()))
            {
              entity.setCollisionOn(true);
              entity.setCollisionDamage(true);
              index = i;
            }
            break;
        }
        //watch the video on objects to find what these values are defined as
        //also check if these statements are placed right
        entity.getSolidArea().x = entity.getSolidAreaDefaultX();
        entity.getSolidArea().y = entity.getSolidAreaDefaultY();
        target[i].getSolidArea().x = target[i].getSolidAreaDefaultX();
        target[i].getSolidArea().y = target[i].getSolidAreaDefaultY();
      }
    }
    if(index != 999)
    {
      return true;
    }
    return false;
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
          entity.setCollisionDamage(true);
        }
        break;
      case "down":
        entity.getSolidArea().y += entity.getSpeed();
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          entity.setCollisionOn(true);
          entity.setCollisionDamage(true);
        }
        break;
      case "left":
        entity.getSolidArea().x -= entity.getSpeed();
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          entity.setCollisionOn(true);
          entity.setCollisionDamage(true);
        }
        break;
      case "right":
        entity.getSolidArea().x += entity.getSpeed();
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          entity.setCollisionOn(true);
          entity.setCollisionDamage(true);
        }
        break;
    }
    //watch the video on objects to find what these values are defined as
    //also check if these statements are placed right
    entity.getSolidArea().x = entity.getSolidAreaDefaultX();
    entity.getSolidArea().y = entity.getSolidAreaDefaultY();
    gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
    gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
  }

  public void checkBorder(Entity entity)
  {
    switch(entity.getDirection())
    {
      case "up":
        if(entity.getY() <= 0)
        {
          entity.setCollisionOn(true);
        }
        break;
      case "down":
        if(entity.getY() + gp.getTileSize() >= gp.getScreenHeight() - 1)
        {
          entity.setCollisionOn(true);
        }
        break;
      case "right":
        if(entity.getX() + gp.getTileSize() >= gp.getScreenWidth())
        {
          entity.setCollisionOn(true);
        }
        break;
      case "left":
        if(entity.getX() <= 0)
        {
          entity.setCollisionOn(true);
        }
        break;
    }
  }

  public void projectileCheckTile(Entity entity)
  {
    
  }

  
  
  
}
