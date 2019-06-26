package com.babachene.gui.menus;

import com.babachene.gui.BabaIsUs;
import com.babachene.gui.MainGame;
import com.badlogic.gdx.Screen;

public class Local2PlayerMenu extends Menu implements Screen {

	public Local2PlayerMenu(MainGame mg, boolean hasBack) {
		super(mg, hasBack);
		
		super.addTitle("selectMode",450, 800, 1000, 250);
		
		super.addButton("Coop", BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 +80, 800, 120, mg.LOCAL2PLAYERLEVELSELECTION);
		
		super.addButton("Babattle Royal", BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 -80, 800, 120, mg.LOCALARENASELECTION);
	}
	
}
