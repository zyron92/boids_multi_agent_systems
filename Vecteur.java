import java.lang.Math;

public class Vecteur {

	private double x;
	private double y;

	public Vecteur(){ 
		this.x = 0;
		this.y = 0;
	}

	public Vecteur(double x, double y){ 
		this.x = x;
		this.y = y;
	}
	
	public Vecteur(Vecteur V){ 
		this.x = V.x;
		this.y = V.y;
	}

	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}

	public void setX(double x){
		this.x=x;
	}
	
	public void setY(double y){
		this.y=y;
	}

	public void egale(Vecteur V){
		this.x = V.x;
		this.y = V.y;
	}

	public void egale(double x, double y){
		this.x = x;
		this.y = y;
	}

	//Renvoie la distance entre le vecteur actuel et le vecteur p2
	public double distance(Vecteur p2){
		return Math.sqrt((p2.y-this.y)*(p2.y-this.y)+
				(p2.x-this.x)*(p2.x-this.x));
	}
	
	//L'addition du vecteur par un vecteur v2 pour chaque composante
	public void ajout(Vecteur v2){
		this.x=this.x + v2.x;
		this.y=this.y + v2.y;
	}
	
	//La soustraction du vecteur par un vecteur v2 pour chaque composante
	public void soustraire(Vecteur v2){
		this.x=this.x - v2.x;
		this.y=this.y - v2.y;
	}
	
	//La methode statique de la soustraction des deux vecteurs quelconques
	public static Vecteur soustraire(Vecteur v1, Vecteur v2){
		Vecteur res = new Vecteur(0,0);
		res.x=v1.x - v2.x;
		res.y=v1.y - v2.y;
		return res;
	}
	
	//La multiplication par un facteur de chaque composante du vecteur
	public void mult(double facteur){
		this.x=this.x * facteur;
		this.y=this.y * facteur;
	}
	
	//La division par une valeur de chaque composante du vecteur
	public void div(double val){
		if (val==0) {
			throw new ArithmeticException("Dans Div : Division par zero!");
		}
		this.x=this.x / val;
	}

	//Renvoie la norme du vecteur
	public double norme(){
		return Math.sqrt(this.y*this.y+this.x*this.x);
	}
	
	//La normalisation du vecteur
	public void normaliser(){
		double norme = this.norme();
		if (norme==0) {
			throw new ArithmeticException("Dans Normaliser : Division par zero!");
		}
		this.div(norme);
	}

	//Limiter la norme du vecteur
	public void limiter(double force_max){
		if (this.norme() > force_max){
			this.normaliser();
			this.mult(force_max);
		}
	}
}
