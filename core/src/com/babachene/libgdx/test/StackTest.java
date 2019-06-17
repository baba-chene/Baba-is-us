package com.babachene.libgdx.test;

import com.babachene.libgdx.GameState;
import com.babachene.libgdx.MainGame;
import com.babachene.libgdx.StateBasedGame;

public class StackTest {

	private StackTest() {
	}

	public static void main(String[] args) {
		
		try {
			StateBasedGame gsm = new MainGame();
			
			try {
				gsm.push(null);
				assert false;
			} catch (IllegalArgumentException e) {}
			GameState gs = new GameState() {
				@Override public void update() {} public void processInput() {}
				@Override public void show() {}@Override
				public void render() {}
				@Override
				public void resize(int width, int height) {}
				@Override
				public void pause() {}
				@Override
				public void resume() {}
				@Override
				public void hide() {}
				@Override public void dispose() {}};
//			try {
//				
//				gsm.push(gs);
//				assert false;
//			} catch (IllegalArgumentException e) {}
//			assert false;
		} catch (IllegalArgumentException e) {}
		
		StateBasedGame gsm = new MainGame();
		try {
			gsm.push(null);
			assert false;
		} catch (IllegalArgumentException e) {}
		
		try {
			gsm.push(new GameState() {
			@Override public void update() {} public void processInput() {}
			@Override public void show() {}@Override
			public void render() {}
			@Override
			public void resize(int width, int height) {}
			@Override
			public void pause() {}
			@Override
			public void resume() {}
			@Override
			public void hide() {}
			@Override public void dispose() {}});
		} catch (NullPointerException e) {} // Provoqué par l'absence d'initialisation de Gdx.
		
		
		System.out.println("Ok " + StackTest.class.getSimpleName());
	}

}
