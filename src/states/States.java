package states;

import game.Game;

import java.awt.*;

public abstract class States{
    protected Game game;

    public States(Game game) {
        this.game = game;
    }
    //Every state has it's own tick() method
    public abstract void tick();

    //Every state has it's own render() method
    public abstract void render(Graphics g);
}
