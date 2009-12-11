package tetris;

import gameengine.GameModel;
import java.awt.Color;
import java.awt.Graphics;

import actions.AddPiece;
import actors.Grid;

import util.resources.ResourceManager;
import view.Canvas;
import view.LevelViewer;

/**
 * A LevelViewer for Tetris.
 * 
 * @author Megan Heysham
 * 
 */

@SuppressWarnings("serial")
public class TetrisLevelViewer extends LevelViewer
{
    private int myLevelNum;

    public TetrisLevelViewer(String gameName, String levelName, Canvas canvas,
            GameModel model)
    {
        super(gameName, levelName, canvas, model);

        myLevelNum = 0;
    }

    @Override
    public void paintComponent(Graphics pen)
    {
        super.paintComponent(pen);

        pen.setColor(Color.BLACK);
        pen.drawString(ResourceManager.getString("Title").substring(0, 10),
                800, 25);
        pen.drawString(getGameName(), 800, 50);

        pen.setColor(Color.WHITE);
        pen.fillRect(800, 160, 110, 50);
        pen.fillRect(800, 260, 110, 50);
        pen.fillRect(800, 360, 110, 50);
        pen.fillRect(800, 460, 110, 110);

        pen.setColor(Color.BLACK);
        pen.drawRect(800, 160, 110, 50);
        pen.drawRect(800, 260, 110, 50);
        pen.drawRect(800, 360, 110, 50);
        pen.drawRect(800, 460, 110, 110);

        pen.drawString(ResourceManager.getString("Score"), 800, 155);
        pen.drawString("" + myModel.getScore(), 805, 190);

        pen.drawString("Level:", 800, 255);
        pen.drawString("" + myLevelNum, 805, 290);

        pen.drawString("Lines:", 800, 355);
        pen.drawString("" + Grid.getNumRowsCleared(), 805, 390);

        pen.drawString("Next Piece:", 800, 455);
        pen.drawImage(AddPiece.nextImage(), 855 - AddPiece.nextImage()
                .getWidth(null) / 2,
                515 - AddPiece.nextImage().getHeight(null) / 2, null);
    }

    @Override
    public void update()
    {
        if (Grid.getNumRowsCleared() / 10 + 1 > myLevelNum)
        {
            myLevelNum++;
        }
        super.update();
    }

    public int getLevelNum()
    {
        return myLevelNum;
    }

}
