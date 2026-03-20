package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    private int x;
    private int y;
    private int speed;
    private Texture texture;

    public Bird(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = new Texture("bird0.png");
    }

    public Bird() {
        this(0, 0, 1);
    }

    public void fly() {
        x += speed;
        y += speed;
    }

    public void draw(Batch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
