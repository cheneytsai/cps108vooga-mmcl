package view;

import gameengine.GameModel;

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
import actors.Actor;
import util.reflection.Reflection;
import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class EditorCreate extends JFrame
{
    private Dimension mySize = new Dimension(300, 300);
    private JButton myButton;
    private String[] actorStats;
    private String[] statAppend;
    private Actor myActor;
    private GameModel myModel;
//    private JTextField myField;
    private JComboBox myImage;
    private String mySaveFile;
    private JTextField[] myDimPoint;
    private JComboBox myBox;
    private JCheckBox myCheckBox;

    public EditorCreate(GameModel model, String levelName, String saveFile,Actor actor,
            String image, int xDim, int yDim, int x, int y)
    {
        setTitle(ResourceManager.getString("EditorTitle"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        actorStats = new String[6];
        String toAppend = "Actor Type: , ,x Dimension: ,y Dimension: ,x Value: ,y Value: ";
        statAppend = toAppend.split(",");
        myModel = model;
        myActor = actor;
        mySaveFile = saveFile;

        copyOriginalFile(levelName);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1));
        panel.add(actorChooser(levelName));
        panel.add(setImage());
        panel.add(setDimPoint(xDim, yDim, x, y));
        if (actor != null)
        {
            panel.add(setDeleteOption());
            panel.add(makeDeleteButton(levelName));
        }
        panel.add(makeCreateButton(levelName));
        getContentPane().add(panel, BorderLayout.NORTH);

        setSize(mySize);
        setPreferredSize(mySize);

        setVisible(true);
    }
    
    private void copyOriginalFile(String levelName)
    {
        if(!ResourceManager.getString(levelName).equals(mySaveFile))
        {
            try
            {
                File oldFile = new File(ResourceManager.getString(levelName));
                File newFile = new File(mySaveFile);

                BufferedReader br = new BufferedReader(new FileReader(oldFile));
                PrintWriter pw = new PrintWriter(new FileWriter(newFile));

                String line = null;

                while ((line = br.readLine()) != null)
                {
                    pw.println(line);
                    pw.flush();
                }
                pw.close();
                br.close();

            } catch (FileNotFoundException e)
            {
                System.out.println(e.getMessage());
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    private JComponent actorChooser(String levelName)
    {
        String[] classNames = ResourceManager.getString(
                levelName.substring(0, levelName.indexOf("level"))
                        + "Editables").split(",");

        myBox = new JComboBox(classNames);

        return myBox;
    }

    private JComponent setImage()
    {
        File folder = new File("src/images");
        File[] listOfFiles = folder.listFiles();
        
        //the first file is src/images/svn...we thought this image would be better, and wouldn't crash
        listOfFiles[0] = new File("src/resources/2150_lightning.jpg");
        
        myImage = new JComboBox(listOfFiles);

        return myImage;
    }

    private JComponent setDimPoint(int xDim, int yDim, int x, int y)
    {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        myDimPoint = new JTextField[4];

        int[] showValue =
        { xDim, yDim, x, y };

        for (int i = 0; i < showValue.length; i++)
        {
            myDimPoint[i] = new JTextField();
            myDimPoint[i].setText(statAppend[i + 2] + showValue[i]);
            panel.add(myDimPoint[i]);
        }
        return panel;
    }

    private JComponent setDeleteOption()
    {
        myCheckBox = new JCheckBox("Delete Old Actor?");
        myCheckBox.setSelected(false);

        return myCheckBox;
    }

    private JComponent makeCreateButton(final String levelName)
    {
        myButton = new JButton(ResourceManager.getString("EditButtonCreate"));
        myButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
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
        actorStats[1] = myImage.getSelectedItem().toString();
        for (int i = 0; i < myDimPoint.length; i++)
        {
            actorStats[i + 2] = myDimPoint[i].getText();
        }

        for (int i = 0; i < actorStats.length; i++)
        {
            if (actorStats[i].contains(statAppend[i]))
            {
                actorStats[i] = actorStats[i].replace(statAppend[i], "");
            }
        }

        createInstance(levelName);
    }

    public void createInstance(String levelName)
    {
        if (myCheckBox != null && myCheckBox.isSelected())
        {
            deleteOldActor(levelName);
        }

        try
        {
            
            // TODO add catches for illformatting
            myModel.addActor((Actor) Reflection.createInstance("actors."
                    + actorStats[0], actorStats[1], new Dimension(Integer
                            .parseInt(actorStats[2]), Integer.parseInt(actorStats[3])),
                            new Point(Integer.parseInt(actorStats[4]), Integer
                                    .parseInt(actorStats[5])), myModel));
            try
            {
                FileWriter output = new FileWriter(mySaveFile,true);
                output.append("\nactors." + actorStats[0] + " " + actorStats[1]
                                                                             + " " + actorStats[2] + " " + actorStats[3] + " "
                                                                             + actorStats[4] + " " + actorStats[5]);
                output.close();
                dispose();
            } catch (FileNotFoundException e)
            {
                System.out.println(e.getMessage());
            } catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        catch(NumberFormatException e)
        {
            System.out.println(e.getMessage());
            dispose();
        }
    }

    private void deleteOldActor(String levelName)
    {
        int matchingIndex = myModel.getActors().size();
        for (int i = 0; i < myModel.getActors().size(); i++)
        {
            if (myModel.getActors().get(i).equals(myActor))
            {
                matchingIndex = i;
                break;
            }
        }
        myModel.remove(myActor);
        dispose();

        try
        {
            File inFile = new File(mySaveFile);

            // Construct the new file that will later be renamed to the original
            // filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(inFile));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            // Read from the original file and write to the new
            // unless content matches data to be removed.
            int lineCount = 0;
            while ((line = br.readLine()) != null)
            {
                lineCount++;
                if (lineCount != matchingIndex + 1)
                {

                    pw.println(line);
                    pw.flush();
                }
            }
            pw.close();
            br.close();

            inFile.delete();
            tempFile.renameTo(inFile);
        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
