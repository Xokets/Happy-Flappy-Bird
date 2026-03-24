package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import static ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame.fps;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String... args) {
		MyGdxGame.applyConfig(MyGdxGame.getConfigFile());
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(fps);
		config.setTitle("Happy Flappy bird");
		config.setWindowedMode(MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
