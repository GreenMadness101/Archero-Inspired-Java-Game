package tile;

import java.awt.image.BufferedImage;

/** creates tiles which show up on screen for map
 * 
 * @author Ishan Voleti
 * @author Samarth Vysyraju
 */
public class Tile 
{
  /** image holding sprite of tile */
  private BufferedImage image;
  /** checks if tile has collision or not */
  private boolean collision = false;
  /** checks if tile is the open gate */
  private boolean teleport = false;

  //SETTERS AND GETTERS
  public void setImage(BufferedImage image) 
  {
    this.image = image;
  }
  public BufferedImage getImage() 
  {
    return image;
  }
  public void setCollision(boolean collision) {
    this.collision = collision;
  }
  public boolean getCollision()
  {
    return collision;
  }
  public boolean getTeleport()
  {
  return teleport;
  }
  public void setTeleport(boolean teleport) 
  {
      this.teleport = teleport;
  }
 
 
}


