package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.ScreenGame.FIXED_TIME_STEP;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.utils.FrameCounter;

public class Bird {
    public static final float X_POS = 300;
    private static final float JUMP_FORCE = 6.4f;
    private static final String BIRD_TILES_PATH = "BirdTiles/";
    private float time;
    private FrameCounter frameCounter;
    private final List<Texture> birdTiles = new ArrayList<>();
    private  float x;
    private float y;
    private int width;
    private int height;
    private float velocityY;
    private Texture texture;
    private float accumulator;

    public Bird(int y) {
        this.y = y;
        this.x = X_POS;
        this.width = 150;
        this.height = 100;
        texture = new Texture(BIRD_TILES_PATH + "bird0.png");
        birdTiles.add(texture);
        birdTiles.add(new Texture(BIRD_TILES_PATH + "bird1.png"));
        birdTiles.add(new Texture(BIRD_TILES_PATH + "bird2.png"));
        frameCounter = new FrameCounter(birdTiles.size() - 1, 3);
        accumulator = 0;
        time = 0;
    }

    public Bird() {
        this(500);
    }

    public void fly(float delta) {
        accumulator += delta;
        time += delta;
        while (accumulator >= FIXED_TIME_STEP) {
            if (velocityY > 0) {
                frameCounter.nextFrame();
            }
            velocityY -= MyGdxGame.G * time;
            y += velocityY;
            accumulator -= FIXED_TIME_STEP;
        }
    }

    public void onClick() {
        if (velocityY < 0) {
            time = 0;
            velocityY = JUMP_FORCE;
        }
    }

    public void draw(Batch batch) {
        if (frameCounter.getFrame() != 0) frameCounter.nextFrame();
        texture = birdTiles.get(frameCounter.getFrame());
        batch.draw(texture, X_POS, y, width,height);
    }

    public boolean isInField() {
        return !(y + height < 0 || y > SCR_HEIGHT);
    }

    public void dispose() {
        texture.dispose();
        for (Texture tile : birdTiles) {
            tile.dispose();
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
