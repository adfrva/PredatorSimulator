
/**
 * Derives from predator class, tells square predators how to behave
 * @author Adrian
 *
 */
public class SquarePred extends Predator {
	/**
	 * isHidden used for square predator. When created, is hidden is true. If predator is close
	 * isHidden becomes false
	 */
	public boolean isHidden;
	
	public SquarePred(double x, double y, Flatland land){
		myX = x;
		myY = y;
		myLand = land;
		isHidden = true;
	}
	
	public void moveTowards(double x, double y){
		double dist = Flatland.distance(myX, myY, x, y);
		myX = (myX + (x-myX)/(dist));
		myY = (myY + (y-myY)/(dist));
	}

	@Override
	public void move(Herbivore h, Plant p) {
		//If distance between herb and predator is less than 50, isHidden is false, predator 
		//moves towards herbivore, predator turns red. Else isHidden is true, coordinates
		//are not changed and color becomes green
		if(Flatland.distance(myX,  myY, h.getX(), h.getY()) < 50){
			isHidden = false;
			moveTowards(h.getX(), h.getY());
			moveTowards(h.getX(), h.getY());
			moveTowards(h.getX(), h.getY());
			myColor = StdDraw.RED;
		}
		else{
			isHidden = true;
			myX = myX;
			myY = myY;
			myColor = StdDraw.GREEN;
		}
		//if moved off screen, predator shows up on other side
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

	@Override
	public void draw() {
		StdDraw.setPenColor(myColor);
		StdDraw.filledSquare(myX, myY, 5);
	}

	@Override
	public double getX() {
		return myX;
	}

	@Override
	public double getY() {
		return myY;
	}

	@Override
	public int getCounter() {
		// TODO Auto-generated method stub
		return counter;
	}

	@Override
	public boolean ate(Herbivore h) {
		if(Flatland.distance(myX, myY, h.getX(), h.getY()) < 2){
			return true;
		}
		return false;
	}
	
	@Override
	public void incCount(){
		counter++;
	}
}
