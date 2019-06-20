package com.babachene;

import java.io.IOException;
import com.babachene.cliserv.Client;
import com.babachene.cliserv.Server;
import com.babachene.logger.GlobalLogger;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	
	SpriteBatch batch;
	Texture img;
	Client client;
	Server server;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		try {
			GlobalLogger.setup(true);
		} catch(IOException ioe) {
			
		}
		client = new Client(10, 10);
		server = new Server(10, 10);
		server.open(4444);
		client.connect("127.0.0.1", 4444);
		client.disconnect();
		server.close();
		server.open();
		client.connect();
		server.disconnect();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		server.shutdown();
		client.shutdown();
	}
}
