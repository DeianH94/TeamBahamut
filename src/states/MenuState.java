package states;

import game.Game;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuState extends States{
    private BufferedImage img;
    private SpriteSheet button;
    private static final int buttonWidth = 254;
    private static final int buttonHeight = 80;
    private int startRow = 0;
    private int exitRow = 0;

    public MenuState(Game game) {
        super(game);
        img = ImageLoader.loadImage("/textures/Background.png");
        button = Assets.button;
    }

    @Override
    public void tick() {
        if (game.getMouseHandler().getMouseX() >= 260
                && game.getMouseHandler().getMouseX() < 260 + buttonWidth
                && game.getMouseHandler().getMouseY() > 260
                && game.getMouseHandler().getMouseY() < 260 + buttonHeight) {
            startRow = 1;
            if (game.getMouseHandler().isLeftPressed())
            {
                StateManager.setState(game.gameState);

            }
        } else {
            startRow = 0;
        }

        if (game.getMouseHandler().getMouseX() >= 260
                && game.getMouseHandler().getMouseX() < 260 + buttonWidth
                && game.getMouseHandler().getMouseY() > 360
                && game.getMouseHandler().getMouseY() < 360 + buttonHeight) {
            exitRow = 1;
            if (game.getMouseHandler().isLeftPressed())
            {
                System.exit(0);
            }
        } else {
            exitRow = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, game.getWidth(), game.getHeight(), null);
        g.drawImage(this.button.crop(0, buttonHeight * startRow, buttonWidth, buttonHeight), 260, 260, null);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 60));
        g.drawString("The Vegetarian Dog",145,150);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("Start new game", 315, 305);
        g.drawImage(this.button.crop(0, buttonHeight * exitRow, buttonWidth, buttonHeight), 260, 360, null);
        g.drawString("Exit", 375, 405);

    }
}
