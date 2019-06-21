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

public class PlayMenu extends GameState implements Screen {

	private MainGame parent;
	
	private Stage stage;
	private SpriteBatch batch;
	private SpriteDrawable title;
	private ImageButton backButton;
	private ImageButton singleButton;
	private ImageButton multiplayerButton;
	
	public PlayMenu(MainGame game) {
		parent=game;
		
		stage = new Stage(getViewport());
		batch= new SpriteBatch();
		
		// Title
		title = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/selectmode.png"))));
		
		
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
		
		
		
		
		//singleButton
		SpriteDrawable oneplayerImage = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/1player.png"))));
		singleButton = new ImageButton(oneplayerImage);
		singleButton.setBounds(BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 +80, 800, 120);
		singleButton.setColor(BabaIsUs.buttonColor);

		singleButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Play Button pressed");
				
				// TODO lancer la selection des niveaux
				
				return; // The event has been handled.
			}
		});
		singleButton.setDisabled(false);
		stage.addActor(singleButton);
		
		
		
		//multiplayerButton
		SpriteDrawable twoplayerImage = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/2player.png"))));
		multiplayerButton = new ImageButton(twoplayerImage);
		multiplayerButton.setBounds(BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 -80, 800, 120);
		multiplayerButton.setColor(BabaIsUs.buttonColor);

		multiplayerButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Play Button pressed");
				
				parent.changeScreen(MainGame.MULTIPLAYERMENU);
				
				return; // The event has been handled.
			}
		});
		multiplayerButton.setDisabled(false);
		stage.addActor(multiplayerButton);
		
		
		
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
