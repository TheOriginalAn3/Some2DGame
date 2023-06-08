package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.chrono.ThaiBuddhistChronology;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entity.Player;
import tile.TileManager;

public class GamePannel extends JPanel implements Runnable {
    // Screen settings
    final int originalTileSize = 16; // Default graphics size in px 16x16 tile
    final int scale = 3; // Multiplier for the tile size to make everithing bigger

    public final int tileSize = originalTileSize * scale; // Actual size that will be displayed 48x48 tile
    public final int maxScreenCol = 16; // Max ammount of tiles to be displayed
    public final int maxScreenRow = 12; // Max ammount of tiles to be displayed
    public final int screenWidth = tileSize * maxScreenCol; // actual window size 768px wide
    public final int screenHeight = tileSize * maxScreenRow; // actual window size 576px long

    private TileManager tileManager = new TileManager(this);
    private KeyHandler keyHandler = new KeyHandler();
    private Thread gameThread;
    Player player = new Player(this, keyHandler);

    // FPS and Frame/Update Restricting
    public final int FPS = 60;
    private double drawInterval = 1000000000 / FPS;
    private double delta;
    private long lastTime;
    private long currentTime;
    private long timer = 0;
    private int frameCount = 0; // Same as drawCount. Counts how many frames have been displayed

    public GamePannel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Try with false
        // Add our own key Listener to recognize input
        /*
         * Using a separate class gives us more controll over the actions than just
         * adding lambda statements for key listeners
         * Alos makes the code look better :P
         */
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
        delta = 0;
        lastTime = System.nanoTime();

        while (gameThread.isAlive()) {
            // Test PrintOut
            // System.out.println("The Game loop is running");

            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta > 1) {
                // Draw the screen with the updated info
                update();
                // "Refresh" / Update frame to show position of everythong
                // Calls the paintComponent method (Dont give me crap about the naming, its
                // built into java (╯°□°）╯︵ ┻━┻)
                repaint();
                delta--;
                frameCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + frameCount);
                frameCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {
        player.update();
    }

    // Draw Method already exists in java. Use the paint component moethod of
    // JPannel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // TODO: Use Graphics2D from the start instead of converting?
        // Convert Graphics g obj to Graphics2D for added functions
        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);

        player.draw(g2);
        g2.dispose();
    }

}
