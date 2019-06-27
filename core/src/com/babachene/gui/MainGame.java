package com.babachene.gui;

import java.io.IOException;

import com.babachene.controller.MetaController;
import com.babachene.gui.menus.JoinMenu;
import com.babachene.gui.menus.Local2PlayerLevelSelection;
import com.babachene.gui.menus.Local2PlayerMenu;
import com.babachene.gui.menus.LocalArenaSelection;
import com.babachene.gui.menus.LocalMenu;
import com.babachene.gui.menus.MainMenu;
import com.babachene.gui.menus.OnlineArenaSelection;
import com.babachene.gui.menus.OnlineLevelSelection;
import com.babachene.gui.menus.OnlineMenu;
import com.babachene.gui.menus.P1Win;
import com.babachene.gui.menus.P2Win;
import com.babachene.gui.menus.PlayMenu;
import com.babachene.gui.menus.SettingsMenu;
import com.babachene.gui.menus.SingleLevelSelection;
import com.babachene.gui.menus.YouWin;
import com.babachene.gui.test.RenderingTest;
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
	private LocalMenu localMenu;
	private SingleLevelSelection singleLevelSelection;
	private Local2PlayerMenu local2PlayerMenu;
	private Local2PlayerLevelSelection local2PlayerLevelSelection;
	private LocalArenaSelection localArenaSelection;
	private JoinMenu joinMenu;
	private OnlineMenu onlineMenu;
	private OnlineLevelSelection onlineLevelSelection;
	private OnlineArenaSelection onlineArenaSelection;
	private YouWin youWin;
	private P1Win p1Win;
	private P2Win p2Win;
	public final static int MAINMENU = 0;
	public final static int PLAYMENU = 1;
	public final static int SETTINGSMENU = 2;
	public final static int LOCALMENU = 3;
	public final static int ONLINEMENU = 4;
	public final static int LOCAL2PLAYERMENU = 5;
	public final static int SINGLELEVELSELECTION = 6;
	public final static int LOCAL2PLAYERLEVELSELECTION = 7;
	public final static int LOCALARENASELECTION = 8;
	public final static int JOINMENU =9;
	public final static int ONLINELEVELSELECTION = 10;
	public final static int ONLINEARENASELECTION =11;
	public final static int YOUWIN =12;
	public final static int P1WIN =13;
	public final static int P2WIN =14;
	
	
	private final String[] singleLevelList = {"Read the rules","Who are you","Rocks rock","Words can hurt","Learn to swim","God saves the rock","basic level","the river","find the path","help your friend","empty is baba","keke is lost"} ;
	private final String[] multiLevelList = {"1v1","help your friend","sandbox2player"};
	private final String[] arenaList = {"1v1","arene1","lavarene","4 vs 4","Water is push"};
	
	
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
//		RenderingTest.main(null);
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
		
		
		this.push(new MainMenu(this,false));
		
		
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
				if(playMenu == null) {playMenu = new PlayMenu(this,true);};
				this.push(playMenu);
				break;
			case SETTINGSMENU:
				if(settingsMenu == null) {settingsMenu = new SettingsMenu(this,true);};
				this.push(settingsMenu);
				break;
			case LOCALMENU:
				if(localMenu == null) {localMenu = new LocalMenu(this,true);};
				this.push(localMenu);
				break;
			case ONLINEMENU:
				if(onlineMenu == null) {onlineMenu = new OnlineMenu(this,true);};
				this.push(onlineMenu);
				break;
			case LOCAL2PLAYERMENU:
				if(local2PlayerMenu == null) {local2PlayerMenu = new Local2PlayerMenu(this,true);};
				this.push(local2PlayerMenu);
				break;
			case JOINMENU:
				if(joinMenu == null) {joinMenu = new JoinMenu(this,true);};
				this.push(joinMenu);
				break;
			case SINGLELEVELSELECTION:
				if(singleLevelSelection == null) {singleLevelSelection = new SingleLevelSelection(this,true,singleLevelList);};
				this.push(singleLevelSelection);
				break;
				
			case LOCAL2PLAYERLEVELSELECTION:
				if(local2PlayerLevelSelection == null) {local2PlayerLevelSelection = new Local2PlayerLevelSelection(this,true,multiLevelList);};
				this.push(local2PlayerLevelSelection);
				break;
			
			case ONLINELEVELSELECTION:
				if(onlineLevelSelection == null) {onlineLevelSelection = new OnlineLevelSelection(this,true,multiLevelList);};
				this.push(onlineLevelSelection);
				break;
				
			case LOCALARENASELECTION:
				if(localArenaSelection == null) {localArenaSelection = new LocalArenaSelection(this,true,arenaList);};
				this.push(localArenaSelection);
				break;
			case ONLINEARENASELECTION:
				if(onlineArenaSelection == null) {onlineArenaSelection = new OnlineArenaSelection(this,true,arenaList);};
				this.push(onlineArenaSelection);
				break;
				
			case YOUWIN:
				if(youWin == null) {youWin = new YouWin(this,false);};
				this.push(youWin);
				break;
			case P1WIN:
				if(p1Win == null) {p1Win = new P1Win(this,false);};
				this.push(p1Win);
				break;
			case P2WIN:
				if(p2Win == null) {p2Win = new P2Win(this,false);};
				this.push(p2Win);
				break;
		}
	}

	public static final void back() {
		pop();
	}
	
	public void setInputProcessor(InputProcessor inputProcessor) {
		while (multiplexer.size() >= 2) {
			multiplexer.removeProcessor(multiplexer.size() - 1);
		}
		multiplexer.addProcessor(inputProcessor);
	}
	
}
