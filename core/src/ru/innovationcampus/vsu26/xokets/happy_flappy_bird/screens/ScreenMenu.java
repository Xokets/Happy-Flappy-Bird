package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.MovingBackGround;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.TextButton;

public class ScreenMenu implements Screen {
    public static final String BUTTON_START_TEXT = "START";
    public static final String BUTTON_EXIT_TEXT = "EXIT";
    public static final String BUTTON_PARTS_TEXT = "PARTS";
    private final MyGdxGame myGdxGame;
    private MovingBackGround backGround;
    private TextButton buttonStart;
    private TextButton buttonExit;
    private TextButton buttonParts;

    public ScreenMenu(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        backGround = new MovingBackGround("Menu_BG.png");
        buttonStart = new TextButton(100, 400, BUTTON_START_TEXT);
        buttonExit = new TextButton(700, 400, BUTTON_EXIT_TEXT);
        buttonParts = new TextButton(400, 50, BUTTON_PARTS_TEXT);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touched = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (buttonStart.isTouch((int) touched.x, (int) touched.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
            } else if (buttonExit.isTouch((int) touched.x, (int) touched.y)) {
                Gdx.app.exit();
            } else if (buttonParts.isTouch((int) touched.x, (int) touched.y)) {
                myGdxGame.setScreen(myGdxGame.screenParts);
            }
        }

        ScreenUtils.clear(Color.BLACK);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        backGround.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);
        buttonStart.draw(myGdxGame.batch);
        buttonParts.draw(myGdxGame.batch);
        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        backGround.dispose();
        buttonStart.dispose();
        buttonExit.dispose();
        buttonParts.dispose();
    }
}
