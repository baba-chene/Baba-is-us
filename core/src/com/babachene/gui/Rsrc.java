package com.babachene.gui;

import java.util.logging.Logger;

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
		
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("Rsrc started to load the assets");
		
		MISSING_TEXTURE = textureAtlas.findRegion("missing_texture");
		
		ROCK_TEXTURE = textureAtlas.findRegion("rock");
		WATER_TEXTURE = textureAtlas.findRegion("water");
		LAVA_TEXTURE = textureAtlas.findRegion("lava");
		WALL_TEXTURE = textureAtlas.findRegion("wall");
		//TREE_TEXTURE = textureAtlas.findRegion("tree");
		FLAG_TEXTURE = textureAtlas.findRegion("flag");
		//SKULL_TEXTURE = textureAtlas.findRegion("rock");
		
		
		TXT_YOU_TEXTURE = textureAtlas.findRegion("txt_you");
		TXT_P1_TEXTURE = textureAtlas.findRegion("txt_p1");
		TXT_P2_TEXTURE = textureAtlas.findRegion("txt_p2");
		TXT_P3_TEXTURE = textureAtlas.findRegion("txt_p3");
		TXT_SINK_TEXTURE = textureAtlas.findRegion("txt_sink");
		TXT_PUSH_TEXTURE = textureAtlas.findRegion("txt_push");
		TXT_WIN_TEXTURE = textureAtlas.findRegion("txt_win");
		TXT_STOP_TEXTURE = textureAtlas.findRegion("txt_stop");
		
		TXT_IS_TEXTURE = textureAtlas.findRegion("txt_is");
		TXT_AND_TEXTURE = textureAtlas.findRegion("txt_and");
		TXT_HAS_TEXTURE = textureAtlas.findRegion("txt_has");
		// MAKE
		TXT_ON_TEXTURE = textureAtlas.findRegion("txt_on");
		TXT_BUT_TEXTURE = textureAtlas.findRegion("txt_but");
		
		TXT_BABA_TEXTURE = textureAtlas.findRegion("txt_baba");
		TXT_ROCK_TEXTURE = textureAtlas.findRegion("txt_rock");
		TXT_WATER_TEXTURE = textureAtlas.findRegion("txt_water");
		TXT_LAVA_TEXTURE = textureAtlas.findRegion("txt_lava");
		TXT_WALL_TEXTURE = textureAtlas.findRegion("txt_wall");
		//TXT_TREE
		TXT_FLAG_TEXTURE = textureAtlas.findRegion("txt_flag");
		
		
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
								MISSING_TEXTURE,
	
//								BABA_TEXTURE,
								ROCK_TEXTURE,
								WATER_TEXTURE,
								LAVA_TEXTURE,
								WALL_TEXTURE,
								TREE_TEXTURE,
								FLAG_TEXTURE,
								SKULL_TEXTURE,
								
								TXT_YOU_TEXTURE,
								TXT_P1_TEXTURE,
								TXT_P2_TEXTURE,
								TXT_P3_TEXTURE,
								TXT_SINK_TEXTURE,
								TXT_PUSH_TEXTURE,
								TXT_WIN_TEXTURE,
								TXT_STOP_TEXTURE,
								
								TXT_IS_TEXTURE,
								TXT_AND_TEXTURE,
								TXT_HAS_TEXTURE,
								TXT_MAKE_TEXTURE,
								TXT_ON_TEXTURE,
								TXT_BUT_TEXTURE,
								
								TXT_BABA_TEXTURE,
								TXT_ROCK_TEXTURE,
								TXT_WATER_TEXTURE,
								TXT_LAVA_TEXTURE,
								TXT_WALL_TEXTURE,
								TXT_TREE_TEXTURE,
								TXT_FLAG_TEXTURE,
								TXT_SKULL_TEXTURE;
								
								
	
}
