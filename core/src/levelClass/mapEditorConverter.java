package levelClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class mapEditorConverter {
private int largeur;
private int hauteur;
private String[][] tab;
private LevelMap map;
	
public mapEditorConverter(int largeur, int hauteur) {
	super();
	this.largeur = largeur;
	this.hauteur = hauteur;
	this.tab = new String[hauteur][largeur];
	this.map = new LevelMap(hauteur, largeur);
}

public void open(String fileName) throws IOException {
		
		BufferedReader br = null;
		
		br = new BufferedReader(new FileReader(fileName));

		String ligne;
		

		
		for (int i = 0; i < hauteur; i++){
			
			ligne = br.readLine();
			
			for (int j = 0; j < largeur; j++){
				// On découpe la ligne en mots pour remplir 'map'
				int spacePos= ligne.indexOf(" ");
				String object= ligne.substring(0, spacePos);
				tab[i][j]=object;
				
				ligne=ligne.substring(spacePos+1);
				
			}
			
			
		}
		br.close();
		
	}
		
private void convertsData() {
	for (int i = 0; i < hauteur;i++) {
		for (int j =0; j< largeur; j++) {
			String typeOfEntity = tab[i][j];
			if (tab[i][j] != "empty")
				this.map.addEntity(i, j, typeOfEntity);
		}
	}
}
	
}

