package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.MovingBackGround;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.PartIcon;

public class ScreenParts implements Screen {
    private static final String TEXTURE_PATH = "Parts/Head/";
    private final MyGdxGame myGdxGame;

    private MovingBackGround backGround;
    private final PartIcon headPartPilotHat = new PartIcon(TEXTURE_PATH + "Head_Part_PilotHat.png");
    private final PartIcon headPartSunglasses = new PartIcon(TEXTURE_PATH + "Head_Part_Sunglasses.png");

    public ScreenParts(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        backGround = new MovingBackGround("restart_bg.png");
    }

    @Override
    public void show() {
        headPartPilotHat.setHeight(64); headPartPilotHat.setWidth(64);
        headPartSunglasses.setHeight(32); headPartSunglasses.setWidth(64);
        headPartPilotHat.setX(0 + headPartPilotHat.getWidth() + 10);
        headPartPilotHat.setY(SCR_HEIGHT - headPartPilotHat.getHeight() - 10);
        headPartSunglasses.setX(headPartPilotHat.getX() + headPartPilotHat.getWidth() + headPartPilotHat.getWidth() + 10);
        headPartSunglasses.setY(headPartPilotHat.getY());
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touched = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (headPartPilotHat.isTouched((int) touched.x, (int) touched.y)) {
                System.out.println("Selected");
            }
        }

        ScreenUtils.clear(Color.BLACK);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        backGround.draw(myGdxGame.batch);
        headPartPilotHat.draw(myGdxGame.batch);
        headPartSunglasses.draw(myGdxGame.batch);
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
        headPartPilotHat.dispose();
        headPartSunglasses.dispose();
    }
}
