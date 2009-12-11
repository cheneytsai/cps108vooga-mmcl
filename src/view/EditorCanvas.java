package view;

import gameengine.GameModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import util.resources.ResourceManager;
import actors.Actor;

/**
 * The main display for the level editor.
 * 
 * When the user clicks on the screen, EditorCreate prompts the user for
 * information.
 * 
 * @author Lisa Gutermuth
 *
 */

@SuppressWarnings("serial")
public class EditorCanvas extends LevelViewer
{
    private String mySaveFile;
    
    public EditorCanvas(String gameName, String levelName, Canvas canvas, GameModel model)
    {
        super(gameName, levelName, canvas, model);

        isPaused = true;
        myCanvas.removeKeyListener(myCanvas.getKeyListeners()[0]);
        myCanvas.addMouseListener(mouseListener());
        
        mySaveFile = (String) JOptionPane.showInputDialog(String.format(ResourceManager
                .getString("EditorSave")), ResourceManager.getString(gameName+"level"+levelName));
        if(mySaveFile == null)
        {
            mySaveFile = "src/resources/"+gameName+"level"+new GregorianCalendar()
                    .getTime().toString().replace(":","_")+".level";
        }
    }

    public void startGame()
    {
        myActors = myModel.getActors();
        myTimer.start();
    }
    
    public void update()
    {
    }

    public MouseAdapter mouseListener()
    {
        MouseAdapter myMouseAdapter = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                Actor match = null;
                for (Actor current : myModel.getActors())
                {
                    if (e.getX() >= current.getLeft()
                            && e.getX() <= current.getRight())
                    {
                        if (e.getY() >= current.getTop()
                                && e.getY() <= current.getBottom())
                        {
                            match = current;
                            break;
                        }
                    }
                }

                // if no Actor in the location clicked, open popup
                // else, be able to update that Actor
                if (match == null)
                {
                    new EditorCreate(myModel, getGameName() + "level"
                            + getLevelName(), mySaveFile,null, "src/images/Paddle.gif", 20,
                            20, e.getX(), e.getY());
                } else
                {

                    new EditorCreate(myModel, getGameName() + "level"
                            + getLevelName(), mySaveFile,match, match.getImageString(), match
                            .getSize().width, match.getSize().height,
                            (int) match.getPosition().getX(), (int) match
                                    .getPosition().getY());
                }
            }
        };
        return myMouseAdapter;
    }
}
