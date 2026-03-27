package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.utils.FrameCounter;

public class Bird {
    public static final float BIRD_X_POS = 300;
    private static final float JUMP_FORCE = 5.5f;
    private static final String BIRD_TILES_PATH = "BirdTiles/";
    private final AtomicInteger time = new AtomicInteger(1);
    private final Runnable taskTimer = () -> {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            time.getAndIncrement();
        }
    };
    private FrameCounter frameCounter;
    private final List<Texture> birdTiles = new ArrayList<>();
    private final float x;
    private float y;
    private float vy;
    private Texture texture;
    private final Thread timerThread;

    public Bird(int y) {
        this.x = BIRD_X_POS;
        this.y = y;
        texture = new Texture(BIRD_TILES_PATH + "bird0.png");
        birdTiles.add(texture);
        birdTiles.add(new Texture(BIRD_TILES_PATH + "bird1.png"));
        birdTiles.add(new Texture(BIRD_TILES_PATH + "bird2.png"));
        frameCounter = new FrameCounter(birdTiles.size() - 1, 3);
        timerThread = new Thread(taskTimer);
        timerThread.start();
    }

    public Bird() {
        this(500);
    }

    public void fly() {
        if (vy > 0) {
            frameCounter.nextFrame();
        }
        vy -= ((float) time.get() / 1000) * MyGdxGame.G;
        y += vy;
    }

    public void onClick() {
        if (vy < 0) {
            time.getAndSet(1);
            vy = JUMP_FORCE;
        }
    }

    public void draw(Batch batch) {
        if (frameCounter.getFrame() != 0) frameCounter.nextFrame();
        texture = birdTiles.get(frameCounter.getFrame());
        batch.draw(texture, x, y, 250,200);
    }

    public void dispose() {
        texture.dispose();
        timerThread.interrupt();
    }
}
