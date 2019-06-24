package com.babachene.gui;

import java.io.IOException;
import com.babachene.controller.MetaController;
import com.babachene.gui.menus.LevelSelection;
import com.babachene.gui.menus.MainMenu;
import com.babachene.gui.menus.MultiplayerMenu;
import com.babachene.gui.menus.PlayMenu;
import com.babachene.gui.menus.SettingsMenu;
import com.babachene.logger.GlobalLogger;
import com.babachene.userinput.AppInputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends StateBasedGame {
	
	@SuppressWarnings("unused")
	private MainMenu mainMenu;
	private PlayMenu playMenu;
	private SettingsMenu settingsMenu;
	private MultiplayerMenu multiplayerMenu;
	private LevelSelection levelSelection;
	public final static int MAINMENU = 0;
	public final static int PLAYMENU = 1;
	public final static int SETTINGSMENU = 2;
	public final static int MULTIPLAYERMENU = 3;
	public final static int LEVELSELECTION = 4;
	
	private final AppInputProcessor inputProcessor;
	private final InputMultiplexer multiplexer;
	
//	private GameController gameController;
//	private Controller controller;
	private MetaController metaController;
	
	public MainGame() {
		//BabaIsUs.skin.getFont("default-font").getData().setScale(3f,3f);
		inputProcessor = new AppInputProcessor();
		multiplexer = new InputMultiplexer(inputProcessor);
		// assert
		if (multiplexer.size() != 1) {
			throw new Error ("Shit happened.");
		}
	}
	
	//////////////////////////////////////
	
	@Override
	public void create() {
		
		Gdx.input.setInputProcessor(multiplexer);
		
		BabaIsUs.assetManager = new AssetManager();

		try {
			GlobalLogger.setup(true);
		} catch(IOException ioe) {
			
		}
		
		/*
		 * Load the textures
		 */
		Rsrc.loadEverything();
		
		// Colin's test
		/*
		levelMap = new LevelMap(20, 20);
		GameLogic logic = new GameLogic(levelMap);
		gameController = new GameController(this, logic);
		*/
		
		/*
		 * TEST ZONE
		 */
		
		BabaIsUs.assetManager.load(BabaIsUs.textures.PEPE, Texture.class);
		BabaIsUs.assetManager.load(BabaIsUs.textures.KERMIT, Texture.class);
		BabaIsUs.assetManager.load(BabaIsUs.textures.THEME_DEFAULT, Texture.class);
		BabaIsUs.assetManager.finishLoading();
		
//		RenderingTest t = new RenderingTest();
//		this.push(new LevelState(t.level, null));
//		t.startTestOnLevelState();
		
		// Testing the controller handler
		metaController = new MetaController(this);
		
		/*
		 * END OF TEST ZONE
		 */
		
//		this.controller = new SoloController(this);
//		gameController = new GameController(this);
		
		
		this.push(new MainMenu(this));
		
		
	}
	
	//////////////////////
	
	public MetaController getMetaController() {
		return metaController;
	}
	
	@Override
	public void render() {
//		gameController.update();
//		controller.update();
		metaController.update();
		
		
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
				if(multiplayerMenu == null) {multiplayerMenu = new MultiplayerMenu(this);};
				this.push(multiplayerMenu);
				break;
			case LEVELSELECTION:
				if(levelSelection == null) {levelSelection = new LevelSelection(this);};
				this.push(levelSelection);
				break;
			
		}
	}

	public final void back() {
		pop();
	}
	
	public void setInputProcessor(InputProcessor inputProcessor) {
		while (multiplexer.size() >= 2) {
			multiplexer.removeProcessor(multiplexer.size() - 1);
		}
		multiplexer.addProcessor(inputProcessor);
	}
	
}
