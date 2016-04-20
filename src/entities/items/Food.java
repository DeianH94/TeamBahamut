package entities.items;

import entities.Entity;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.Random;

public class Food extends Entity {
    private final int DEFAULT_FOOD_WIDTH = 29;
    private final int DEFAULT_FOOD_HEIGHT = 28;

    private int width, height;
    public Rectangle boundingBox;
    private SpriteSheet img;
    private Random r;
    private int rWidth;
    private int rHeight;


    public Food(int x, int y) {
        super(x, y);
        r = new Random();
        rWidth = r.nextInt(4);
        rHeight = r.nextInt(4);
        this.width = DEFAULT_FOOD_WIDTH;
        this.height = DEFAULT_FOOD_HEIGHT;
        this.boundingBox = new Rectangle(x, y, width, height);
        this.img = Assets.fruit;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.drawImage(this.img.crop(width * rWidth, height * rHeight, width, height),
                this.x,
                this.y,
                null);
        // g.drawRect(this.x, this.y, this.width, this.height);
    }
}
