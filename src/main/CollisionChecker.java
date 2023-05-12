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
    int entityLeftX = entity.x + entity.solidArea.x;
    int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
    int entityTopY = entity.y + entity.solidArea.y;
    int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

    int entityLeftCol = entityLeftX/gp.tileSize;
    int entityRightCol = entityRightX/gp.tileSize;
    int entityTopRow = entityTopY/gp.tileSize;
    int entityBottomRow = entityBottomY/gp.tileSize;
    if(entityBottomRow >= gp.maxScreenRow)
    {
      entityBottomRow = gp.maxScreenRow - 1;
    }


    int tileNum1, tileNum2;

    switch(entity.direction)
    {
      case "up":
        entityTopRow = ((entityTopY - entity.speed)/gp.tileSize);
        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
        if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision || entity.y <= 0)
        {
          entity.collisionOn = true;
        }
        break;
      case "down":
        entityBottomRow = ((entityBottomY + entity.speed)/gp.tileSize);
        if(entityBottomRow >= gp.maxScreenRow)
        {
          entityBottomRow = gp.maxScreenRow - 1;
        }
        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
        if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision || entity.y + gp.tileSize >= gp.screenHeight - 1)
        {
          entity.collisionOn = true;
        }
        break;
      case "right":
        entityRightCol = ((entityRightX + entity.speed)/gp.tileSize);
        tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
        if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision || entity.x + gp.tileSize >= gp.screenWidth)
        {
          entity.collisionOn = true;
        }
        break;
      case "left":
        entityLeftCol = ((entityLeftX - entity.speed)/gp.tileSize);
        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
        if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision || entity.x <= 0)
        {
          entity.collisionOn = true;
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
        entity.solidArea.x = entity.x + entity.solidArea.x;
        entity.solidArea.y = entity.y + entity.solidArea.y;
        //get entity solid area
        target[i].solidArea.x = target[i].x + target[i].solidArea.x;
        target[i].solidArea.y = target[i].y + target[i].solidArea.y;

        switch(entity.direction)
        {
          case "up":
            entity.solidArea.y -= entity.speed;
            if(entity.solidArea.intersects(target[i].solidArea))
            {
              entity.collisionOn = true;
              index = i;
            }
            break;
          case "down":
            entity.solidArea.y += entity.speed;
            if(entity.solidArea.intersects(target[i].solidArea))
            {
              entity.collisionOn = true;
              index = i;
            }
            break;
          case "left":
            entity.solidArea.x -= entity.speed;
            if(entity.solidArea.intersects(target[i].solidArea))
            {
              entity.collisionOn = false;
              index = i;
            }
            break;
          case "right":
            entity.solidArea.x += entity.speed;
            if(entity.solidArea.intersects(target[i].solidArea))
            {
              entity.collisionOn = false;
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
    entity.solidArea.x = entity.x + entity.solidArea.x;
    entity.solidArea.y = entity.y + entity.solidArea.y;
    //get entity solid area
    gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
    gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;

    switch(entity.direction)
    {
      case "up":
        entity.solidArea.y -= entity.speed;
        if(entity.solidArea.intersects(gp.player.solidArea))
        {
          entity.collisionOn = true;
        }
        break;
      case "down":
        entity.solidArea.y += entity.speed;
        if(entity.solidArea.intersects(gp.player.solidArea))
        {
          entity.collisionOn = true;
        }
        break;
      case "left":
        entity.solidArea.x -= entity.speed;
        if(entity.solidArea.intersects(gp.player.solidArea))
        {
          entity.collisionOn = false;
        }
        break;
      case "right":
        entity.solidArea.x += entity.speed;
        if(entity.solidArea.intersects(gp.player.solidArea))
        {
          entity.collisionOn = false;
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
