package com.qoire.walk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.qoire.walk.screen.GameScreen;

public class walk extends Game {
	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}
}
