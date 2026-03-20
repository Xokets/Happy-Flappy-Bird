package ru.innovationcampus.vsu26.xokets.happy_flappy_bird;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.innovationcampus.vsu26.xokets.happy_flappy_bird.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Happy Flappy bird");
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}
