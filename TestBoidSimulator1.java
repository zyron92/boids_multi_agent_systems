//Le fichier de test de simulateur de Boids
import java.awt.Color;
import gui.GUISimulator;

public class TestBoidSimulator1 {
	public static void main(String[] args) {
		//On cree un simulateur
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK );

		//On cree un gestionnaire manager
		EventManager manager = new EventManager();

		//Creation d'un groupe de balles de differents types
		Boids pboids = new Boids();
		pboids.ajout_boid(new BoidG1(110, 120, 1, 3, 0.05, 3, 50, 25));
		pboids.ajout_boid(new BoidG1(108, 125, 1, 3, 0.05, 3, 50, 25));

		//Creation d'un premier evenement pour demarrer la recursivite
		manager.addEvent(new BoidSimulatorEvent(1, pboids, 500, 500));
		
		//Creation de simulator de boids en integrant le gestionnaire d'evenements
		//L'affichage de simulator
		BoidSimulator boid_simu = new BoidSimulator(
				gui, pboids, manager);
		gui.setSimulable(boid_simu);
	}
}
