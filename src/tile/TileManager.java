package tile;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import main.GamePannel;
import main.ImageHandler;

public class TileManager {
    private GamePannel gp;
    private Map<String, Tile> tileMap = new HashMap<>(); // Map each array index to a string for better readability

    private int col = 0;
    private int row = 0;
    private int x = 0;
    private int y = 0;

    private String mapTileNum[][];

    public TileManager(GamePannel gp) {
        this.gp = gp;
        mapTileNum = new String[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap("/maps/map02.txt");
    }

    // Load the image for the Tile and put the Tiles in the Map
    private void getTileImage() {
        try {
            Tile tile = new Tile();
            tile.image = ImageHandler.readImage("res/tiles/grass.png");
            tileMap.put("grass", tile);

            tile = new Tile();
            tile.image = ImageHandler.readImage("res/tiles/water.png");
            tileMap.put("water", tile);

            tile = new Tile();
            tile.image = ImageHandler.readImage("res/tiles/wall_stone.png");
            tileMap.put("stone_wall", tile);

            tile = new Tile();
            tile.image = ImageHandler.readImage("res/tiles/dirt.png");
            tileMap.put("dirt", tile);

            tile = new Tile();
            tile.image = ImageHandler.readImage("res/tiles/sand.png");
            tileMap.put("sand", tile);

            tile = new Tile();
            tile.image = ImageHandler.readImage("res/tiles/tree_grass.png");
            tileMap.put("tree_grass", tile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getTile(String tileName) {
        return tileMap.get(tileName).image;
    }

    public void loadMap(String pathToMap) {
        try {
            // Read text file
            InputStream is = getClass().getResourceAsStream(pathToMap);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenCol) {
                    String[] tileNames = line.split(",");
                    String tileName = tileNames[col];

                    mapTileNum[col] [row] = tileName;

                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {
        col = 0;
        row = 0;
        x = 0; // I have to do this because it starts at row No.2 if i dont...
        y = 0;

        while (col < gp.maxScreenCol && row <  gp.maxScreenRow ) {
            g2.drawImage(getTile(mapTileNum[col][row]), x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
