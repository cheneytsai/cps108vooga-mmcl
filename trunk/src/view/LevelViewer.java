package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import actions.AddPiece;
import actors.Actor;
import util.resources.ResourceManager;

/**
 * 
 * 
 * @author Lisa Gutermuth
 */
@SuppressWarnings("serial")
public class LevelViewer extends Canvas implements ActionListener {
    private String myGameName;
    protected List<Actor> myActors;
    protected String myLastKeyPressed;
    protected int myLevelNum;
    private Timer myTimer;
    // animate 25 times per second if possible
    public static final int DEFAULT_DELAY = 1000 / 25; // in milliseconds

    public LevelViewer(String gameName, int levelNum, int score, Canvas canvas) {
        myCanvas = canvas;
        myGameName = gameName;
        myLevelNum = levelNum;
        myScore = score;
        myCanvas.setActive(this);
        myCanvas.requestFocus();
        myGameModel = canvas.getGameModel();

        if (myCanvas.getMouseListeners().length > 0) {
            myCanvas.removeMouseListener(myCanvas.getMouseListeners()[0]);
        }

        myCanvas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {

                myLastKeyPressed = KeyEvent.getKeyText(e.getKeyCode());
            }

            public void keyReleased(KeyEvent e) {

                myLastKeyPressed = "";
            }
        });

        icon = new ImageIcon(ResourceManager.getString(myGameName
                + "level.background"));

        myCanvas.repaint();

        myActors = myGameModel.getActors();
        myGameModel.setGameOver(false);
        myTimer = new Timer(DEFAULT_DELAY, this);
        myTimer.start();

        update();
    }

    public void stopTimer() {
        myTimer.stop();
    }

    public void update() {
        myGameModel.update(myLastKeyPressed);
        if (myGameModel.gameOver()) {
            myTimer.stop();
        }
    }

    public void paintComponent(Graphics pen) {

        pen.drawImage(icon.getImage(), 0, 0, mySize.width, mySize.height, null);
        pen.setFont(SCOREBOARD_FONT);
        pen.setColor(Color.WHITE);
        pen.drawString(ResourceManager.getString("Title").substring(0, 10), 0,
                20);
        pen.drawString(myGameName, 0, 40);
        pen.drawString(ResourceManager.getString("Score") + myScore, 800, 20);

        paint(pen);
    }

    public String getGameName() {
        return myGameName;
    }

    public int getLevelNum() {
        return myLevelNum;
    }

    /**
     * Never called by you directly, instead called by Java runtime when area of
     * screen covered by this container needs to be displayed (i.e., creation,
     * uncovering, change in status)
     * 
     * @param pen
     *            smart pen to draw on the canvas with
     */
    public void paint(Graphics pen) {
        myActors = myGameModel.getActors();
        for (Actor current : myActors) {
            current.paint(pen);
        }
        if (myGameName.equals("Tetris")) {
            pen.setColor(Color.WHITE);
            pen.fillRect(800, 160, 110, 110);
            pen.setColor(Color.BLACK);
            pen.drawRect(800, 160, 110, 110);
            pen.drawString("Next Piece:", 800, 155);
            pen.drawImage(AddPiece.nextImage(), 855 - AddPiece.nextImage()
                    .getWidth(null) / 2, 215 - AddPiece.nextImage().getHeight(
                    null) / 2, null);
        }
    }

    public void actionPerformed(ActionEvent arg0) {
        update();
        // let Java runtime know panel needs to be repainted
        myCanvas.repaint();
    }

    public void loadNextLevel() {
        myTimer.stop();
        myGameModel.setGameOver(true);
        myLevelNum++;
        new LevelViewer(myGameName, myLevelNum, myScore, myCanvas);
    }

    public void loadEnd(String endCondition) {
        new EndView(endCondition, myGameName, myScore, myCanvas);
    }
}
