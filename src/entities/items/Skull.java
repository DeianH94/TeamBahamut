package entities.items;

import entities.Entity;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Skull extends Entity {
    private final int DEFAULT_SKULL_WIDTH = 27;
    private final int DEFAULT_SKULL_HEIGHT = 27;

    private int width, height;
    public Rectangle boundingBox;
    private BufferedImage image;

    public Skull(int x, int y) {
        super(x, y);
        this.width = DEFAULT_SKULL_WIDTH;
        this.height = DEFAULT_SKULL_HEIGHT;
        this.boundingBox = new Rectangle(x, y, this.width, this.height);
        this.image = Assets.skull;
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.drawImage(this.image,this.x, this.y, this.width, this.height, null);
        // g.drawRect(this.x, this.y, this.width, this.height);
    }
}



