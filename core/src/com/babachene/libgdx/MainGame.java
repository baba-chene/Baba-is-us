package com.babachene.libgdx;

import com.babachene.libgdx.gui.MainMenu;

public class MainGame extends StateBasedGame {
	
	public MainGame() {
		
	}
	
	//////////////////////////////////////
	
	@Override
	public void create() {
		this.push(new MainMenu());
	}
	
	
}
