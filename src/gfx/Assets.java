package gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage background;
    public static SpriteSheet player;
    public static BufferedImage food;
    public static BufferedImage rock;

    private static final int width = 48, height = 48;

    public static void init(){
        background = ImageLoader.loadImage("/textures/Background.png");
        player = new SpriteSheet(ImageLoader.loadImage("/textures/Dog.png"));
        SpriteSheet fruit = new SpriteSheet(ImageLoader.loadImage("/textures/food.png"));
        SpriteSheet skull = new SpriteSheet(ImageLoader.loadImage("/textures/skull.png"));
        food = fruit.crop(0, 0, 29, 28);
        rock = skull.crop(0, 0, 17, 17);
    }
}
