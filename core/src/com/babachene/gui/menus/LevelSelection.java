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

public class LevelSelection extends GameState implements Screen {
	
	private MainGame parent;
	private Stage stage;
	private SpriteBatch batch;
	private SpriteDrawable title;
	private ImageButton backButton;
	private ImageButton level1;
	private ImageButton level2;
	private ImageButton level3;
	private ImageButton level4;
	private ImageButton level5;
	private ImageButton level6;
	
	
	public LevelSelection(MainGame game) {
		parent=game;
		
		stage = new Stage(getViewport());
		batch= new SpriteBatch();
		
		// Title
		title = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/levelSelection.png"))));
		
		
		//BackButton
		SpriteDrawable backImage = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/back.png"))));
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
		
		
		
		
		//First Level
		SpriteDrawable l1Image = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/level1.png"))));
		level1 = new ImageButton(l1Image);
		level1.setBounds(100, 500, 200, 200);
		level1.setColor(BabaIsUs.buttonColor);

		level1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			
				parent.getMetaController().launchLevel("arene1");

				return; // The event has been handled.
			}
		});
		level1.setDisabled(false);
		stage.addActor(level1);
		
		// Level 2
		SpriteDrawable l2Image = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/level2.png"))));
		level2 = new ImageButton(l2Image);
		level2.setBounds(400, 500, 200, 200);
		level2.setColor(BabaIsUs.buttonColor);

		level2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			
				parent.getMetaController().launchLevel("find the path");

				return; // The event has been handled.
			}
		});
		level2.setDisabled(false);
		stage.addActor(level2);
				
		//Level 3
		SpriteDrawable l3Image = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/level3.png"))));
		level3 = new ImageButton(l3Image);
		level3.setBounds(700, 500, 200, 200);
		level3.setColor(BabaIsUs.buttonColor);

		level3.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			
				// TODO launch the good level
				parent.getMetaController().launchLevel("the river");

				return; // The event has been handled.
			}
		});
		level3.setDisabled(false);
		stage.addActor(level3);
	
		//Level 4
		SpriteDrawable l4Image = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/level4.png"))));
		level4 = new ImageButton(l4Image);
		level4.setBounds(1000, 500, 200, 200);
		level4.setColor(BabaIsUs.buttonColor);

		level4.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			
				// TODO launch the good level
				parent.getMetaController().launchLevel("the river again");

				return; // The event has been handled.
			}
		});
		level4.setDisabled(false);
		stage.addActor(level4);

		
		//Level 5
		SpriteDrawable l5Image = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/level5.png"))));
		level5 = new ImageButton(l5Image);
		level5.setBounds(1300, 500, 200, 200);
		level5.setColor(BabaIsUs.buttonColor);

		level5.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
			
				// TODO launch the good level
				parent.getMetaController().launchLevel("keke is lost");

				return; // The event has been handled.
			}
		});
		level5.setDisabled(false);
		stage.addActor(level5);
		
		
		//Level 6
				SpriteDrawable l6Image = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/level5.png"))));
				level6 = new ImageButton(l6Image);
				level6.setBounds(1600, 500, 200, 200);
				level6.setColor(BabaIsUs.buttonColor);

				level6.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
					
						// TODO launch the good level
						parent.getMetaController().launchLevel("help your friend");

						return; // The event has been handled.
					}
				});
				level6.setDisabled(false);
				stage.addActor(level6);
	
		
		
		// The inputProcessor (here: the stage) must added to the MainGame, and not Gdx.input.
		parent.setInputProcessor(stage);
		
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
	}

	@Override
	public void pause() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void show() {
		// The inputProcessor (here: the stage) must added to the MainGame, and not Gdx.input.
		parent.setInputProcessor(stage);
		
	}

	@Override
	public void hide() {}

	@Override
	public void render(float delta) {
	}



	
	
	
}
