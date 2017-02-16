import java.awt.Color;
import java.util.LinkedList;

public class BoidG2 extends Boid{

	public BoidG2(double x, double y, double masse, double radian,
			double force_max, double vitesse_max, 
			double voisinage, double dist_separation){
		super(x, y, masse, radian, force_max, vitesse_max, 
				voisinage, dist_separation);
		this.couleur=Color.green;
	}
	
	//renvoyer un type BoidG2 de lui-meme
	public Boid copie(){
		return new BoidG2(this.coord.getX(), this.coord.getY(), this.masse, 
				this.radian, this.force_max, this.vitesse_max, 
				this.voisinage, this.dist_separation);
	}

	@Override
	//L'agregation des boids
	public void agregation(LinkedList<Boid> boids){
		Vecteur partie_cohesion = super.coherer(boids);
		partie_cohesion.mult(1);
		super.appliquer_force(partie_cohesion);
		//Acceleration = Force / Masse
		this.acceleration.div(this.masse);
	}
}

