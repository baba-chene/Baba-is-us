package com.babachene.gui.renderer;

/**
 * Stores graphical data ( x,y and width,  height) of the graphical
 * representation of the map. <br> For entities:
 * stores data for binding the (x, y) map tile data to the graphical
 * (x, y, w, h) of a Sprite/Texture to draw.
 * <p>
 * The purpose is to compute the data once when the map is initialized.
 * 
 * @author jeremy
 *
 */
final class MapRenderingData { // Not a public class.
	
	public MapRenderingData(RenderableMap map, float screenWidth, float screenHeight) {
		
		// Screen dimension
		final float W = screenWidth,
		            H = screenHeight,
		            mapW = map.getWidth(),
		            mapH = map.getHeight();
		
		// The constraint is that tiles are squares. So we need to find which dimension is the limitating one.
		// solution : format.(x / y)
		/*
		float screenFormat = W / H;
		float mapFormat = mapW / mapH;
		-> (screenFormat > mapFormat)
		equivalent to the following condition in if statement.
		*/
		
		if (W * mapH > H * mapW) {
			// Map height = screen height.
			mapY = 0f;
			mapHeight = H;
			
			tileWidth =  H / mapH;
			tileHeight = tileWidth;
			
			mapWidth = tileWidth * mapW;
			mapX = (W - mapWidth) / 2f;
			
		} else {
			// Map width = screen width.
			mapX = 0f;
			mapWidth = W;
			
			tileWidth  = W / mapW;
			tileHeight = tileWidth;
			
			mapHeight = tileHeight * mapH;
			mapY = (H - mapHeight) / 2f;
		}
		
		
	}
	
	/** The width and the height of the graphical map. */
	public final float mapWidth, mapHeight;
	
	/** The (X,Y) coord of the bottom left of the graphical map. */
	public final float mapX, mapY;
	
	/** The dimensions of a graphical tile. */
	public final float tileWidth, tileHeight;
	
	public float xPosition(int xIndex) {
		return mapX + xIndex * tileWidth;
	}
	
	public float yPosition(int yIndex) {
		return mapY + yIndex * tileHeight;
	}
	
}

