//Le fichier de test de Simulateur Balls avec un gestionnaire d'evenements
import java.awt.Color;
import java.awt.Point;

import gui.GUISimulator;

public class TestBallsSimulator {
	public static void main(String[] args) throws InterruptedException{
		//On cree un simulateur
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK );

		//On cree un gestionnaire manager
		EventManager manager = new EventManager();

		//Creation d'un premier groupe de balles
		Point [] tab = {new Point(250, 150), new Point(450, 150), 
				new Point(50, 150)};
		Balls bal = new Balls(3, tab); //2eme attribut

		//Creation d'un premier evenement pour demarrer la recursivite
		manager.addEvent(new BallsSimulatorEvent(1, bal, 500, 500));

		//Creation de simulator de balles en integrant le gestionnaire d'evenements
		//L'affichage de simulator
		BallsSimulator bs = new BallsSimulator(gui, bal, manager);
		gui.setSimulable(bs);
	}
}
