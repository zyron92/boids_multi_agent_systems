public class BoidSimulatorEvent extends Event{
	private Boids pboids;
	private int gui_largeur;
	private int gui_hauteur;
	
	public BoidSimulatorEvent(long date, Boids pboids, int gui_largeur, int gui_hauteur){
		super(date);
		this.pboids = pboids;
		this.gui_largeur = gui_largeur;
		this.gui_hauteur = gui_hauteur;
	}
	
	public void execute(){
		this.pboids.temp_suivant(this.gui_largeur, this.gui_hauteur);
	}
	
}

