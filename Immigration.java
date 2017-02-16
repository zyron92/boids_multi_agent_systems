public class Immigration extends Automate {

	public Immigration(int[][] grid, int nbEtats) {
		super(grid, nbEtats);
	}

	@Override
	/**
	 * Une case passe de l'etat k a l'etat (k+1) % nbEtats 
	 * quand elle a au moins 3 voisins 
	 * dont l'etat est (k+1) % nbEtats
	 */
	
	void majCase(int i, int j, int[][] voisins, int[][] grilleSuiv) {
	
		int etatSuiv=(grilleCour[i][j] + 1) % nbEtats;
		int nbVoisins=compterEtat(voisins,etatSuiv);
		if (nbVoisins >=3) {
			grilleSuiv[i][j]=etatSuiv;						
		}		
		else {
			grilleSuiv[i][j]=grilleCour[i][j];
		}
	}

}
