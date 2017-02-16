import java.util.LinkedList;

public class EventManager {
	private long currentDate;
	private int compteur;
	public LinkedList<Event> liste_events; //La liste des evenements

	public EventManager(){
		this.currentDate=0;
		this.compteur=0;
		this.liste_events=new LinkedList<Event>(); //Initialisation de la liste
	}

	public long getCurrentDate(){
		return this.currentDate;
	}

	//L'ajout d'un evenement
	public void addEvent(Event e){
		this.liste_events.add(e);
	}

	//L'execution du prochain evenement
	public void next(){
		//Incrementation de la date actuelle
		this.currentDate++;

		//Chercher et afficher tous les evenements a cette date actuelle
		System.out.println("Next... Current Date : "+this.currentDate);
		for(int i=0; i<this.liste_events.size(); i++){
			if (this.liste_events.get(i).getDate()==currentDate){
				this.liste_events.get(i).execute();
				this.compteur++;
			}
		}
	}

	//Tester si on a execute tous les evenements
	public boolean isFinished(){
		//Tester si le nombre d'evenements egale a la taille de la liste
		if (this.compteur==(long)(this.liste_events.size())){
			return true;
		}
		return false;
	}

	//Le redemarrage du compteur de temps
	//pour relire les evenements deja crees
	public void restart(){
		this.currentDate=0;
		this.compteur=0;
	}
	
	//Supression de la liste des evenements
	public void reInit(){
		this.liste_events.clear();
		this.restart();
	}

}
