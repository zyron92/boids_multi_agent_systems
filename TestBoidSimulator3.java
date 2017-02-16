//Le fichier de test de simulateur de Boids avec un gestionnaire d'evenement
import java.awt.Color;
import gui.GUISimulator;

public class TestBoidSimulator3 {
	public static void main(String[] args) {
		//On cree un simulateur
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK );

		//On cree un gestionnaire manager
		EventManager manager = new EventManager();

		//Creation d'un groupe de balles de differents types
		Boids pboids1 = new Boids();
		BoidG1 b11 = new BoidG1(100, 100, 1, 3, 0.05, 3, 10, 10);
		BoidG1 b12 = new BoidG1(102, 102, 1, 3, 0.05, 3, 10, 10);
		pboids1.ajout_boid(b11);
		pboids1.ajout_boid(b12);

		//Creation d'un groupe de balles de differents types
		Boids pboids2 = new Boids();
		BoidG2 b21 = new BoidG2(200, 200, 1, 3, 0.05, 3, 10, 10);
		BoidG2 b22 = new BoidG2(205, 205, 1, 3, 0.05, 3, 10, 10);
		pboids2.ajout_boid(b21);
		pboids2.ajout_boid(b22);


		for(int i=1; i<1000; i++)
		{
			manager.addEvent(new BoidSimulatorEvent(i, pboids1, 500, 500));
		}
		for(int j=1; j<1000; j+=10)
		{
			manager.addEvent(new BoidSimulatorEvent(j, pboids2, 500, 500));
		}

		Boids pboids_tout = new Boids();
		pboids_tout.ajout_boid(b11);
		pboids_tout.ajout_boid(b12);
		pboids_tout.ajout_boid(b21);
		pboids_tout.ajout_boid(b22);

		//Creation de simulator de boids en integrant le gestionnaire d'evenements
		//L'affichage de simulator
		BoidSimulatorVer2 boid_simu = new BoidSimulatorVer2(
				gui, pboids_tout, manager);
		gui.setSimulable(boid_simu);
	}
}
