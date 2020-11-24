package com.mygdx.game;

import com.badlogic.gdx.Game;

public class BoatGame extends Game {

	public GameScreen gameScreen;
	public MenuScreen menuScreen;
	public EndScreen endScreen;

	public static int score = 0;
	public static int raceNo = 1;
	
	@Override
	public void create() {
		Assets.load();
		endScreen = new EndScreen(this);
		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

}
