import java.awt.Color;
import gui.GUISimulator;

public class TestAutomateSimulateur {
	public static void main(String[] args) {
		/*Cette classe sert a tester nos simulateurs d'automates.
		 * 
		 * Choisir un test (1 - 3)
		 */
		int choix = 3;
		
		//On cree un simulateur
		GUISimulator window;
		
		//On cree un simulateur d'automate qui sera initialise selon son type
		AutomateSimulator autoSimu;
		
		//On cree un gestionnaire manager
		EventManager manager = new EventManager();
		
		switch (choix) {
		case 1:
			//Test sur Conway
			window = new GUISimulator(500, 500, Color.WHITE);
			int [][] grille1= {
					{0,0,0,0,0},
					{0,1,1,0,0},
					{0,1,0,0,0},
					{0,0,1,0,0},
					{0,0,0,0,1}
			};
			Automate auto1 = new Conway(grille1);
			
			//Creation d'un premier evenement pour demarrer la recursivite
			manager.addEvent(new AutomateSimulatorEvent(1, auto1));

			autoSimu = new ConwaySimulator(window, auto1, manager);
			break;
		case 2: 
			//Test sur Immigration
			window = new GUISimulator(500, 500, Color.WHITE);
			int [][] grille2= {
					{0,2,2},
					{0,1,1},
					{3,1,0}
			};

			Automate auto2 = new Immigration(grille2, 4);
			
			//Creation d'un premier evenement pour demarrer la recursivite
			manager.addEvent(new AutomateSimulatorEvent(1, auto2));

			autoSimu = new ImmigrationSimulator(window, auto2, manager);
			break;
		default:
			//Test sur Schelling
			window = new GUISimulator(500, 500, Color.WHITE);
			//Les cases a 0 representent les maisons vides (pas une couleur)
			int [][] grille3= {
					{0,2,0,2,0,2,3,1,0,2,1,1,2},
					{0,1,2,1,3,1,2,3,3,0,3,0,0},
					{2,1,1,3,2,3,0,1,2,1,3,2,1},
					{1,1,2,2,2,3,0,1,2,1,1,0,3},
					{0,2,0,2,0,2,3,1,0,2,1,1,2},
					{3,3,2,1,3,1,0,1,2,3,1,1,1},
					{2,1,0,1,2,1,3,1,2,3,2,2,3},				
					{0,2,0,2,0,2,3,1,0,2,1,1,2},
					{2,1,3,1,2,1,0,1,2,3,2,2,3},
					{3,1,0,2,2,1,0,1,3,3,2,2,1},
					{2,1,0,1,3,1,0,1,2,3,3,2,2}
			};

			Automate auto3 = new Schelling(grille3, 3, 3);
			
			//Creation d'un premier evenement pour demarrer la recursivite
			manager.addEvent(new AutomateSimulatorEvent(1, auto3));

			autoSimu = new SchellingSimulator(window, auto3, manager);
			break;
		}
		
		//Lancement de la simulation
		window.setSimulable(autoSimu);
	}
}
