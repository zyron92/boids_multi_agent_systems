public class BallsSimulatorEvent extends Event{
	private Balls balles;
	private int gui_largeur;
	private int gui_hauteur;
	
	public BallsSimulatorEvent(long date, Balls balles, int gui_largeur, int gui_hauteur){
		super(date);
		this.balles = balles;
		this.gui_largeur = gui_largeur;
		this.gui_hauteur = gui_hauteur;
	}
	
	public void execute(){
		this.balles.translate(10, 10, this.gui_largeur, this.gui_hauteur);
	}
	
}

