/**
 * 
 * @author Adrian Fraire
 *
 */
public class WildernessDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(1000, 1000);
		StdDraw.setXscale(0.0, 1000.0);
		StdDraw.setYscale(0.0, 1000.0);
		
		Flatland theLand = new Flatland(20, 300, 2, 2);
		while(true){
			StdDraw.clear();
			theLand.runSimulation();
			
			theLand.draw();
			
			theLand.printStats();
			StdDraw.show(5);
		}
	}

}
