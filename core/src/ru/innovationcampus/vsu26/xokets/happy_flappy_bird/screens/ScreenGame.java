package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;
import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.game_objects.Bird;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.MovingBackGround;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.game_objects.Tube;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.PointCounter;

public class ScreenGame implements Screen {

    private final MyGdxGame myGdxGame;


    public int point;
    public boolean isGameOver;
    public static final float FIXED_TIME_STEP = 0.0167f;
    public static final int SPEED_X = 10;
    public static final int TUBES_COUNT = 3;
    private static final int POINT_COUNTER_MARGIN_TOP = 60;
    private static final int POINT_COUNTER_MARGIN_RIGHT = 400;
    private final List<MovingBackGround> gameBG = new ArrayList<>();
//    private MovingBackGround backGround;
    public Bird bird;
    public Texture sun;
    public PointCounter pointCounter;
    public final List<Tube> tubeList = new ArrayList<>();
    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
//        backGround = new MovingBackGround("game_bg.png");
        gameBG.add(new MovingBackGround("GameBG/Game_BG_SKY.png"));
        gameBG.add(new MovingBackGround("GameBG/Game_BG_Clouds.png"));
        gameBG.add(new MovingBackGround("GameBG/Game_BG_Back_mount.png"));
        gameBG.add(new MovingBackGround("GameBG/Game_BG_Mount.png"));
        for (int i = 0; i < gameBG.size(); i++) {
            gameBG.get(i).setSpeed(i);
        }
        bird = new Bird(SCR_HEIGHT / 2, myGdxGame);
        sun = new Texture("BackGround/GameBG/Game_BG_Sun1.png");
    }

    @Override
    public void show() {
        initTubes();
        point = 0;
        pointCounter = new PointCounter(SCR_WIDTH - POINT_COUNTER_MARGIN_RIGHT, SCR_HEIGHT - POINT_COUNTER_MARGIN_TOP);
        bird.placeOnStart();
        isGameOver = false;
    }

    @Override
    public void render(float delta) {
        if (isGameOver) {
            myGdxGame.screenRestart.setPoint(point);
            myGdxGame.setScreen(myGdxGame.screenRestart);
        }
        if (!bird.isInField()) {
            isGameOver = true;
        }
        if (Gdx.input.justTouched()) {
            bird.onClick();
        }
//        backGround.move(delta);
        for (MovingBackGround element : gameBG) {
            element.move(delta);
        }
        bird.fly(delta);
        ScreenUtils.clear(Color.BLACK);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        for (int i = 0; i < gameBG.size(); i++) {
            gameBG.get(i).draw(myGdxGame.batch);
            if (i == 0) {
                myGdxGame.batch.draw(sun, 10, SCR_HEIGHT - sun.getHeight() - 10);
            }
        }
//        backGround.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubeList) {
            tube.move(delta);
            if (tube.isHit(bird)) {
                isGameOver = true;
            } else if (tube.needAddPoint(bird)) {
                point++;
            }
            tube.draw(myGdxGame.batch);
            pointCounter.draw(myGdxGame.batch, point);
        }
        myGdxGame.batch.end();
        switch (point) {
            case 25: myGdxGame.data.putBoolean("has_cap", true); break;
            case 50: myGdxGame.data.putBoolean("has_pilot_hat", true); break;
            case 100: myGdxGame.data.putBoolean("has_sunglasses", true); break;
        }
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

    private void initTubes() {
        if (isGameOver) {
            for (Tube tube : tubeList) {
                tube.dispose();
            }
            tubeList.clear();
        }
        for (int i = 0; i < TUBES_COUNT; i++) {
            tubeList.add(new Tube(TUBES_COUNT, i));
        }
    }

    @Override
    public void dispose() {
//        backGround.dispose();
        sun.dispose();
        for (MovingBackGround element : gameBG) {
            element.dispose();
        }
        bird.dispose();
        for (Tube tube : tubeList) {
            tube.dispose();
        }
        pointCounter.dispose();
    }
}
