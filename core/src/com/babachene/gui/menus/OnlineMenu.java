package com.babachene.gui.menus;

import com.babachene.gui.BabaIsUs;
import com.babachene.gui.MainGame;
import com.badlogic.gdx.Screen;

public class OnlineMenu extends Menu implements Screen {

	public OnlineMenu(MainGame mg, boolean hasBack) {
		super(mg, hasBack);
		
		super.addTitle("selectMode", 450, 800, 1000, 250);
		
		super.addButton("Coop", BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 +80, 800, 120, mg.ONLINELEVELSELECTION);
		
		super.addButton("Babattle Royal", BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 -80, 800, 120, mg.ONLINEARENASELECTION);
	}

}
