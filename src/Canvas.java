import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
    
    protected Dimension mySize = new Dimension(960, 720);
    protected static final Font DEFAULT_TITLE_FONT = new Font("TAHOMA",
            Font.BOLD, 75);
    protected static final Font DEFAULT_OPTION_FONT = new Font("TAHOMA",
            Font.BOLD, 40);
    protected static final int AVG_PIXELS_PER_LETTER = 30;
    protected ImageIcon icon;
    protected Canvas myCanvas;
    private Canvas active;

    public Canvas() {
        myCanvas = this;
    }

    public void paintComponent(Graphics g) {
        if (active != null) {
            active.paintComponent(g);
        }
        super.paintComponent(g);
        setOpaque(false);
    }

    public void setActive(Canvas toUse) {
        active = toUse;
    }

    public MouseListener mouseListener()
    {
        return active.mouseListener();
    }
}
