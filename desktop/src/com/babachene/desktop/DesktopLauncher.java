package com.babachene.desktop;

import com.babachene.test.TestGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.fullscreen=true; // Ça bug, j'ai dû passé en console pour tuer le processus.
		//  En revanche, passer en plein écran après, avec Gdx.graphics.setFullscreen, fonctionne.
		config.height = TestGame.H /2;
		config.width  = TestGame.W /2;
		config.title="Just a test game.";
		
		new LwjglApplication(new TestGame(), config);
	}
}
