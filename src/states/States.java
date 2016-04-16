package states;

import java.awt.*;

public abstract class States{
    //Every state has it's own tick() method
    public abstract void tick();

    //Every state has it's own render() method
    public abstract void render(Graphics g);
}
