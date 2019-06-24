package com.babachene.gui;

import com.babachene.gui.renderer.LevelRenderer;
import com.babachene.gui.renderer.RenderableLevel;
import com.babachene.userinput.LevelInputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The {@linkplain GameState} that holds a level inside.
 * @author jeremy
 *
 */
public class LevelState extends GameState {
	
	private MainGame parent;
	private InputProcessor inputProcessor;
	private LevelRenderer levelRenderer;
	private SpriteBatch batch;
	
	
	public LevelState(MainGame mainGame, RenderableLevel levelToRender, LevelInputProcessor inputProcessor) {
		if (mainGame == null)
			throw new IllegalArgumentException("The MainGame cannot be null");
		parent = mainGame;
		
		if (levelToRender == null)
			throw new IllegalArgumentException("The RenderableLevel cannot be null");
		
		levelRenderer = new LevelRenderer(levelToRender);
		
		this.inputProcessor = inputProcessor;
		parent.setInputProcessor(inputProcessor);
		
		batch = new SpriteBatch();
		
		
		
	}
	
	
//	public RendererUpdateQueue getRendererUpdateQueue() {
//		return updateQueue;
//	}
//	
//	public void setRendererUpdateQueue(RendererUpdateQueue newQueue) {
//		this.updateQueue = newQueue;
//		// For now: does not re-update the renderers in the setter.
//	}
	
	
	
	
	@Override
	public void update() {
		
		
		// Don't put the controller-related calls here.
		// The belonged to the MetaController.
		
		// updates the renderers.
		levelRenderer.update();
		
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(.0f, .0f, .0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		batch.setProjectionMatrix(getViewport().getCamera().combined);
		batch.begin();
		levelRenderer.render(batch);
		batch.end();
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
		parent.setInputProcessor(inputProcessor);
	}
	
	@Override
	public void hide() {
		
	}
	
}
