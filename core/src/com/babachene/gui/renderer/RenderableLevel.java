package com.babachene.gui.renderer;

/**
 * Represents a level. It should take up the entire
 * game screen and display game 'meta' data such as the score,
 * the music, etc...
 * @author jeremy
 *
 */
public interface RenderableLevel {
	
	
	/**
	 * Return the map to render.
	 * <br>This may be upgraded later to support more than
	 * one map levels.
	 * @return
	 */
	RenderableMap getMap();
	
	/**
	 * Return the theme of the level, which provides information
	 * for the background.
	 * @return an id.
	 */
	byte getThemeId();
	
	/**
	 * Return the id indentyfying the music to play.
	 * @return
	 */
	byte getMusicId();
	
	/**
	 * A level data storing object sould know the level's title as
	 * it is stored in the level file.
	 * @return The title or name of this level.
	 */
	String getTitle();
	
}
