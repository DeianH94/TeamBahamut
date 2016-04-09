package gfx;

import java.awt.image.BufferedImage;

/**
 * Created by Anton on 09-Apr-16.
 */
public class SpriteSheet {

    private BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    public BufferedImage crop (int x, int y, int width, int height){
        return image.getSubimage(x, y, width, height);
    }
}
