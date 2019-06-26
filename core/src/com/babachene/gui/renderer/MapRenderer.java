package com.babachene.gui.renderer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.babachene.gui.BabaIsUs;
import com.babachene.logic.data.LevelMap;
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
	
//	/** This table should not be used in the render() method. For performance reason.
//	 * <br> It maps an entity id to the EntityGroupRenderer index in the list. */
//	private TreeMap<Short, Integer> idtable;     // I give up on this id:index mapping.
	private final RenderableMap map;
	
	private List<EntityRenderer> renderers;
	/*
	 * This is a temporary solution for the neightboor-dependent renderers.
	 */
	private List<NeightboorRenderer> neightboor;
//	private RenderableMap map;
	private MapRenderingData mapRenderingData;
	
	private MapUpdateQueue updateQueue;
	
	// Graphic-related data
//	private Color backgroundColor;
	private Texture backgroundTexture;
	
	//////////////////////////////////////////////
	
	public MapRenderer(RenderableMap map, byte theme) {
		if (map == null)
			throw new IllegalArgumentException("The RenderableMap object cannot bu null");
		this.map = map;
		
		if (map.getMapUpdateQueue() == null) {
			logger.log(Level.WARNING, "The MapUpdateQueue is null: MapRenderer will not change its entities structure.");
			updateQueue = new MapUpdateQueue();
		} else
			updateQueue = map.getMapUpdateQueue();
		
		/*
		 *  set background
		 */
		backgroundTexture = new Texture("textures/backgrounds/fond2.jpg");
		
		
		/*
		 * Map data manager
		 */
		mapRenderingData = new MapRenderingData(map, BabaIsUs.WIDTH, BabaIsUs.HEIGHT);
		
		/*
		 *  Entity                                          Give a reasonable size.
		 */
		renderers = new ArrayList<EntityRenderer>(map.getWidth() * map.getHeight() * 2);
		neightboor = new LinkedList<>();
//		for (RenderableEntity e : map) {
//			
//			addEntity(e); // FIXME Well, I don't know how it should best work. But in current testing, the map sends all info through the queue.
//			
//		}
		
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
		 * Current policy is one entity renderer per entity.
		 */
		
		String id = e.getId();
		
		if (id.isEmpty()) {
			logger.fine("An entity with ID \"\" has dropped and will not be renderer.");
			return;
		}
		
		/*
		 * This will require changes if an EntityGroup class comes up. And Directional renderer already did.
		 */
		try {
			
			if (e.getId().equals("baba") || e.getId().equals("keke"))
				renderers.add(new DirectionalEntityRenderer(e, mapRenderingData));
			else if (e.getId().equals("water") && map instanceof LevelMap) {
				NeightboorRenderer r = new WaterRenderer(e, mapRenderingData, (LevelMap) map);
				renderers.add(r);
				neightboor.add(r);
			} else if (e.getId().equals("wall") && map instanceof LevelMap) {
				NeightboorRenderer r = new WallRenderer(e, mapRenderingData, (LevelMap) map);
				renderers.add(r);
				neightboor.add(r);
			} else if (e.getId().equals("lava") && map instanceof LevelMap) {
				NeightboorRenderer r = new LavaRenderer(e, mapRenderingData, (LevelMap) map);
				renderers.add(r);
				neightboor.add(r);
			} else
				renderers.add(new EntityRenderer(e, mapRenderingData));
			
		} catch (MissingResourceException ex) {
			ex.printStackTrace();
		}
		logger.log(Level.FINE, "Created a new EntityRenderer, entity id="+id );
		
		updateNeightboorRenderers();
		
	}
	
	public final void removeEntity(RenderableEntity e) {
		/* Cases:
		 * - entity does not exist.
		 * - entity exists.
		 * That's simpler with no grouped entities.
		 * But here thay are, if the entity is a neightboor entity, we need to update them all.
		 */
		
		if (e == null)
			return;
		
		boolean found = false;
		for (int i = 0; i < renderers.size(); i++) {
			if (renderers.get(i).getRenderableEntity() == e) {
				renderers.remove(i);
				found = true;
				break;
			}
		}
		
		if (e instanceof NeightboorRenderer) {
			if (found) {
				neightboor.remove( (NeightboorRenderer) e);
				// Don't forget to update the other renderers.
				updateNeightboorRenderers();
			}
		}
		
		if ( ! found)
			logger.log(Level.INFO, "Attempted to remove a RenderableEntity which was not found. id=" + e.getId());
		
	}
	
	public final void removeAllEntitiesById(String id) {
		
		if (id == null)
			return;
		
		// temporary solution
		ArrayList<EntityRenderer> l=  new ArrayList<>();
		boolean found = false;
		for (int i = 0; i < renderers.size(); i++) {
			if (renderers.get(i).getRenderableEntity().getId().equals(id)) {
				l.add(renderers.get(i));//renderers.remove(i--);
				found = true;
			}
			
		}
		
		renderers.removeAll(l);
		neightboor.removeAll(l);
		updateNeightboorRenderers();
		
		if ( ! found)
			logger.log(Level.INFO, "Attempted to remove some RenderableEntities by id but no was found.");
		else
			logger.log(Level.FINE, "Removed all RenderableEntity of id " + id);
	}
	
	private final void updateNeightboorRenderers() {
		for (NeightboorRenderer e : neightboor)
			e.update();
	}
	
}
