package utilities;

import java.util.List;

import actors.Actor;

public abstract class CollisionChecker
{
    public static void checkCollisions(List<Actor> actors)
    {
        for (int k = actors.size() - 1; k >= 0; k--)
        {
            Actor a = actors.get(k);
            if (a.hasChanged)
            {
                for (int i = actors.size() - 1; i >= 0; i--)
                {
                    Actor b = actors.get(i);
                    if (i != k && intersects(a, b))
                    {
                        System.out.println(a.getClass().getCanonicalName() + " " + b.getClass().getCanonicalName());
                        a.interact(b);
                        b.interact(a);
                    }
                }
            }
        }
    }
    
    private static boolean intersects(Actor a, Actor b)
    {
        return a.getShape().contains(b.getShape().getBounds2D());
    }
}
