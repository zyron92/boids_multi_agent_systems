import java.awt.Color;
import gui.GUISimulator;

public class ConwaySimulator extends AutomateSimulator {
	final static String NOTRECOULEUR="#1f77b4";

	public ConwaySimulator(GUISimulator gui, Automate auto, 
			EventManager automate_manager) {
		super(gui, auto, automate_manager);
	}
	
	@Override
	/**
	 * renvoie NOTRECOULEUR si l'etat est 1, du blanc sinon
	 */
	public Color determinerCoul(int lig, int col) {
		
		if (automate.getEtat(lig,col)==1) {
			return Color.decode(NOTRECOULEUR);
		}
		else {
			return Color.WHITE;
		}
	}
	
	
}

