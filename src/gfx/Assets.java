package gfx;

import java.awt.image.BufferedImage;

/**
 * Created by Anton on 09-Apr-16.
 */
public class Assets {

    public static BufferedImage background;
    public static BufferedImage player;
    public static BufferedImage gift;

    private static final int width = 32, height = 32;

    public static void init(){
        background = ImageLoader.loadImage("/resources/Antarctica2.jpg");
        player = ImageLoader.loadImage("/resources/Penguin.png");
        gift = ImageLoader.loadImage("/resources/fruit-catcher-spritesheet.png");
    }
}
