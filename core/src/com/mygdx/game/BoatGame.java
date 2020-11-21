package com.mygdx.game;

import com.badlogic.gdx.Game;

public class BoatGame extends Game {

	public GameScreen gameScreen;
	public MenuScreen menuScreen;
	public EndScreen endScreen;
	
	@Override
	public void create() {
		Assets.load();
		endScreen = new EndScreen(this);
		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen(endScreen);
	}

}
