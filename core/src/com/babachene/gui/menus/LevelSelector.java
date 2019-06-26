package com.babachene.gui.menus;

import com.babachene.gui.MainGame;

public abstract class LevelSelector extends Menu{

	
	public LevelSelector(MainGame mg, boolean hasBack, String[] levelList) {
		super(mg, hasBack);
		
		super.addTitle("levelSelection", 450,920,1000,130);
		
		
		final int nbMaxLigne = 4;
		int ecart = 1920/(nbMaxLigne);
		int n = levelList.length;
		int i = 0;
		for(String level:levelList) {
			
			int ligne = i/nbMaxLigne;
			int colonne = i-(nbMaxLigne*ligne);
			
			super.addLevelButton(level, (colonne+1)*ecart-450, 1080-(300+(ligne*130)), 420, 110);
			i++;
		}
		
	}
	
}
