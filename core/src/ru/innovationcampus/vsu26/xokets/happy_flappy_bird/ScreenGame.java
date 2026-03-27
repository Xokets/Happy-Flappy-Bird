package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class ScreenGame implements Screen {
    public static final int SPEED_X = 10;
    public static final int TUBES_COUNT = 3;
    private MyGdxGame myGdxGame;
    private Bird bird;
    private final List<Tube> tubeList = new ArrayList<>();
    @Override
    public void show() {
    }

    long lastTime;
    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        bird = new Bird();
        for (int i = 0; i < TUBES_COUNT; i++) {
            tubeList.add(new Tube(3, i));
        }
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }
        bird.fly();

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubeList) {
            tube.move();
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
    }
}
