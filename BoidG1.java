import java.util.LinkedList;
import java.awt.Color;

public class BoidG1 extends Boid{

	public BoidG1(double x, double y, double masse, double radian,
			double force_max, double vitesse_max, 
			double voisinage, double dist_separation){
		super(x, y, masse, radian, force_max, vitesse_max, 
				voisinage, dist_separation);
		this.couleur=Color.BLUE;
	}
	
	//renvoyer un type BoidG1 de lui-meme
	public Boid copie(){
		return new BoidG1(this.coord.getX(), this.coord.getY(), this.masse, 
				this.radian, this.force_max, this.vitesse_max, 
				this.voisinage, this.dist_separation);
	}

	@Override
	//L'agregation des boids
	public void agregation(LinkedList<Boid> boids){
		Vecteur partie_separation = super.separer(boids);
		Vecteur partie_alignement = super.aligner(boids);
		Vecteur partie_cohesion = super.coherer(boids);

		//Le coefficient pour chaque partie
		partie_separation.mult(1);
		partie_alignement.mult(1);
		partie_cohesion.mult(1);
		
		//L'application de toutes les forces
		super.appliquer_force(partie_separation);
		super.appliquer_force(partie_alignement);
		super.appliquer_force(partie_cohesion);

		//Acceleration = Force / Masse
		this.acceleration.div(this.masse);
	}
}

