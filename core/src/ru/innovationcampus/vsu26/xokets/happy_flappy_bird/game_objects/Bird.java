package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.game_objects;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.utils.FrameCounter;

public class Bird implements Disposable {
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
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "Eye0001.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "Eye0002.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "Eye0003.png"));
        birdEyeTiles.add(new Texture(TEXTURE_PATH + "Eye0004.png"));

        //sorting dye-tile files
        FileHandle[] files = Gdx.files.internal(TEXTURE_PATH + "dye_animation").list();
        Arrays.sort(files, (file, file2) -> file.file().getName().compareTo(file2.file().getName()));
        for (FileHandle file : files) {
            birdDyeTiles.add(new Texture(file));
        }
        //initialization of framecounters
        frameCounter = new FrameCounter(birdTiles.size() - 1, 3);
        eyeFrameCounter = new FrameCounter(birdEyeTiles.size() - 1);
        dyeFrameCounter = new FrameCounter(birdDyeTiles.size() - 1);
        placeOnStart(y, width, height);
    }

    public void fly() {
        if (velocityY > 0) {
            frameCounter.nextFrame();
        }
        velocityY -= MyGdxGame.G * time;
        y += velocityY;
        if (eyeFrameCounter.getFrame() > 0) {
            eyeFrameCounter.nextFrame();
        } else if (rand.nextInt(100) < 1) {
            eyeFrameCounter.nextFrame();
        }
    }

    public void onClick() {
        //player cannot jump when bird doesn't reach peak of jump
        if (velocityY < 0) {
            time = 0;
            velocityY = JUMP_FORCE;
        }
    }

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


        this.y = y;
        velocityY = 0;


        frameCounter.setToStart();
        dyeFrameCounter.setToStart();
        eyeFrameCounter.setToStart();
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
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
