package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import actors.Actor;
import util.reflection.Reflection;
import util.resources.ResourceManager;

@SuppressWarnings("serial")
public class EditorCreate extends JFrame
{
    private Dimension mySize = new Dimension(600, 600);
    private JButton myButton;
    
    public EditorCreate(int x, int y)
    {
        setTitle(ResourceManager.getString("EditorTitle"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JPanel p = new JPanel();
        p.add(actorChooser());
        p.add(setImage());
        p.add(setInt("xDimension"));
        p.add(setInt("yDimension"));
        p.add(setInt(x+""));
        p.add(setInt(y+""));
        p.add(makeButton());
        getContentPane().add(p,BorderLayout.NORTH);

        setSize(mySize);
        setPreferredSize(mySize);
        
        setVisible(true);
    }
    
    private JComponent actorChooser()
    {
        String[] classNames = {"Ball","Brick","Paddle"};
        
        return new JComboBox(classNames);
    }
    
    private JComponent setImage()
    {
        JTextField myField = new JTextField(30);
        
        myField.setText("src/resources/insertClassNameHere.gif");
//        
//        myField.addKeyListener(new KeyListener() 
//        {
//            public void keyPressed(KeyEvent e)
//            {
//                int key = e.getKeyCode();
//            } 
//
//            public void keyReleased(KeyEvent e) {}
//            
//            public void keyTyped(KeyEvent e){}
//        }); 
        
        return myField;
    }
    
    private JComponent setInt(String location)
    {
        JTextField point = new JTextField(); 
        
        point.setText(""+location);
        
        return point;
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
        System.out.println("setting stats");
    }
    
    public String[] getStats()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
