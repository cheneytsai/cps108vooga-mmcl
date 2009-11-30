package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
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
public class EditorCreate extends JFrame
{
    private Dimension mySize = new Dimension(250, 250);
    private JButton myButton;
    private String[] actorStats;
    private GameModel myModel;
    private JTextField myField;
    private JTextField[] myDimPoint;
    private JComboBox myBox;
    
    public EditorCreate(GameModel model, Actor actor, 
                        String image, int xDim, int yDim, 
                        int x, int y)
    {
        setTitle(ResourceManager.getString("EditorTitle"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        actorStats = new String[6];
        myModel = model;
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(7,1));
        if(actor == null)
        {
            p.add(actorChooser());
        }
        p.add(setImage(image));
        p.add(setDimPoint(xDim,yDim,x,y));
        p.add(makeButton());
        getContentPane().add(p,BorderLayout.NORTH);

        setSize(mySize);
        setPreferredSize(mySize);
        
        setVisible(true);
    }
    
    private JComponent actorChooser()
    {
        String[] classNames = {"Ball","Brick","Paddle"};
        
        myBox = new JComboBox(classNames);
        
        return myBox;
    }
    
    private JComponent setImage(String name)
    {
        myField = new JTextField(30);
        
        myField.setText(name);
        
        return myField;
    }
    
    private JComponent setDimPoint(int xDim,int yDim,int x,int y)
    {
        JPanel panel = new JPanel();
        myDimPoint = new JTextField[4];

        int[] showValue = {xDim, yDim, x,y};
        for(int i = 0; i < showValue.length; i++)
        {
            myDimPoint[i] = new JTextField();
            myDimPoint[i].setText(""+xDim);
            panel.add(myDimPoint[i]);
        }
        
        return panel;
    }
    
    private JComponent makeButton()
    {
        myButton = new JButton(ResourceManager.getString("LevelEditButton"));
        myButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                setStats();
            }
        });
        
        return myButton;
    }
    
    public void setStats()
    {
        actorStats[0] = (String) myBox.getSelectedItem();
        actorStats[1] = myField.getText();
        for(int i = 0; i < myDimPoint.length; i++)
        {
            actorStats[i+2] = myDimPoint[i].getText();
        }
        
        createInstance();
    }
    
    public void createInstance()
    {
        myModel.addActor((Actor) Reflection.createInstance(
                "actors."+actorStats[0],
                actorStats[1],
                new Dimension(Integer.parseInt(actorStats[2]),Integer.parseInt(actorStats[3])),
                new Point(Integer.parseInt(actorStats[4]), Integer.parseInt(actorStats[5])),
                myModel));
        try
        {
            FileWriter output = new FileWriter("src/resources/ArkanoidLevel1.level",true);
            output.append("\nactors."+actorStats[0]+" "+actorStats[1]+" "+actorStats[2]+" "
                        +actorStats[3]+" "+actorStats[4]+" "+actorStats[5]);
            output.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
