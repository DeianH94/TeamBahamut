package game;

import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rock {

    private int x, y, width, height;
    public Rectangle boundingBox;
    private BufferedImage image;


    public Rock(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.image = Assets.rock;
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.drawImage(this.image,this.x, this.y, this.width, this.height, null);
    }
}



