package states;

import HighscoreManager.LoadRanking;
import game.Game;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.TreeMap;

public class HighscoresState extends States {
    private BufferedImage img;
    private SpriteSheet button;
    private TreeMap<Integer, String> rank;
    private int i = 0;
    private static final int buttonWidth = 254;
    private static final int buttonHeight = 80;
    private int backRow = 0;

    public HighscoresState(Game game) {
        super(game);
        img = Assets.background;
        button = Assets.button;
        rank = LoadRanking.loadRanking();
    }

    @Override
    public void tick() {
        if (game.getMouseHandler().getMouseX() >= 260
                && game.getMouseHandler().getMouseX() < 260 + buttonWidth
                && game.getMouseHandler().getMouseY() > 400
                && game.getMouseHandler().getMouseY() < 400 + buttonHeight) {
            backRow = 1;
            if (game.getMouseHandler().isLeftPressed())
            {
                StateManager.setState(game.menuState);
            }
        } else {
            backRow = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, 0, 0, game.getWidth(), game.getHeight(), null);
        g.setFont(new Font("TimesRoman", Font.ITALIC, 60));
        g.drawString("Highscores", 220, 150);
        g.setFont(new Font("TimesRoman", Font.BOLD, 25));
        for (Map.Entry<Integer, String> user : rank.entrySet()) {
            g.drawString(user.getKey() + " -> " + user.getValue(), 300, 300 + 20 * i);
            i++;
        }

        i = 0;
        g.drawImage(this.button.crop(0, buttonHeight * backRow, buttonWidth, buttonHeight), 260, 400, null);
        g.drawString("Back", 350, 445);
    }
}
