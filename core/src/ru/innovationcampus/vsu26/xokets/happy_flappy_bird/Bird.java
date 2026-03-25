package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.utils.FrameCounter;

public class Bird {
    private static final float JUMP_FORCE = 10f;
    private static final String BIRD_TILES_PATH = "BirdTiles/";
    private FrameCounter frameCounter;

    private final List<Texture> birdTiles = new ArrayList<>();
    private final float x;
    private float y;
    private float vy;
    private Texture texture;

    public Bird(int y) {
        this.x = 200;
        this.y = y;
        texture = new Texture(BIRD_TILES_PATH + "bird0.png");
        birdTiles.add(texture);
        birdTiles.add(new Texture(BIRD_TILES_PATH + "bird1.png"));
        birdTiles.add(new Texture(BIRD_TILES_PATH + "bird2.png"));
        frameCounter = new FrameCounter(birdTiles.size() - 1, 3);
    }

    public Bird() {
        this(500);
    }

    public void fly() {
        if (vy > 0) {
            frameCounter.nextFrame();
        }
        vy -= MyGdxGame.G;
        y += vy;
    }

    public void onClick() {
        if (vy < 0) {
            vy = JUMP_FORCE;
        }
    }

    public void draw(Batch batch) {
        if (frameCounter.getFrame() != 0) frameCounter.nextFrame();
        texture = birdTiles.get(frameCounter.getFrame());
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}
