package com.mygdx.game;

import com.badlogic.gdx.Game;

public class BoatGame extends Game {

	public GameScreen gameScreen;
	
	@Override
	public void create () {
		Assets.load();
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

}
