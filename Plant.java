/**
 * Plant class creates class using Math.random() method and draws it on the screen
 * @author Adrian
 *
 */
public class Plant {
	private double myX;
	private double myY;
	/**
	 * 
	 * @param x - x coordinate on screen
	 * @param y - y coordinate on screen
	 */
	public Plant(double x, double y){
		myX = x;
		myY = y;
	}
	
	public double getX(){
		return myX;
	}
	
	public double getY(){
		return myY;
	}
	/**
	 * takes in x and y coordinates, calculates distance between point and plant
	 * @param x
	 * @param y
	 * @return
	 */
	public double distance(double x, double y){
		//Distance calculation
		double dx = x - myX;
		double dy = y - myY;
		return Math.sqrt( (dx * dx) + (dy * dy));
	}
	
	public void draw(){
		StdDraw.setPenColor();
		StdDraw.circle(myX,  myY, 1);
	}
}
