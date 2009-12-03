package conditions;

import java.util.List;

import actors.Actor;
import model.GameModel;

public class NumberOf implements Condition {
    
    private GameModel myModel;
    private String myType;
    private int numberOf;
    
    public NumberOf(GameModel model, String type, int number)
    {
        myModel = model;
        myType = type;
        numberOf = number;
        System.out.println(myType);
    }
    public boolean evaluate() {
        int total = 0;
        List<Actor> actors = myModel.getActors();
        for (Actor a : actors)
        {
            if (a.getClass().getName().equals(myType))
                total++;
                
        }
        if (total == numberOf)
        { 
            return true;
           
        }
        else
            return false;
    }
    
}
