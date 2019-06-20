package com.babachene.gui.renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.babachene.gui.BabaIsUs;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Renders a map on the screen and delegates the rendering of
 * the entities.<p>
 * This class has become a mess.
 * @author jeremy
 *
 */
class MapRenderer extends Renderer { // Not a public class.
	
	/** logger ref */
	final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	/** This table should not be used in the render() method. For performance reason.
	 * <br> It maps an entity id to the EntityGroupRenderer index in the list. */
	private TreeMap<Short, Integer> idtable;
	private List<EntityGroupRenderer> renderers;
	private RenderableMap map;
	private MapRenderingData mapRenderingData;
	
	private MapUpdateQueue updateQueue;
	
	// Graphic-related data
//	private Color backgroundColor;
	private Texture backgroundTexture;
	
	//////////////////////////////////////////////
	
	public MapRenderer(RenderableMap map, byte theme) {
		if (map == null)
			throw new IllegalArgumentException("The RenderableMap object cannot bu null");
		
		if (map.getMapUpdateQueue() == null) {
			logger.log(Level.WARNING, "The MapUpdateQueue is null: MaRenderer will not change its entities structure?");
			updateQueue = new MapUpdateQueue();
		} else
			updateQueue = map.getMapUpdateQueue();
		
		/*
		 *  resolves the theme
		 */
		switch (theme) {
		case BabaIsUs.DEFAULT_THEME : //backgroundColor = new Color(0.1f, 0.1f, 0.1f, 1);
			backgroundTexture = BabaIsUs.assetManager.get(BabaIsUs.textures.THEME_DEFAULT, Texture.class);
			break;
		default:
//			backgroundColor  = Color.BLACK;
			break;
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
			
			addEntity(e);
			
		}
		
	}
	
	//////////////////// METHODS /////////////////////
	
	public void update() {
		
		if (updateQueue.requiresUpdate()) {
			while (updateQueue.hasRemovedGroup())
				removeAllEntitiesById(updateQueue.popRemovedGroup());
			
			while (updateQueue.hasRemovedEntity())
				removeEntity(updateQueue.popRemovedEntity());
			
			while (updateQueue.hasCreatedEntity()) {
				addEntity(updateQueue.popCreatedEntity());
			}
		}
		
	}
	
	// temp
//	ShapeRenderer sr = new ShapeRenderer();
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
		
		// Entity rendering  // As this is an ArrayList, we reach here good performance.
		int size = renderers.size();
		for (int i = 0; i < size; i++) {
			renderers.get(i).render(batch);
		}
		
	}
	
	
	
//	private void fetchData(RenderableMap map) {
//		this.map = map;
//		this.mapRenderingData = new MapRenderingData(map, BabaIsUs.WIDTH, BabaIsUs.HEIGHT);
//	}
	
	public final void addEntity(RenderableEntity e) {
		
		if (e == null) {
			logger.log(Level.WARNING, "A RenderableMap contains a null RenderableEntity object. It shouldn't.");
			return;
		}
		
		/*
		 * Current policy is one entity renderer per entity id.
		 */
		
		short id = e.getId();
		/*
		 *  Still in test phase.
		 */
		if ( ! idtable.containsKey(id)) {
			
			try {
				renderers.add(new EntityGroupRenderer(mapRenderingData, id));
			} catch (IllegalArgumentException ex) {
				logger.log(Level.INFO, "Failed to create a new EntityGroupRenderer.", ex);
			}
			
			// Here we update the id table.
			idtable.put(id, renderers.size() - 1);
			logger.log(Level.FINE, "Created a new EntityGroupRenderer, entity id="+id );
		}
		
		
		renderers.get(idtable.get(id)).addRenderableEntity(e);
		
	}
	
	public final void removeEntity(RenderableEntity e) {
		/* Cases:
		 * - entity does not exist.
		 * - entity exists and its group contains other ones.
		 * - entity exists and it the only entity of the group (which shall be removed).
		 */
		
		if (e == null)
			return;
		
		short id = e.getId();
		
		if ( ! idtable.containsKey(id)) {
			logger.log(Level.INFO, "Attempted to remove a RenderableEntity which was not found.");
			return;
		}
		
		EntityGroupRenderer egr = renderers.get(idtable.get(id));
		egr.removeRenderableEntity(e);
		
		// No remove of the entity group renderer if it's empty.
		
	}
	
	public final void removeAllEntitiesById(short id) {
		
		if ( ! idtable.containsKey(id)) {
			logger.log(Level.INFO, "Failed attempt to remove all RenderableEntity of id " + id);
			return;
		}
		
		// We don't remove the group renderer.
		EntityGroupRenderer egr = renderers.get(idtable.get(id));
		egr.clear();
		
		logger.log(Level.FINE, "Removed all RenderableEntity of id " + id);
	}
	
}
