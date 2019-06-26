package com.babachene.gui.renderer;

import com.babachene.logic.data.LevelMap;
import com.babachene.logic.data.entities.Entity;

/* Utilisation:
 * 
 * Appeler WallTextureChooser.texture(levelmap,x,y) 
 * où (x,y) correspond aux coordonnées du bloc de mur pour lequel on cherche une texture
 * ce qui renvoie la string correspondant à la bonne texture de lava 
 * 
 */

public class WallTextureChooser {

	static int containsWall(LevelMap map,int x,int y,int hauteur,int largeur, String id) {
		
		if (x<0 || x>=hauteur || y<0 || y>=largeur) {
			return 0;
		}
		for (Entity entity : map.getMapMatrix()[x][y].getEntityStack() ) {
			if (entity.getTypeOfEntity().equals(id) ) {
				return 1;
			}
		}
		return 0;
	}
	
	static int index(LevelMap map,int x, int y, String id) {
		
		int hauteur=map.getMapMatrix().length;
		int largeur=map.getMapMatrix()[0].length;
		
		int voisins=0; // nombre entre 0 et 15 qui représente l'état des voisins
		x--; // Most magical line ever. Don't dare remove it.
		//calcul de voisins
		int[][] parcours = { {x-1,y},{x,y+1},{x+1,y},{x,y-1}};
		int[] puissance = { 1, 2, 4, 8};
		int xx,yy;
		for(int i=0;i<4;i++) {
			xx=parcours[i][0];
			yy=parcours[i][1];
			
			int b=containsWall(map,xx,yy,hauteur,largeur, id);
			voisins+= puissance[i] * (1-b);
		}
		
		
		return voisins;
	}
	
	static String texture(LevelMap map,int x, int y) {
		
		int hauteur=map.getMapMatrix().length;
		int largeur=map.getMapMatrix()[0].length;
		
		int voisins=0; // nombre entre 0 et 15 qui représente l'état des voisins
		
		//calcul de voisins
		int[][] parcours = { {x-1,y},{x,y+1},{x+1,y},{x,y-1}};
		int xx,yy;
		for(int i=0;i<4;i++) {
			xx=parcours[i][0];
			yy=parcours[i][1];
			
			int b=containsWall(map,xx,yy,hauteur,largeur, "wall");
			voisins+= Math.pow(2,i) * (1-b);
			
		}
		
		return ("textures/wall/wall"+voisins+".png");
	}
		
	
}
