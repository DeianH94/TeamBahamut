package entities.creatures;

import entities.Entity;
import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

import static states.GameState.player;

public class Player extends Entity {
    private final int DEFAULT_PLAYER_WIDTH = 48;
    private final int DEFAULT_PLAYER_HEIGHT = 48;
    private final int INITIAL_VELOCITY = 5;

    private int velocity;
    private int width, height;

    private SpriteSheet img;
    private Rectangle boundingBox;
    private int col = 0;
    private int row = 0;
    private int score;

    private static boolean goingUp;
    private static boolean goingDown;
    private static boolean goingLeft;
    private static boolean goingRight;

    public Player(int x, int y) {
        super(x, y);
        this.width = DEFAULT_PLAYER_WIDTH;
        this.height = DEFAULT_PLAYER_HEIGHT;
        this.velocity = INITIAL_VELOCITY;
        this.img = Assets.player;
        this.boundingBox = new Rectangle(this.width, this.height);
        this.score = 0;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }


    //Checks if the player intersects with something
    // if rock ....             if food .....
    public boolean intersects(Rectangle r) {
        if(this.boundingBox.contains(r) || r.contains(this.boundingBox)) {
            return true;
        }

        return false;
    }

    public static void setGoingUp(boolean goingUp) {
        Player.goingUp = goingUp;
    }

    public static void setGoingDown(boolean goingDown) {
        Player.goingDown = goingDown;
    }

    public static void setGoingLeft(boolean goingLeft) {
        Player.goingLeft = goingLeft;
    }

    public static void setGoingRight(boolean goingRight) {
        Player.goingRight = goingRight;
    }

    //Update the movement of the player
    public void tick() {
        //Update the bounding box's position
        this.boundingBox.setBounds(this.x, this.y, this.width, this.height);

        if(goingUp) {
            this.y -= this.velocity;
            this.row =3;
        }

        if(goingDown) {
            this.y += this.velocity;
            this.row = 0;
        }

        if(goingLeft) {
            this.x -= this.velocity;
            this.row = 1;
        }

        if(goingRight) {
            this.x += this.velocity;
            this.row = 2;
        }

        this.col++;
        this.col %= 3;
    }

    //Draws the player
                                           // player 1 comes form Spritesheet
    public void render(Graphics g) {
        g.drawImage(this.img.crop(this.col * this.width, this.row * this.height, this.width, this.height),
                this.x,
                this.y,
                null);
        // g.drawRect(this.x, this.y, this.width, this.height);
    }

    public void speedDown(){
       this.velocity += 2;
    }

    public void speedUp(){
        this.velocity -= 0.3;
    }

    public void speedUpMore() { this.velocity = 3; }

    public boolean isAlive(){
        Rectangle playerBoundingBox = player.getBoundingBox();
        return this.velocity >= 1
                && playerBoundingBox.getX() < 745.0
                && playerBoundingBox.getX() >= 0.0
                && playerBoundingBox.getY() < 515
                && playerBoundingBox.getY() > -10.0;
    }

}
