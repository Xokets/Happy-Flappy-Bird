package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.game_objects;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenGame.FIXED_TIME_STEP;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.GameElement;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.utils.FrameCounter;

public class Bird implements GameElement {
    private static final int[] HEAD_PART_PIVOT_POINT = {85, 100};
    public static final float X_POS = 300;
    private static final float JUMP_FORCE = 6.1f;
    private static final String TEXTURE_PATH = "BirdTiles/";


    private final List<Texture> birdTiles = new ArrayList<>();
    private final List<Texture> birdEyeTiles = new ArrayList<>();
    private final List<Texture> birdDyeTiles = new ArrayList<>();


    private float time;
    private FrameCounter frameCounter;
    private FrameCounter eyeFrameCounter;
    private FrameCounter dyeFrameCounter;
    private float x;
    private float y;
    private int width;
    private int height;
    private float velocityY;
    private Texture texture;
    private float accumulator;
    private final MyGdxGame myGdxGame;
    private boolean isDying;
    private boolean isDye;

    private static final Random rand = new Random();

    public Bird(float y, int width, int height, MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        this.x = X_POS;
        texture = new Texture(TEXTURE_PATH + "Bird0001.png");

        //adding frames into tiles-collections
        birdTiles.add(texture);
        birdTiles.add(new Texture(TEXTURE_PATH + "Bird0002.png"));
        birdTiles.add(new Texture(TEXTURE_PATH + "Bird0003.png"));
        birdTiles.add(new Texture(TEXTURE_PATH + "Bird0004.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "eye0001.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "eye0002.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "eye0003.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "eye0004.png"));

        //sorting dye-tile files
        Set<File> sorted = new TreeSet<>();
        sorted.addAll(Arrays.asList(Gdx.files.internal("assets/" + TEXTURE_PATH + "dye_animation").file().listFiles()));
        for (File file : sorted) {
            birdDyeTiles.add(new Texture(TEXTURE_PATH + "dye_animation/" + file.getName()));
        }

        //initialization of framecounters
        frameCounter = new FrameCounter(birdTiles.size() - 1, 3);
        eyeFrameCounter = new FrameCounter(birdEyeTiles.size() - 1);
        dyeFrameCounter = new FrameCounter(birdDyeTiles.size() - 1);
        placeOnStart(y, width, height);
    }

    public void fly(float delta) {
        accumulator += delta;
        time += delta;
        //If delta is too long physics must be reproduced repeatedly. If delta is too small physics cannot be reproduced while delta is not long
        while (accumulator >= FIXED_TIME_STEP) {
            //fly animation must starts when bird is jumping
            if (velocityY > 0) {
                frameCounter.nextFrame();
            }
            velocityY -= MyGdxGame.G * time;
            y += velocityY;
            accumulator -= FIXED_TIME_STEP;
            if (eyeFrameCounter.getFrame() > 0) {
                eyeFrameCounter.nextFrame();
            } else if (rand.nextInt(100) < 1) {
                eyeFrameCounter.nextFrame();
            }
        }
    }

    public void onClick() {
        //player cannot jump when bird doesn't reach peak of jump
        if (velocityY < 0) {
            time = 0;
            velocityY = JUMP_FORCE;
        }
    }
    @Override
    public void draw(Batch batch) {
        if (velocityY < 0 && frameCounter.getFrame() != 0) frameCounter.previousFrame();
        texture = birdTiles.get(frameCounter.getFrame());
        //reproduce dye animation
        if (isDying) {
            batch.draw(birdDyeTiles.get(dyeFrameCounter.getFrame()), x - (float) width / 2, y - (float) height / 2, width, height);
            dyeFrameCounter.nextFrame();
            //resizing bird while it doesn't reach player's screen (visually)
            if (dyeFrameCounter.getFrame() <= 30) {
                width += 20;
                height += 20;
            }
            if (dyeFrameCounter.getFrame() == dyeFrameCounter.getMaxFrame()) {
                isDye = true;
            }
            return;
        }
        batch.draw(texture, x, y, width, height);
        batch.draw(birdEyeTiles.get(eyeFrameCounter.getFrame()), X_POS + width - 85, y + height - 40, (float) width * 0.14f, (float) height * 0.16f);
        if (myGdxGame.screenParts.selected != null) {
            batch.draw(myGdxGame.screenParts.selected, X_POS + HEAD_PART_PIVOT_POINT[0], y + HEAD_PART_PIVOT_POINT[1], 64, 64);
        }
    }

    public boolean isInField() {
        return !(y + height < 0 || y > SCR_HEIGHT);
    }

    public void kill() {
        isDying = true;
    }

    public boolean isDye() {
        return isDye;
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

    public void placeOnStart(float y, int width, int height) {
        this.width = width;
        this.height = height;


        //starts dying animation
        isDying = false;
        //sets to true when dying animation has ended
        isDye = false;


        //initialization of "fixed time step" variables
        time = 0;
        accumulator = 0;


        this.y = y;
        velocityY = 0;


        frameCounter.setToStart();
        dyeFrameCounter.setToStart();
        eyeFrameCounter.setToStart();
    }
    @Override
    public void dispose() {
        texture.dispose();
        for (Texture tile : birdTiles) {
            tile.dispose();
        }
        for (Texture tile : birdEyeTiles) {
            tile.dispose();
        }
        for (Texture tile : birdDyeTiles) {
            tile.dispose();
        }
    }
}
