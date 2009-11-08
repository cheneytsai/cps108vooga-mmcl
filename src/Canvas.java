import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Canvas extends JPanel
{
    protected ImageIcon icon;
    private Canvas active;
    
    public Canvas()
    {
    }
    
    public void paintComponent(Graphics g)
    {
        if(active != null)
        {
            active.paintComponent(g);
        }
        super.paintComponent(g);
        setOpaque(false);
    }
    
    public void setActive(Canvas toUse)
    {
        active = toUse;
    }
}
