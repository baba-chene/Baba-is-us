package com.babachene.gui.menus;

import com.babachene.gui.GameState;
import com.babachene.gui.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class P1Win extends Menu{

	
	
	public P1Win(MainGame mg, boolean hasBack) {
		super(mg, hasBack);
		
		super.addTitle("P1win",360,600,1200,300);
		
		Skin skin = new Skin(Gdx.files.internal("skin/pixthulhuui/pixthulhu-ui.json"));
		TextButton button = new TextButton("Continue", skin);
		button.setBounds(760, 200, 400 , 140);
		
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				parent.back();
				parent.back();
				parent.back();

				return;
			}
		});
		button.setDisabled(false);
		stage.addActor(button);
		
		parent.setInputProcessor(stage);
		
		
	}

}

