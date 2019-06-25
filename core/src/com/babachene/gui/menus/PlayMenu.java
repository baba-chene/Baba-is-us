package com.babachene.gui.menus;

import com.babachene.gui.BabaIsUs;
import com.babachene.gui.GameState;
import com.babachene.gui.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class PlayMenu extends Menu implements Screen {
	
	public PlayMenu(MainGame game, boolean hasBack) {
		super(game,hasBack);
		
		super.addTitle("selectMode",450, 800, 1000, 250);
		
		super.addButton("local", BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 +80, 800, 120, game.LOCALMENU);
		
		super.addButton("online", BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 -80, 800, 120, game.JOINMENU);
				
	}
}