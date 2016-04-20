package entities.items;

import entities.Entity;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rock extends Entity {

    private int width, height;
    public Rectangle boundingBox;
    private BufferedImage image;

    public Rock(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.image = Assets.rock;
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.drawImage(this.image,this.x, this.y, this.width, this.height, null);
        //g.drawRect(this.x, this.y, this.width, this.height);
    }
}



