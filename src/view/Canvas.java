package view;

//TODO make abstract

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.GameModel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

    protected Dimension mySize = new Dimension(960, 720);

    protected Canvas myCanvas;
    private Canvas myActive;
    protected GameModel myGameModel;
    protected String myGameName;
    // private Menu active;
    protected int myScore;
    protected static final Font TITLE_FONT = new Font("TAHOMA", Font.BOLD, 75);
    protected static final Font OPTION_FONT = new Font("TAHOMA", Font.BOLD, 40);
    protected static final Font SCOREBOARD_FONT = new Font("TAHOMA",
            Font.PLAIN, 20);
    protected static final Font END_FONT = new Font("TAHOMA", Font.PLAIN, 60);
    protected static final int AVG_PIXELS_PER_LETTER = 30;
    protected ImageIcon icon;

    public Canvas() {
        myActive = this;
        setSize(mySize);
        myGameModel = new GameModel(this);
    }

    public void setGame(GameModel model) {
        myGameModel = model;
    }

    public void paintComponent(Graphics g) {
        if (myActive != null) {
            myActive.paintComponent(g);
        }
        super.paintComponent(g);
        setOpaque(false);
    }

    public void setActive(Canvas toUse) 
    {
        myActive.stopTimer();

        myActive = toUse;
        myGameModel.newView(myActive);
    }

    public void stopTimer()
    {
    }
    
    public boolean getTimerStat()
    {
        return myActive.isTimerRunning();
    }
    
    private boolean isTimerRunning()
    {
        return false;
    }

    public int getScore()
    {
        return myScore;
    }
    public String getGameName() {

        return myActive.getGameName();
    }
        
    public int getLevelNum() {
        return 0;
    }

    public MouseListener mouseListener() {
        return myActive.mouseListener();
    }

    public void updateScore(int increment) {
        myScore += increment;
    }

    public int getWidth() {
        return mySize.width;
    }

    public int getHeight() {
        return mySize.height;
    }

    public boolean isGameInProgress() {
        return myActive != null && getGameName() != null;
    }

    protected GameModel getGameModel() {
        return myGameModel;
    }

    public void loadNextLevel() {
    }

    public void loadEnd(String endCondition) {
    }
}
