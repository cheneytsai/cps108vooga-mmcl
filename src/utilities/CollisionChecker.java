//TODO: Fix the collision bugs, make this all work with duvall's shape making stuff
package utilities;

import java.awt.Point;
import java.awt.geom.Area;
import java.util.List;
import actors.Actor;
import actors.Ball;
import actors.Wall;

public abstract class CollisionChecker {

    // public void collisionCheck(List<Actor> actors)
    // {
    // for (int k = 0; k < actors.size(); k++)
    // {
    // if (myActors.get(k).hasChanged == true)
    // {
    // CollisionChecker.checkCollisions(myActors.get(k), myActors);
    // }
    // }
    // }
    public static void checkCollisions(List<Actor> actors) {

        // System.out.println("YOOHOO");
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
                //if (interacted[i][k]) continue;
                Actor b = actors.get(k);
                if (!preliminaryCheck(a, b))
                    continue;
                if (collide(a, b))
                {
                    // if(intersect(a,b)){
//                    interacted[i][k] = true;
//                    interacted[k][i] = true;
                    a.interact(b);
                    b.interact(a);
                }
            }

        }
    }

    // public static void checkCollisions(Actor movedActor, List<Actor> actors)
    // {
    //
    // // System.out.println("YOOHOO");
    // for (int i = actors.size() - 1; i >= 0; i--)
    // {
    // Actor b = actors.get(i);
    // if (b == movedActor)
    // continue;
    // if (!preliminaryCheck(movedActor, b))
    // continue;
    // if (collide(movedActor, b))
    // {
    // // if(intersect(a,b)){
    //
    // movedActor.interact(b);
    // b.interact(movedActor);
    // }
    //
    // }
    // }

    public static boolean preliminaryCheck(Actor a, Actor b) {

        if (preliminaryIntersect(a, new Point(b.getPosition().x, b.getTop())) || preliminaryIntersect(a, new Point(b.getPosition().x, b.getBottom())))
        {

            return true;
        }

        if (preliminaryIntersect(a, new Point(b.getLeft(), b.getPosition().y)) || preliminaryIntersect(a, new Point(b.getRight(), b.getPosition().y)))
        {

            return true;
        }

        if (preliminaryIntersect(b, new Point(a.getPosition().x, a.getTop())) || preliminaryIntersect(b, new Point(a.getPosition().x, a.getBottom())))
        {

            return true;
        }

        if (preliminaryIntersect(b, new Point(a.getLeft(), a.getPosition().y)) || preliminaryIntersect(b, new Point(a.getRight(), a.getPosition().y)))
        {
            return true;
        }
        return false;

        // if ((a.getTop() < b.getBottom() ^ a.getBottom() < b.getTop()) ||
        // ( a.getLeft() < b.getRight() ^ a.getRight() < b.getLeft() ))
        // {
        // if (b.getClass().equals(Wall.class))
        // System.out.println("Preliminary True");
        // return true;
        // }
        // if (b.getClass().equals(Wall.class))
        // System.out.println("Preliminary false");
        // return false;

    }

    private static boolean preliminaryIntersect(Actor a, Point pt) {
        return (a.getLeft() <= pt.x && pt.x <= a.getRight() && a.getTop() <= pt.y && pt.y <= a.getBottom());
    }

    protected static Area getIntersectionArea(Actor a, Actor b) {
        // System.out.println("?" + a + " " + b);
        // Dimension size = a.getSize();
        // Dimension otherSize = b.getSize();
        // double max = Math.max(size.getWidth(), size.getHeight())
        // + Math.max(otherSize.getWidth(), otherSize.getHeight());
        // if (a.getCenter().distance(b.getCenter()) < max / 2) {
        Area me = (Area) a.getShape();
        Area you = (Area) b.getShape();
        me.intersect(you);
        // you.intersect(me);
        if (!me.isEmpty())
        {
            return me;
        }
        // }
        return null;
    }

    public static boolean collide(Actor a, Actor b) {

        // getIntersectionArea(a, b);
        // System.out.println("?" + a + " " + b);
        // if (intersects(a, new Point(b.getPosition().x, b.getTop()))
        // || intersects(a, new Point(b.getPosition().x, b.getBottom())))
        // {
        // return true;
        // }
        //
        // if (intersects(a, new Point(b.getLeft(), b.getPosition().y))
        // || intersects(a, new Point(b.getRight(), b.getPosition().y)))
        // {
        // return true;
        // }
        return getIntersectionArea(a, b) != null;
    }

    /**
     * /** Returns true if the given point is within a rectangle representing
     * this shape.
     */
    public static boolean intersects(Actor a, Point pt) {
        return a.getShape().contains(pt);
    }

}
