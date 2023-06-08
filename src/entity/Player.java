package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePannel;
import main.KeyHandler;

public class Player extends Entity {

    GamePannel gp;
    KeyHandler keyHandler;

    public Player(GamePannel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
    }

    public void setDefaultValues() {
        // Set player default position
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        // Upper Lift corner is 0,0. X increses to the right. Y increses downwards lmao
        // why T_T

        // Basic up-down, left-right movement
        if (keyHandler.upPressed) {
            y -= speed; // Same as y = y - speed
        } else if (keyHandler.downPressed) {
            y += speed;
        } else if (keyHandler.leftPressed) {
            x -= speed;
        } else if (keyHandler.rightPressed) {
            x += speed;
        }
        // TODO: Add support for diagonal movement
        // Diagonal Up
        // Diagonal Down
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
    }
}
