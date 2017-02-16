import java.util.LinkedList;

public class Boids {
	
	private LinkedList<Boid> liste_boids;
	private LinkedList<Boid> liste_boids_init; //la liste qui stocke les valeurs initiales 
											 //des boids
	
	//Initialisation des deux listes avec des nouvelles listes vides
	public Boids(){
		this.liste_boids=new LinkedList<Boid>();
		this.liste_boids_init=new LinkedList<Boid>();
	}
	
	//Initialisation des deux listes avec des listes passees en parametre
	public Boids(LinkedList<Boid> liste_boids, LinkedList<Boid> liste_boids_init){
		this.liste_boids=liste_boids;
		this.liste_boids_init=liste_boids_init;
	}
	
	//Calcul de le pas suivant des boids
	public void temp_suivant(int hauteur, int largeur){
		for(Boid pb : this.liste_boids){
			pb.temp_suivant(this.liste_boids, hauteur, largeur);
		}
	}
	
	//L'ajout d'un boid dans les listes
	public void ajout_boid(Boid pb){
		this.liste_boids.add(pb);
		this.liste_boids_init.add(pb.copie());
	}

	//Reinitialiser la liste des boids a l'aide de la deuxieme liste
	public void reInit(){
		
		for(int i=0; i<this.liste_boids.size(); i++){
			this.liste_boids.set(i, (this.liste_boids_init.get(i).copie()));
		}
	}
	
	//Methodes Classiques Gets et Sets (qui sont necessaires)
	public LinkedList<Boid> getListe_boids(){
		return this.liste_boids;
	}
	
}
