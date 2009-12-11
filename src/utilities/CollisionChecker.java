package utilities;

import java.awt.Point;
import java.awt.geom.Area;
import java.util.List;
import actors.Actor;

public abstract class CollisionChecker
{

    public static void checkCollisions(List<Actor> actors)
    {

        boolean[][] interacted = new boolean[actors.size()][actors.size()];
        for (int i = actors.size() - 1; i >= 0; i--)
        {
            Actor a = actors.get(i);
            if (a.hasChanged == false)
                continue;
            for (int k = 0; k < actors.size(); k++)
            {
                if (i == k)
                    continue;
                if (interacted[i][k])
                    continue;
                Actor b = actors.get(k);
                if (!preliminaryCheck(a, b))
                    continue;
                if (collide(a, b))
                {
                    interacted[i][k] = true;
                    interacted[k][i] = true;
                    a.interact(b);
                    b.interact(a);
                }
            }

        }
    }

    public static boolean preliminaryCheck(Actor a, Actor b)
    {

        if (preliminaryIntersect(a, new Point(b.getPosition().x, b.getMaxTop()))
                || preliminaryIntersect(a, new Point(b.getPosition().x, b
                        .getMaxBottom())))
        {
            return true;
        }

        if (preliminaryIntersect(a,
                new Point(b.getMaxLeft(), b.getPosition().y))
                || preliminaryIntersect(a, new Point(b.getMaxRight(), b
                        .getPosition().y)))
        {
            return true;
        }

        if (preliminaryIntersect(b, new Point(a.getPosition().x, a.getMaxTop()))
                || preliminaryIntersect(b, new Point(a.getPosition().x, a
                        .getMaxBottom())))
        {

            return true;
        }

        if (preliminaryIntersect(b,
                new Point(a.getMaxLeft(), a.getPosition().y))
                || preliminaryIntersect(b, new Point(a.getMaxRight(), a
                        .getPosition().y)))
        {
            return true;
        }
        return false;

    }

    public static boolean preliminaryIntersect(Actor a, Point pt)
    {
        return (a.getLeft() < pt.x && pt.x < a.getRight() && a.getTop() < pt.y && pt.y < a
                .getBottom());
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
        // }
        return null;
    }

    public static boolean collide(Actor a, Actor b)
    {

        return getIntersectionArea(a, b) != null;
    }

    /**
     * /** Returns true if the given point is within a rectangle representing
     * this shape.
     */
    public static boolean intersects(Actor a, Point pt)
    {
        return a.getShape().contains(pt);
    }

}
