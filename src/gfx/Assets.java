package gfx;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import static com.sun.org.apache.bcel.internal.util.SecuritySupport.getResourceAsStream;

public class Assets {

    public static BufferedImage background;
    public static SpriteSheet player;
    public static SpriteSheet fruit;
    public static BufferedImage rock;


    private static final int skullWidth = 17, skullHeight = 17;

    public static void init(){
        background = ImageLoader.loadImage("/textures/Background.png");
        player = new SpriteSheet(ImageLoader.loadImage("/textures/Dog.png"));
        fruit = new SpriteSheet(ImageLoader.loadImage("/textures/food.png"));
        SpriteSheet skull = new SpriteSheet(ImageLoader.loadImage("/textures/skull.png"));

        rock = skull.crop(0, 0, skullWidth, skullHeight);
    }
}
