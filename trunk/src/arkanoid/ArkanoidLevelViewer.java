package arkanoid;

import java.awt.Color;
import java.awt.Graphics;

import util.resources.ResourceManager;
import view.Canvas;
import view.LevelViewer;

@SuppressWarnings("serial")
public class ArkanoidLevelViewer extends LevelViewer
{

    public ArkanoidLevelViewer(String gameName, int levelNum,
            Canvas canvas)
    {
        super(gameName, levelNum, canvas);
    }
    
    @Override
    public void paintComponent(Graphics pen)
    {
        super.paintComponent(pen);
        pen.setColor(Color.WHITE);
        pen.drawString(ResourceManager.getString("Title").substring(0, 10),
                0, 20);
        pen.drawString(getGameName(), 0, 40);
        pen.drawString(ResourceManager.getString("Score") + myModel.getScore(), 800,
                20);
    }

}
