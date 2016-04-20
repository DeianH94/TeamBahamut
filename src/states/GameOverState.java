package states;

import game.Game;
import gfx.Assets;
import gfx.ImageLoader;
import gfx.SpriteSheet;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameOverState extends States{
    private BufferedImage img;
    private SpriteSheet button;
    private static final int buttonWidth = 254;
    private static final int buttonHeight = 80;
    private int startRow = 0;
    private int exitRow = 0;

    public GameOverState(Game game) {
        super(game);
        img = ImageLoader.loadImage("/textures/Background.png");
        button = Assets.button;

        // String name = JOptionPane.showInputDialog(game.display.frame,
        //         "New Highscore",
        //         "Input your name:",
        //         JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void tick() {
        if (game.getMouseHandler().getMouseX() >= 100
                && game.getMouseHandler().getMouseX() < 100 + buttonWidth
                && game.getMouseHandler().getMouseY() > 360
                && game.getMouseHandler().getMouseY() < 360 + buttonHeight) {
            startRow = 1;
            if (game.getMouseHandler().isLeftPressed()) {
                //game.display.hideTextField();
                game.gameState = new GameState(game);
                StateManager.setState(game.gameState);
            }
        } else {
            startRow = 0;
        }

        if (game.getMouseHandler().getMouseX() >= 400
                && game.getMouseHandler().getMouseX() < 400 + buttonWidth
                && game.getMouseHandler().getMouseY() > 360
                && game.getMouseHandler().getMouseY() < 360 + buttonHeight) {
            exitRow = 1;
            if (game.getMouseHandler().isLeftPressed()) {
                System.exit(0);
            }
        } else {
            exitRow = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, game.getWidth(), game.getHeight(), null);
        g.drawImage(this.button.crop(0, buttonHeight * startRow, buttonWidth, buttonHeight), 100, 360, null);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("Start new game", 150, 405);
        g.drawImage(this.button.crop(0, buttonHeight * exitRow, buttonWidth, buttonHeight), 400, 360, null);
        g.drawString("Exit", 500, 405);
        g.setFont(new Font("TimesRoman", Font.BOLD, 80));
        g.drawString("Game Over",175,250);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.setColor(Color.RED);
        g.drawString("Your Score is:    " + GameState.player.getScore(), 335, 300);
    }
}
