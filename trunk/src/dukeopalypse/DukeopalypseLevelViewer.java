package dukeopalypse;

import arkanoid.ArkanoidLevelViewer;
import gameengine.GameModel;
import view.Canvas;

@SuppressWarnings("serial")
public class DukeopalypseLevelViewer extends ArkanoidLevelViewer
{

    public DukeopalypseLevelViewer(String gameName, String levelName,
            Canvas canvas,GameModel model)
    {
        super(gameName, levelName, canvas,model);
    }

}
