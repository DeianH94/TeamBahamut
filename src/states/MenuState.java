package states;

import game.Game;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuState extends States{
    private BufferedImage img;
    private SpriteSheet startButton;
    private SpriteSheet exitButton;
    private static final int buttonWidth = 254;
    private static final int buttonHeight = 80;
    private int startRow = 0;
    private int exitRow = 0;

    public MenuState(Game game) {
        super(game);
        img = ImageLoader.loadImage("/textures/Background.png");
        startButton = Assets.button;
        exitButton = Assets.button;
    }

    @Override
    public void tick() {
        if (game.mouseHandler.getMouseX() >= 260
                && game.mouseHandler.getMouseX() < 260 + buttonWidth
                && game.mouseHandler.getMouseY() > 260
                && game.mouseHandler.getMouseY() < 260 + buttonHeight) {
            startRow = 1;
            if (game.mouseHandler.isLeftPressed())
            {
                StateManager.setState(game.gameState);
            }
        } else {
            startRow = 0;
        }

        if (game.mouseHandler.getMouseX() >= 260
                && game.mouseHandler.getMouseX() < 260 + buttonWidth
                && game.mouseHandler.getMouseY() > 360
                && game.mouseHandler.getMouseY() < 360 + buttonHeight) {
            exitRow = 1;
            if (game.mouseHandler.isLeftPressed())
            {
                System.exit(0);
            }
        } else {
            exitRow = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, game.width, game.height, null);
        g.drawImage(this.startButton.crop(0, buttonHeight * startRow, buttonWidth, buttonHeight), 260, 260, null);
        g.drawString("Start new game", 345, 305);
        g.drawImage(this.startButton.crop(0, buttonHeight * exitRow, buttonWidth, buttonHeight), 260, 360, null);
        g.drawString("Exit", 375, 405);
    }
}
