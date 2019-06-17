package com.babachene.libgdx;

import com.babachene.libgdx.test.TestGameState;

public class MainGame extends StateBasedGame {
	
	public MainGame() {
		
	}
	
	//////////////////////////////////////
	
	@Override
	public void create() {
		
		TestGameState.main(this);
	}
	
	
}
