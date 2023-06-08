package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
//import javax.imageio.stream.FileImageInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePannel;
import main.KeyHandler;

public class Player extends Entity {

    GamePannel gp;
    KeyHandler keyHandler;
    private BufferedImage image;
    

    public Player(GamePannel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
        setAnimationSpeedMultiplier(0.75);
    }

    public void setDefaultValues() {
        // Set player default position
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
        updateLastDirection();
        animationSpeedMultiplier = 1;
    }
    public void setAnimationSpeedMultiplier(double multiplier) {
        animationSpeedMultiplier = animationSpeedMultiplier * (multiplier%100);
    }

    // Get sprite images for the player
    public void getPlayerImage() {
        try {
            /*
             * BufferedImage image =
             * ImageIO.read(new File("/player/image.png"));
             * would be a workaround if getClass().getResourceAsStream() dosnt work
             */
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            upStatic = ImageIO.read(getClass().getResourceAsStream("/player/player_up_Static.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            downStatic = ImageIO.read(getClass().getResourceAsStream("/player/player_down_static.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
            rightStatic = ImageIO.read(getClass().getResourceAsStream("/player/player_right_static.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            leftStatic = ImageIO.read(getClass().getResourceAsStream("/player/player_left_static.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // Upper Lift corner is 0,0. X increses to the right. Y increses downwards lmao
        // why T_T

        if (keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.upPressed) {
            // Basic up-down, left-right movement
            if (keyHandler.upPressed) {
                direction = "up";
                updateLastDirection();
                y -= speed; // Same as y = y - speed
            } else if (keyHandler.downPressed) {
                direction = "down";
                updateLastDirection();
                y += speed;
            } else if (keyHandler.leftPressed) {
                direction = "left";
                updateLastDirection();
                x -= speed;
            } else if (keyHandler.rightPressed) {
                direction = "right";
                updateLastDirection();
                x += speed;
            }
            spriteCounter++;
            if ((spriteCounter * animationSpeedMultiplier) > defaultAnimationSpeed) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum =1;
                spriteCounter = 0; 
            }
            // TODO: Add support for diagonal movement
            // Diagonal Up
            // Diagonal Down
        }
        else {
            direction = (getLastDirection() + "Static");
        }
            
    }

    public void draw(Graphics2D g2) {
        // // For testing
        // // White Rectangle as player sprite
        // g2.setColor(Color.WHITE);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "upStatic":
                image = upStatic;
                break;

            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "downStatic":
                image = downStatic;
                break;

            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "leftStatic":
                image = leftStatic;
                break;

            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "rightStatic":
                image = rightStatic;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
