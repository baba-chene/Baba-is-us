package com.babachene.gui;

import com.babachene.gui.test.RenderingTest;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainGame extends StateBasedGame {
	
	public MainGame() {
		
	}
	
	//////////////////////////////////////
	
	@Override
	public void create() {
		
		BabaIsUs.assetManager = new AssetManager();
		
		/*
		 * TEST ZONE
		 */
		
		
		BabaIsUs.assetManager.load(BabaIsUs.textures.PEPE, Texture.class);
		BabaIsUs.assetManager.load(BabaIsUs.textures.KERMIT, Texture.class);
		BabaIsUs.assetManager.load(BabaIsUs.textures.THEME_DEFAULT, Texture.class);
		BabaIsUs.assetManager.finishLoading();
		
		this.push(new RenderingTest());
		
		/*
		 * END OF TEST ZONE
		 */
		
	}
	
	
}
