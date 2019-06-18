package com.babachene.libgdx.gui;

import com.babachene.libgdx.BabaIsUs;
import com.babachene.libgdx.GameState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * 
 * @author jeremy
 *
 */
public class MainMenu extends GameState {
	
	private Stage stage;
	private Actor title;
	private TextButton button;
	private Skin skin;
	
	public MainMenu() {
		
		// Trying to make the stage work.
		stage = new Stage(getViewport());
		
		// Wokrs, but only tested with the default skin. May not work with others.
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("assets/gui/default_skin/uiskin.atlas"));
		skin = new Skin(Gdx.files.internal("assets/gui/default_skin/uiskin.json"), atlas);
		
		
		title = new Label("FQkhdsbfkjhb fhjsb24243434jhkfgdsjhgfbhjsd bdsghfjg", skin);
		title.setY(100);
		
		button = new TextButton("Clic me.", skin);
		button.setBounds(BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 - 60, 800, 120);
		button.setColor(Color.FOREST);
		
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				System.out.println("Event of which button is " + event.getButton() + " at (" +x+", " + y + ")");
				return; // The event has been handled.
			}
		});
		
		button.setDisabled(false);
		
		stage.addActor(title);
		stage.addActor(button);
		
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
		stage.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
	
}
