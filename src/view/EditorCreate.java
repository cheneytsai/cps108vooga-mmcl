package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.EditorModel;
import actors.Actor;
import util.reflection.Reflection;
import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class EditorCreate extends JFrame
{
    private Dimension mySize = new Dimension(250, 250);
    private JButton myButton;
    private String[] actorStats;
    private EditorModel myModel;
    private JTextField myField;
    private JTextField[] myPoint;
    private JTextField[] myDimension;
    private JComboBox myBox;
    
    public EditorCreate(EditorModel model, int x, int y)
    {
        setTitle(ResourceManager.getString("EditorTitle"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        actorStats = new String[6];
        myModel = model;
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(7,1));
        p.add(actorChooser());
        p.add(setImage());
        p.add(setDimension());
        p.add(setPoint(x,y));
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
    
    private JComponent setImage()
    {
        myField = new JTextField(30);
        
        myField.setText("src/images/Brick4.gif");
        
        return myField;
    }
    
    private JComponent setDimension()
    {
        JPanel panel = new JPanel();
        myDimension = new JTextField[2];

        myDimension[0] = new JTextField();
        myDimension[0].setText("16");
        myDimension[1] = new JTextField();
        myDimension[1].setText("16");
        
        panel.add(myDimension[0]);
        panel.add(myDimension[1]);
        
        return panel;
    }
    
    private JComponent setPoint(int x, int y)
    {
        JPanel panel = new JPanel();
        myPoint = new JTextField[2];

        myPoint[0] = new JTextField();
        myPoint[0].setText(""+x);
        myPoint[1] = new JTextField();
        myPoint[1].setText(""+y);
        
        panel.add(myPoint[0]);
        panel.add(myPoint[1]);
        
        return panel;
    }
    
    private JComponent makeButton()
    {
        myButton = new JButton("Create!");
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
        actorStats[0] = (String) myBox.getSelectedItem();//"Ball";
        actorStats[1] = myField.getText();
        actorStats[2] = myDimension[0].getText();
        actorStats[3] = myDimension[1].getText();
        actorStats[4] = myPoint[0].getText();
        actorStats[5] = myPoint[1].getText();
        
        createInstance();
    }
    
    public void createInstance()
    {
        myModel.add((Actor) Reflection.createInstance(
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
