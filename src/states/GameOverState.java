package states;

import HighscoreManager.LoadRanking;
import HighscoreManager.Ranking;
import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameOverState extends States{
    private static final int BUTTON_WIDTH = 254;
    private static final int BUTTON_HEIGHT = 80;

    private BufferedImage img;
    private SpriteSheet button;
    private int startRow = 0;
    private int exitRow = 0;
    private String name;
    private boolean isNewGame = true;

    public GameOverState(Game game) {
        super(game);
        img = Assets.background;
        button = Assets.button;
        saveName();
    }

    public void saveName() {
        if (!GameState.player.isAlive()) {
            name = JOptionPane.showInputDialog(game.display.frame,
                    "Input your name:",
                    "New Highscore",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void tick() {
        if (game.getMouseHandler().getMouseX() >= 100
                && game.getMouseHandler().getMouseX() < 100 + BUTTON_WIDTH
                && game.getMouseHandler().getMouseY() > 360
                && game.getMouseHandler().getMouseY() < 360 + BUTTON_HEIGHT) {
            startRow = 1;
            if (game.getMouseHandler().isLeftPressed()) {
                //game.display.hideTextField();
                isNewGame = true;
                game.gameState = new GameState(game);
                StateManager.setState(game.gameState);
            }
        } else {
            startRow = 0;
        }

        if (game.getMouseHandler().getMouseX() >= 400
                && game.getMouseHandler().getMouseX() < 400 + BUTTON_WIDTH
                && game.getMouseHandler().getMouseY() > 360
                && game.getMouseHandler().getMouseY() < 360 + BUTTON_HEIGHT) {
            exitRow = 1;
            if (game.getMouseHandler().isLeftPressed()) {
                System.exit(0);
            }
        } else {
            exitRow = 0;
        }
        try {

            if (isNewGame){
                Ranking.ranking(LoadRanking.loadRanking(), GameState.player.getScore(), name);
                isNewGame = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, game.getWidth(), game.getHeight(), null);
        g.drawImage(this.button.crop(0, BUTTON_HEIGHT * startRow, BUTTON_WIDTH, BUTTON_HEIGHT), 100, 360, null);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("Start new game", 150, 405);
        g.drawImage(this.button.crop(0, BUTTON_HEIGHT * exitRow, BUTTON_WIDTH, BUTTON_HEIGHT), 400, 360, null);
        g.drawString("Exit", 500, 405);
        g.setFont(new Font("TimesRoman", Font.BOLD, 80));
        g.drawString("Game Over",175,250);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.setColor(Color.RED);
        g.drawString("Your Score is:    " + GameState.player.getScore(), 335, 300);
    }
}
