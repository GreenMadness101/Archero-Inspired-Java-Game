package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

/** creates maps
 * 
 * @author Ishan Voleti
 * @author Samarth Vysyraju
 */
public class TileManager 
{

  /** reference to game panel */
  private GamePanel gp;
  /** list of tiles */
  public Tile[] tile;
  /** 2D array list which holds maps */
  public int mapTileNum[][];

  /** constructor for tile manager
   * 
   * @param gp game panel
   */
  public TileManager(GamePanel gp)
  {
    //assigns param to reference
    this.gp = gp;

    //creates tile array
    tile = new Tile[20];

    //creates 2D map
    mapTileNum = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];

    //calls sprite image
    getTileImage();
    //calls maps
    loadMap("/res/maps/start.txt");
  }

  /** chooses tile's sprite depending on corrosponding  number
   * 
   */
  public void getTileImage()
  {
    try 
    {
      tile[0] = new Tile();
      tile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass00.png")));

      tile[1] = new Tile();
      tile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass01.png")));
      
      tile[2] = new Tile();
      tile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water02.png")));
      tile[2].setCollision(true);

      tile[3] = new Tile();
      tile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water03.png")));
      tile[3].setCollision(true);

      tile[4] = new Tile();
      tile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water04.png")));
      tile[4].setCollision(true);
      
      tile[5] = new Tile();
      tile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water05.png")));
      tile[5].setCollision(true);

      tile[6] = new Tile();
      tile[6].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water06.png")));
      tile[6].setCollision(true);

      tile[7] = new Tile();
      tile[7].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water07.png")));
      tile[7].setCollision(true);
      
      tile[8] = new Tile();
      tile[8].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water08.png")));
      tile[8].setCollision(true);

      tile[9] = new Tile();
      tile[9].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water09.png")));
      tile[9].setCollision(true);

      tile[10] = new Tile();
      tile[10].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png")));
      tile[10].setCollision(true);

      tile[11] = new Tile();
      tile[11].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png")));
      tile[11].setCollision(true);

      tile[12] = new Tile();
      tile[12].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png")));
      tile[12].setCollision(true);

      tile[13] = new Tile();
      tile[13].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/Water Block.png")));
      tile[13].setCollision(true);
      
      tile[14] = new Tile();
      tile[14].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/gate_1.png")));
      // tile[14].image = uTool.scaleImage
      tile[14].setCollision(true);
      
      tile[15] = new Tile();
      tile[15].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/gate_2.png")));
      tile[15].setTeleport(true);
      
    } 
    catch(IOException e)
    {
      e.printStackTrace();
    }
  } 

  /** reads the file and puts in the 2D array
   * 
   * @param filePath file that is being inputted
   */
  public void loadMap(String filePath)
  {
    //default set the tile to closed gate
    mapTileNum[5][0] = 14;
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

  /** draws tiles and maps based on images
   * 
   * @param g2 graphics
   */
  public void draw(Graphics2D g2)
  {
    int col = 0;
    int row = 0;
    int x = 0;
    int y = 0;

    while(col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow())
    {
      int tileNum = mapTileNum[col][row];

      g2.drawImage(tile[tileNum].getImage(), x, y, gp.getTileSize(), gp.getTileSize(), null);
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

  

  public int[][] getMapTileNum() 
  {
      return mapTileNum;
  }
  public void setMapTileNum(int[][] mapTileNum) 
  {
      this.mapTileNum = mapTileNum;
  }
  public void openGate()
  {
    mapTileNum[5][0] = 15;
  }
  public void closeGate()
  {
    mapTileNum[5][0] = 14;
  }
  
  // public boolean getMonsterTile(int i, int j)
  // {
  //   return mapTileNum[i][j] == x;
  // }

}
