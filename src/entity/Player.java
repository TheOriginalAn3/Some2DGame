package entity;

import main.GamePannel;
import main.KeyHandler;

public class Player extends Entity{
    
    GamePannel gp;
    KeyHandler keyHandler;

    public Player(GamePannel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
    }
}
