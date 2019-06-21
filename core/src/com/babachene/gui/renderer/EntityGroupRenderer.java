package com.babachene.gui.renderer;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

import com.babachene.gui.BabaIsUs;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * It does noy function as the orther renderers. But rather
 * adds an abstract layer.
 * <p>
 * This class renders groups of entities which are similar enough to
 * only need to send basic data (such as x,y) to be drawn.
 * @author jeremy
 *
 */
class EntityGroupRenderer extends Renderer { // Not a public class.
	
	/** One texture to rule them all. */
	private Texture tex;
	
	private List<RenderableEntity> entities;
	
	private MapRenderingData mapData;
	
	public EntityGroupRenderer(MapRenderingData mapRenderingData, short entityId) {
		
		entities = new LinkedList<RenderableEntity>();
		if (mapRenderingData == null)
			throw new IllegalArgumentException("MapRenderingData oject cannot be null.");
		mapData = mapRenderingData;
		
		
		switch (entityId) { // TODO todo
		case 0: tex = BabaIsUs.assetManager.get(BabaIsUs.textures.PEPE, Texture.class);
			break;
		case 1: tex = BabaIsUs.assetManager.get(BabaIsUs.textures.KERMIT, Texture.class);
			break;
		case 2:
			break;
		case 3:
			break;
		default:
			throw new InvalidParameterException("Unreconsized entity id: " + entityId);
		}
		
	}
	
	@Override
	public void update() {
		throw new UnsupportedOperationException("EntityGroupRenderer does not upadte.");
	}
	
	@Override
	public void render(SpriteBatch batch) {
		
		for (RenderableEntity e : entities) {
			batch.draw(tex, mapData.xPosition(e.getX()),
			                mapData.yPosition(e.getY()),
			                mapData.tileWidth,
			                mapData.tileHeight);
		}
		
	}
	
	public void addRenderableEntity(RenderableEntity e) {
		if (e == null)
			throw new IllegalArgumentException("RenderableEntity object cannot be null.");
		entities.add(e);
	}
	
	public void removeRenderableEntity(RenderableEntity e) {
		entities.remove(e);
	}
	
	public void clear() {
		entities.clear();
	}
	
	public boolean isEmpty() {
		return entities.isEmpty();
	}
	
}
