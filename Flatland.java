import java.util.ArrayList;


public class Flatland {
	private ArrayList<Herbivore> myHerbs = new ArrayList<Herbivore>();
	private ArrayList<Plant> myPlants = new ArrayList<Plant>();
	private ArrayList<Predator> myPreds = new ArrayList<Predator>();
	private int simSteps;
	private boolean predHasEaten;

	/**
	 * 
	 * @param herbNum - number of herbivores in the arraylist
	 * @param plantNum - number of plants in arraylist
	 * @param squareNum - number of square predators in arraylist
	 * @param triangleNum - number of triangle predators in arraylist
	 */
	public Flatland( int herbNum, int plantNum, int squareNum, int triangleNum){
		for(int i = 0; i < herbNum; i++){
			Herbivore aHerb = new Herbivore(Math.random()*1000, Math.random()*1000, 0, this);
			myHerbs.add(aHerb);
		}
		
		for(int i = 0; i < plantNum; i++){
			Plant aPlant = new Plant(Math.random()*950, Math.random()*950);
			myPlants.add(aPlant);
		}
		
		for(int i = 0; i < squareNum; i++){
			Predator aSquarePred = new SquarePred(Math.random()*950, Math.random()*950, this);
			myPreds.add(aSquarePred);
		}
		
		for(int i = 0; i < triangleNum; i++){
			Predator aTriPred = new TrianglePred(Math.random()*1000, Math.random()*1000, this);
			myPreds.add(aTriPred);
		}
		predHasEaten = false;
		simSteps = 0;
	}
	
	/**
	 * tells herbivore to move, if plants are within 50 meters herbivore is told to move 
	 * towards closest plant. If a plant is eaten it is assigned to an arraylist and is 
	 * removed after for loop
	 */
	public void runSimulation(){
		ArrayList<Plant> removedPlants = new ArrayList<Plant>();
		ArrayList<Herbivore> removedHerbs = new ArrayList<Herbivore>();
		for(Herbivore aHerb: myHerbs){
			Plant closestPlant = findNearestPlant(aHerb.getX(), aHerb.getY(), myPlants);
			if(distance(aHerb.getX(), aHerb.getY(), closestPlant.getX(), closestPlant.getY())
			   > 100){
				aHerb.move(myPreds);
			}
			else{
				aHerb.moveTowards(closestPlant.getX(), closestPlant.getY());  
				for(Plant p: myPlants){
					if(aHerb.ate(p)){
						removedPlants.add(p);
						aHerb.incCount();
					}
				}
			}
		}
		for(Predator aPred: myPreds){
			Herbivore closestHerb = findNearestHerb(aPred, myHerbs);
			Plant closestPlant = findNearestPlant( aPred.getX(), aPred.getY(), myPlants);
			aPred.move(closestHerb, closestPlant);
			for(Herbivore h: myHerbs){
				if(aPred.ate(h)){
					removedHerbs.add(h);
					aPred.incCount();
				}
			}
		}

		simSteps++;
		myPlants.removeAll(removedPlants);
		myHerbs.removeAll(removedHerbs);
	}
	/**
	 * calls draw methods in plant and herbivore class
	 */
	public void draw(){
		for(Plant p: myPlants){
			p.draw();
		}
		for(Herbivore h: myHerbs){
			h.draw();
		}
		for(Predator pred: myPreds){
			pred.draw();
		}
	}

	/**
	 * If plant or herbivore class is empty, method prints amount of stuff eaten by
	 * predator then herbivore and ends simulation.
	 * 
	 */
	public void printStats(){
		if(myHerbs.isEmpty() || myPlants.isEmpty()){
			for(int i = 0; i < myPreds.size(); i++){
				
				System.out.println("Predator " + i + " ate " + myPreds.get(i).getCounter() + " herbivores");
			}
			for(int i = 0; i < myHerbs.size(); i++){
				System.out.println("Herbivore " + i + " ate " + myHerbs.get(i).getCounter() + " plants");
				System.out.println("Amount of steps for simulation: " + simSteps);
			}
			System.exit(0);
		}
	}
	/**
	 * takes in herbivore and arraylist of plants as parameters, calculates distance between
	 * herbivore and plants and returns closest plant
	 * @param h
	 * @param thePlants
	 * @return
	 */
	public static Plant findNearestPlant(double x, double y, ArrayList<Plant> thePlants){
		Plant closestPlant = thePlants.get(0);
		for(Plant p: thePlants){
			if(distance(x, y, p.getX(), p.getY() ) <
			   distance(x, y, closestPlant.getX(), closestPlant.getY() ) ){
				closestPlant = p;
			}
		}
		return closestPlant;
	}

	/**
	 * 
	 * @param p
	 * @param theHerbs
	 * @return
	 */
	public static Herbivore findNearestHerb(Predator p, ArrayList<Herbivore> theHerbs){
		Herbivore closestHerb = theHerbs.get(0);
		for(Herbivore herb: theHerbs){
			if(distance(p.getX(), p.getY(), herb.getX(), herb.getY()) < 
			   distance(p.getX(), p.getY(), closestHerb.getX(), closestHerb.getY()) ){
				closestHerb = herb;
			}
		}
		return closestHerb;
	}
	/**
	 * takes in two sets of x and y coordinates, calculates distance between the two points
	 * @param x
	 * @param y
	 * @param otherX
	 * @param otherY
	 * @return
	 */
	public static double distance(double x, double y, double otherX, double otherY){
		double result = 0;
		double dx = otherX - x;
		double dy = otherY - y;
		result = Math.sqrt( (dx*dx) + (dy*dy) );
		return result;
	}
	
}
