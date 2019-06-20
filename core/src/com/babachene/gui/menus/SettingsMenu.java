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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class SettingsMenu extends GameState implements Screen {

	private MainGame parent;
	
	private Stage stage;
	private SpriteBatch batch;
	private SpriteDrawable title;
	private ImageButton backButton;
	
	public SettingsMenu(MainGame game) {
		parent=game;
		
		stage = new Stage(getViewport());
		batch= new SpriteBatch();
		
		// Title
		title = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/babaisus.png"))));
		
		
		//BackButton
		SpriteDrawable backImage = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/back.png"))));
		backButton = new ImageButton(backImage);
		backButton.setBounds(100, 80, 200, 120);
		
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
							
				parent.back();
				
				return; // The event has been handled.
			}
		});
		backButton.setDisabled(false);
		stage.addActor(backButton);
				
		
		
		
		
		
		Gdx.input.setInputProcessor(stage);
		
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(.1f, .05f, .01f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(getViewport().getCamera().combined);
		batch.begin();
		title.draw(batch, 450, 800, 1000, 250);
		batch.end();
		stage.act();
		stage.draw();
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void hide() {}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

}
