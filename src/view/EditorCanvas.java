package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import actors.Actor;

@SuppressWarnings("serial")
public class EditorCanvas extends LevelViewer
{  
    public EditorCanvas(String gameName, String levelName, int score, Canvas canvas)
    {
        super(gameName, levelName, score, canvas);

        myCanvas.removeKeyListener(myCanvas.getKeyListeners()[0]);
        myCanvas.addMouseListener(mouseListener());
    }

    protected void update()
    {
    }

    public MouseAdapter mouseListener()
    {
        MouseAdapter myMouseAdapter = new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                Actor match = null;
                for(Actor current : myActors)
                {
                    if(e.getPoint().equals(current.getCenter()))
                    {
                        match = current;
                        break;
                    }
                }
                
//              if nothing there, open popup
//              else,be able to update that object
                if(match == null)
                {
                    new EditorCreate(myGameModel,null,null,0,0,e.getX(),e.getY());
                }
                else
                {
                    new EditorCreate(myGameModel,match,match.getImage().toString(),
                            match.getSize().width, match.getSize().height,
                            e.getX(),e.getY());
                }
                 
            }
        };
        return myMouseAdapter;
    }
}
