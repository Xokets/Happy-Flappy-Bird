package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String... args) {
		final MyGdxGame myGdxGame = new MyGdxGame();
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Happy Flappy bird");
		config.setWindowedMode(MyGdxGame.SCR_WIDTH, MyGdxGame.SCR_HEIGHT);
//		config.useVsync(true);
		config.setForegroundFPS(0);
		new Lwjgl3Application(myGdxGame, config);
	}
}
