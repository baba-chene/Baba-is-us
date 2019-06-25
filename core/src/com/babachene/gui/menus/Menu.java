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

//TODO Background sprite

public abstract class Menu extends GameState implements Screen{

	protected MainGame parent;
	protected Stage stage;
	protected SpriteDrawable title;
	protected SpriteBatch batch;
	private int titleX,titleY,titleWidth,titleHeight;
	
	public Menu(MainGame mg, boolean hasBack) {
		parent=mg;
		title=null;
		stage = new Stage(getViewport());
		batch= new SpriteBatch();
		
		if (hasBack) {
			SpriteDrawable backImage = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/back.png"))));
			ImageButton backButton = new ImageButton(backImage);
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
		}
		
		parent.setInputProcessor(stage);
		
	}
	
	public void addTitle(String image,int posx,int posy,int width,int height) {
		titleX = posx;
		titleY = posy;
		titleWidth = width;
		titleHeight = height;
		title = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/"+image+".png"))));
	}
	
	public void addButton(String image, int posx,int posy, int width, int height, final int nextMenu) {
		
		SpriteDrawable buttonImage = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/"+image+".png"))));
		ImageButton button = new ImageButton(buttonImage);
		button.setBounds(posx, posy, width , height);
		button.setColor(BabaIsUs.buttonColor);

		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				parent.changeScreen(nextMenu);
				
				return;
			}
		});
		button.setDisabled(false);
		stage.addActor(button);
		
		parent.setInputProcessor(stage);
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
		if (title != null) {title.draw(batch, titleX, titleY, titleWidth, titleHeight);}
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
		// The inputProcessor (here: the stage) must added to the MainGame, and not Gdx.input.
		parent.setInputProcessor(stage);
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
