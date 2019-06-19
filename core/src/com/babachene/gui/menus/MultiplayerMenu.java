package com.babachene.gui.menus;

import com.babachene.gui.BabaIsUs;
import com.babachene.gui.GameState;
import com.babachene.gui.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MultiplayerMenu extends GameState implements Screen {

	private MainGame parent;
	
	private Stage stage;
	private TextButton backButton;
	
	private TextField portJoin;
	private TextField ip;
	private TextButton joinButton;
	
	private TextField portHost;
	private TextButton hostButton;
	
	
	
	public MultiplayerMenu(MainGame game) {
		parent=game;
		
		stage = new Stage(getViewport());
		
		SpriteBatch batch = new SpriteBatch();
		
		// Title
		Label title = new Label("Multiplayer",BabaIsUs.skin);
		title.setX(850);
		title.setY(1000);
		title.setFontScale(3,3);
		stage.addActor(title);
		
		
		//BackButton
		backButton = new TextButton("back", BabaIsUs.skin);
		backButton.setBounds(100, 80, 120, 80);
		backButton.setColor(BabaIsUs.buttonColor);
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("back Button pressed");
				
				parent.back();
				
				return; // The event has been handled.
			}
		});
		backButton.setDisabled(false);
		stage.addActor(backButton);
		
		
		//JOIN
		Label lblPort = new Label("Port :",BabaIsUs.skin);
		lblPort.setX(530);
		lblPort.setY(700);
		stage.addActor(lblPort);
		
		Label lblIP = new Label("IP :",BabaIsUs.skin);
		lblIP.setX(530);
		lblIP.setY(630);
		stage.addActor(lblIP);
		
		portJoin= new TextField("",BabaIsUs.skin);
		portJoin.setX(600);
		portJoin.setY(700);
		portJoin.setWidth(250);
		stage.addActor(portJoin);
		
		ip = new TextField("",BabaIsUs.skin);
		ip.setX(600);
		ip.setY(630);
		ip.setWidth(250);
		stage.addActor(ip);
		
		joinButton = new TextButton("Join", BabaIsUs.skin);
		joinButton.setBounds(1000, 630, 120, 110);
		joinButton.setColor(BabaIsUs.buttonColor);
		joinButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("join Button pressed");
				
				String port = portJoin.getText();
				String IP = ip.getText();
				
				// TODO Sombre code a placer ici (demander a Colin)
				
				return; // The event has been handled.
			}
		});
		joinButton.setDisabled(false);
		stage.addActor(joinButton);
		
		
		//HOST
		Label lbl = new Label("Port :",BabaIsUs.skin);
		lbl.setX(530);
		lbl.setY(350);
		stage.addActor(lbl);
		
		portHost= new TextField("",BabaIsUs.skin);
		portHost.setX(600);
		portHost.setY(350);
		portHost.setWidth(250);
		stage.addActor(portHost);
		
		hostButton = new TextButton("Host", BabaIsUs.skin);
		hostButton.setBounds(1000, 300, 120, 110);
		hostButton.setColor(BabaIsUs.buttonColor);
		hostButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				System.out.println("host Button pressed");
				
				String port = portHost.getText();
				// TODO Sombre code a placer ici (demander a Colin)
				
				return; // The event has been handled.
			}
		});
		hostButton.setDisabled(false);
		stage.addActor(hostButton);
		
		
		
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
