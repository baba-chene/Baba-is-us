package com.babachene.desktop;

import com.babachene.gui.MainGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.fullscreen=true; // Ça bug, j'ai dû passé en console pour tuer le processus.
		//  En revanche, passer en plein écran après, avec Gdx.graphics.setFullscreen, fonctionne.
		config.height = 800;//TestGame.H;
		config.width  = 800;//TestGame.W;
		/*
		 * TODO Manage the background FPS, as this is a multiplayer game.
		 * And the game updates are made through the render thread. (but not the server thread...)
		 */
		//config.backgroundFPS = -1;
		config.title="Baba is Everyone";
		
//		TexturePacker.process("/home/jeremy/Bureau/PAF/assets-building/ready-to-use",
//				"/home/jeremy/Bureau/PAF/desktop/assets", "packedTextures");;
		new LwjglApplication(new MainGame(), config);
	}
}
