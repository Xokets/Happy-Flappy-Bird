package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenMenu;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenParts;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenRestart;


public class MyGdxGame extends Game {
	public Preferences data;
	public static final float G = 1.2f;
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public ScreenGame screenGame;
	public ScreenRestart screenRestart;
	public ScreenMenu screenMenu;
	public ScreenParts screenParts;
	public Boolean hasPilotHat = false;
	public Boolean hasSunglasses = false;
	public Boolean hasCap = false;

	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		screenMenu = new ScreenMenu(this);
		screenGame = new ScreenGame(this);
		screenRestart = new ScreenRestart(this);
		screenParts = new ScreenParts(this);
		setScreen(screenMenu);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screenGame.dispose();
		screenRestart.dispose();
		screenMenu.dispose();
		screenParts.dispose();
	}

	public void applyData() {
		if (data == null) {
			data = Gdx.app.getPreferences("HappyFlappyBirdData");
		}
		String pref = "has_pilot_hat";
		if (!data.contains(pref)) {
			data.putBoolean(pref, false);
		}
		hasPilotHat = data.getBoolean(pref);
		pref = "has_sunglasses";
		if (!data.contains(pref)) {
			data.putBoolean(pref, false);
		}
		hasSunglasses = data.getBoolean(pref);
		pref = "has_cap";
		if (!data.contains(pref)) {
			data.putBoolean(pref, false);
		}
		hasCap = data.getBoolean(pref);
		data.flush();
	}
}
