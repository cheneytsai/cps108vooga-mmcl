//TODO: Fix the collision bugs, make this all work with duvall's shape making stuff
package utilities;

import java.awt.Point;
import java.awt.geom.Area;
import java.util.List;
import actors.Actor;

/**
 * Collision checker to see if Actors have collided and prompt them to respond
 * to collisions.
 * 
 * @author Michael Yu
 * 
 */

public abstract class CollisionChecker
{


    public static void checkCollisions(List<Actor> actors)
    {

        for (int i = actors.size() - 1; i >= 0; i--)
        {
            Actor a = actors.get(i);
            if (a.hasChanged == false)
                continue;
            for (int k = 0; k < actors.size(); k++)
            {
                if (i == k)
                    continue;
                Actor b = actors.get(k);
                if (!preliminaryCheck(a, b))
                    continue;
                if (collide(a, b))
                {
                    a.interact(b);
                    b.interact(a);
                }
            }

        }
    }

    

    public static boolean preliminaryCheck(Actor a, Actor b)
    {

        if (preliminaryIntersect(a, new Point(b.getPosition().x, b.getTop()))
                || preliminaryIntersect(a, new Point(b.getPosition().x, b
                        .getBottom())))
        {

            return true;
        }

        if (preliminaryIntersect(a, new Point(b.getLeft(), b.getPosition().y))
                || preliminaryIntersect(a, new Point(b.getRight(), b
                        .getPosition().y)))
        {

            return true;
        }

        if (preliminaryIntersect(b, new Point(a.getPosition().x, a.getTop()))
                || preliminaryIntersect(b, new Point(a.getPosition().x, a
                        .getBottom())))
        {

            return true;
        }

        if (preliminaryIntersect(b, new Point(a.getLeft(), a.getPosition().y))
                || preliminaryIntersect(b, new Point(a.getRight(), a
                        .getPosition().y)))
        {
            return true;
        }
        return false;


    }

    private static boolean preliminaryIntersect(Actor a, Point pt)
    {
        return (a.getLeft() <= pt.x && pt.x <= a.getRight()
                && a.getTop() <= pt.y && pt.y <= a.getBottom());
    }

    protected static Area getIntersectionArea(Actor a, Actor b)
    {

        Area me = (Area) a.getShape();
        Area you = (Area) b.getShape();
        me.intersect(you);
        if (!me.isEmpty())
        {
            return me;
        }
        return null;
    }

    public static boolean collide(Actor a, Actor b)
    {

        return getIntersectionArea(a, b) != null;
    }

    /**
     * Returns true if the given point is within a rectangle representing this
     * shape.
     */
    public static boolean intersects(Actor a, Point pt)
    {
        return a.getShape().contains(pt);
    }

}
