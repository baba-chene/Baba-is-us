package com.babachene.gui.renderer;

import com.babachene.gui.Rsrc;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

class AnimatedParticle extends Renderer { // Not a public class
	
	/** The collection containing this object, so it can
	 * manage its own destrcution. */
	private final Array<AnimatedParticle> masterCollection;
	
	
	private Animation<AtlasRegion> animation;
	private float stateTime;
	private float lifeTime;
	
	private float x, y, w, h,
					dx, dy; // values to add
	
	public AnimatedParticle(int xTile, int yTile, float theta, MapRenderingData mapData, Array<AnimatedParticle> masterCollection) {
		
		animation = new Animation<>(0.1f, Rsrc.textureAtlas.findRegions("particle/particle"), PlayMode.LOOP);
		stateTime = 0;
		lifeTime = animation.getAnimationDuration();
		lifeTime -= lifeTime / 5f; // Ajustement
		this.masterCollection = masterCollection;

		w = mapData.tileWidth / 4F;
		h = mapData.tileHeight / 4F;
		
		this.x = mapData.xPosition(xTile) + (mapData.tileWidth - w) / 2f;
		this.y = mapData.yPosition(yTile) + (mapData.tileHeight - h) / 2f;
		
		final float p = Rsrc.random.nextFloat() * 1.5f + 2.0f;
		dx = mapData.tileWidth * (float) Math.cos(theta) * p;
		dy = mapData.tileHeight * (float) Math.sin(theta) * p;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		
		final float delta = Gdx.graphics.getDeltaTime();
		stateTime += delta;
		x += dx * delta;
		y += dy * delta;
		
		batch.draw(animation.getKeyFrame(stateTime, false), x, y, w, h);
		
		/** Removes itself if the animation is finished. */
		if (stateTime > lifeTime) {
			masterCollection.removeValue(this, true);
		}
	}
	
	@Override
	public void update() {
		
	}
	
}
