package com.babachene.gui.menus;

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
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class SettingsMenu extends Menu implements Screen {

	
	
	public SettingsMenu(MainGame mg, boolean hasBack) {
		super(mg, hasBack);
		
		super.addTitle("settingsmenu",450, 800, 1000, 250);
		
		Skin skin = new Skin(Gdx.files.internal("skin/pixthulhuui/pixthulhu-ui.json"));
		final CheckBox fs = new CheckBox("Full screen", skin);
		fs.setBounds(600,500,400,120);
			
		fs.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				if (fs.isChecked()) {
					Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
				}
				else {
					Gdx.graphics.setWindowedMode(800, 600);
				}
				return;
			}
		});
		
		fs.setDisabled(false);
		stage.addActor(fs);
	}
}