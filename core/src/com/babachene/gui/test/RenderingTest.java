package com.babachene.gui.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.babachene.gui.BabaIsUs;
import com.babachene.gui.GameState;
import com.babachene.gui.renderer.LevelRenderer;
import com.babachene.gui.renderer.RenderableEntity;
import com.babachene.gui.renderer.RenderableLevel;
import com.babachene.gui.renderer.RenderableMap;
import com.babachene.gui.renderer.MapUpdateQueue;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RenderingTest extends GameState {
	
	private LevelRenderer lr;
	private Map map;
	
	public MapUpdateQueue q;
	
	private SpriteBatch batch;
	private ShapeRenderer shape;
	
	public static void main(String[] args) {
		/*
		 * Testing the moveing feaure of EntityRenderer.
		 */
		RenderingTest rt = new RenderingTest();
		
		LevelRenderer levelRenderer = new LevelRenderer(rt.level);
		
		rt.batch.begin();
		levelRenderer.render(rt.batch);
		rt.e.x ++;
		levelRenderer.render(rt.batch);
		rt.e.y ++;
		levelRenderer.render(rt.batch);
		rt.batch.end();
		
		System.out.println("Fin " + RenderingTest.class.getSimpleName());
	}
	
	Entity e ;
	
	// Here are the tests.
	public RenderingTest() {
		// INIT
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		viewport = getViewport();
		
		this.q = new MapUpdateQueue();
		
		map = new Map();
		
		e = new Entity();
		e.id = "baba";
		e.x=4;
		e.y= 6;
//		map.add(e);
//		e = new Entity();
//		e.x=-2;
//		e.y= 0;
//		e.id = 2;
//		map.add(e);
//		e = new Entity();
//		e.x=5;
//		e.y=5;
//		e.id = 2;
//		map.add(e);
//		e = new Entity();
//		e.x=5;
//		e.y=5;
//		e.id = 2;
//		map.add(e);
//		e = new Entity();
//		e.x=5;
//		e.y=5;
//		e.id = 1;
//		map.add(e);
//		e = new Entity();
//		e.x=2;
//		e.y=1;
//		map.add(e);
//		e = new Entity();
//		e.x=1;
//		e.y= 1;
//		e.id = 2;
//		
//		
		map.add(e);
//		map.add(new Entity());
		
		// ACTUAL TEST
		
		Level level = new Level("TEST TITLE", (byte)0, (byte)-1, map);
		this.level = level;
		
		//lr = new LevelRenderer(level);
		
		
	}
	public RenderableLevel level;
	/** for test. ShapeRenderer needs the matrix.   */
	public static Viewport viewport;
	
	private static Color color = new Color(.0625f,.0625f,.0625f,1f);
	@Override
	public void render() {
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shape.setProjectionMatrix(getViewport().getCamera().combined);
		shape.begin(ShapeType.Filled);
		shape.setColor(color);
		shape.rect(0, 0, BabaIsUs.WIDTH, BabaIsUs.HEIGHT);
		shape.end();
		/* VERY IMPORTANT: projection matrix */
		batch.setProjectionMatrix(getViewport().getCamera().combined);
		batch.begin();
		lr.render(batch);
		batch.end();
	}
	
	private Entity toRemove;
	public void startTestOnLevelState() {
		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean keyTyped(char character) {
				
				/*
				 * Testing the update queue.
				 */
				
				if (character == 'a') {
					Entity e = new Entity();
					e.x = 0;
					e.y = 4;
					q.pushCreatedEntity(e);
					
					e = new Entity();
					e.x = 0;
					e.y = 5;
					q.pushCreatedEntity(e);

					e = new Entity();
					e.id = "rock";
					e.x = 0;
					e.y = 5;
					q.pushCreatedEntity(e);
					
					toRemove = e;
					
					return true;
				}
				
				else if (character == 'z') {
					q.pushRemovedEntity(toRemove);
					return true;
				}
				
				else if (character == 'e') {
					q.pushRemovedGroup("This test is outdated !");
					return true;
				}
				
				return false;
			}
		});
		System.out.println("In this test mode, press a, z or e to alter the map.");
	}
	
	
	
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	
	private static class Level implements RenderableLevel {
		
		String t;
		RenderableMap map;
		
		public Level(String title, byte theme, byte music, RenderableMap map) {
			t = title;
			this.map = map;
		}
		
		@Override
		public RenderableMap getMap() {
			return map;
		}

		@Override
		public byte getThemeId() {
			return 0;
		}

		@Override
		public byte getMusicId() {
			return 0;
		}

		@Override
		public String getTitle() {
			return t;
		}
		
	}
	
	private static class Entity implements RenderableEntity {
		
		int x = 2;
		int y = 2;
		String id = "baba";
		
		@Override
		public String getId() {
			return id;
		}
		@Override
		public int getX() {
			return x;
		}
		@Override
		public int getY() {
			return y;
		}
		@Override
		public boolean hasDirection() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object getDirection() {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public boolean hasVaration() {
			// TODO Auto-generated method stub
			return false;
		}
		@Override
		public Object getVaration() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	private class Map extends ArrayList<RenderableEntity> implements RenderableMap {
		
		private static final long serialVersionUID = 360L;

		@Override
		public Iterator<RenderableEntity> iterator() {
			return super.iterator();
		}
		
		@Override
		public int getWidth() {
			return 10;
		}

		@Override
		public int getHeight() {
			return 10;
		}

		@Override
		public List<RenderableEntity> getEntity(int x, int y) {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public MapUpdateQueue getMapUpdateQueue() {
			return RenderingTest.this.q;
		}
		
	}
	
	
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	//////////////////////////////////////////////////////
	
	
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
