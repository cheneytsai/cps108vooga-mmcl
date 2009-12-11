package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import actions.Quit;
import util.reflection.Reflection;
import util.resources.ResourceManager;

/**
 * 
 * @author Lisa Gutermuth
 * 
 */

@SuppressWarnings("serial")
public class Frame extends JFrame
{
    private Dimension mySize = new Dimension(960, 694);
    private Canvas myCanvas;
    private JMenu myGamePlayMenu;
    private final int DEFAULT_START_LEVEL = 0;

    public Frame()
    {
        myCanvas = new Canvas();

        myCanvas.setActive(new GameChooser(myCanvas));
        setSize(mySize);
        setPreferredSize(mySize);

        setTitle(ResourceManager.getString("Title"));
        JMenuBar menu = new JMenuBar();
        menu.add(makeMenu());
        menu.add(makeGamePlayMenu());
        setJMenuBar(menu);

        getContentPane().add(myCanvas);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JMenu makeMenu()
    {
        JMenu fileMenu = new JMenu(ResourceManager.getString("File"));

        fileMenu.add(new AbstractAction(ResourceManager.getString("MainMenu"))
        {
            public void actionPerformed(ActionEvent ev)
            {
                if (!(myCanvas.getActive() instanceof GameChooser))
                {
                    new GameChooser(myCanvas);
                }
            }
        });
        fileMenu.add(new AbstractAction(ResourceManager.getString("GameMenu"))
        {
            public void actionPerformed(ActionEvent ev)
            {
                if (myCanvas.getGameName() != null)
                {
                    new GameMenu(myCanvas.getGameName(), myCanvas);
                }
            }
        });
        fileMenu.add(new AbstractAction(ResourceManager.getString("Quit"))
        {
            public void actionPerformed(ActionEvent ev)
            {
                new Quit().execute();
            }
        });

        return fileMenu;
    }

    private JMenu makeGamePlayMenu()
    {
        myGamePlayMenu = new JMenu(ResourceManager.getString("GameOptions"));

        myGamePlayMenu.add(new AbstractAction(ResourceManager
                .getString("NewGame"))
        {
            public void actionPerformed(ActionEvent ev)
            {
                if (myCanvas.getGameName() != null)
                {
                    String gameName = myCanvas.getGameName();
                    Reflection.createInstance(gameName.toLowerCase() + "."
                            + gameName + "Model", gameName, "",
                            DEFAULT_START_LEVEL, gameName.toLowerCase() + "."
                                    + gameName + "LevelViewer", myCanvas);
                }
            }
        });
        myGamePlayMenu.add(new AbstractAction(ResourceManager
                .getString("Instructions"))
        {
            public void actionPerformed(ActionEvent ev)
            {
                if (myCanvas.getGameName() != null)
                {
                    new InstructionView(myCanvas.getGameName(), myCanvas);
                }
            }
        });
        myGamePlayMenu.add(new AbstractAction(ResourceManager
                .getString("HighScores"))
        {
            public void actionPerformed(ActionEvent ev)
            {
                if (myCanvas.getGameName() != null)
                {
                    new ScoresView(myCanvas.getGameName(), myCanvas);
                }
            }
        });
        myGamePlayMenu
                .add(new AbstractAction(ResourceManager.getString("Save"))
                {
                    public void actionPerformed(ActionEvent ev)
                    {
                        if (myCanvas.getActive() instanceof LevelViewer)
                        {
                            ((LevelViewer) myCanvas.getActive()).saveState();
                        }
                    }
                });

        return myGamePlayMenu;
    }

    public void setGamePlayMenuVisibility(boolean visibility)
    {
        myGamePlayMenu.setVisible(visibility);
    }
}
