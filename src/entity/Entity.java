package entity;

import java.awt.image.BufferedImage;

public abstract class Entity {

    protected int worldPosX, worldPosY;
    protected int speed;

    protected String direction;
    protected String lastDirection;
    protected void updateLastDirection() {
        lastDirection = direction;
    }
    protected String getLastDirection() {
        return lastDirection;
    }

    protected void setWorldPosition(int tileColNr, int tileRowNr, int tileSize) {
        worldPosX = tileColNr * tileSize;
        worldPosY = tileRowNr * tileSize;
    }
    protected BufferedImage up1, up2, upStatic, down1, down2, downStatic, right1, right2, rightStatic, left1, left2, leftStatic;

    protected int spriteNum = 1;
    protected int spriteCounter = 0;
    protected double animationSpeedMultiplier;
    protected int defaultAnimationSpeed = 10;
}
