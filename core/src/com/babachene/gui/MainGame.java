package com.babachene.gui;

import com.babachene.gui.menus.MainMenu;

public class MainGame extends StateBasedGame {
	
	public MainGame() {
		
	}
	
	//////////////////////////////////////
	
	@Override
	public void create() {
		this.push(new MainMenu());
	}
	
	
}
