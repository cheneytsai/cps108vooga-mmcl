package gameengine;

import java.io.File;
import java.util.List;
import java.util.Map;

import util.resources.VoogaResources;
import datamanager.FileParser;

/**
 * GameEngine Supplement for Data
 * 
 * @author Cheney Tsai
 */
public class DataEngine {

    protected GameEngine myEngine;
    private String myGameName;
    private String myCurrentLevel;
    private List<String[]> myLevelActors;
    private Map<List<String[]>,List<String[]>> myLevelInteractions;
    
    public DataEngine(GameEngine engine) 
    {
       myEngine = engine;     
    }
    
    public void loadGame(String gameName){
        myGameName = gameName;
        myCurrentLevel = FileParser.getFirstLevel(gameName);
        loadLevelData(myCurrentLevel);
        
    }
    
    public void loadLevelData(String levelName){

        myCurrentLevel = levelName;
        VoogaResources.setGamePath(myGameName);
        myLevelActors = FileParser.loadActors(new File(VoogaResources.getLevelFactoryReference(myCurrentLevel)));
        myLevelInteractions = FileParser.loadInteractions(new File(VoogaResources.getLevelFactoryReference(myCurrentLevel)));
        for (List<String[]> conditionSet: myLevelInteractions.keySet())
        {
            loadInteraction(conditionSet, myLevelInteractions.get(conditionSet));
        }
        
    }
    
    public void loadInteraction(List<String[]> conditionData,List<String[]> actionData){
        myEngine.addInteraction(conditionData, actionData);
    }
    
    public String[] getActorData(String nickname){
        for ( String[] actorInfo : myLevelActors)
        {
            if (findActorByID(actorInfo, nickname)) return actorInfo;
        }
        return null;
    }
    
    private boolean findActorByID(String[] actorInfo, String id)
    {
        if (actorInfo[1].equals(id)) return true;
        else return false;
    }
    
        
}






