package entities;

import java.awt.*;

public abstract class Entity {
    // Change to float if there is problem whit the rendering
    protected int x, y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void tick();

    public abstract void render(Graphics g);
}
