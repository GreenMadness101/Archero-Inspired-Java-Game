package main;

import java.util.ArrayList;

import entity.Entity;

/** checks collision between entities and tiles
 * 
 * @author Ishan Voleti
 * @author Samarth Vysyraju
 */
public class CollisionChecker 
{
  /** reference to game panel variable */
  private GamePanel gp;
 
  /** constructor for collision checker, assigns gp to reference
   * 
   * @param gp game panel
   */
  public CollisionChecker(GamePanel gp)
  {
    this.gp = gp;
  }

  /** checks if entity is colliding with a tile
   * 
   * @param entity entity variable 
   */
  public void checkTile(Entity entity)
  {
    //location of entity's solid area on map
    int entityLeftX = entity.getX() + entity.getSolidArea().x;
    int entityRightX = entity.getX() + entity.getSolidArea().x + entity.getSolidArea().width;
    int entityTopY = entity.getY() + entity.getSolidArea().y;
    int entityBottomY = entity.getY() + entity.getSolidArea().y + entity.getSolidArea().height;

    //finds what tile on map
    int entityLeftCol = entityLeftX/gp.getTileSize();
    int entityRightCol = entityRightX/gp.getTileSize();
    int entityTopRow = entityTopY/gp.getTileSize();
    int entityBottomRow = entityBottomY/gp.getTileSize();

    //makes sure nothing goes out of bound
    if(entityBottomRow >= gp.getMaxScreenRow())
    {
      entityBottomRow = gp.getMaxScreenRow() - 1;
    }
    if(entityBottomRow <= 0)
    {
      entityBottomRow = 0;
    }
    if(entityTopRow <= 0)
    {
      entityTopRow = 0;
    }
    if(entityRightCol >= gp.getMaxScreenCol())
    {
      entityRightCol = gp.getMaxScreenCol() - 1;
    }
    if(entityRightCol <= 0)
    {
      entityRightCol = 0;
    }
    if(entityLeftCol <= 0)
    {
      entityLeftCol = 0;
    }
    
    //variables that hold the value of the two tiles the player could collide with
    int tileNum1, tileNum2;

    //finds which tiles depending on location
    switch(entity.getDirection())
    {
      case "up":
        //finds what the entity's solid area will be after moving in certain direction
        entityTopRow = ((entityTopY - entity.getSpeed())/gp.getTileSize());
        if(entityTopRow <= 0)
        {
          entityTopRow = 0;
        }
        //defines the tiles based on location
        tileNum1 = gp.getTileM().mapTileNum[entityLeftCol][entityTopRow];
        tileNum2 = gp.getTileM().mapTileNum[entityRightCol][entityTopRow];
        //if tiles collide with entity turn on collision 
        if(gp.getTileM().tile[tileNum1].getCollision() || gp.getTileM().tile[tileNum2].getCollision())
        {
          entity.setCollisionOn(true);
        }
        //checks if tile is an open gate to teleport
        entity.setTeleportCounter(entity.getTeleportCounter() + 1);
        if(gp.getTileM().tile[tileNum1].getTeleport() && entity.getTeleportCounter() >= 100)
        {
          int i = (int)(Math.random()*7) + 1;
          gp.getTileM().loadMap("/res/maps/map" + i + ".txt");
          gp.getPlayer().setX(240);
          gp.getPlayer().setY(600);
          gp.aSetter.setMonster();
          entity.setTeleportCounter(0);
        }
        break;
      case "down":
        //finds what the entity's solid area will be after moving in certain direction
        entityBottomRow = ((entityBottomY + entity.getSpeed())/gp.getTileSize());
        if(entityBottomRow >= gp.getMaxScreenRow())
        {
          entityBottomRow = gp.getMaxScreenRow() - 1;
        }
        //defines the tiles based on location
        tileNum1 = gp.getTileM().mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.getTileM().mapTileNum[entityRightCol][entityBottomRow];
        //if tiles collide with entity turn on collision 
        if(gp.getTileM().tile[tileNum1].getCollision() || gp.getTileM().tile[tileNum2].getCollision())
        {
          entity.setCollisionOn(true);
        }
        break;
      case "right":
        //finds what the entity's solid area will be after moving in certain direction
        entityRightCol = ((entityRightX + entity.getSpeed())/gp.getTileSize());
        if(entityRightCol >= gp.getMaxScreenCol())
        {
          entityRightCol = gp.getMaxScreenCol() - 1;
        }
        //defines the tiles based on location
        tileNum1 = gp.getTileM().mapTileNum[entityRightCol][entityBottomRow];
        tileNum2 = gp.getTileM().mapTileNum[entityRightCol][entityTopRow];
        //if tiles collide with entity turn on collision 
        if(gp.getTileM().tile[tileNum1].getCollision() || gp.getTileM().tile[tileNum2].getCollision())
        {
          entity.setCollisionOn(true);
        }
        //checks if tile is an open gate to teleport
        entity.setTeleportCounter(entity.getTeleportCounter() + 1);
        if(gp.getTileM().tile[tileNum1].getTeleport() && entity.getTeleportCounter() > 100)
        {
          int i = (int)(Math.random()*7) + 1;
          gp.getTileM().loadMap("/res/maps/map" + i + ".txt");
          gp.getPlayer().setX(240);
          gp.getPlayer().setY(600);
          gp.aSetter.setMonster();
          entity.setTeleportCounter(0);
        }
        break;
      case "left":
        //finds what the entity's solid area will be after moving in certain direction
        entityLeftCol = ((entityLeftX - entity.getSpeed())/gp.getTileSize());
        if(entityLeftCol <= 0)
        {
          entityLeftCol = 0;
        }
        //defines the tiles based on location
        tileNum1 = gp.getTileM().mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.getTileM().mapTileNum[entityLeftCol][entityTopRow];
        //if tiles collide with entity turn on collision 
        if(gp.getTileM().tile[tileNum1].getCollision() || gp.getTileM().tile[tileNum2].getCollision())
        {
          entity.setCollisionOn(true);
        }
        //checks if tile is an open gate to teleport
        entity.setTeleportCounter(entity.getTeleportCounter() + 1);
        if(gp.getTileM().tile[tileNum1].getTeleport() && entity.getTeleportCounter() > 100)
        {
          int i = (int)(Math.random()*7) + 1;
          gp.getTileM().loadMap("/res/maps/map" + i + ".txt");
          gp.getPlayer().setX(240);
          gp.getPlayer().setY(600);
          gp.aSetter.setMonster();
          entity.setTeleportCounter(0);
        }
        break;
    }
  }

  /** checks if player is colliding with monster
   * 
   * @param entity entity variable
   * @param monster monster arraylist
   * @return index of the monster in array list if colliding, or 999 for no collision
   */
  public int checkEntity(Entity entity, ArrayList<Entity> monster)
  {
    //keeps track of value of the monster's index
    int index = 999;

    //checks through every monster index
    for(int i = 0; i < monster.size(); i++)
    {
      //changes solid area of player to solid area's location on the map
      entity.getSolidArea().x = entity.getX() + entity.getSolidArea().x;
      entity.getSolidArea().y = entity.getY() + entity.getSolidArea().y;
      //changes solid area of monster to solid area's location on the map
      monster.get(i).getSolidArea().x = monster.get(i).getX() + monster.get(i).getSolidArea().x;
      monster.get(i).getSolidArea().y = monster.get(i).getY() + monster.get(i).getSolidArea().y;

      //checks depending on direction of player
      switch(entity.getDirection())
      {
        case "up":
          //finds what the entity's solid area will be after moving in certain direction
          entity.getSolidArea().y -= entity.getSpeed();
          //checks if it will collide
          if(entity.getSolidArea().intersects(monster.get(i).getSolidArea()))
          {
            //if colliding say collision is on and say damage should occur
            entity.setCollisionOn(true);
            entity.setCollisionDamage(true);
            //sets index to monster's index in arraylist
            index = i;
          }
          break;
        case "down":
          //finds what the entity's solid area will be after moving in certain direction
          entity.getSolidArea().y += entity.getSpeed();
          //checks if it will collide
          if(entity.getSolidArea().intersects(monster.get(i).getSolidArea()))
          {
            //if colliding say collision is on and say damage should occur
            entity.setCollisionOn(true);
            entity.setCollisionDamage(true);
            //sets index to monster's index in arraylist
            index = i;
          }
          break;
        case "left":
          //finds what the entity's solid area will be after moving in certain direction
          entity.getSolidArea().x -= entity.getSpeed();
          //checks if it will collide
          if(entity.getSolidArea().intersects(monster.get(i).getSolidArea()))
          {
            //if colliding say collision is on and say damage should occur
            entity.setCollisionOn(true);
            entity.setCollisionDamage(true);
            //sets index to monster's index in arraylist
            index = i;
          }
          break;
        case "right":
          //finds what the entity's solid area will be after moving in certain direction
          entity.getSolidArea().x += entity.getSpeed();
          //checks if it will collide
          if(entity.getSolidArea().intersects(monster.get(i).getSolidArea()))
          {
            //if colliding say collision is on and say damage should occur
            entity.setCollisionOn(true);
            entity.setCollisionDamage(true);
            //sets index to monster's index in arraylist
            index = i;
          }
          break;
      }

      //resets solid area to original value with relation to the entity's x and y coordinate
      entity.getSolidArea().x = entity.getSolidAreaDefaultX();
      entity.getSolidArea().y = entity.getSolidAreaDefaultY();
      monster.get(i).getSolidArea().x = monster.get(i).getSolidAreaDefaultX();
      monster.get(i).getSolidArea().y = monster.get(i).getSolidAreaDefaultY();
    }
    return index;
  }

  /** checks if entity is colliding with the player
   * 
   * @param entity entity variable, mostly holds value of monster
   */
  public void checkPlayer(Entity entity)
  {
    //changes solid area of monster to solid area's location on the map
    entity.getSolidArea().x = entity.getX() + entity.getSolidArea().x;
    entity.getSolidArea().y = entity.getY() + entity.getSolidArea().y;
    //changes solid area of player to solid area's location on the map
    gp.getPlayer().getSolidArea().x = gp.getPlayer().getX() + gp.getPlayer().getSolidArea().x;
    gp.getPlayer().getSolidArea().y = gp.getPlayer().getY() + gp.getPlayer().getSolidArea().y;

    //checks collision depending on direction facing
    switch(entity.getDirection())
    {
      case "up":
        //finds what the entity's solid area will be after moving in certain direction
        entity.getSolidArea().y -= entity.getSpeed();
        //checks if it will collide
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          //if colliding say collision is on and say damage should occur
          entity.setCollisionOn(true);
          entity.setCollisionDamage(true);
        }
        break;
      case "down":
        //finds what the entity's solid area will be after moving in certain direction
        entity.getSolidArea().y += entity.getSpeed();
        //checks if it will collide
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          //if colliding say collision is on and say damage should occur
          entity.setCollisionOn(true);
          entity.setCollisionDamage(true);
        }
        break;
      case "left":
        //finds what the entity's solid area will be after moving in certain direction
        entity.getSolidArea().x -= entity.getSpeed();
        //checks if it will collide
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          //if colliding say collision is on and say damage should occur
          entity.setCollisionOn(true);
          entity.setCollisionDamage(true);
        }
        break;
      case "right":
        //finds what the entity's solid area will be after moving in certain direction
        entity.getSolidArea().x += entity.getSpeed();
        //checks if it will collide
        if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea()))
        {
          //if colliding say collision is on and say damage should occur
          entity.setCollisionOn(true);
          entity.setCollisionDamage(true);
        }
        break;
    }

    //resets solid area to original value with relation to the entity's x and y coordinate
    entity.getSolidArea().x = entity.getSolidAreaDefaultX();
    entity.getSolidArea().y = entity.getSolidAreaDefaultY();
    gp.getPlayer().getSolidArea().x = gp.getPlayer().getSolidAreaDefaultX();
    gp.getPlayer().getSolidArea().y = gp.getPlayer().getSolidAreaDefaultY();
  }

  /** checks if entity is colliding with the border of game panel
   * 
   * @param entity entity variable
   */
  public void checkBorder(Entity entity)
  {
    //checks the bounds depending on the direction entity is facing
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
}
