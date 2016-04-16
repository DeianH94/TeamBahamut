package states;

import entities.creatures.Player;
import game.Game;

import java.awt.*;

public class GameState extends States{
    //private Player player;

    public GameState(Game game) {
        super(game);
        //player = new Player(200, 300);
    }

    @Override
    public void tick() {
        //player.tick();

    }

    @Override
    public void render(Graphics g) {
        //player.render(g);
    }
}
