package tile;

import java.awt.image.BufferedImage;

public class Tile 
{
  public BufferedImage image;
  public boolean collision = false;
  private boolean teleport = false;

  
  public boolean getTeleport()
  {
  return teleport;
  }
  public void setTeleport(boolean teleport) 
  {
      this.teleport = teleport;
  }
}


