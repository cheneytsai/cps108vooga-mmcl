package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import actors.Actor;

@SuppressWarnings("serial")
public class EditorCanvas extends LevelViewer {
    public EditorCanvas(String gameName, int levelNum, int score, Canvas canvas) {
        super(gameName, levelNum, score, canvas);

        myLevelNum = levelNum;
        myCanvas.removeKeyListener(myCanvas.getKeyListeners()[0]);
        myCanvas.addMouseListener(mouseListener());
    }

    public void update() {
    }

    public MouseAdapter mouseListener() {
        MouseAdapter myMouseAdapter = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Actor match = null;
                for (Actor current : myActors) {
                    if (e.getX() >= current.getLeft()
                            && e.getX() <= current.getRight()) {
                        if (e.getY() >= current.getTop()
                                && e.getY() <= current.getBottom()) {
                            match = current;
                            break;
                        }
                    }
                }

                // if nothing there, open popup
                // else,be able to update that object
                if (match == null) {
                    new EditorCreate(myGameModel, getGameName() + "level"
                            + myLevelNum, null, "src/images/Paddle.gif", 20,
                            20, e.getX(), e.getY());
                } else {

                    new EditorCreate(myGameModel, getGameName() + "level"
                            + myLevelNum, match, match.getImageString(), match
                            .getSize().width, match.getSize().height,
                            (int) match.getPosition().getX(), (int) match
                                    .getPosition().getY());
                }
            }
        };
        return myMouseAdapter;
    }
}
