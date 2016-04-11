package game;

import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class Player {
    private int x, y;
    private int velocity;
    private int width, height;

    private SpriteSheet img;
    private Rectangle boundingBox;
    private int col = 0;
    private int row = 0;

    public static boolean goingUp;
    public static boolean goingDown;
    public static boolean goingLeft;
    public static boolean goingRight;

    public Player() {
        this.x = 200;
        this.y = 300;
        this.width = 48;
        this.height = 48;
        this.velocity = 5;
        this.img = Assets.player;
        this.boundingBox = new Rectangle(this.width, this.height);
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }


    //Checks if the player intersects with something
    // if rock ....             if food .....
    public boolean Intersects(Rectangle r) {
        if(this.boundingBox.contains(r) || r.contains(this.boundingBox)) {
            return true;
        }
        return false;
    }

    //Update the movement of the player
    public void tick() {
        //Update the bounding box's position
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);

        if(goingUp) {
            this.y -= this.velocity;
            this.row =3;
            this.col++;
            this.col %= 3;
        }
        if(goingDown) {
            this.y += this.velocity;
            this.row = 0;
            this.col++;
            this.col %= 3;
        }
        if(goingLeft) {
            this.x -= this.velocity;
            this.row = 1;
            this.col++;
            this.col %= 3;
        }
        if(goingRight) {
            this.x += this.velocity;
            this.row = 2;
            this.col++;
            this.col %= 3;
        }
    }

    //Draws the player
                                           // player 1 comes form Spritesheet
    public void render(Graphics g) {
        g.drawImage(this.img.crop(this.col * this.width, this.row * this.height, this.width, this.height),
                this.x,
                this.y,
                null);
    }
}
