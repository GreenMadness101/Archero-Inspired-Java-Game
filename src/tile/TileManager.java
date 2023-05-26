package tile;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.lang.model.element.Name;

import main.GamePanel;

public class TileManager 
{
  GamePanel gp;
  public Tile[] tile;
  public int mapTileNum[][];

  public TileManager(GamePanel gp)
  {
    this.gp = gp;

    tile = new Tile[20];

    mapTileNum = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];

    getTileImage();
    loadMap("/res/maps/map7.txt");
  }

  public void getTileImage()
  {
    try 
    {
      tile[0] = new Tile();
      tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass00.png"));

      tile[1] = new Tile();
      tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass01.png"));
      
      tile[2] = new Tile();
      tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water02.png"));
      tile[2].collision = true;

      tile[3] = new Tile();
      tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water03.png"));
      tile[3].collision = true;

      tile[4] = new Tile();
      tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water04.png"));
      tile[4].collision = true;
      
      tile[5] = new Tile();
      tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water05.png"));
      tile[5].collision = true;

      tile[6] = new Tile();
      tile[6].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water06.png"));
      tile[6].collision = true;

      tile[7] = new Tile();
      tile[7].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water07.png"));
      tile[7].collision = true;
      
      tile[8] = new Tile();
      tile[8].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water08.png"));
      tile[8].collision = true;

      tile[9] = new Tile();
      tile[9].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water09.png"));
      tile[9].collision = true;

      tile[10] = new Tile();
      tile[10].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));
      tile[10].collision = true;

      tile[11] = new Tile();
      tile[11].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png"));
      tile[11].collision = true;

      tile[12] = new Tile();
      tile[12].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png"));
      tile[12].collision = true;

      tile[13] = new Tile();
      tile[13].image  =  ImageIO.read(getClass().getResourceAsStream("/res/tiles/Water Block.png"));
      tile[13].collision = true;

      // tile[14] = new Tile();
      // tile[14].image = ImageIO.read(getClass().getResourceAsStream("res/tiles/FenceTile.png"));
      // tile[14].collision = true;
      
    } 
    catch(IOException e)
    {
      e.printStackTrace();
    }
  } 

  public void loadMap(String filePath)
  {
    try
    {
      //used to import text file
      InputStream is = getClass().getResourceAsStream(filePath);
      //used to read the text file
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      int col = 0;
      int row = 0;

      while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow())
      {
        //reads a single line and puts it into the String line
        String line = br.readLine();

        while(col < gp.getMaxScreenCol())
        {
          //puts all the strings into an array
          String numbers[] = line.split(" ");
          //makes the array integer
          int num = Integer.parseInt(numbers[col]);

          mapTileNum[col][row] = num;
          col++;
        }
        if(col == gp.getMaxScreenCol())
        {
          col = 0;
          row++;
        }
      }
      br.close();
    }
    catch(Exception e)
    {

    }
  }

  public void draw(Graphics2D g2)
  {
    int col = 0;
    int row = 0;
    int x = 0;
    int y = 0;

    while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow())
    {
      int tileNum = mapTileNum[col][row];

      g2.drawImage(tile[tileNum].image, x, y, gp.getTileSize(), gp.getTileSize(), null);
      col++;
      x += gp.getTileSize();

      if(col == gp.getMaxScreenCol())
      {
        col = 0;
        x = 0;
        row++;
        y += gp.getTileSize();
      }
    }
  }
  
}
