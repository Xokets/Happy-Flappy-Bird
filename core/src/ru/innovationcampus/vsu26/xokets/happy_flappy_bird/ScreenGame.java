package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class ScreenGame implements Screen {
    private boolean isGameOver;
    public static final float FIXED_TIME_STEP = 0.0167f;
    public static final int SPEED_X = 10;
    public static final int TUBES_COUNT = 3;
    private MyGdxGame myGdxGame;
    private Bird bird;
    private final List<Tube> tubeList = new ArrayList<>();
    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        bird = new Bird();
        for (int i = 0; i < TUBES_COUNT; i++) {
            tubeList.add(new Tube(TUBES_COUNT, i));
        }
    }

    @Override
    public void show() {
        isGameOver = false;
    }

    @Override
    public void render(float delta) {
        if (!bird.isInField()) {
            System.out.println("Game over!");
            isGameOver = true;
        }
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }
        bird.fly(delta);
        ScreenUtils.clear(0, 0.5f, 0.25f, 0);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubeList) {
            tube.move(delta);
            if (tube.isHit(bird)) {
                System.out.println("Game over!");
                isGameOver = true;
            }
            tube.draw(myGdxGame.batch);
        }
        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bird.dispose();
        for (Tube tube : tubeList) {
            tube.dispose();
        }
    }
}
