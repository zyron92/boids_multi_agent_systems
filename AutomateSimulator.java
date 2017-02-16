import java.awt.Color;
import gui.Simulable;
import gui.GUISimulator;
import gui.Rectangle;

public abstract class AutomateSimulator implements Simulable{
	private GUISimulator gui;
	protected Automate automate;
	//Taille (en pixel)des cases 
	private int largeurCase;
	private int hauteurCase;
	private EventManager automate_manager; //le gestionnaire d'evenements

	protected AutomateSimulator(GUISimulator gui, Automate aut, EventManager automate_manager) {
		this.gui=gui;
		this.automate=aut;
		taillesCases();
		this.automate_manager = automate_manager;
		updateGui();//Premier affichage
	}
	
	private void taillesCases() {	
		/*
		 * Calcul de la hauteur et de la largeur de chaque case
		 * en fonction de la taille du GUI
		 */
		this.largeurCase=gui.getPanelWidth()/this.automate.getNbCol();
		this.hauteurCase=gui.getPanelHeight()/this.automate.getNbLig();
	}

	abstract Color determinerCoul(int lig, int col);	
	
	/**
	 * Affichage de chacune des cases
	 */
	private void updateGui() {		
		this.gui.reset();
		for (int i=0; i <this.automate.getNbLig();i++) {
			for (int j=0; j <this.automate.getNbCol();j++) {
				Color remplissage = determinerCoul(i,j);
				this.gui.addGraphicalElement(					 
						new Rectangle(j*this.largeurCase+this.largeurCase/2,
								i*this.hauteurCase+this.hauteurCase/2,
								Color.BLACK, remplissage,
								this.largeurCase, this.hauteurCase));
			}			
		}
	}

	@Override
	public void next() {
		this.automate_manager.next();
		this.automate_manager.addEvent(
				new AutomateSimulatorEvent(this.automate_manager.getCurrentDate()+1,
						this.automate));
		updateGui();
	}

	@Override
	public void restart() {
		this.automate.reInit();
		this.automate_manager.reInit();
		//pour redemarrer la recursivite
		this.automate_manager.addEvent(new AutomateSimulatorEvent(1, 
				this.automate));
		updateGui();
	}
}

