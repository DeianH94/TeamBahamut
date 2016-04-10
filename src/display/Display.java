package display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Misho on 9.4.2016 г..
 */
public class Display {
    private JFrame frame;
    private Canvas canvas;

    public Display (String name , int widht , int height ) {
        this.frame = new JFrame(name);
        this.frame.setPreferredSize(new Dimension(widht, height));
        this.frame.setMinimumSize(new Dimension(widht, height));
        this.frame.setMaximumSize(new Dimension(widht, height));
        this.frame.setVisible(true);
        this.frame.setFocusable(true);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(widht, height));
        this.canvas.setMinimumSize(new Dimension(widht, height));
        this.canvas.setMaximumSize(new Dimension(widht, height));
        this.canvas.setFocusable(true);


        this.frame.add(this.canvas);
        this.frame.pack();
    }

        public Canvas getCanvas() {
        return canvas;
    }
}
