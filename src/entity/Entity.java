package entity;

import java.awt.image.BufferedImage;

public abstract class Entity {

    public int x, y;
    public int speed;
    public String direction = null;

    public BufferedImage up1, up2, upStatic, down1, down2, downStatic, right1, right2, rightStatic, left1, left2, leftStatic;

    public int spriteNum = 1;
    protected int spriteCounter = 0;
    protected double animationSpeedMultiplier;
    protected int defaultAnimationSpeed = 10;
}
