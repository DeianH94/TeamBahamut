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

    private static final int width = 32, height = 32;

    public static void init(){
        SpriteSheet pinguin = new SpriteSheet(ImageLoader.loadImage("/textures/Penguin.png"));
        background = ImageLoader.loadImage("/textures/Antarctica2.jpg");
        player = pinguin.crop(0, 0 ,32 , 32);
        SpriteSheet fruit = new SpriteSheet(ImageLoader.loadImage("/textures/fruit-catcher-spritesheet.png"));
        food = fruit.crop(0, 0, 34 , 34);
        rock = fruit.crop(0 , 34 , 34 , 34);
    }
}
