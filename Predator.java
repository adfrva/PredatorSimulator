import java.awt.Color;

/**
 * Predator is an abstract class used to derive other predator classes from it
 * @author Adrian
 *
 */
public abstract class Predator {
	/**
	 * Variables used in classes derived from this are an x and y location,
	 * the flatland it belongs to, an integer for the eat tally and the color of the predator
	 */
	protected double myX;
	protected double myY;
	protected static Flatland myLand;
	protected int counter;
	protected Color myColor;
	/**
	 * overloading class definition
	 */
	public Predator(){
		this(0.0, 0.0, myLand);
	}
	/**
	 * 
	 * @param x - x-coordinate
	 * @param y - y-coordinate
	 * @param land - the land predator belongs to
	 */
	public Predator(double x, double y, Flatland land){
		myX = x;
		myY = y;
		myLand = land;
		counter = 0;
	}
	/**
	 * Move method takes in herbivore and plant as parameter.
	 * Method tells predator how to move in certain circumstances
	 * @param h
	 * @param p
	 */
	public abstract void move(Herbivore h, Plant p);
	/**
	 * Method takes in x and y coordinate, tells predator to move its coordinates to the 
	 * coordinates given as parameters
	 * @param x
	 * @param y
	 */
	public abstract void moveTowards(double x, double y);
	/**
	 * Method tells StdDraw which shape to draw predator in
	 */
	public abstract void draw();
	/**
	 * Takes herbivore as parameter, if predators coordinates and herbivores coordinate are
	 * less than 5, returns true
	 * @param h
	 * @return
	 */
	public abstract boolean ate(Herbivore h);
	public abstract double getX();
	public abstract double getY();
	public abstract int getCounter();
	/**
	 * When called, counter variable owned by predator increases by one.
	 */
	public abstract void incCount();
}
