package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenGame;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenMenu;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.screens.ScreenRestart;


public class MyGdxGame extends Game {
	public static final float G = 1.2f;
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public ScreenGame screenGame;
	public ScreenRestart screenRestart;
	public ScreenMenu screenMenu;

	@Override
	public void create() {
		Preferences prefs = Gdx.app.getPreferences("HappyFlappyBirdData");
		prefs.flush();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		screenMenu = new ScreenMenu(this);
		screenGame = new ScreenGame(this);
		screenRestart = new ScreenRestart(this);
		setScreen(screenMenu);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		screenGame.dispose();
		screenRestart.dispose();
		screenMenu.dispose();
	}
}
