import java.awt.Color;
import gui.GUISimulator;

public abstract class MultiColorSimulator extends AutomateSimulator {

	protected MultiColorSimulator(GUISimulator gui, Automate aut, 
			EventManager automate_manager) {
		super(gui, aut, automate_manager);
	}

	@Override

	/**
	 * Calcul de la couleur (niveau de gris) associee a un etat.
	 *  Plus l'etat correspond a un entier faible, plus la
	 *  couleur se rapprochera du blanc. Moins il y aura
	 *  d'etats possibles, plus l'ecart de couleur entre deux 
	 *  etats proches sera important.
	 */
	
	public Color determinerCoul(int lig, int col) {
		
		
		int nbEtats=super.automate.getNbEtats();
		//s'il y a un seul etat possible, on retourne la couleur blanche
		if (nbEtats==1) 
			return Color.WHITE;

		float intensiteGris=(float)
				(nbEtats-1-automate.getEtat(lig,col))/(nbEtats-1);
		return new Color(intensiteGris,intensiteGris,intensiteGris);		
	}

}
