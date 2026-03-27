package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_WIDTH;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.ScreenGame.SPEED_X;

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
    private int count;
    private int distanceBetweenTubes;
    private float gapY;
    private int gapHeight;

    public Tube(int tubeCount, int tubeIdx) {
        count = 1;
        gapHeight = 400;
        gapY = (float) gapHeight / 2 + PADDING + rand.nextInt(SCR_HEIGHT - 2 * (PADDING + gapHeight / 2));
        distanceBetweenTubes = SCR_WIDTH + width / tubeCount;
        x = distanceBetweenTubes * tubeIdx + SCR_WIDTH;
        width = 200;
        height = 700;
        this.textureUpperTube = new Texture(TEXTURE_PATH + "tube_flipped.png");
        this.textureDownTube = new Texture(TEXTURE_PATH + "tube.png");
    }
    public void move() {
        x -= SPEED_X;
        if (x < -width) {
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = (float) gapHeight / 2 + PADDING + rand.nextInt(SCR_HEIGHT - 2 * (PADDING + gapHeight / 2));
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(textureUpperTube, x, gapY + ((float) gapHeight / 2), width, height);
        batch.draw(textureDownTube, x, gapY - ((float) gapHeight * 2), width, height);
        count++;
    }

    public void dispose() {
        textureUpperTube.dispose();
        textureDownTube.dispose();
    }
}
