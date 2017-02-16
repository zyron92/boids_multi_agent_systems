import java.util.*;

public class Schelling extends Automate {
	private int seuil;	
	private Queue <CoupleIndices> queue=new LinkedList <CoupleIndices>();
	private Queue <CoupleIndices> queueDebut=new LinkedList <CoupleIndices>();
	
	public Schelling(int[][] grid, int nbEtats, int seuil) {
		super(grid, nbEtats+1);
		this.seuil=seuil;
		/*On range les maisons vacantes (cases ayant pour valeur 0)
		 *  dans la file queue et dans queueDebut */
		for (int i=0;i<nbLig;i++) {
			for (int j=0;j<nbCol;j++) {
				if (grilleCour[i][j]==0) {
					queue.add(new CoupleIndices(i,j));
					queueDebut.add(new CoupleIndices(i,j));
				}
			}
		}

		if (queue.size()==0) {
			throw new ArithmeticException("Il faut au moins une "
														+ "maison vacante");
		}
	}

	private class CoupleIndices {
		/*Cette classe represente des couples 
		 * d'indices de la grille */
		private int lig;
		private int col;

		public CoupleIndices(int lig, int col) {
			this.lig=lig;
			this.col=col;
		}
		public int getLig() {
			return lig;
		}		
		public int getCol () {
			return col;
		}				
	}

	@Override
	/**
	 * Une famille demenage si elle plus de seuil
	 * voisins de couleur (ou etats) differentes de la sienne
	 * Elle emmenege alors dans une maison vacante
	 */
	void majCase(int i, int j, int[][] voisins, int[][] grilleSuiv) {

		if (grilleCour[i][j]==0)
			return; //La maison est vacante : on ne peut rien en deuire

		//voisinsDiff = 8 - voisins de la meme couleur - maisons voisines vides
		int voisinsDiff = 8 - compterEtat(voisins,grilleCour[i][j]) 
			- compterEtat(voisins,0);
		if (voisinsDiff>seuil){
			//On sort de la pile la nouvelle adresse de la famille
			CoupleIndices nouvAdr=queue.remove(); 			
			//La famille demenage
			grilleSuiv[nouvAdr.getLig()][nouvAdr.getCol()]=grilleCour[i][j]; 
			//La maison liberee devient vacante
			queue.add(new CoupleIndices(i,j)); 
		}
		else { // Cas ou la famille ne demenage pas
			grilleSuiv[i][j]=grilleCour[i][j];
		}
	}

	public void reInit() {
		super.reInit();
		//Reinitialisation de la file
		queue.clear();
		Iterator<CoupleIndices> it = queueDebut.iterator();
		while (it.hasNext()) {
			queue.add(it.next());
		}
	}
}
