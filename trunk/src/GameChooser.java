import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameChooser extends JFrame
{
    private Dimension mySize = new Dimension(960,720);
//    protected static Image image;
    ImageIcon icon;
    
    public GameChooser()
    {
        setPreferredSize(mySize);
        setTitle("Title");
        setJMenuBar(makeMenu());
        getContentPane().add(setBackgroundImage());

        
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private JMenuBar makeMenu()
    {
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        
        //New Game, Instructions, High Score, Quit.  Only quit works at this stage?
        fileMenu.add(new AbstractAction("Quit")
        {
            public void actionPerformed (ActionEvent ev)
            {
                System.exit(0);
            }
        });
        menu.add(fileMenu);
        
        return menu;
    }

    private JPanel setBackgroundImage()
    {
        icon = new ImageIcon("2150_lightning.jpg");
        
        JPanel panel = new JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                g.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
 
                super.paintComponent(g);
            }
        };
        panel.setOpaque( false );
        return panel;
    }
}
