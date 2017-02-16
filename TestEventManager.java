//Le fichier de test de la classe Event Manager (en annexe)
public class TestEventManager {
	public static void main(String[] args) throws InterruptedException{
		//On cree un simulator
		EventManager manager = new EventManager();

		//On poste un evenement [PING] tous les deux pas de temps
		for (int i=2; i<=10;  i+=2){
			manager.addEvent(new MessageEvent(i, " [PING]"));
		}
		
		//On poste un evenement [PONG] tous les trois pas de temps
		for (int i=3; i<=9;  i+=3){
			manager.addEvent(new MessageEvent(i, " [PONG]"));
		}
		
		while(!manager.isFinished()){
			manager.next();
			Thread.sleep(1000);
		}
		
		//Tester le redemarrage avec une autre valeur de sleep
		manager.restart();
		while(!manager.isFinished()){
			manager.next();
			Thread.sleep(100);
		}
	}
}
