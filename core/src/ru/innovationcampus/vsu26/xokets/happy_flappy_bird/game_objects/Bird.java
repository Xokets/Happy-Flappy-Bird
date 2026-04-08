package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.game_objects;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenGame.FIXED_TIME_STEP;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.utils.FrameCounter;
import sun.jvm.hotspot.debugger.dummy.DummyDebugger;

public class Bird {
    private static final int[] HEAD_PART_PIVOT_POINT = {85, 100};
    public static final float X_POS = 300;
    private static final float JUMP_FORCE = 6.1f;
    private static final String TEXTURE_PATH = "BirdTiles/default/";
    private float time;
    private FrameCounter frameCounter;
    private FrameCounter eyeFrameCounter;
    private final List<Texture> birdTiles = new ArrayList<>();
    private final List<Texture> birdEyeTiles = new ArrayList<>();
    private float x;
    private float y;
    private int width;
    private int height;
    private float velocityY;
    private Texture texture;
    private float accumulator;
    private static final Random rand = new Random();
    private final MyGdxGame myGdxGame;

    public Bird(int y, MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        this.y = y;
        this.x = X_POS;
        this.width = 200;
        this.height = 150;
        texture = new Texture(TEXTURE_PATH + "Bird0001.png");
        birdTiles.add(texture);
        birdTiles.add(new Texture(TEXTURE_PATH + "Bird0002.png"));
        birdTiles.add(new Texture(TEXTURE_PATH + "Bird0003.png"));
        birdTiles.add(new Texture(TEXTURE_PATH + "Bird0004.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "eye0001.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "eye0002.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "eye0003.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "eye0004.png"));
        frameCounter = new FrameCounter(birdTiles.size() - 1, 3);
        eyeFrameCounter = new FrameCounter(birdEyeTiles.size() - 1);
        accumulator = 0;
        time = 0;
    }

    public Bird(MyGdxGame myGdxGame) {
        this(500, myGdxGame);
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
            if (eyeFrameCounter.getFrame() > 0) {
                eyeFrameCounter.nextFrame();
            } else if (rand.nextInt(0, 100) < 1) {
                eyeFrameCounter.nextFrame();
            }
        }
    }

    public void onClick() {
        if (velocityY < 0) {
            time = 0;
            velocityY = JUMP_FORCE;
        }
    }

    public void draw(Batch batch) {
        if (velocityY < 0 && frameCounter.getFrame() != 0) frameCounter.previousFrame();
        texture = birdTiles.get(frameCounter.getFrame());
        batch.draw(texture, X_POS, y, width, height);
        batch.draw(birdEyeTiles.get(eyeFrameCounter.getFrame()), X_POS + width - 85, y + height - 40, (float) width * 0.14f, (float) height * 0.16f);
        if (myGdxGame.screenParts.selected != null) {
            batch.draw(myGdxGame.screenParts.selected, X_POS + HEAD_PART_PIVOT_POINT[0], y + HEAD_PART_PIVOT_POINT[1], 64, 64);
        }
    }

    public boolean isInField() {
        return !(y + height < 0 || y > SCR_HEIGHT);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
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

    public void placeOnStart() {
        time = 0;
        accumulator = 0;
        y = (float) SCR_HEIGHT / 2;
        velocityY = 0;
    }

    public void dispose() {
        texture.dispose();
        for (Texture tile : birdTiles) {
            tile.dispose();
        }
        for (Texture tile : birdEyeTiles) {
            tile.dispose();
        }
    }
}
