import java.util.LinkedList;
import java.lang.Math;
import java.awt.Color;

public abstract class Boid{

	protected Vecteur coord;
	protected Vecteur vitesse;
	protected Vecteur acceleration;
	protected double masse;
	protected double radian;//le radian
	protected double theta; //le sens d'orientation du boid
	protected double force_max;
	protected double vitesse_max;
	protected double voisinage;//la distance de voisinage
	protected double dist_separation;//la distance de voisinage
	
	protected Color couleur;//la frequence du pas
	
	public Boid(double x, double y, double masse, double radian,
			double force_max, double vitesse_max, 
			double voisinage, double dist_separation){
		this.coord = new Vecteur(x, y);
		this.vitesse = new Vecteur(alea(-1,1), alea(-1,1));
		this.acceleration = new Vecteur();
		this.masse=masse;
		this.radian=radian;
		this.force_max=force_max;
		this.vitesse_max=vitesse_max;
		this.voisinage=voisinage;
		this.dist_separation=dist_separation;
	}
	
	//Permettre de faire une recopie par valeur (en creant un nouveau objet)
	public abstract Boid copie();

	//Generer un double aleatoire 
	private static double alea(double min, double max)
	{
		double range = (max - min);     
		return (Math.random() * range) + min;
	}

	//L'ajout d'un type de force
	//a son acceleration actuelle du Boid
	// Acceleration est proportionnel a la force
	protected void appliquer_force(Vecteur force){
		this.acceleration.ajout(force);
	}

	//La regle de separation
	protected Vecteur separer(LinkedList<Boid> boids){
		Vecteur force_direction = new Vecteur();
		int compteur=0;

		for(Boid autre : boids){
			double dist_autre = this.coord.distance(autre.coord);
			if ((dist_autre>0) && (dist_autre<this.dist_separation)){
				Vecteur diff=Vecteur.soustraire(this.coord, 
						autre.coord);
				diff.normaliser();
				diff.div(dist_autre);
				force_direction.ajout(diff);
				compteur++;
			}
		}

		if(compteur>0){
			force_direction.div((double)(compteur));
		}

		if(force_direction.norme()>0){
			force_direction.normaliser();
			force_direction.mult(this.vitesse_max);
			force_direction.soustraire(this.vitesse);
			force_direction.limiter(this.force_max);			
		}

		return force_direction;
	}

	//La regle de cohesion
	public Vecteur coherer(LinkedList<Boid> boids){
		Vecteur somme = new Vecteur();
		int compteur=0;

		for(Boid autre : boids){
			double dist_autre = this.coord.distance(autre.coord);
			if ((dist_autre>0) && (dist_autre<this.voisinage)){
				somme.ajout(autre.coord);
				compteur++;
			}
		}

		if(compteur>0){
			somme.div((double)(compteur));
			return this.chercher(somme);
		}else{
			return new Vecteur();
		}
	}

	//La methode pour appliquer la force de direction a un cible
	public Vecteur chercher(Vecteur cible){
		Vecteur cible_final = Vecteur.soustraire(cible,this.coord);
		cible_final.normaliser();
		cible_final.mult(this.vitesse_max);

		Vecteur force_direction = Vecteur.soustraire(cible_final,
							     this.vitesse);
		force_direction.limiter(this.force_max);

		return force_direction;
	}

	//La regle d'alignement
	public Vecteur aligner(LinkedList<Boid> boids){
		Vecteur somme = new Vecteur();
		int compteur=0;

		for(Boid autre : boids){
			double dist_autre = this.coord.distance(autre.coord);
			if ((dist_autre>0) && (dist_autre<this.voisinage)){
				somme.ajout(autre.vitesse);
				compteur++;
			}
		}

		if(compteur>0){
			somme.div((double)(compteur));
			somme.normaliser();
			somme.mult(this.vitesse_max);
			Vecteur force_direction = Vecteur.soustraire(somme,
								     this.vitesse);
			force_direction.limiter(this.force_max);
			return force_direction;
		}else{
			return new Vecteur();
		}
	}

	//@@ABSTRAIT - L'agregation des boids
	public abstract void agregation(LinkedList<Boid> boids);

	//La mise a jour des coordonees d'un Boid
	public void maj_coord(){
		this.vitesse.ajout(this.acceleration);
		this.vitesse.limiter(this.vitesse_max);
		this.coord.ajout(this.vitesse);

		//Initialise a zero, son acceleration
		this.acceleration.egale(0,0);
	}

	//La verification des coordonnees d'un Boid
	//au niveau de la limite du mur
	public void limit_mur(int hauteur, int largeur){
		if (this.coord.getX()<-1*this.radian){
			this.coord.setX((double)(largeur)+this.radian);
		}
		if (this.coord.getY()<-1*this.radian){
			this.coord.setY((double)(hauteur)+this.radian);
		}
		if (this.coord.getX()>this.radian+(double)(largeur)){
			this.coord.setX(-1*this.radian);
		}
		if (this.coord.getY()>this.radian+(double)(hauteur)){
			this.coord.setY(-1*this.radian);
		}	
	}
	
	//Calcul du sens d'orientation du boid et
	//translation des cordonnees afin de produire un gui correcte
	public void maj_sens_translate(){
		this.theta = Math.atan((this.vitesse.getY())/(this.vitesse.getX()))
				+(Math.PI/2);	
	}

	//Calcul du pas suivant d'un boid 
	//en prenant en compte de ses boids au voisinage
	public void temp_suivant(LinkedList<Boid> boids,
				 int hauteur, int largeur){
		this.agregation(boids);
		this.maj_coord();
		this.limit_mur(hauteur,largeur);
		this.maj_sens_translate();
	}
	
	//Methodes Classiques Gets et Sets (qui sont necessaires)
	public Vecteur getCoord(){
		return this.coord;
	}
	
	public double getTheta(){
		return this.theta;
	}
	
	public Color getCouleur(){
		return this.couleur;
	}
}

