public class Line {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	Line(Znacznik x, Znacznik y){
		x1 = x.getX();
		y1 = x.getY();
		x2 = y.getX();
		y2 = y.getY();	
	}
	
	public int returnInitialXCoordinate(){
		return x1;
	}
	public int returnInitialYCoordinate(){
		return y1;
	}
	public int returnFinalXCoordinate(){
		return x2;
	}
	public int returnFinalYCoordinate(){
		return y2;
	}
}
