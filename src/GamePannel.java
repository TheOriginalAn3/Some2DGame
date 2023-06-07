import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    // Set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;




    public GamePannel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Try with false
        // Add our own key Listener to recognize input
        /* Using a separate class gives us more controll over the actions than just
           adding lambda statements for key listeners
           Alos makes the code look better :P */
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    // Thread for updating the gamePannel. You can say it creates the FPS of a game
    // Each update is equal to 1 FPS
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread.isAlive()) {
            // Test PrintOut
            // System.out.println("The Game loop is running");

            // "Refresh" / Update frames
            // Update information such as charachter position
            update();

            // Draw the screen with the updated info
            repaint(); // Calls the paintComponent method (Dont give me crap about the naming, its
                       // built into java (╯°□°）╯︵ ┻━┻)
        }

    }

    public void update() {
        // Upper Lift corner is 0,0. X increses to the right. Y increses downwards lmao why T_T
        // TODO: Change if-s to Case?
        if (keyHandler.upPressed) {
            playerY -= playerSpeed; // Same as playerY = playerY - playerSpeed
        }
        else if (keyHandler.downPressed) {
            playerY += playerSpeed;
        }
        else if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        }
        else if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }

    // Draw Method already exists in java. Use the paint component moethod of
    // JPannel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO: Use Graphics2D from the start instead of converting?
        // Convert Graphics g obj to Graphics2D for added functions
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }

}
