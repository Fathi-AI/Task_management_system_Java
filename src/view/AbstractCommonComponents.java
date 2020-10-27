package view;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class AbstractCommonComponents extends JPanel {
    private JButton clearTaskButton;
    private JButton taskUpdateButton;
    private JButton addTaskButton;
    private JButton startTaskButton;
    private JButton searchButton;
    private JButton deleteTaskButton;


    //common functionality between panel is added here
    public AbstractCommonComponents(String panelTitle , int x, int y, int width, int height, Color color, Boolean createTitleBoder, int boundsHeight)
    {
        this.setLayout(null);
        this.setSize(width, height);
        this.setBorder(BorderFactory.createLineBorder(color));
        JLabel panelLabel = new JLabel(panelTitle, SwingConstants.CENTER);
        if(createTitleBoder == true){Border blackLine = BorderFactory.createTitledBorder(""); panelLabel.setBorder(blackLine);}
        panelLabel.setBounds(x, y, width, boundsHeight);
        panelLabel.setForeground(color);
        panelLabel.setFocusable(false);
        this.add(panelLabel);
    }




    protected JLabel createJLabel(JLabel labelName, String title,int x , int y, int width, int height, Color color){
        labelName = new JLabel(title);
        labelName.setBounds(x, y, width, height);
        labelName.setBackground(color);
        labelName.setForeground(color);
        labelName.setFocusable(false);
        this.add(labelName);

        return labelName;
    }

}
