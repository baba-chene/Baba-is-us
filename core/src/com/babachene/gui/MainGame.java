package com.babachene.gui;

import com.babachene.gui.menus.MainMenu;
import com.babachene.gui.menus.MultiplayerMenu;
import com.babachene.gui.menus.PlayMenu;
import com.babachene.gui.menus.SettingsMenu;

public class MainGame extends StateBasedGame {
	
	private MainMenu mainMenu;
	private PlayMenu playMenu;
	private SettingsMenu settingsMenu;
	private MultiplayerMenu multiplayerMenu;
	public final static int MAINMENU = 0;
	public final static int PLAYMENU = 1;
	public final static int SETTINGSMENU = 2;
	public final static int MULTIPLAYERMENU = 3;
	
	
	public MainGame() {
		//BabaIsUs.skin.getFont("default-font").getData().setScale(3f,3f);
	}
	
	//////////////////////////////////////
	
	@Override
	public void create() {
		this.push(new MainMenu(this));
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
			
		}
	}

	public void back() {
		pop();
	}
	
}
