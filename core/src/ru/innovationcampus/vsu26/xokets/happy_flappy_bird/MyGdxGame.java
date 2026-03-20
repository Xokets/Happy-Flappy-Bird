package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame extends Game {
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;
	protected SpriteBatch batch;
	protected OrthographicCamera camera;
	protected ScreenGame screenGame;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);


		screenGame = new ScreenGame(this);
		setScreen(screenGame);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
