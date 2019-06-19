package com.babachene.gui.renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.babachene.gui.BabaIsUs;
import com.babachene.gui.test.RenderingTest;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

class MapRenderer extends Renderer { // Not a public class.
	
	/** This table should not be used in the render() method. For performance reason.
	 * <br> It maps an entity id to the EntityGroupRenderer index in the list. */
	private TreeMap<Short, Integer> idtable;
	private List<EntityGroupRenderer> renderers;
	private RenderableMap map;
	private MapRenderingData mapRenderingData;
	
	// Graphic-related data
	Color backgroundColor;
	Texture backgroundTexture;
	
	public MapRenderer(RenderableMap map, byte theme) {
		if (map == null)
			throw new IllegalArgumentException("The RenderableMap object cannot bu null");
		
		/*
		 *  Logger ref
		 */
		final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		
		/*
		 *  resolves the theme
		 */
		switch (theme) {
		case BabaIsUs.DEFAULT_THEME : backgroundColor = new Color(0.1f, 0.1f, 0.1f, 1);
			backgroundTexture = BabaIsUs.assetManager.get(BabaIsUs.textures.THEME_DEFAULT, Texture.class);
			break;
		default:
			backgroundColor  = Color.BLACK;
		}
		
		/*
		 * Map data manager
		 */
		mapRenderingData = new MapRenderingData(map, BabaIsUs.WIDTH, BabaIsUs.HEIGHT);
		
		/*
		 *  Entity                                          Give a reasonable size.
		 */
		renderers = new ArrayList<EntityGroupRenderer>(map.getWidth() * map.getHeight() * 2);
		idtable = new TreeMap<Short, Integer>();
		
		for (RenderableEntity e : map) {
			
			if (e == null) {
				logger.log(Level.WARNING, "A RenderableMap contains a null RenderableEntity object. It shouldn't.");
				continue;
			}
			/*
			 * Current policy is one entity renderer per entity id.
			 */
			
			short id = e.getId();
			/*
			 *  Still in test phase.
			 */
			if ( ! idtable.containsKey(id)) {
				switch (id) {
				case 0: renderers.add(new EntityGroupRenderer(mapRenderingData,
						BabaIsUs.assetManager.get(BabaIsUs.textures.PEPE, Texture.class)));
					break;
				case 1: renderers.add(new EntityGroupRenderer(mapRenderingData,
						BabaIsUs.assetManager.get(BabaIsUs.textures.KERMIT, Texture.class)));
					break;
				case 2:
					break;
				default:
					logger.log(Level.WARNING, "RenderableMap: Unreconsized entity id");
				}
				idtable.put(id, renderers.size() - 1);
				logger.log(Level.FINE, "RenderableMap: created a new EntityGroupRenderer, entity id="+id );
			} else {
				renderers.get(idtable.get(id)).addRenderableEntity(e);
				logger.log(Level.FINE, "Added new RenderableEntity. id=" + id);
			}
			
			
			
		}
		
	}
	
	ShapeRenderer sr = new ShapeRenderer();
	@Override
	public void render(SpriteBatch batch) {
		
		// Map background rendering
		// FIXME shape renderer ? How ?
//		sr.setProjectionMatrix(RenderingTest.viewport.getCamera().combined);
//		sr.begin(ShapeType.Filled);
//		sr.setColor(backgroundColor);
//		sr.rect(mapRenderingData.mapX,
//				mapRenderingData.mapY,
//				mapRenderingData.mapWidth,
//				mapRenderingData.mapHeight);
//		sr.end();
		
		batch.draw(backgroundTexture,
				mapRenderingData.mapX,
				mapRenderingData.mapY,
				mapRenderingData.mapWidth,
				mapRenderingData.mapHeight);
		
		/*
		 * At this point, I think that in order to implement various map rendering (background mono-color,
		 * texture, shaded off color, ...) we will have to make many MapRenderer sub-classes.
		 */
		
		// Entity rendering
		for (EntityGroupRenderer r : renderers) {
			r.render(batch);
		}
		
	}
	
	
	
	private void fetchData(RenderableMap map) {
		this.map = map;
		this.mapRenderingData = new MapRenderingData(map, BabaIsUs.WIDTH, BabaIsUs.HEIGHT);
	}
	
}
