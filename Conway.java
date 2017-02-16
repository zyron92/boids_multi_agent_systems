public class Conway extends Automate{
	final static int NBETATS=2;
	
	public Conway (int [][] grid) {
		super(grid,NBETATS);		
	}

	@Override
	/**
	 * On compte les voisins vivants. S'ils sont au nombre de 3,
	 * la case consideree devient (ou reste) vivante. Si la case 
	 * est deja vivante, elle le restera si elle a 2 voisins vivants.
	 * Les autres cases deviennent (ou restent) mortes
	 */
	 
	void majCase (int i, int j, int [][] voisins, int[][] grilleSuiv) {		
		
		int voisinsVivants=compterEtat(voisins,1);
		if (voisinsVivants ==3) {
			grilleSuiv[i][j]=1;						
		}
		else if (grilleCour[i][j]==1 && voisinsVivants==2) {
			grilleSuiv[i][j]=1;
		}
		else {
			grilleSuiv[i][j]=0;
		}
		
	}	

}
