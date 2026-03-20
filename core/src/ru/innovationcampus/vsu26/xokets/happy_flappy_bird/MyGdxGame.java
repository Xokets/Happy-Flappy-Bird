package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;
	private SpriteBatch batch;
	private Bird bird;
	private OrthographicCamera camera;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bird = new Bird();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
	}

	@Override
	public void render () {
		bird.moveX(1);
		bird.moveY(1);
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(bird.getTexture(), bird.getX(), bird.getY());
		batch.end();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		bird.dispose();
	}
}
