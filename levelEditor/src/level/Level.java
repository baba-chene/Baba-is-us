package level;

public class Level {

	public static final int hauteur = 20;
	public static final int largeur = 30;
	
	public static final String empty = "empty";
	public static final String baba = "baba";
	public static final String wall = "wall";
	public static final String rock = "rock";
	public static final String water = "water";
	public static final String lava = "lava";
	
	public static final String textempty = "textempty";
	public static final String textbaba = "textbaba";
	public static final String textwall = "textwall";
	public static final String textrock = "textrock";
	public static final String textwater = "textwater";
	public static final String textlava = "textlava";
	public static final String textis = "textis";
	public static final String textwin = "textwin";
	public static final String textdefeat = "textdefeat";
	public static final String textblock = "textblock";
	
	private String[][] map;	
	
	public Level () {
		map=new String[hauteur][largeur];
		for(int i=0;i<hauteur;i++) {
			for(int j=0;j<largeur;j++) {
				map[i][j]=empty;
			}
		}
	}

	
}
