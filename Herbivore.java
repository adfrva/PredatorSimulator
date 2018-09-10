import java.util.ArrayList;

/**
 * Herbivore class uses 6 parameters, x y coordinate, int counter, a flatland, and another set
 * of x and y coordinates used as a target for a random direction
 * @author Adrian
 *
 */
public class Herbivore {
	private double myX;
	private double myY;
	private int myCounter;
	private Flatland myLand;
	private double myOtherX;
	private double myOtherY;
	/**
	 * 
	 * @param x - x coordinate on screen
	 * @param y - y coordinate on screen
	 * @param count - int keeps track of amount of plants eaten
	 * @param land - flatland the herbivore belongs to
	 * other x and y coordinates are randomized when herbivore is created
	 */
	public Herbivore(double x, double y, int count, Flatland land){
		myX = x;
		myY = y;
		myCounter = count;
		myLand = land;
		myOtherX = Math.random()*1000;
		myOtherY = Math.random()*1000;
	}
	
	public double getX(){
		return myX;
	}
	
	public double getY(){
		return myY;
	}
	
	public int getCounter(){
		return myCounter;
	}
	/**
	 * Method increments the Herbivores counter variable by 1
	 */
	public void incCount(){
		myCounter++;
	}
	/**
	 * circle method in StdDraw is used to show herbivore on screen
	 */
	public void draw(){
		StdDraw.setPenColor();
		StdDraw.circle(myX,  myY, 5);
	}

	/**
	 * Move tells herbivore to move under certain circumstances
	 * If moved off screen, the herbivore is moved to the other side
	 * Takes ArrayList of Predators as a parameter
	 */
	public void move(ArrayList<Predator> Preds){
		for(Predator aPred: Preds){
			// if the distance between predator and herbivore is less than 100, herbivore moves
			// away from predator
			if(Flatland.distance(myX, myY, aPred.getX(), aPred.getY()) < 100){
				changeDirection();
				moveAway(aPred.getX(), aPred.getY());
			}
		}
		//Herbivore moves towards its other coordinate
		moveTowards(myOtherX, myOtherY);
		//New other coordinates created if herbivores coordinates are within 5 of other coordinates
		if(Flatland.distance(myX, myY, myOtherX, myOtherY) < 5){
			changeDirection();
		}
		
		if(myX < 0){
			myX = 1000;
		}
		if(myX > 1000){
			myX = 0;
		}
		if(myY < 0){
			myY = 1000;
		}
		if(myY > 1000){
			myY = 0;
		}
	}
	/**
	 * Takes plant as parameter, moves herbivores x and y coordinates towards plant
	 * @param p
	 */
	public void moveTowards(double x, double y){
		double dist = Flatland.distance(myX, myY, x, y);
		myX = myX + ( (x-myX) / ( dist) );
		myY = myY + ( (y-myY) / ( dist) );
	}
	/**
	 * Takes x and y coordinates as parameter,
	 * moves herbivores coordinates of coordinates taken as parameter
	 * @param x
	 * @param y
	 */
	public void moveAway(double x, double y){
		double dist = Flatland.distance(myX, myY, x, y);
		myX = myX - ( (x-myX) / ( dist) );
		myY = myY - ( (y-myY) / ( dist) );
	}
	/**
	 * Takes plant as parameter, if distance between herbivore and plan is less than 2
	 * method returns true
	 * @param p
	 * @return
	 */
	public boolean ate(Plant p){
		if(Flatland.distance(myX, myY, p.getX(), p.getY() ) < 2){
			return true;
		}
		return false;
	}
	/**
	 * Herbivores target coordinates are randomized when this method is called
	 */
	public void changeDirection() {
		myOtherX = Math.random()*1000;
		myOtherY = Math.random()*1000;
	}

	public double getOtherX() {
		return myOtherX;
	}

	public double getOtherY() {
		return myOtherY;
	}
}
