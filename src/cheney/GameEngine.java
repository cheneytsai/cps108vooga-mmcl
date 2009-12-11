package cheney;

import view.Canvas;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.util.*;
import conditions.Condition;
import actions.*;
import actors.*;

/**
 * Core Game Engine
 * 
 * @author Cheney Tsai
 */
public class GameEngine
{

    private String myGameName;
    private Canvas myCanvas;
    private boolean doneInitialize;
    protected List<Actor> myActorList;
    protected InteractionEngine myInteractionEngine;
    protected DataEngine myDataEngine;
    protected KeyEvent myLastKeyPressed;
    protected MouseEvent myLastMouseEvent;

    public GameEngine(Canvas canvas)
    {
        myGameName = null;
        myCanvas = canvas;
        doneInitialize = false;
        myActorList = new ArrayList<Actor>();
        myInteractionEngine = new InteractionEngine(this);
        myDataEngine = new DataEngine(this);
    }

    public void initialize(String gameName)
    {
        myGameName = gameName;
        myCanvas.updateGameName(myGameName);
        myDataEngine.loadGame(myGameName);
        update(null, null);
        doneInitialize = true;

    }

    public void loadLevel(String levelName)
    {
        doneInitialize = false;
        myActorList.clear();
        myInteractionEngine = new InteractionEngine(this);
        myDataEngine.loadLevelData(levelName);
        update(null, null);
        doneInitialize = true;
    }

    public void update(KeyEvent newLastKeyPressed, MouseEvent e)
    {
        try
        {
            myLastMouseEvent = e;
        } catch (NullPointerException ee)
        {

        }
        myLastKeyPressed = newLastKeyPressed;
        myInteractionEngine.doInteractions();

        for (int k = 0; k < myActorList.size(); k++)
        {
            Point tempPos = myActorList.get(k).getPosition();
            myActorList.get(k).act(myLastKeyPressed);
            if (myActorList.get(k).getPosition() != tempPos)
            {
                myActorList.get(k).hasMoved = true;
            }
        }
        for (int k = 0; k < myActorList.size(); k++)
        {
            myActorList.get(k).hasMoved = false;
        }

    }

    public void remove(Actor actor)
    {
        myActorList.remove(actor);
    }

    public void addActor(Actor actor)
    {
        myActorList.add(actor);
    }

    public void addActor(String[] actorData)
    {
        try
        {
            Constructor<?> con = Class.forName(actorData[0]).getConstructor(
                    String[].class, GameEngine.class);
            Object[] params =
            { actorData, this };

            myActorList.add((Actor) con.newInstance(params));
        } catch (Throwable ex)
        {

        }
    }

    public void addInteraction(List<String[]> conditionsData,
            List<String[]> actionsData)
    {
        ArrayList<Condition> conditions = new ArrayList<Condition>();
        ArrayList<Action> actions = new ArrayList<Action>();
        try
        {
            for (String[] conditionData : conditionsData)
            {

                Constructor<?> con = Class.forName(conditionData[0])
                        .getConstructor(String[].class, GameEngine.class);
                Object[] params =
                { conditionData, this };
                conditions.add((Condition) con.newInstance(params));
            }

            for (String[] actionData : actionsData)
            {
                Constructor<?> con = Class.forName(actionData[0])
                        .getConstructor(String[].class, GameEngine.class);
                Object[] params =
                { actionData, this };
                actions.add((Action) con.newInstance(params));
            }
        } catch (Throwable ex)
        {
        }

        myInteractionEngine.addInteractions(conditions, actions);
    }

    public List<Actor> getActors()
    {
        return myActorList;
    }

    public String[] getActorData(String id)
    {
        return myDataEngine.getActorData(id);
    }

    public List<Actor> getActorsByID(String id)
    {
        ArrayList<Actor> filteredActors = new ArrayList<Actor>();
        for (Actor a : myActorList)
        {
            if (a.getID().equals(id))
                filteredActors.add(a);
        }
        return filteredActors;
    }

    public Canvas getCanvas()
    {
        return myCanvas;
    }

    public boolean doneInitialize()
    {
        return doneInitialize;

    }

    public KeyEvent getLastKeyPressed()
    {
        return myLastKeyPressed;
    }

    public MouseEvent getLastMouseEvent()
    {
        return myLastMouseEvent;
    }

}