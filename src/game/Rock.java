package game;

import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Anton on 12-Apr-16.
 */
public class Rock {

    private int x, y, width, heigh;
    public Rectangle boundingBox;
    private BufferedImage image;


    public Rock(int x, int y, int width, int heigh) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigh = heigh;
        this.boundingBox = new Rectangle(x, y, width, heigh);
        this.image = Assets.rock;
    }

    public void tick(){

    }
    public void render(Graphics g){
        g.drawImage(this.image,this.x, this.y, this.width,this.heigh,null);
    }
}



