package com.babachene.gui.test;

import com.babachene.gui.BabaIsUs;
import com.babachene.gui.GameState;
import com.babachene.gui.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TestGameState extends GameState {
	
	private static final float delta = 100f;
	
	private ShapeRenderer sr;
	private float x, y;
	
	public TestGameState() {
		// Put here the creation.
		sr = new ShapeRenderer();
		x = y = 0;
	}
	
	///////////////////////////////////////////////
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(.1f, .05f, .01f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sr.setProjectionMatrix(getViewport().getCamera().combined);
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.BROWN);
		sr.rect(0, 0, BabaIsUs.WIDTH, BabaIsUs.HEIGHT);
		sr.setColor(Color.CHARTREUSE);
		sr.circle(x, y, 30);
		sr.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		sr.dispose();
	}

	@Override
	public void processInput() {
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			x -= delta;
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			x += delta;
		if (Gdx.input.isKeyPressed(Keys.UP))
			y += delta;
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			y -= delta;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
		//==============================================
		//================== TEST ======================
		
	public static void main(MainGame gameToTest) {
		gameToTest.push(new TestGameState());
	}
		
		//==============================================
		//==============================================
}
