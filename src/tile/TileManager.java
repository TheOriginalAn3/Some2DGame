package tile;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePannel;

public class TileManager {
    private GamePannel gp;
    private Tile[] tile;


    public TileManager(GamePannel gp) {
        this.gp = gp;
        // TODO: Use List instead of array?
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            //tile[0].image = ImageIO.read(getClass().getResourceAsStream("res/tiles/grass.png")) not working, using workaround...
            tile[0].image = ImageIO.read(new File("res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res/tiles/water.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res/tiles/wall_stone.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenCol && row <  gp.maxScreenRow ) {
            g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
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
