package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_WIDTH;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenGame.FIXED_TIME_STEP;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MovingBackGround {
    private static final String TEXTURE_PATH = "BackGround/";
    public static final int BACKGROUND_SPEED = 1;
    private Texture texture;
    private int speed;
    private float accumulator;
    private float texture1X, texture2X;
    public MovingBackGround(String fileName) {
        texture1X = 0;
        texture2X = SCR_WIDTH;
        speed = BACKGROUND_SPEED;
        accumulator = 0;
        texture = new Texture(TEXTURE_PATH + fileName);
    }
    public void move(float delta) {
        accumulator += delta;
        while (true) {
            accumulator -= FIXED_TIME_STEP;
            texture1X -= speed;
            texture2X -= speed;
            if (texture1X <= -SCR_WIDTH) {
                texture1X = SCR_WIDTH;
            }
            if (texture2X <= -SCR_WIDTH) {
                texture2X = SCR_WIDTH;
            }
            if (accumulator <= FIXED_TIME_STEP) {
                break;
            }
        }
    }
    public void draw(SpriteBatch batch) {
        batch.draw(texture, texture1X, 0, SCR_WIDTH, SCR_HEIGHT);
        batch.draw(texture, texture2X, 0, SCR_WIDTH, SCR_HEIGHT);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void dispose() {
        texture.dispose();
    }
}
