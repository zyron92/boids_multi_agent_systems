public class AutomateSimulatorEvent extends Event{
	private Automate automate;
	
	public AutomateSimulatorEvent(long date, Automate automate){
		super(date);
		this.automate = automate;
	}
	
	public void execute(){
		this.automate.calculNextGrid();
	}
	
}

