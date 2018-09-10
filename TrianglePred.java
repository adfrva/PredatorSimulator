/**
 * Derives from Predator class, tells all triangle predators how to behave
 * @author Adrian
 *
 */
public class TrianglePred extends Predator{
	//isSleeping is false when created, true when predator eats a herbivore.
	//sleepCount used to keep track of amount of steps passed when predator is asleep
	private boolean isSleeping;
	private int sleepCount;
	
	public TrianglePred(double x, double y, Flatland land){
		myX = x;
		myY = y;
		myLand = land;
		myColor = StdDraw.RED;
		isSleeping = false;
		sleepCount = 0;
	}
	
	@Override
	public void draw() {
		double[] xCoords = {myX, myX + 6, myX - 6};
		double[] yCoords = {myY + 6, myY - 6, myY - 6};
		StdDraw.setPenColor(myColor);
		StdDraw.filledPolygon(xCoords, yCoords);
	}

	@Override
	public void moveTowards(double x, double y){
		double dist = Flatland.distance(myX, myY, x, y);
		myX = (myX + (x-myX)/(dist));
		myY = (myY + (y-myY)/(dist));
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
		return counter;
	}



	@Override
	public void move(Herbivore h, Plant p) {
		//if isSleeping is true, predator does nothing, sleepCount is incremented by 1
		//if sleepCount is over 100, isSleeping is false, sleepCount becomes 0
		if(isSleeping){
			sleepCount++;
			if(sleepCount > 100){
				isSleeping = false;
				sleepCount = 0;
			}
		}
		
		else{
			//if distance between herbivore and predator is less than 200, predator moves towards
			//herbivore. Else predator moves towards closest plant
			if(Flatland.distance(myX, myY, h.getX(), h.getY()) < 200){
				moveTowards(h.getX(), h.getY());
				moveTowards(h.getX(), h.getY());
			}
			else{
				moveTowards(p.getX(), p.getY());
			}
		}
		//If predator moves off screen, it shows up on the other side
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
	public boolean ate(Herbivore h) {
		if(Flatland.distance(myX, myY, h.getX(), h.getY()) < 2){
			//if predator eats herbivore, isSleeping is true
			isSleeping = true;
			return true;
		}
		return false;
	}
	
	@Override
	public void incCount(){
		counter++;
	}
}
