package gfx;

import java.awt.image.BufferedImage;

public class Assets {

    public static BufferedImage background;
    public static SpriteSheet player;
    public static SpriteSheet fruit;
    public static SpriteSheet button;
    public static BufferedImage skull;

    public static void init(){
        background = ImageLoader.loadImage("/textures/Background.png");
        player = new SpriteSheet(ImageLoader.loadImage("/textures/Dog.png"));
        fruit = new SpriteSheet(ImageLoader.loadImage("/textures/food.png"));
        skull = ImageLoader.loadImage("/textures/skull.png");
        button = new SpriteSheet(ImageLoader.loadImage("/textures/button.png"));
    }
}
