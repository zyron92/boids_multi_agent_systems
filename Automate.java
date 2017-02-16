public abstract class Automate implements Reinitialisable {
	protected int nbEtats; //le nb d'�tats possibles que peut prendre une case
	protected int nbLig;
	protected int nbCol;
	protected int [][] grilleDep;//la grille fournie au d�part de la simulation
	protected int [][] grilleCour;
	
	/**
	 * V�rification de la validit� de la grille 
	 * & initialisation des attributs*/
	protected Automate (int [][] grid, int nbEtats) {		
		nbLig=grid.length;
		nbCol=grid[0].length;
		if (nbLig<3 || nbCol <3) {
			throw new ArithmeticException("La grille doite �tre de taille "
					+ "3x3 au minimum");
		}
		grilleCour = new int[nbLig][nbCol];
		grilleDep = new int[nbLig][nbCol];
		this.nbEtats=nbEtats;
		for(int i=0; i<nbLig;i++) {
			if (grid[i].length != nbCol) {
				throw new ArithmeticException("Toutes les lignes doivent etre "
						+ "de meme taille");
												
			}
			for(int j=0; j<nbCol;j++) {
				if (grid[i][j]<0 || grid [i][j]>=nbEtats) {
					throw new ArithmeticException("L'element (" + i + "," +j +
							") a une couleur non autoris�e");
				}
				grilleDep[i][j]=grid[i][j];
				grilleCour[i][j]=grid[i][j];
			}
		}
		
	}
	
	public int getEtat (int lig, int col){
		return grilleCour[lig][col];
	}
	
	public int getNbEtats() {
		return nbEtats;
	}
	
	public int getNbLig() {
		return nbLig;
	}
	public int getNbCol() {
		return nbCol;
	}
	
	/**Parmi les voisins d'une case donnee, on compte 
	 * ceux qui sont dans un certain �tat pass� en param�tre */
	public int compterEtat (int [][] voisins, int etat) {		 
		int res=0;		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (i!=1 || j!=1) {					
					res+= (grilleCour[voisins[0][i]][voisins[1][j]]==etat) ?
							1 : 0;
				}
			}			
		}	
		return res;
	}
	
	abstract void majCase(int i, int j, int [][] voisins, int[][] grilleSuiv);
	
	/**	
	 *  calcul de la grille suivante
	 */

	public void calculNextGrid () {
		int [][] grilleSuiv = new int [nbLig][nbCol];
		int [][] voisins = new int [2][3]; /*La premi�re ligne de ce tableau 
		contient les indices des lignes des voisins de grilleCour[i][j]. 
		La seconde ligne contient les indices des colonnes des voisins*/

		for (int i=0; i<nbLig;i++) {
			/*On prend en compte la circularit� de la grille. Par exemple, 
			 pour la ligne d'indice i=0, la ligne voisine pr�c�dente sera 
			 d'indice nbLig-1 et non i-1. */

			voisins[0][0]= (i==0) ? nbLig-1 : i-1;  
			voisins[0][1]= i;
			voisins[0][2]= (i==nbLig-1) ? 0 : i+1;

			for (int j=0; j<nbCol;j++) {				
				voisins[1][0]= (j==0) ? nbCol-1 : j-1;
				voisins[1][1]=j;
				voisins[1][2]= (j==nbCol-1) ? 0 : j+1;
				
				majCase(i,j,voisins,grilleSuiv);

			}
		}
		grilleCour=grilleSuiv;

	}

	/**
	 * R�initialisation de la grille
	 */

	public void reInit() { 
		for(int i=0; i<nbLig;i++) {
			for(int j=0; j<nbCol;j++) {
				grilleCour[i][j]=grilleDep[i][j];
			}
		}
	}

}
