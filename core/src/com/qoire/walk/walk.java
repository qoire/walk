package com.qoire.walk;

import com.badlogic.gdx.Game;
import com.qoire.walk.screen.GameScreen;
import com.qoire.walk.utils.TextureSetup;

public class walk extends Game {
	
	@Override
	public void create () {
        //setup textures!
        //TextureSetup.setup();
        setScreen(new GameScreen());
	}
}
