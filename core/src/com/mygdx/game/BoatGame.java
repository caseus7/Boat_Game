package com.mygdx.game;

import com.badlogic.gdx.Game;

public class BoatGame extends Game {

	//IMPORTANT NOTE
	//

	//Three screens for different parts of the game
	public GameScreen gameScreen;
	public MenuScreen menuScreen;
	public EndScreen endScreen;

	//Score and raceNo variables
	public static int score = 0;
	public static int raceNo = 1;
	
	@Override
	public void create() {
		//Load in the assets
		Assets.load();

		//Initialise the screens
		endScreen = new EndScreen(this);
		gameScreen = new GameScreen(this);
		menuScreen = new MenuScreen(this);

		//Set the current screen (the starting screen)
		setScreen(menuScreen);
	}

}
