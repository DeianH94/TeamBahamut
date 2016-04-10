package gfx;

import java.awt.image.BufferedImage;

/**
 * Created by Anton on 09-Apr-16.
 */
public class Assets {

    public static BufferedImage background;
    public static BufferedImage player;
    public static BufferedImage food;
    public static BufferedImage rock;

    private static final int width = 48, height = 48;

    public static void init(){
        SpriteSheet pinguin = new SpriteSheet(ImageLoader.loadImage("/textures/Dog.png"));
        background = ImageLoader.loadImage("/textures/forest.jpg");
        player = pinguin.crop(48, 0, width, height);
        SpriteSheet fruit = new SpriteSheet(ImageLoader.loadImage("/textures/food.png"));
        food = fruit.crop(0, 0, 34 , 34);
        rock = fruit.crop(0 , 34 , 34 , 34);
    }
}
