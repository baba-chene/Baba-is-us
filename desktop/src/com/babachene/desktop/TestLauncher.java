package com.babachene.desktop;

import com.babachene.gui.MainGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * To launch test application.
 * @author jeremy
 *
 */
public class TestLauncher {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.fullscreen=true; // Ça bug, j'ai dû passé en console pour tuer le processus.
		//  En revanche, passer en plein écran après, avec Gdx.graphics.setFullscreen, fonctionne.
		config.height = 800;//TestGame.H;
		config.width  = 800;//TestGame.W;
		config.title="TEST";
		
		new LwjglApplication(new MainGame(), config);
	}

}
