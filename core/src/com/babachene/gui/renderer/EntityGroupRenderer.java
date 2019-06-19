package com.babachene.gui.renderer;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * It does noy function as the orther renderers. But rather
 * adds an abstract layer.
 * @author jeremy
 *
 */
class EntityGroupRenderer extends Renderer { // Not a public class.
	
	/** One texture to rule them all. */
	private Texture tex;
	
	private List<RenderableEntity> entities;
	
	private MapRenderingData mapData;
	
	public EntityGroupRenderer(MapRenderingData mapRenderingData, Texture tex) {
		
		entities = new LinkedList<RenderableEntity>();
		if (tex == null)
			throw new IllegalArgumentException("Texture is null.");
		if (mapRenderingData == null)
			throw new IllegalArgumentException("MapRenderingData oject cannot be null.");
		this.tex = tex;
		mapData = mapRenderingData;
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
	
}
