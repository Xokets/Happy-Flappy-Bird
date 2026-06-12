package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;

public class MovingBackGround implements Disposable {
    private static final String TEXTURE_PATH = "BackGround/";
    public static final int BACKGROUND_SPEED = 1;
    private Texture texture;
    private float speed;
    private float texture1X;

    public MovingBackGround(String fileName) {
        texture1X = 0;
        speed = BACKGROUND_SPEED;
        texture = new Texture(TEXTURE_PATH + fileName);
    }

    public void move(float acceleration) {
        texture1X -= speed + acceleration;
        if (texture1X + SCR_WIDTH <= 0) {
            texture1X = 0;
        }
    }

    public void draw(Batch batch) {
        batch.draw(texture, texture1X, 0, SCR_WIDTH, SCR_HEIGHT);
        batch.draw(texture, texture1X + SCR_WIDTH, 0, SCR_WIDTH, SCR_HEIGHT);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void dispose() {
        texture.dispose();
    }

    public float getSpeed() {
        return speed;
    }
}
