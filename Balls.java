import java.awt.Point;

public class Balls implements Reinitialisable{

	/**
	 * *********************Attributs****************************
	 * init : tableau qui stocke les cordonnees des balles en t=0
	 * balles : coordonnees des balles
	 * directions : quelle directions les balles vont predre
	 * 				1 : sens positif . -1 : negatif
	 */
	private Point[] init;
	private Point[] balles;
	private Point[] directions;
	
	/**
	 * constructeur naturelle de la classe balls a partir de
	 * @param nombre : nombre de balles qu'on veut
	 * @param initialisation : coordonnees initiales des balles
	 */
	public Balls(int nombre, Point initialisation[]) {
		this.init = new Point[nombre];
		this.balles = new Point[nombre];
		this.directions = new Point[nombre];
		for (int i = 0; i < nombre; i++) {
			if ( initialisation[i].x < 0 || initialisation[i].y < 0){
				throw new ArithmeticException("La balle " + i +
						" a une cordonnee negatif");
			}
			this.init[i] = new Point(initialisation[i].x, initialisation[i].y);
			this.balles[i] = new Point(initialisation[i].x, initialisation[i].y);
			this.directions[i] = new Point(1, 1);
		}
	}
	
	/**
	 * constructeur de balls a partir d'une autre balls
	 * @param b : balle deja existante
	 */
	public Balls(Balls b) {
		this(b.init.length, b.init);
	}
	
	/**
	 * retourne le nombre de balles 
	 * @return le bombre de balles
	 */
	public int number() {
		return this.balles.length;
	}
	
	/**
	 * rebond avec le mur vertical a gauche
	 * @param p
	 * @param tY
	 * @return boolean true si il y un rebond vertical
	 */
	private static boolean rebondVer1 (Point p, int tY) {
		if (p.y + tY < 0)
			return true;
		else
			return false;
	}
	
	private static boolean rebondVer2 (Point p, int tY, int h) {
		if (p.y + tY > h)
			return true;
		else
			return false;
	}
	
	/**
	 * nous informe si il y a eu un rebond avec le mur vertical
	 * @param p : position des balles
	 * @param tY : translation
	 * @return boolean true si il y un rebond vertical
	 */
	private static boolean rebondVer (Point p, int tY, int h) {
		if (rebondVer1(p, tY) || rebondVer2(p, tY, h))
			return true;
		else
			return false;
	}
	
	private static boolean rebondHor1 (Point p, int tX) {
		if (p.x + tX < 0)
			return true;
		else
			return false;
	}
	
	private static boolean rebondHor2 (Point p, int tX, int w) {
		if (p.x + tX > w)
			return true;
		else
			return false;
	}
	
	private static boolean rebondHor (Point p, int tX, int w) {
		if (rebondHor1(p, tX) || rebondHor2(p, tX, w))
			return true;
		else
			return false;
	}
	
	/**
	 * @return true si il n y a pas de rebond
	 */
	private static boolean rebondNon (Point p, int tX, int tY, int w, int h) {
		if ((rebondHor(p, tX, w) == false) & (rebondVer(p, tY, h) == false)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @return true si il y a deux rebonds
	 */
	private static boolean rebondDouble (Point p, int tX, int tY, int w, int h) {
		if ((rebondHor(p, tX, w) == true) & (rebondVer(p, tY, h) == true)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * translater les balles et prendre en compte les rebonds
	 * @param dx : translation horizontale
	 * @param dy : translation verticale
	 * @param width : largeur de l'extrimite horizontale
	 * @param height : largeur de l'extremite verticale
	 */
	public void translate(int dx, int dy,int width, int height) {
		for (int i = 0; i < this.balles.length; i++) {
			//cas ou il n y a pas de rebond
			if (rebondNon (this.balles[i], dx, dy, width, height)) {
				this.balles[i].x += dx * this.directions[i].x;
				this.balles[i].y += dy * this.directions[i].y;
			}
			//cas ou il y a deux rebonds
			else if(rebondDouble(this.balles[i], dx, dy, width, height)) {
				if (rebondHor1(this.balles[i], dx)) {
					this.balles[i].x = -(dx * this.directions[i].x + this.balles[i].x);
				}
				else {
					this.balles[i].x = 2 * width - (dx * this.directions[i].x + this.balles[i].x);
				}
				this.directions[i].x *= -1;
						
				if (rebondVer1(this.balles[i], dy)) {
					this.balles[i].y = -(dy * this.directions[i].y + this.balles[i].y);
				}
				else {
					this.balles[i].y = 2 * height - (dy * this.directions[i].y + this.balles[i].y);
				}
					this.directions[i].y *= -1;
			}
			//cas ou il y a un seul rebond
			else {
				int memo = this.balles[i].y;
				if (rebondHor(this.balles[i], dx, width)) {
					if (rebondHor1(this.balles[i], dx)) {
						this.balles[i].x = -(dx * this.directions[i].x + this.balles[i].x);
					}
					else {
						this.balles[i].x = 2 * width - (dx * this.directions[i].x + this.balles[i].x);
					}
					memo += dy * this.directions[i].y;
					this.directions[i].x *= -1;	
				}
				if (rebondVer(this.balles[i], dy, height)) {
					if (rebondVer1(this.balles[i], dy)) {
						memo = -(dy * this.directions[i].y + this.balles[i].y);
					}
					else {
						memo = 2 * height - (dy * this.directions[i].y + this.balles[i].y);
					}
					this.balles[i].x += dx * this.directions[i].x;
					this.directions[i].y *= -1;
				}
				this.balles[i].y = memo;
			}
		}
	}
	
	/**
	 * remet les balles au point de depart
	 */
	public void reInit() {
		for (int i = 0; i < this.balles.length; i++) {
			this.balles[i].setLocation(init[i]);
			this.directions[i].x = 1;
			this.directions[i].y = 1;
		}
	}
	
	/**
	 * obtenir  la composante x de la balle i 
	 * @param i : numero de la balle
	 * @return
	 */
	public int getX(int i) {
		return this.balles[i].x;
	}
	
	public int getY(int i) {
		return this.balles[i].y;
	}
	
	/**
	 * changer  la composante x de la balle i
	 * @param i : numero de la balle
	 * @param coor1 : nouvelle valeur
	 */
	public void setX(int i, int coor1) {
		this.balles[i].x = coor1;
	}
	
	public void setY(int i, int coor2) {
		this.balles[i].y = coor2;
	}
	
	@Override
	public String toString() {
		String s = new String(" ");
		for (int i = 0; i < this.balles.length; i++) {
			s += "(" + this.balles[i].x + ", " + 
					this.balles[i].y + ")" ;
		}
		return s;
	}
}