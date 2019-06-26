package com.babachene.gui.menus;

import com.babachene.gui.MainGame;

public abstract class ArenaSelector extends Menu {

	public ArenaSelector(MainGame mg, boolean hasBack, String[] arenaList) {
		super(mg, hasBack);
		
		super.addTitle("ArenaSelection", 450,920,1000,130);
		
		final int nbMaxLigne = 3;
		int ecart = 1920/(nbMaxLigne);
		int n = arenaList.length;
		int i = 0;
		for(String level:arenaList) {
			
			int ligne = i/nbMaxLigne;
			int colonne = i-(nbMaxLigne*ligne);
			
			super.addLevelButton(level, (colonne+1)*ecart-550, 1080-(450+(ligne*300)), 420, 200);
			i++;
		}
		
	}

}
