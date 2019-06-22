package com.babachene.gui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Static class to store all ressources links and names. (e.g.
 * texture file paths.)
 * @author jeremy
 *
 */
public final class Rsrc {
	
	private Rsrc() {}
	
	/**
	 * The asset manager of the game.
	 */
	public static AssetManager assetManager;
	
	
	/**
	 * The texture atlas from which you can retrieve
	 * your TextureRegion.
	 */
	public static TextureAtlas textureAtlas;
	
	public static void loadEverything() {
		if (assetManager == null)
			assetManager = new AssetManager();
		
		if (textureAtlas == null)
			textureAtlas = new TextureAtlas("assets/packedTextures.atlas");
		
		
		ROCK_TEXTURE = textureAtlas.findRegion("rock");
		
		TXT_YOU_TEXTURE = textureAtlas.findRegion("txt_you");
		TXT_SINK_TEXTURE = textureAtlas.findRegion("txt_sink");
		TXT_PUSH_TEXTURE = textureAtlas.findRegion("txt_push");
		TXT_WIN_TEXTURE = textureAtlas.findRegion("txt_win");
		
		TXT_IS_TEXTURE = textureAtlas.findRegion("txt_is");
		
		TXT_BABA_TEXTURE = textureAtlas.findRegion("txt_baba");
		TXT_ROCK_TEXTURE = textureAtlas.findRegion("txt_rock");
		
		
		
	}
	
//	private static final String DIR = "";
//	public static final String
//							BABA_TEXTURE	= DIR + "baba",
//							ROCK_TEXTURE	= DIR + "rock",
//							WATER_TEXTURE	= DIR + "water",
//							LAVA_TEXTURE	= DIR + "lava",
//							WALL_TEXTURE	= DIR + "wall",
//							TREE_TEXTURE	= DIR + "tree",
//							FLAG_TEXTURE	= DIR + "flag",
//							
//							TXT_YOU_TEXTURE	= DIR + "txt_you",
//							TXT_P1_TEXTURE	= DIR + "txt_p1",
//							TXT_P2_TEXTURE	= DIR + "txt_p2",
//							TXT_P3_TEXTURE	= DIR + "txt_p3",
//							TXT_SINK_TEXTURE	= DIR + "txt_sink";
	/*
	 * To verify all these fields are initialized in loadEverything,
	 * set them to static and remplace loadEverything by static so
	 * eclipse groans.
	 */
	public static TextureRegion
//								BABA_TEXTURE,
								ROCK_TEXTURE,
//								WATER_TEXTURE;
								
								TXT_YOU_TEXTURE,
								TXT_P1_TEXTURE,
								TXT_P2_TEXTURE,
								TXT_P3_TEXTURE,
								TXT_SINK_TEXTURE,
								TXT_PUSH_TEXTURE,
								TXT_WIN_TEXTURE,
								
								TXT_IS_TEXTURE,
								
								TXT_BABA_TEXTURE,
								TXT_ROCK_TEXTURE,
								TXT_WATER_TEXTURE,
								
								TXT_FLAG_TEXTURE;
								
								
	
}
