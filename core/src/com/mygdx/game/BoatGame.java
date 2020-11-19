package com.mygdx.game;

import com.badlogic.gdx.Game;

public class BoatGame extends Game {

	public GameScreen gameScreen;
	public MenuScreen menuScreen;
	
	@Override
	public void create() {
		Assets.load();
		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	public void setMenu(){
		setScreen(menuScreen);
	}

	public void setGame(){
		setScreen(gameScreen);
	}

}
