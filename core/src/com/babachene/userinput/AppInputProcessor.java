package com.babachene.userinput;

import com.babachene.gui.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

/**
 * An input processor that is always active throught the game.
 * <br>Servesthe use to go fullscreen.
 * @author jeremy
 *
 */
public class AppInputProcessor extends InputAdapter {
	
	public AppInputProcessor() {
		
	}
	
	@Override
	public boolean keyDown(int code) {
		if (code == Keys.F) {
			if (Gdx.graphics.isFullscreen()) {
				Gdx.graphics.setWindowedMode(640, 480);
			} else {
				Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
			}
			return true;
		}
		
		
		if (code == Keys.ESCAPE)
			MainGame.back();
			//Gdx.app.exit();
		
		return false;
	}
	
}
