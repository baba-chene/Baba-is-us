package com.babachene.gui.menus;

import com.babachene.gui.BabaIsUs;
import com.babachene.gui.GameState;
import com.babachene.gui.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * 
 * @author jeremy
 *
 */
public class MainMenu extends GameState implements Screen {
	
	private MainGame parent;
	
	private Stage stage;
	private ImageButton playButton;
	private ImageButton settingsButton;
	private ImageButton quitButton;
	private Sprite backgroundSprite;
	private SpriteBatch batch;
	private SpriteDrawable title;
	public static Music menuMusic;
	
	
	public MainMenu(MainGame game) {
		
		parent=game;
		
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/SoundIsPlay.wav")) ;
		menuMusic.setLooping(true);
		menuMusic.play();
		menuMusic.setVolume(0.5f);
		
		
		
		
		batch=new SpriteBatch();
		Texture backgroundTexture = new Texture("badlogic.jpg");
        backgroundSprite =new Sprite(backgroundTexture);
		
		
		stage = new Stage(getViewport());
		
		//TODO Background
		
		// Title
		title = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/babaisus.png"))));
		
		
		//Play Button
		SpriteDrawable playImage = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/play.png"))));
		playButton = new ImageButton(playImage);
		playButton.setBounds(BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 +30, 800, 120);
		playButton.setColor(BabaIsUs.buttonColor);

		playButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Play Button pressed");
				
				parent.changeScreen(MainGame.PLAYMENU);
				
				return; // The event has been handled.
			}
		});
		playButton.setDisabled(false);
		
		//SettingsButton
		SpriteDrawable settingsImage = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/settings.png"))));
		settingsButton = new ImageButton(settingsImage);
		settingsButton.setBounds(BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 - 110, 800, 120);
		settingsButton.setColor(BabaIsUs.buttonColor);

		settingsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Settings Button pressed");
				
				parent.changeScreen(MainGame.SETTINGSMENU);
				
				return; // The event has been handled.
			}
		});
		settingsButton.setDisabled(false);
		
		
		//QuitButton
		SpriteDrawable quitImage = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/quit.png"))));
		quitButton = new ImageButton(quitImage);
		quitButton.setBounds(BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 - 250, 800, 120);
		quitButton.setColor(BabaIsUs.buttonColor);

		quitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Quit button pressed");
				
				Gdx.app.exit();
				
				return; // The event has been handled.
			}
		});
		quitButton.setDisabled(false);
		
		
		//stage.addActor(title);
		stage.addActor(playButton);
		stage.addActor(settingsButton);
		stage.addActor(quitButton);
		
		Gdx.input.setInputProcessor(stage);
	}
	
	
	
	
	
	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// Pour afficher une image en fond
		/*
		batch.begin();
		backgroundSprite.draw(batch);
		batch.end();
		*/
		//Pour afficher une couleur unis en fond
		Gdx.gl.glClearColor(.1f, .0f, .01f, 1);
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
		stage.dispose();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		

	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}







	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}
	
}
