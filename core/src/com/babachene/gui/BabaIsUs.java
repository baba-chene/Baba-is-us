package com.babachene.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * A wrapper class for storing the most global data
 * of the game such as the language or the width & height
 * of the virtual screen.
 * <br>Inspired from the <b><code>Gdx</code></b> class from libgdx.
 * @author jeremy
 *
 */
public final class BabaIsUs {
	
	private BabaIsUs() {}
	
	public static int WIDTH = 1920, HEIGHT = 1080;
	
	//Colors
	public static Color buttonColor=Color.FOREST;
	
	
	//Skin
	static TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("assets/gui/default_skin/uiskin.atlas"));
	public static Skin skin = new Skin(Gdx.files.internal("assets/gui/default_skin/uiskin.json"), atlas);
	
	
	
	
	// Fonts
	FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
	FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	//parameter.size=12;
	BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
	//generator.dispose(); // don't forget to dispose to avoid memory leaks!
	
	
}
