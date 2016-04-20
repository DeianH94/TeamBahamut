package display;

import javax.swing.*;
import java.awt.*;

public class Display {
    private String name;
    private int width;
    private int height;

    public JFrame frame;
    private Canvas canvas;

    // public JTextField playerName;

    public Display (String name , int width , int height ) {
        this.name = name;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        this.frame = new JFrame(name);
        this.frame.setPreferredSize(new Dimension(width, height));
        this.frame.setMinimumSize(new Dimension(width, height));
        this.frame.setMaximumSize(new Dimension(width, height));
        this.frame.setVisible(true);
        this.frame.setFocusable(true);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(width, height));
        this.canvas.setMinimumSize(new Dimension(width, height));
        this.canvas.setMaximumSize(new Dimension(width, height));
        this.canvas.setFocusable(true);
        // playerName = new JTextField(30);
        // playerName.setBounds(200, 200, 100, 10);
        // playerName.setEditable(true);
        // playerName.setVisible(false);
        // frame.add(playerName);
        this.frame.add(this.canvas);
        this.frame.pack();

    }

    // public void hideTextField(){
    //     playerName.setVisible(false);
    // }

    public Canvas getCanvas() {
        return canvas;
    }
}
