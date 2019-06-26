package com.babachene.gui;

import java.util.logging.Logger;

import com.babachene.logic.data.Direction;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
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
	
//	private static final Random random = new Random();
	
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
		
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("Rsrc starts to retrieve the assets.");
		
		MISSING_TEXTURE = textureAtlas.findRegion("missing_texture");
		
		BABA_TEXTURE = textureAtlas.findRegion("baba_right0");
		KEKE_TEXTURE = textureAtlas.findRegion("keke_right0");
		ROCK_TEXTURE = textureAtlas.findRegion("rock");
		WATER_TEXTURE = textureAtlas.findRegion("water1");
		LAVA_TEXTURE = textureAtlas.findRegion("lava15");
		WALL_TEXTURE = textureAtlas.findRegion("wall15");
		TREE_TEXTURE = textureAtlas.findRegion("tree");
		PALM_TEXTURE = textureAtlas.findRegion("palm");
		FLAG_TEXTURE = textureAtlas.findRegion("flag");;
		SKULL_TEXTURE = textureAtlas.findRegion("skull");
		GRASS_TEXTURE = textureAtlas.findRegion("grass");
		LEGO_TEXTURE = textureAtlas.findRegion("lego");
		LOVE_TEXTURE = textureAtlas.findRegion("love");
		BUSH_TEXTURE = textureAtlas.findRegion("bush");
		BOX_TEXTURE = textureAtlas.findRegion("box");
			
		TXT_YOU_TEXTURE = textureAtlas.findRegion("txt_you");
		TXT_P1_TEXTURE = textureAtlas.findRegion("txt_p1");
		TXT_P2_TEXTURE = textureAtlas.findRegion("txt_p2");
		TXT_P3_TEXTURE = textureAtlas.findRegion("txt_p3");
		TXT_SINK_TEXTURE = textureAtlas.findRegion("txt_sink");
		TXT_PUSH_TEXTURE = textureAtlas.findRegion("txt_push");
		TXT_WIN_TEXTURE = textureAtlas.findRegion("txt_win");
		TXT_KILL_TEXTURE = textureAtlas.findRegion("txt_kill");
		TXT_STOP_TEXTURE = textureAtlas.findRegion("txt_stop");
		TXT_US_TEXTURE = textureAtlas.findRegion("txt_us");
		TXT_OPEN_TEXTURE = textureAtlas.findRegion("txt_open");
		TXT_SHUT_TEXTURE = textureAtlas.findRegion("txt_shut");
		
		TXT_IS_TEXTURE = textureAtlas.findRegion("txt_is");
		TXT_AND_TEXTURE = textureAtlas.findRegion("txt_and");
		TXT_HAS_TEXTURE = textureAtlas.findRegion("txt_has");
		TXT_MAKE_TEXTURE = textureAtlas.findRegion("txt_make");
		TXT_ON_TEXTURE = textureAtlas.findRegion("txt_on");
		TXT_OFF_TEXTURE = textureAtlas.findRegion("txt_off");
		TXT_BUT_TEXTURE = textureAtlas.findRegion("txt_but");
		TXT_MOVE_TEXTURE = textureAtlas.findRegion("txt_move");
		TXT_WEAK_TEXTURE = textureAtlas.findRegion("txt_weak");
		
		TXT_BABA_TEXTURE = textureAtlas.findRegion("txt_baba");
		TXT_KEKE_TEXTURE = textureAtlas.findRegion("txt_keke");
		TXT_ROCK_TEXTURE = textureAtlas.findRegion("txt_rock");
		TXT_WATER_TEXTURE = textureAtlas.findRegion("txt_water");
		TXT_LAVA_TEXTURE = textureAtlas.findRegion("txt_lava");
		TXT_WALL_TEXTURE = textureAtlas.findRegion("txt_wall");
		TXT_TREE_TEXTURE = textureAtlas.findRegion("txt_tree");
		TXT_PALM_TEXTURE = textureAtlas.findRegion("txt_palm");
		TXT_PINE_TEXTURE = textureAtlas.findRegion("txt_pine");
		TXT_FLAG_TEXTURE = textureAtlas.findRegion("txt_flag");
		TXT_SKULL_TEXTURE = textureAtlas.findRegion("txt_skull");
		TXT_GRASS_TEXTURE = textureAtlas.findRegion("txt_grass");
		TXT_LEGO_TEXTURE = textureAtlas.findRegion("txt_lego");
		TXT_LOVE_TEXTURE = textureAtlas.findRegion("txt_love");
		TXT_BUSH_TEXTURE = textureAtlas.findRegion("txt_bush");
		TXT_BOX_TEXTURE = textureAtlas.findRegion("txt_box");
		
		TXT_PAF_TEXTURE = textureAtlas.findRegion("txt_paf");
		TXT_HUG_TEXTURE = textureAtlas.findRegion("txt_hug");
		TXT_LIGHT_TEXTURE = textureAtlas.findRegion("txt_light");
		
		/// Multiple sprite.
		BABA_UP_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("baba_up0"),
				textureAtlas.findRegion("baba_up1"),
				textureAtlas.findRegion("baba_up2")
		};
		BABA_RIGHT_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("baba_right0"),
				textureAtlas.findRegion("baba_right1"),
				textureAtlas.findRegion("baba_right2")
		};
		BABA_LEFT_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("baba_left0"),
				textureAtlas.findRegion("baba_left1"),
				textureAtlas.findRegion("baba_left2")
		};
		BABA_DOWN_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("baba_down0"),
				textureAtlas.findRegion("baba_down1"),
				textureAtlas.findRegion("baba_down2")
		};
		KEKE_UP_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("keke_up0"),
				textureAtlas.findRegion("keke_up1"),
				textureAtlas.findRegion("keke_up2")
		};
		KEKE_RIGHT_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("keke_right0"),
				textureAtlas.findRegion("keke_right1"),
				textureAtlas.findRegion("keke_right2")
		};
		KEKE_LEFT_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("keke_left0"),
				textureAtlas.findRegion("keke_left1"),
				textureAtlas.findRegion("keke_left2")
		};
		KEKE_DOWN_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("keke_down0"),
				textureAtlas.findRegion("keke_down1"),
				textureAtlas.findRegion("keke_down2")
		};
		
		WALL_ARRAY_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("wall0"),
				textureAtlas.findRegion("wall1"),
				textureAtlas.findRegion("wall2"),
				textureAtlas.findRegion("wall3"),
				textureAtlas.findRegion("wall4"),
				textureAtlas.findRegion("wall5"),
				textureAtlas.findRegion("wall6"),
				textureAtlas.findRegion("wall7"),
				textureAtlas.findRegion("wall8"),
				textureAtlas.findRegion("wall9"),
				textureAtlas.findRegion("wall10"),
				textureAtlas.findRegion("wall11"),
				textureAtlas.findRegion("wall12"),
				textureAtlas.findRegion("wall13"),
				textureAtlas.findRegion("wall14"),
				textureAtlas.findRegion("wall15"),
		};
		
		LAVA_ARRAY_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("lava0"),
				textureAtlas.findRegion("lava1"),
				textureAtlas.findRegion("lava2"),
				textureAtlas.findRegion("lava3"),
				textureAtlas.findRegion("lava4"),
				textureAtlas.findRegion("lava5"),
				textureAtlas.findRegion("lava6"),
				textureAtlas.findRegion("lava7"),
				textureAtlas.findRegion("lava8"),
				textureAtlas.findRegion("lava9"),
				textureAtlas.findRegion("lava10"),
				textureAtlas.findRegion("lava11"),
				textureAtlas.findRegion("lava12"),
				textureAtlas.findRegion("lava13"),
				textureAtlas.findRegion("lava14"),
				textureAtlas.findRegion("lava15"),
		};
		
		WATER_ARRAY_TEXTURE = new TextureRegion[] {
				textureAtlas.findRegion("water1"), // index 0
				textureAtlas.findRegion("water1"), // starts from index 1
				textureAtlas.findRegion("water2"), // so the index of a texture is the same as in the name.
				textureAtlas.findRegion("water3"),
				textureAtlas.findRegion("water4"),
				textureAtlas.findRegion("water5"),
				textureAtlas.findRegion("water6"),
				textureAtlas.findRegion("water7"),
				textureAtlas.findRegion("water8"),
				textureAtlas.findRegion("water9"),
				textureAtlas.findRegion("water10"),
				textureAtlas.findRegion("water11"),
				textureAtlas.findRegion("water12"),
				textureAtlas.findRegion("water13"),
				textureAtlas.findRegion("water14"),
				textureAtlas.findRegion("water15"),
				textureAtlas.findRegion("water16"),
				textureAtlas.findRegion("water17"),
				textureAtlas.findRegion("water18"),
				textureAtlas.findRegion("water19"),
				textureAtlas.findRegion("water20"),
				textureAtlas.findRegion("water21"),
				textureAtlas.findRegion("water22"),
				textureAtlas.findRegion("water23"),
				textureAtlas.findRegion("water24"),
				textureAtlas.findRegion("water25"),
				textureAtlas.findRegion("water26"),
				textureAtlas.findRegion("water27"),
				textureAtlas.findRegion("water28"),
				textureAtlas.findRegion("water29"),
				textureAtlas.findRegion("water30"),
				textureAtlas.findRegion("water31"),
				textureAtlas.findRegion("water32"),
				textureAtlas.findRegion("water33"),
				textureAtlas.findRegion("water34"),
				textureAtlas.findRegion("water35"),
				textureAtlas.findRegion("water36"),
				textureAtlas.findRegion("water37"),
				textureAtlas.findRegion("water38"),
				textureAtlas.findRegion("water39"),
				textureAtlas.findRegion("water40"),
				textureAtlas.findRegion("water41"),
				textureAtlas.findRegion("water42"),
				textureAtlas.findRegion("water43"),
				textureAtlas.findRegion("water44"),
				textureAtlas.findRegion("water45"),
				textureAtlas.findRegion("water46"),
				textureAtlas.findRegion("water47")
		};
	}
	
	//====================================================================================//
	//====================================================================================//
	//==============                           ===========================================//
	//==============     TextureRegion         ===========================================//
	//==============                           ===========================================//
	//====================================================================================//
	//====================================================================================//
	
	/*
	 * To verify all these fields are initialized in loadEverything,
	 * set them to static and remplace loadEverything by static so
	 * eclipse groans.
	 */
	public static TextureRegion
								MISSING_TEXTURE,
	
								BABA_TEXTURE,
								BABA_UP_TEXTURE[],
								BABA_DOWN_TEXTURE[],
								BABA_RIGHT_TEXTURE[],
								BABA_LEFT_TEXTURE[],
								KEKE_TEXTURE,
								KEKE_UP_TEXTURE[],
								KEKE_DOWN_TEXTURE[],
								KEKE_RIGHT_TEXTURE[],
								KEKE_LEFT_TEXTURE[],
								ROCK_TEXTURE,
								WATER_TEXTURE,
								WATER_ARRAY_TEXTURE[],
								LAVA_TEXTURE,
								LAVA_ARRAY_TEXTURE[],
								WALL_TEXTURE,
								WALL_ARRAY_TEXTURE[],
								TREE_TEXTURE,
								PALM_TEXTURE,
								FLAG_TEXTURE,
								SKULL_TEXTURE,
								GRASS_TEXTURE,
								LEGO_TEXTURE,
								LOVE_TEXTURE,
								BUSH_TEXTURE,
								BOX_TEXTURE,
								
								TXT_YOU_TEXTURE,
								TXT_P1_TEXTURE,
								TXT_P2_TEXTURE,
								TXT_P3_TEXTURE,
								TXT_SINK_TEXTURE,
								TXT_PUSH_TEXTURE,
								TXT_WIN_TEXTURE,
								TXT_STOP_TEXTURE,
								TXT_US_TEXTURE,
								TXT_KILL_TEXTURE,
								TXT_OPEN_TEXTURE,
								TXT_SHUT_TEXTURE,
								TXT_WEAK_TEXTURE,
								
								TXT_IS_TEXTURE,
								TXT_AND_TEXTURE,
								TXT_HAS_TEXTURE,
								TXT_MAKE_TEXTURE,
								TXT_ON_TEXTURE,
								TXT_OFF_TEXTURE,
								TXT_BUT_TEXTURE,
								TXT_MOVE_TEXTURE,
								
								TXT_BABA_TEXTURE,
								TXT_KEKE_TEXTURE,
								TXT_ROCK_TEXTURE,
								TXT_WATER_TEXTURE,
								TXT_LAVA_TEXTURE,
								TXT_WALL_TEXTURE,
								TXT_TREE_TEXTURE,
								TXT_PALM_TEXTURE,
								TXT_PINE_TEXTURE,
								TXT_FLAG_TEXTURE,
								TXT_SKULL_TEXTURE,
								TXT_GRASS_TEXTURE,
								TXT_LEGO_TEXTURE,
								TXT_LOVE_TEXTURE,
								TXT_BUSH_TEXTURE,
								TXT_BOX_TEXTURE,
								
								TXT_PAF_TEXTURE,
								TXT_HUG_TEXTURE,
								TXT_LIGHT_TEXTURE;
	
	
	//====================================================================================//
	//====================================================================================//
	//==============                           ===========================================//
	//==============          Mapping          ===========================================//
	//==============                           ===========================================//
	//====================================================================================//
	//====================================================================================//
	
	/**
	 * Map an entity ID to a TextureRegion object that can
	 * be rendered to draw the entity.
	 * @param id - The ID of the entity.
	 * @return
	 * <li>- The TextureRegion corresponding to the entity.
	 * <li>- A "missing texture" texture if the ID is missing
	 * in the switch/case, check it!
	 * <li>- <code>null</code> when the texture object happens
	 * not to be loaded (or a problem occured), see the texture
	 * region fields of <code>Rsrc</code> and the <code>
	 * loadEverything</code> method to correct the bug.
	 */
	public static TextureRegion getTextureFromID(String id) {
		
		// C'est parti pour le switch/case. -__-
		
		switch (id) {
		// actual entities
		case "baba":			return BABA_TEXTURE;
		case "rock":			return ROCK_TEXTURE;
		case "water":			return WATER_TEXTURE;
		case "lava":			return LAVA_TEXTURE;
		case "wall":			return WALL_TEXTURE;
		case "tree":			return TREE_TEXTURE;
		case "flag":			return FLAG_TEXTURE;
		case "skull":			return SKULL_TEXTURE;
		case "grass":			return GRASS_TEXTURE;
		case "keke":			return KEKE_TEXTURE;
		case "lego":			return LEGO_TEXTURE;
		case "love":			return LOVE_TEXTURE;
		case "bush":			return BUSH_TEXTURE;
		case "box":				return BOX_TEXTURE;
		
		// text for attributes
		case "textyou":			return TXT_YOU_TEXTURE;
		case "textp1":			return TXT_P1_TEXTURE;
		case "textp2":			return TXT_P2_TEXTURE;
		case "textp3":			return TXT_P3_TEXTURE;
		case "textsink":		return TXT_SINK_TEXTURE;
		case "textpush":		return TXT_PUSH_TEXTURE;
		case "textwin":			return TXT_WIN_TEXTURE;
		case "textblock":
		case "textstop":		return TXT_STOP_TEXTURE;
		case "textus":			return TXT_US_TEXTURE;
		case "textkill":		return TXT_KILL_TEXTURE;
		case "textopen":		return TXT_OPEN_TEXTURE;
		case "textshut":		return TXT_SHUT_TEXTURE;
		case "textweak":		return TXT_WEAK_TEXTURE;
		
		// verbs
		case "textis":			return TXT_IS_TEXTURE;
		case "textand":			return TXT_AND_TEXTURE;
		case "textmake":		return TXT_MAKE_TEXTURE;
		case "texthas":			return TXT_HAS_TEXTURE;
		case "texton":			return TXT_ON_TEXTURE;
		case "textbut":			return TXT_BUT_TEXTURE;
		case "textmove":		return TXT_MOVE_TEXTURE;
		
		// text for verbs
		case "textbaba":		return TXT_BABA_TEXTURE;
		case "textrock":		return TXT_ROCK_TEXTURE;
		case "textwater":		return TXT_WATER_TEXTURE;
		case "textlava":		return TXT_LAVA_TEXTURE;
		case "textwall":		return TXT_WALL_TEXTURE;
		case "texttree":		return TXT_TREE_TEXTURE;
		case "textflag":		return TXT_FLAG_TEXTURE;
		case "textskull":		return TXT_SKULL_TEXTURE;
		case "textgrass":		return TXT_GRASS_TEXTURE;
		case "textkeke":		return TXT_KEKE_TEXTURE;
		case "textlove":		return TXT_LOVE_TEXTURE;
		case "textlego":		return TXT_LEGO_TEXTURE;
		case "textbush":		return TXT_BUSH_TEXTURE;
		case "textbox":			return TXT_BOX_TEXTURE;
		
		// non-entity text as subject
		case "textpaf":			return TXT_PAF_TEXTURE;
		case "texthug":			return TXT_HUG_TEXTURE;
		case "textlight":		return TXT_LIGHT_TEXTURE;
		
		default: 			return MISSING_TEXTURE;
		}
		
	}
	
	/**
	 * TODO doc
	 * @param id
	 * @param dir
	 * @return
	 */
	public static TextureRegion[] getDirectionTextureArray(String id, Direction dir) {
		switch (dir) {
		case NORTH: switch (id) {
			case "baba":		return BABA_UP_TEXTURE;
			case "keke":		return KEKE_UP_TEXTURE;
		}
		case EAST: switch (id) {
			case "baba":		return BABA_RIGHT_TEXTURE;
			case "keke":		return KEKE_RIGHT_TEXTURE;
		}
		case SOUTH: switch (id) {
			case "baba":		return BABA_DOWN_TEXTURE;
			case "keke":		return KEKE_DOWN_TEXTURE;
		}
		case WEST: switch (id) {
			case "baba":		return BABA_LEFT_TEXTURE;
			case "keke":		return KEKE_LEFT_TEXTURE;
		}
		}
		Logger.getGlobal().warning("[ Rsrc ] could not sastisfy request for textures of " + id +". Returing null");
		return null;
	}
	
}
