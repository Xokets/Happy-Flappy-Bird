package ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.Button;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.IconButton;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.MovingBackGround;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.components.TextButton;

public class ScreenParts implements Screen {
    private static final String TEXTURE_PATH = "Parts/Head/";
    private final MyGdxGame myGdxGame;

    public Texture selected;

    private MovingBackGround backGround;
    private Button menuButton;
    private final List<IconButton> parts = new ArrayList<>();
    private final IconButton partHeadPilotHat = new IconButton(0, 0, "Button_PartIcon_StateNotSelected.png", "Button_PartIcon_StateSelected.png", TEXTURE_PATH + "Part_Head_PilotHat.png");
    private final IconButton partHeadSunglasses = new IconButton(0, 0, "Button_PartIcon_StateNotSelected.png", "Button_PartIcon_StateSelected.png", TEXTURE_PATH + "Part_Head_Sunglasses.png");
    private final IconButton partHeadCap = new IconButton(0, 0, "Button_PartIcon_StateNotSelected.png", "Button_PartIcon_StateSelected.png", TEXTURE_PATH + "Part_Head_Cap.png");

    public ScreenParts(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        backGround = new MovingBackGround("Menu_BG.png");
        parts.add(partHeadPilotHat); parts.add(partHeadSunglasses); parts.add(partHeadCap);
        for (int i = 0; i < parts.size(); i++) {
            if (i > 0) {
                parts.get(i).setX(parts.get(i - 1).getX() + parts.get(i - 1).getWidth() + 10);
            } else {
                parts.get(i).setX(parts.get(i).getWidth());
            }
            parts.get(i).setY((SCR_HEIGHT - parts.get(i).getHeight() - 10));
        }
        menuButton = new TextButton("CLOSE");
    }

    @Override
    public void show() {
        myGdxGame.applyData();
        partHeadPilotHat.setOpen(myGdxGame.hasPilotHat);
        partHeadSunglasses.setOpen(myGdxGame.hasSunglasses);
        partHeadCap.setOpen(myGdxGame.hasCap);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Vector3 touched = myGdxGame.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (menuButton.isTouch((int) touched.x,(int) touched.y)) myGdxGame.setScreen(myGdxGame.screenMenu);
            for (IconButton iconButton : parts) {
                if (!(iconButton.isTouch((int) touched.x, (int) touched.y))) continue;
                if (!iconButton.isOpen()) continue;
                if (iconButton.getState()) {
                    iconButton.switchState();
                    selected = null;
                } else if (selected == null){
                    iconButton.switchState();
                    selected = iconButton.getIcon();
                }
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
        menuButton.draw(myGdxGame.batch);
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
        if (selected != null) {
            selected.dispose();
        }
    }
}
