package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.utils.PointCounter;

public class ScreenGame implements Screen {
    private int point;
    private boolean isGameOver;
    public static final float FIXED_TIME_STEP = 0.0167f;
    public static final int SPEED_X = 10;
    public static final int TUBES_COUNT = 3;
    private static final int POINT_COUNTER_MARGIN_TOP = 60;
    private static final int POINT_COUNTER_MARGIN_RIGHT = 400;
    private MyGdxGame myGdxGame;
    private MovingBackGround backGround;
    private Bird bird;
    private PointCounter pointCounter;
    private final List<Tube> tubeList = new ArrayList<>();
    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        backGround = new MovingBackGround();
        bird = new Bird();
        for (int i = 0; i < TUBES_COUNT; i++) {
            tubeList.add(new Tube(TUBES_COUNT, i));
        }
    }

    @Override
    public void show() {
        isGameOver = false;
        point = 0;
        pointCounter = new PointCounter(SCR_WIDTH - POINT_COUNTER_MARGIN_RIGHT, SCR_HEIGHT - POINT_COUNTER_MARGIN_TOP);
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
        backGround.move(delta);
        bird.fly(delta);
        ScreenUtils.clear(0, 0.5f, 0.25f, 0);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        backGround.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubeList) {
            tube.move(delta);
            if (tube.isHit(bird)) {
                System.out.println("Game over!");
                isGameOver = true;
            } else if (tube.needAddPoint(bird)) {
                point++;
            }
            tube.draw(myGdxGame.batch);
            pointCounter.draw(myGdxGame.batch, point);
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
        backGround.dispose();
        bird.dispose();
        for (Tube tube : tubeList) {
            tube.dispose();
        }
        pointCounter.dispose();
    }
}
