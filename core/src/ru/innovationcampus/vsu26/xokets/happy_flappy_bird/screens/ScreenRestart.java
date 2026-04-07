package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.MovingBackGround;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.PointCounter;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.TextButton;

public class ScreenRestart implements Screen {

    private final MyGdxGame myGdxGame;
    private MovingBackGround backGround;
    private TextButton restartButton;
    private TextButton menuButton;

    private int point;

    private PointCounter pointCounter;


    public ScreenRestart(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        restartButton = new TextButton(100, 150, "RESTART");
        menuButton = new TextButton(700, 150, "MENU");
        backGround = new MovingBackGround("restart_bg.png");
        point = 0;
        pointCounter = new PointCounter(750, 530);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touch = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (restartButton.isTouch((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
            } else if (menuButton.isTouch((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
        }
        ScreenUtils.clear(Color.BLACK);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        backGround.draw(myGdxGame.batch);
        restartButton.draw(myGdxGame.batch);
        menuButton.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, point);
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
        restartButton.dispose();
        menuButton.dispose();
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
