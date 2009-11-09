package actions;

public class Direction {
    private double myX;
    private double myY;
    
    public Direction(int x , int y)
    {
        double magnitude = Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
        myX = x / magnitude;
        myY = y / magnitude;
    }
    
    public double xShift()
    {
        return myX;
    }
    
    public double yShift()
    {
        return myY;
    }
}
