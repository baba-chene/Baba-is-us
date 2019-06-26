package com.babachene.gui.menus;

import com.babachene.gui.BabaIsUs;
import com.babachene.gui.MainGame;
import com.badlogic.gdx.Screen;

public class LocalMenu extends Menu implements Screen {

	public LocalMenu(MainGame mg, boolean hasBack) {
		super(mg, hasBack);
		
		super.addTitle("localMenu", 450, 800, 1000, 250);
		
		super.addButton("1 Player", BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 +80, 800, 120, mg.LEVELSELECTION /*mg.SINGLELEVELSELECTION*/);
		
		super.addButton("2 Player", BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 -80, 800, 120, mg.LOCAL2PLAYERMENU);
		
	}

}
