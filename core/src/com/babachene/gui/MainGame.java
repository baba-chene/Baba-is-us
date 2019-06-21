package com.babachene.gui;

import com.babachene.game.GameController;
import com.babachene.gui.menus.MainMenu;
import com.babachene.gui.menus.MultiplayerMenu;
import com.babachene.gui.menus.PlayMenu;
import com.babachene.gui.menus.SettingsMenu;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends StateBasedGame {
	
	private MainMenu mainMenu;
	private PlayMenu playMenu;
	private SettingsMenu settingsMenu;
	private MultiplayerMenu multiplayerMenu;
	public final static int MAINMENU = 0;
	public final static int PLAYMENU = 1;
	public final static int SETTINGSMENU = 2;
	public final static int MULTIPLAYERMENU = 3;
	
	private GameController gameController;
	
	public MainGame() {
		//BabaIsUs.skin.getFont("default-font").getData().setScale(3f,3f);
	}
	
	//////////////////////////////////////
	
	@Override
	public void create() {
		
		BabaIsUs.assetManager = new AssetManager();
		
		gameController = new GameController(this);
		/*
		 * TEST ZONE
		 */
		
		
		BabaIsUs.assetManager.load(BabaIsUs.textures.PEPE, Texture.class);
		BabaIsUs.assetManager.load(BabaIsUs.textures.KERMIT, Texture.class);
		BabaIsUs.assetManager.load(BabaIsUs.textures.THEME_DEFAULT, Texture.class);
		BabaIsUs.assetManager.finishLoading();
		/*
		RenderingTest t = new RenderingTest();
		this.push(new LevelState(t.level));
		t.startTestOnLevelState();
		*/
		/*
		 * END OF TEST ZONE
		 */
		
		this.push(new MainMenu(this));
	}
	

	@Override
	public void render() {
		gameController.update();
		super.render();
	}

	public void changeScreen(int screen){
		// Si returnTo=True alors on détruit la fenètre actuelle, sinon on créé une nouvelle fenètre
		switch(screen){
			case MAINMENU:
				pop();
				break;
			case PLAYMENU:
				if(playMenu == null) {playMenu = new PlayMenu(this);};
				this.push(playMenu);
				break;
			case SETTINGSMENU:
				if(settingsMenu == null) {settingsMenu = new SettingsMenu(this);};
				this.push(settingsMenu);
				break;
			case MULTIPLAYERMENU:
				if(multiplayerMenu == null) {multiplayerMenu = new MultiplayerMenu(this, gameController);};
				this.push(multiplayerMenu);
				break;
			
		}
	}

	public void back() {
		pop();
	}
	
}
