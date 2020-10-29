package com.mygdx.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BoatGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new BoatGame(), config);

		config.title = "Boat Racing";
		config.width = 1920;
		config.height = 1080;
		//config.addIcon("", Files.FileType.Internal);

		new LwjglApplication(new BoatGame(), config);
	}
}
