package tile;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePannel;

public class TileManager {
    private GamePannel gp;
    private Map<String, Tile> tileMap = new HashMap<>(); // Map each array index to a string for better readability

    private int col;
    private int row;
    private int x;
    private int y;

    public TileManager(GamePannel gp) {
        this.gp = gp;
        getTileImage();
    }

    // Load the image for the Tile and put the Tiles in the Map
    private void getTileImage() {
        try {
            Tile tile = new Tile();
            tile.image = readImage("res/tiles/grass.png");
            tileMap.put("grass", tile);

            tile = new Tile();
            tile.image = readImage("res/tiles/water.png");
            tileMap.put("water", tile);

            tile = new Tile();
            tile.image = readImage("res/tiles/wall_stone.png");
            tileMap.put("stone_wall", tile);


            tile.image = null;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private BufferedImage readImage(String pathToImg) throws IOException {
        BufferedImage img = null;
        File file = new File(pathToImg);
        if (file.exists()) {
            img = ImageIO.read(file);
        }
        return img;
    }
    public BufferedImage getTile(String tileName) {
        return tileMap.get(tileName).image;
    }

    public void draw(Graphics2D g2) {
        col = 0;
        row = 0;
        x = 0;
        y = 0;

        while (col < gp.maxScreenCol && row <  gp.maxScreenRow ) {
            g2.drawImage(getTile("grass"), x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x = col * gp.tileSize;

            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y = row * gp.tileSize;
            }
        }
    }
}
