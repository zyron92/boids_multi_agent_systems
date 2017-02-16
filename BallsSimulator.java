import gui.GUISimulator;
import gui.Simulable;
import java.awt.Color;
import gui.Oval;

public class BallsSimulator implements Simulable {
	private GUISimulator gui;
	private Balls balles; //l'ensemble des balles se trouvent sur le simulateur
	private EventManager balles_manager; //le gestionnaire d'�v�nements
	final static String NOTRECOULEUR="#1f77b4";
	
	public BallsSimulator(GUISimulator gui, Balls bal, 
			EventManager balles_manager) {
		this.balles = bal;	
		this.gui = gui;
		this.balles_manager = balles_manager;
		updateGui();   //premier affichage
	}
	
	/**
	 * affichage de chaque balle
	 */
	private void updateGui() {
		gui.reset();
		for (int i=0; i < this.balles.number();i++) {
			if ( balles.getX(i) >  this.gui.getPanelWidth()|| 
					balles.getY(i) >  this.gui.getPanelHeight()){
				throw new ArithmeticException("La balle " + i +
						" a une coordonnee qui depasse le bord");
			}
			gui.addGraphicalElement(
					new Oval(balles.getX(i),balles.getY(i),
							Color.decode(NOTRECOULEUR), 
							Color.decode(NOTRECOULEUR), 10));
			
		}
	}
	
	@Override
	public void next() {
		this.balles_manager.next();
		this.balles_manager.addEvent(
				new BallsSimulatorEvent(this.balles_manager.getCurrentDate()+1,
						this.balles, this.gui.getPanelWidth(), 
						this.gui.getPanelHeight()));
		updateGui();
	}
	
	@Override
	public void restart() {
		this.balles.reInit();
		this.balles_manager.reInit();
		//pour redemarrer la recursivit�
		this.balles_manager.addEvent(new BallsSimulatorEvent(1, this.balles, 
				this.gui.getPanelWidth(), this.gui.getPanelHeight()));
		updateGui();
	}
	
}