package com.babachene;

public final class Baba {
	
	private Baba() {}
	
	/**
	 * TODO to comment
	 */
	public static final float BASE_ENTITY_MOVING_SPEED = .2f;
	
	/** Entities identifiant.
	 * <p>
	 * There are two advantages in having such a String list:
	 * <li> We keep a reference to all recognized entities, at the cost
	 * of maintaining the list correct and updated.
	 * <li> We have a quick list of all available entities.
	 */
	public static final String[] ENTITIES = new String[] {
	// Actual entities
					"baba",
					"rock",
					"water",
					"lava",
					"wall",
					"tree",
					"flag",
					"skull",
					"grass",
					"keke",
					"lego",
					"love",
					"bush",
	// Text for attributes
					"textyou",
					"textp1",
					"textp2",
					"textp3",
					"textsink",
					"textpush",
					"textwin",
					"textblock",// duplicated
					"textstop", //
					"textus",
	// Text for verbs
					"textis",
					"textand",
					"texthas",
					"textmake",
					"texton",
					"textbut",
	// Text for entities
					"textbaba",
					"textrock",
					"textwater",
					"textlava",
					"textwall",
					"texttree",
					"textflag",
					"textskull",
					"textgrass",
					"textkeke",
	//Text for non-entities subject
					"textpaf"
	};
	
}
