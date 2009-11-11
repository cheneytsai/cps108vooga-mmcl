package view;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class Frame extends JFrame
{
    private Dimension mySize = new Dimension(960, 720);
    private Canvas myCanvas;

    public Frame()
    {
        myCanvas = new Canvas();
        myCanvas.setActive(new GameChooser(myCanvas));
        setSize(mySize);
        setPreferredSize(mySize);
        setTitle(ResourceManager.getString("Title"));
        setJMenuBar(makeMenu());
        myCanvas.addMouseListener(myCanvas.mouseListener());

        getContentPane().add(myCanvas);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private JMenuBar makeMenu()
    {
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu(ResourceManager.getString("File"));

        fileMenu.add(new AbstractAction(ResourceManager.getString("NewGame"))
        {
            public void actionPerformed(ActionEvent ev)
            {
                if(myCanvas.getActive() != null && myCanvas.getGameName() != null)
                {
                    new GameMenu(myCanvas.getGameName(), myCanvas);
                }
            }
        });
        fileMenu.add(new AbstractAction(ResourceManager
                .getString("Instructions"))
        {
            public void actionPerformed(ActionEvent ev)
            {
                //Will display instructions for the current game
            }
        });
        fileMenu.add(new AbstractAction(ResourceManager.getString("HighScores"))
        {
            public void actionPerformed(ActionEvent ev)
            {
               // Will display high score for the current game
            }
        });
        fileMenu.add(new AbstractAction(ResourceManager.getString("Quit"))
        {
            public void actionPerformed(ActionEvent ev)
            {
                System.exit(0);
            }
        });
        menu.add(fileMenu);

        return menu;
    }
}
