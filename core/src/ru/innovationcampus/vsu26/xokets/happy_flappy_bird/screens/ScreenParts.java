package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.MovingBackGround;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.PartIcon;

public class ScreenParts implements Screen {
    private static final String TEXTURE_PATH = "Parts/Head/";
    private final MyGdxGame myGdxGame;

    private MovingBackGround backGround;
    private final List<PartIcon> parts = new ArrayList<>();
    private final PartIcon partHeadPilotHat = new PartIcon(TEXTURE_PATH + "Part_Head_PilotHat.png");
    private final PartIcon partHeadSunglasses = new PartIcon(TEXTURE_PATH + "Part_Head_Sunglasses.png");
    private final PartIcon partHeadCap = new PartIcon(TEXTURE_PATH + "Part_Head_Cap.png");

    public ScreenParts(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        backGround = new MovingBackGround("restart_bg.png");
        parts.add(partHeadPilotHat); parts.add(partHeadSunglasses); parts.add(partHeadCap);
        for (int i = 0; i < parts.size(); i++) {
            parts.get(i).setWidth(128);
            parts.get(i).setHeight(128);
            if (i > 0) {
                parts.get(i).setX(parts.get(i - 1).getX() + 138);
            } else {
                parts.get(i).setX(138);
            }
            parts.get(i).setY((SCR_HEIGHT - 138));
        }
    }

    @Override
    public void show() {
//        partHeadPilotHat.setX(0 + partHeadPilotHat.getWidth() + 10);
//        partHeadPilotHat.setY(SCR_HEIGHT - partHeadPilotHat.getHeight() - 10);
//        partHeadSunglasses.setX(partHeadPilotHat.getX() + partHeadPilotHat.getWidth() + partHeadPilotHat.getWidth() + 10);
//        partHeadSunglasses.setY(partHeadPilotHat.getY());
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touched = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (partHeadPilotHat.isTouched((int) touched.x, (int) touched.y)) {
                myGdxGame.screenGame.bird.setHeadPart(partHeadPilotHat.getTexture());
                myGdxGame.setScreen(myGdxGame.screenMenu);
            } else if (partHeadSunglasses.isTouched((int) touched.x, (int) touched.y)) {
                myGdxGame.screenGame.bird.setHeadPart(partHeadSunglasses.getTexture());
                myGdxGame.setScreen(myGdxGame.screenMenu);
            } else if (partHeadCap.isTouched((int) touched.x, (int) touched.y)) {
                myGdxGame.screenGame.bird.setHeadPart(partHeadCap.getTexture());
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
        }

        ScreenUtils.clear(Color.BLACK);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        backGround.draw(myGdxGame.batch);
        partHeadPilotHat.draw(myGdxGame.batch);
        partHeadSunglasses.draw(myGdxGame.batch);
        partHeadCap.draw(myGdxGame.batch);
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
        partHeadPilotHat.dispose();
        partHeadSunglasses.dispose();
        partHeadCap.dispose();
    }
}
