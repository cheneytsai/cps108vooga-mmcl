import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class GameChooser extends JFrame
{
    private Dimension mySize = new Dimension(960,720);
    protected static Image image;
    
    public GameChooser()
    {
        setPreferredSize(mySize);
        setTitle("Title");
        setJMenuBar(makeMenu());
//        setBackgroundImage();

        
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

    
//    private void setBackgroundImage()
//    {
//        try
//        {
//            image = javax.imageio.ImageIO.read(new File("2150_lightning.jpg"));
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        
//        repaint();
//    }
//    
//    public void paintComponent(Graphics pen)
//    {
//        pen.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//    }
    
}
