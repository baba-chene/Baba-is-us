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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * 
 * @author jeremy
 *
 */
public class MainMenu extends Menu implements Screen {
	
	public static Music menuMusic;
	
	
	public MainMenu(MainGame game,boolean hasBack) {
		
		super(game,  hasBack);
		
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/SoundIsPlay.wav")) ;
		menuMusic.setLooping(true);
		menuMusic.play();
		menuMusic.setVolume(0.5f);
		
		super.addTitle("babaisus", 450,800,1000,250);
		
		super.addButton("Play", BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 +30, 800, 120, game.PLAYMENU);
		
		super.addButton("Settings", BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 -110, 800, 120, game.SETTINGSMENU);
		
		
		Skin skin = new Skin(Gdx.files.internal("skin/pixthulhuui/pixthulhu-ui.json"));
		TextButton quitButton = new TextButton("Quit", skin);
		quitButton.setBounds(BabaIsUs.WIDTH / 2 - 400, BabaIsUs.HEIGHT / 2 - 250, 800, 120);

		quitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("Quit button pressed");
				
				Gdx.app.exit();
				
				return; // The event has been handled.
			}
		});
		quitButton.setDisabled(false);
		stage.addActor(quitButton);
		
		
		
	
		
	}
	
}
