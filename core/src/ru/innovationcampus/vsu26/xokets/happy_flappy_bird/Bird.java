package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.graphics.Texture;

public class Bird {
    private int x;
    private int y;
    private Texture texture;

    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
        this.texture = new Texture("bird0.png");
    }

    public Bird() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Texture getTexture() {
        return texture;
    }

    public void moveX(int num) {
        if (num < 0) {
            x = 0;
            return;
        }
        x += num;
    }
    public void moveY(int num) {
        if (num < 0) {
            y = 0;
            return;
        }
        y += num;
    }

    public void dispose() {
        texture.dispose();
    }
}
