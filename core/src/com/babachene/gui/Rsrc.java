package com.babachene.gui;

import com.badlogic.gdx.assets.AssetManager;

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
	
	private static final String DIR = "assets/";
	public static final String BABA_TEXTURE = DIR + "baba.png";
	
	
	
}
