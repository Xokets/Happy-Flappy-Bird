package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class MyGdxGame extends Game {
	public static final float G = 9.8f;
	public static final int DEFAULT_FPS = 60;
	public static int fps;
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

	public static float convertTime(int t) {
		return (float) t / fps;
	}

	public static File getConfigFile() {
		File gameConfig = new File("options.txt");
		if (!gameConfig.exists()) {
			try {
				gameConfig.createNewFile();
			} catch (IOException e) {
				System.out.println("Не удалось создать файл настроек");
				e.printStackTrace();
			}
		}
		return gameConfig;
	}

	public static void applyConfig(File gameConfig) {
		try (InputStream inputStream = new FileInputStream(gameConfig)) {
			byte[] array = new byte[1024];
			int count = inputStream.read(array);
			StringBuilder sb = new StringBuilder();
			while (count > 0) {
				sb.append(new String(array, 0, count));
				count = inputStream.read(array);
			}
			String[] lines = sb.toString().split("\n");
			if (lines[0].startsWith("max_fps=")) {
				String[] raw = lines[0].split("=");
				try {
					fps = Integer.parseInt(raw[1]);
				} catch (NumberFormatException e) {
					fps = DEFAULT_FPS;
				}
			} else {
				System.out.println("Не удалось прочитать поле max_fps");
				fps = DEFAULT_FPS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
