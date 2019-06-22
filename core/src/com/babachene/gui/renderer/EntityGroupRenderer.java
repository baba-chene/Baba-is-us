package com.babachene.gui.renderer;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;

import com.babachene.Baba;
import com.babachene.gui.BabaIsUs;
import com.babachene.gui.Rsrc;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	private TextureRegion tex;
	
	private List<RenderableEntity> entities;
	
	private MapRenderingData mapData;
	
	public EntityGroupRenderer(MapRenderingData mapRenderingData, short entityId) {
		
		entities = new LinkedList<RenderableEntity>();
		if (mapRenderingData == null)
			throw new IllegalArgumentException("MapRenderingData oject cannot be null.");
		mapData = mapRenderingData;
		
		
		switch (entityId) { // TODO todo
		
		/*
		 * Text blocks
		 */
		case Baba.TXT_PUSH: tex = Rsrc.TXT_PUSH_TEXTURE;
			break;
		case Baba.TXT_ROCK: tex = Rsrc.TXT_ROCK_TEXTURE;
			break;
		case Baba.TXT_BABA: tex = Rsrc.TXT_BABA_TEXTURE;
			break;
		case Baba.TXT_IS: tex = Rsrc.TXT_IS_TEXTURE;
			break;
		case Baba.TXT_WIN: tex = Rsrc.TXT_WIN_TEXTURE;
			break;
		case Baba.TXT_YOU: tex = Rsrc.TXT_YOU_TEXTURE;
			break;
		case Baba.TXT_SINK: tex = Rsrc.TXT_SINK_TEXTURE;
			break;
		
		/*
		 * Non text blocks.
		 */
		case 1: tex = new TextureRegion(BabaIsUs.assetManager.get(BabaIsUs.textures.KERMIT, Texture.class));
			break;
		case Baba.ROCK: tex = Rsrc.ROCK_TEXTURE;
			break;
		default:
			throw new InvalidParameterException("Unreconsized entity id: " + entityId);
		}
		if (tex == null)
			throw new RuntimeException("Texture of entity " + entityId + " is missing from Rsrc.");
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
