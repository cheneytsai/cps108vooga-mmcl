//TODO: Make this work with fancy pantsy outline/shape making thing
package utilities;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Area;
import java.util.List;

import actors.Actor;

public abstract class CollisionChecker {
    public static void checkCollisions(List<Actor> actors) {
        for (int k = actors.size() - 1; k >= 0; k--) {
            Actor a = actors.get(k);
            if (a.hasMoved) {
                for (int i = actors.size() - 1; i >= 0; i--) {
                    Actor b = actors.get(i);
                    if (i != k && collide(a, b)) {
                        System.out.println("Collision");
                        a.interact(b);
                        b.interact(a);
                    }
                }
            }
        }
    }

    private static boolean collide(Actor a, Actor b) {
        if (intersects(a, new Point(b.getPosition().x, b.getTop()))
                || intersects(a, new Point(b.getPosition().x, b.getBottom()))) {
            return true;
        }

        if (intersects(a, new Point(b.getLeft(), b.getPosition().y))
                || intersects(a, new Point(b.getRight(), b.getPosition().y))) {
            return true;
        }
        return false;
    }

    /**
     * /** Returns true if the given point is within a rectangle representing
     * this shape.
     */
    public static boolean intersects(Actor a, Point pt) {
        return (a.getLeft() <= pt.x && pt.x <= a.getRight()
                && a.getTop() <= pt.y && pt.y <= a.getBottom());
    }

    // private static boolean intersects (Actor a, Actor b)
    // {
    // return (getIntersectionArea(a, b) != null);
    // }
    //
    //
    // private static Area getIntersectionArea (Actor a, Actor b)
    // {
    // Dimension size = a.getSize();
    // Dimension otherSize = b.getSize();
    // double max = Math.max(size.getWidth(), size.getHeight()) +
    // Math.max(otherSize.getWidth(), otherSize.getHeight());
    // if (a.getPosition().distance(b.getPosition()) < max / 2)
    // {
    // Area me = (Area)a.getShape();
    // Area you = (Area)b.getShape();
    // me.intersect(you);
    // you.intersect(me);
    // if (! me.isEmpty())
    // {
    // return me;
    // }
    // }
    //        
    // return null;
    // }
}
