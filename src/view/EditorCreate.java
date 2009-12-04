package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.GameModel;
import actors.Actor;
import util.reflection.Reflection;
import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class EditorCreate extends JFrame {
    private Dimension mySize = new Dimension(300, 300);
    private JButton myButton;
    private String[] actorStats;
    private String[] statAppend;
    private Actor myActor;
    private GameModel myGameModel;
    private JTextField myField;
    private JTextField[] myDimPoint;
    private JComboBox myBox;
    private JCheckBox myCheckBox;

    public EditorCreate(GameModel model, String levelName, Actor actor,
            String image, int xDim, int yDim, int x, int y) {
        setTitle(ResourceManager.getString("EditorTitle"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        actorStats = new String[6];
        String toAppend = "Actor Type: ,Image Location: ,x Dimension: ,y Dimension: ,x Value: ,y Value: ";
        statAppend = toAppend.split(",");
        myGameModel = model;
        myActor = actor;

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(7, 1));
        p.add(actorChooser(levelName));
        p.add(setImage(image));
        p.add(setDimPoint(xDim, yDim, x, y));
        if (actor != null) {
            p.add(setDeleteOption());
            p.add(makeDeleteButton(levelName));
        }
        p.add(makeCreateButton(levelName));
        getContentPane().add(p, BorderLayout.NORTH);

        setSize(mySize);
        setPreferredSize(mySize);

        setVisible(true);
    }

    private JComponent actorChooser(String levelName) {
        String[] classNames = ResourceManager.getString(
                levelName.substring(0, levelName.indexOf("level"))
                        + "Editables").split(",");

        myBox = new JComboBox(classNames);

        return myBox;
    }

    private JComponent setImage(String name) {
        myField = new JTextField(50);

        myField.setText(statAppend[1] + name);

        return myField;
    }

    private JComponent setDimPoint(int xDim, int yDim, int x, int y) {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        myDimPoint = new JTextField[4];

        int[] showValue = { xDim, yDim, x, y };

        for (int i = 0; i < showValue.length; i++) {
            myDimPoint[i] = new JTextField();
            myDimPoint[i].setText(statAppend[i + 2] + showValue[i]);
            panel.add(myDimPoint[i]);
        }
        return panel;
    }

    private JComponent setDeleteOption() {
        myCheckBox = new JCheckBox("Delete Old Actor?");
        myCheckBox.setSelected(false);

        return myCheckBox;
    }
    
    private JComponent makeCreateButton(final String levelName)
    {
        myButton = new JButton(ResourceManager.getString("EditButtonCreate"));
        myButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setStats(levelName);
            }
        });

        return myButton;
    }

    
    private JComponent makeDeleteButton(final String levelName)
    {
        myButton = new JButton(ResourceManager.getString("EditButtonDestroy"));
        myButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                deleteOldActor(levelName);
            }
        });
        
        return myButton;
    }
    
    public void setStats(String levelName)
    {
        actorStats[0] = (String) myBox.getSelectedItem();
        actorStats[1] = myField.getText();
        for (int i = 0; i < myDimPoint.length; i++) {
            actorStats[i + 2] = myDimPoint[i].getText();
        }

        for (int i = 0; i < actorStats.length; i++) {
            if (actorStats[i].contains(statAppend[i])) {
                actorStats[i] = actorStats[i].replace(statAppend[i], "");
            }
        }

        createInstance(levelName);
    }

    public void createInstance(String levelName) {
        if (myCheckBox != null && myCheckBox.isSelected()) {
            deleteOldActor(levelName);
        }

        // TODO add catches for illformatting
        myGameModel.addActor((Actor) Reflection.createInstance("actors."
                + actorStats[0], actorStats[1], new Dimension(Integer
                .parseInt(actorStats[2]), Integer.parseInt(actorStats[3])),
                new Point(Integer.parseInt(actorStats[4]), Integer
                        .parseInt(actorStats[5])), myGameModel));
        try {
            FileWriter output = new FileWriter(ResourceManager
                    .getString(levelName), true);
            output.append("\nactors." + actorStats[0] + " " + actorStats[1]
                    + " " + actorStats[2] + " " + actorStats[3] + " "
                    + actorStats[4] + " " + actorStats[5]);
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteOldActor(String levelName) {
        int matchingIndex = myGameModel.getActors().size();
        for (int i = 0; i < myGameModel.getActors().size(); i++) {
            if (myGameModel.getActors().get(i).equals(myActor)) {
                matchingIndex = i;
                break;
            }
        }
        myGameModel.remove(myActor);

        try {
            File inFile = new File(ResourceManager.getString(levelName));

            // Construct the new file that will later be renamed to the original
            // filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(inFile));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            // Read from the original file and write to the new
            // unless content matches data to be removed.
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                lineCount++;
                if (lineCount != matchingIndex + 1) {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            inFile.delete();
            tempFile.renameTo(inFile);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
