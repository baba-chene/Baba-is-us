package com.babachene.gui.renderer;

import com.babachene.logic.data.Entity;
import com.babachene.logic.data.EntityLava;
import com.babachene.logic.data.LevelMap;

/* Utilisation:
 * 
 * Appeller LavaTextureChooser.texture(levelmap,x,y) 
 * ou (x,y) correspond aux coordonnées du bloc de water pour lequel on cherche une texture
 * ce qui renvoie la string correspondant à la bonne texture de lava 
 * 
 */

public class LavaTextureChooser {

	static int containsLava(LevelMap map,int x,int y,int hauteur,int largeur) {
		
		if (x<0 || x>=hauteur || y<0 || y>=largeur) {
			return 0;
		}
		for (Entity entity:map.getMapMatrix()[x][y].getEntityStack() ) {
			if (entity instanceof EntityLava) {
				return 1;
			}
		}
		return 0;
	}
	
	static String texture(LevelMap map,int x, int y) {
		
		int hauteur=map.getMapMatrix().length;
		int largeur=map.getMapMatrix()[0].length;
		
		int voisins=0; // nombre entre 0 et 15 qui représente l'état des voisins
		
		//calcul de voisins
		int[][] parcours = { {-1,0},{0,1},{1,0},{0,-1}};
		int xx,yy;
		for(int i=0;i<4;i++) {
			xx=parcours[i][0];
			yy=parcours[i][1];
			
			int b=containsLava(map,xx,yy,hauteur,largeur);
			voisins+= Math.pow(2,i) * (1-b);
			
		}
		
		return ("lava/lava"+voisins+".png");
	}
		
	
}
