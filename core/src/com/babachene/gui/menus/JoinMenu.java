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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class JoinMenu extends Menu implements Screen {

	private MainGame parent;
	
	private SpriteBatch batch;
	private SpriteDrawable title;
	private ImageButton backButton;
	
	private TextField portJoin;
	private TextField ip;
	private TextButton joinButton;
	
	private TextField portHost;
	private TextButton hostButton;
	
	
	public JoinMenu(final MainGame game,boolean hasBack) {
		
		super(game, hasBack);
		
		parent=game;
		//stage = new Stage(getViewport());
		batch= new SpriteBatch();
		
		// Title
		//title = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("textures/menus/2playermode.png"))));
		
		Skin skin = new Skin(Gdx.files.internal("skin/pixthulhuui/pixthulhu-ui.json"));
		//JOIN
		Label lblPort = new Label("Port :",skin);
		lblPort.setX(510);
		lblPort.setY(720);
		stage.addActor(lblPort);
		
		Label lblIP = new Label("IP :",skin);
		lblIP.setX(510);
		lblIP.setY(650);
		stage.addActor(lblIP);
		
		portJoin= new TextField("4444",skin);
		portJoin.setX(600);
		portJoin.setY(700);
		portJoin.setWidth(250);
		stage.addActor(portJoin);
		
		ip = new TextField("127.0.0.1",skin);
		ip.setX(600);
		ip.setY(630);
		ip.setWidth(250);
		stage.addActor(ip);
		
		
		//JOIN
		joinButton = new TextButton("Join",skin);
		joinButton.setBounds(1000, 630, 180, 120);
		
		joinButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
							
				String port = portJoin.getText();
				String IP = ip.getText();
				
				int intPort = -1;
				try {
					intPort = Integer.parseInt(port);
				} catch(NumberFormatException e) {
					
				}
				if (intPort != -1)
					parent.getMetaController().joinServer(IP, intPort);
				
				return; // The event has been handled.
			}
		});
		joinButton.setDisabled(false);
		stage.addActor(joinButton);
		
		

		//HOST
		Label lbl = new Label("Port :",skin);
		lbl.setX(510);
		lbl.setY(370);
		stage.addActor(lbl);
		
		portHost= new TextField("4444",skin);
		portHost.setX(600);
		portHost.setY(350);
		portHost.setWidth(250);
		stage.addActor(portHost);
		
//		this.gameController = new GameController(parent);
		
		hostButton = new TextButton("Host",skin);
		hostButton.setBounds(1000, 330, 180, 120);
		
		hostButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				
				String port = portHost.getText();
				int intPort = -1;
				try {
					intPort = Integer.parseInt(port);
				} catch(NumberFormatException e) {
					
				}
				/*
				if (intPort != -1)
					parent.getMetaController().hostServer(intPort);
				*/
				game.changeScreen(game.ONLINEMENU);
				
				return; // The event has been handled.
			}
		});
		hostButton.setDisabled(false);
		stage.addActor(hostButton);
		
		
		
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
		//batch.begin();
		//title.draw(batch, 450, 800, 1000, 250);
		//batch.end();
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
		// The inputProcessor (here: the stage) must added to the MainGame, and not Gdx.input.
		parent.setInputProcessor(stage);
		
	}

	@Override
	public void hide() {}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

}
