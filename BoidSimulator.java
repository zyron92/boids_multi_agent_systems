import gui.Simulable;
import gui.GUISimulator;
import java.awt.Color;
import gui.Oval;;

public class BoidSimulator implements Simulable{

	/*
	 * chaque agent dispose d'un corps + d'une tete servant a 
	 * signifier la direction vers laquelle il pointe
	 */
	
	private GUISimulator gui;
	private Boids pboids;//l'ensemble des boids se trouvent sur le simulateur
	//proportion du diametre du corps du boid par rapport a la taille du gui
	private final double PROPBOID=0.05; 
	//proportion du diametre de la tete du boid par rapport a son corps
	private final double PROPTETE=0.20;
	private double corps;
	private double tete;
	private EventManager boids_manager; //le gestionnaire d'evenements

	public BoidSimulator(GUISimulator gui, Boids pboids,
			EventManager boids_manager) {
		this.gui=gui;
		this.pboids=pboids;
		corps=(PROPBOID * ((gui.getPanelWidth()>gui.getPanelWidth())?
				gui.getPanelWidth() : gui.getPanelHeight()));
		tete=corps*PROPTETE;
		this.boids_manager=boids_manager;
		updateGui();//Premier affichage
	}

	/**
	 * Affichage de chacune des cases
	 */
	private void updateGui() {		
		this.gui.reset();
		for (Boid pb : this.pboids.getListe_boids()) {
			this.gui.addGraphicalElement(new Oval((int)(pb.getCoord().getX()), 
					(int)(pb.getCoord().getY()),
					Color.BLACK,pb.getCouleur() ,(int)corps));
			this.gui.addGraphicalElement(new Oval((int)
					(Math.cos(pb.getTheta())*(corps+tete)/2+pb.getCoord().getX()),
					(int)(Math.sin(pb.getTheta())*(corps+tete)/2+pb.getCoord().getY()),
					Color.WHITE,pb.getCouleur() ,(int)tete));
		}
	}
	@Override
	public void next() {
		this.boids_manager.next();
		this.boids_manager.addEvent(
				new BoidSimulatorEvent(this.boids_manager.getCurrentDate()+1,
						this.pboids, this.gui.getPanelWidth(), 
						this.gui.getPanelHeight()));
		this.updateGui();
	}

	@Override
	public void restart() {
		this.pboids.reInit();
		this.boids_manager.reInit();
		//pour redemarrer la recursivite
		this.boids_manager.addEvent(new BoidSimulatorEvent(1, this.pboids, 
				this.gui.getPanelWidth(), this.gui.getPanelHeight()));
		this.updateGui();
	}

}