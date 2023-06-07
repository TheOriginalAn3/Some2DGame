import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class GamePannel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // Default graphics size in px 16x16 tile
    final int scale = 3; // Multiplier for the tile size to make everithing bigger

    final int tileSize = originalTileSize * scale; // Actual size that will be displayed 48x48 tile
    final int maxScreenCol = 16; // Max ammount of tiles to be displayed
    final int maxScreenRow = 12; // Max ammount of tiles to be displayed
    final int screenWidth = tileSize * maxScreenCol; // actual window size 768px wide
    final int screenHeight = tileSize * maxScreenRow; // actual window size 576px long
    
    Thread gameThread;

    public GamePannel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Try with false

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}
