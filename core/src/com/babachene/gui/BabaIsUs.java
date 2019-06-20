package com.babachene.gui;

import com.badlogic.gdx.assets.AssetManager;

/**
 * A wrapper class for storing the most global data
 * of the game such as the language or the width & height
 * of the virtual screen.<br>
 * <br>Inspired by the <b><code>Gdx</code></b> class from libgdx.
 * @author jeremy
 *
 */
public final class BabaIsUs {
	
	private BabaIsUs() {}
	
	public static int WIDTH = 1920, HEIGHT = 1080;
	
	public static AssetManager assetManager;
	
	public static TextureRegions textures = new TextureRegions();
	
	
	public static final byte
					DEFAULT_THEME = 0;
	
	
	
	
	/////////////////////////////////////////
	
	public static final class TextureRegions {
		
		private TextureRegions() {}
		
		private static final String DIR = "./assets/";
		public final String
					PEPE     = DIR + "spiral_tree.png",
					KERMIT   = DIR + "kermit.jpg",
					
					
					THEME_DEFAULT = DIR + "dark_gray.png";
	}
	
	
	
}
