package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.convertTime;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    public static final int MAX_HEIGHT = 200;
    private static final int START_JUMP_SPEED = 2;
    private int x;
    private int y;
    private double vy;
    private int timeFromStart = 0;
    private int speed;
    private Texture texture;
    private boolean isJumping;
    private int maxHeight;

    public Bird(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = new Texture("bird0.png");
    }

    public Bird() {
        this(0, 500, 5);
    }

    public void fly() {
        timeFromStart++;
        x += 0*speed;
        if (y > maxHeight) {
            timeFromStart = 0;
            isJumping = false;
        }
        y += vy * convertTime(timeFromStart) - (MyGdxGame.G + (convertTime(timeFromStart) * convertTime(timeFromStart) / 2));
        vy += -MyGdxGame.G*convertTime(timeFromStart);
        if (isJumping) {
            vy = START_JUMP_SPEED * 10;
            //y += START_JUMP_SPEED * convertTime(timeFromStart) - (MyGdxGame.G + (convertTime(timeFromStart) * convertTime(timeFromStart) / 2));
            return;
        }
        //y -= MyGdxGame.G + (convertTime(timeFromStart) * convertTime(timeFromStart) / 2);

    }

    public void onClick() {
        isJumping = true;
        maxHeight = y + MAX_HEIGHT;
    }

    public void draw(Batch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
