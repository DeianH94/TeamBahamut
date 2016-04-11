package game;

import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Anton on 11-Apr-16.
 */
public class Food {
    private int x, y, width, heigh;
    public Rectangle boundingBox;
    private BufferedImage image;


    public Food(int x, int y, int width, int heigh) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigh = heigh;
        this.boundingBox = new Rectangle(x, y, width, heigh);
        this.image = Assets.food;
    }

    public void tick(){

    }
    public void render(Graphics g){
        g.drawImage(this.image,this.x, this.y, this.width,this.heigh,null);
    }
}
