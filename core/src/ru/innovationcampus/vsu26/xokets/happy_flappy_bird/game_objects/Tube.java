package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.game_objects;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_WIDTH;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenGame.FIXED_TIME_STEP;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenGame.SPEED_X;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Tube {
    private static final Random rand = new Random();
    private static final String TEXTURE_PATH = "Tubes/";
    private static final int PADDING = 50;
    private Texture textureUpperTube;
    private Texture textureDownTube;
    private float x;
    private int width;
    private int height;
    private float distanceBetweenTubes;
    private float gapY;
    private int gapHeight;
    private float accumulator;
    private boolean isPointReceived;

    public Tube(int tubeCount, int tubeIdx) {
        int id = tubeIdx + 1;
        gapHeight = 410;
        gapY = (float) gapHeight / 2 + PADDING + rand.nextInt(SCR_HEIGHT - 2 * (PADDING + gapHeight / 2));
        distanceBetweenTubes = (SCR_WIDTH + (float)  width) / (tubeCount - 1);
        x = distanceBetweenTubes * id + SCR_WIDTH;
        width = 200;
        height = 700;
        this.textureUpperTube = new Texture(TEXTURE_PATH + "tube_flipped.png");
        this.textureDownTube = new Texture(TEXTURE_PATH + "tube.png");
        accumulator = 0;
        isPointReceived = false;
    }
    public void move(float delta) {
        accumulator += delta;
        while (accumulator >= FIXED_TIME_STEP) {
            x -= SPEED_X;
            if (x < -width) {
                isPointReceived = false;
                x = SCR_WIDTH + distanceBetweenTubes;
                gapY = (float) gapHeight / 2 + PADDING + rand.nextInt(SCR_HEIGHT - 2 * (PADDING + gapHeight / 2));
            }
            accumulator -= FIXED_TIME_STEP;
        }
    }

    public boolean isHit(Bird bird) {
        return bird.getX() + bird.getWidth() >= x && bird.getX() <= x + width && (bird.getY() <= gapY - (float) gapHeight / 2 || bird.getY() + bird.getHeight() >= gapY + (float) gapHeight / 2);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(textureUpperTube, x, gapY + ((float) gapHeight / 2), width, height);
        batch.draw(textureDownTube, x, gapY - ((float) gapHeight * 2), width, height);
    }

    public void dispose() {
        textureUpperTube.dispose();
        textureDownTube.dispose();
    }
    public boolean needAddPoint(Bird bird) {
        if (bird.getX() > x + width && !isPointReceived) {
            isPointReceived = true;
            return true;
        }
        return false;
    }
}
