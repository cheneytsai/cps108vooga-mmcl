package physics;

/**
 * 
 * @author Michael Yu
 * 
 */
public class Direction
{
    private double myX;
    private double myY;

    public Direction(double d, double e)
    {
        double magnitude = Math.sqrt((Math.pow(d, 2) + Math.pow(e, 2)));
        myX = d / magnitude;
        myY = e / magnitude;
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
