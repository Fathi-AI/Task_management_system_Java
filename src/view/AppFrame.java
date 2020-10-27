package view;
import model.AddTaskModel;
import model.Task;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class AppFrame extends JFrame {

    private final int minimumWidth = AddTaskPanel.MIN_WIDTH+TaskTreePanel.MIN_WIDTH+5;
    private final int minimumHeight = Math.max(AddTaskPanel.MIN_HEIGHT,TaskTreePanel.MIN_HEIGHT);
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = 3*screenSize.height/4;
    private int width=3*screenSize.width/4;
    private BasicArrowButton eastFacingArrowButtonToMoveSelectedItemForUpdate;
    private TaskTreePanel task_tree_panel;
    private AddTaskPanel add_task_panel;
    private CommencedTasksPanel comenced_task_panel;
    private BasicArrowButton eastFacingArrowForCompletedTasks;
    private CompletedTasksPanel completed_task_panel;
    private JLabel completeTaskLabel;



    public AppFrame(AddTaskModel model) throws HeadlessException{
        super("TASK MANAGEMENT SYSTEM.");
        setDefaultBehaviour();
        initFields(model);
        this.setVisible(true);
     }


    /**
     * This is used to set the jframe size, closing default behaviour and position on screen
     */
    private void setDefaultBehaviour(){
        getContentPane().setLayout(null);
        this.setLocation((screenSize.width ) / 8,
                (screenSize.height) / 8);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
    }

    /**
     * This method is to add panels onto the frame.
     */
    private void initFields(AddTaskModel model){
        Container pane = this.getContentPane();
        final int rightColumnWidth = this.getWidth()/2;

        final  int rightColumnStart = this.width - rightColumnWidth - 5;
        task_tree_panel  = new TaskTreePanel("TASK MANAGEMENT TREE",0,2,rightColumnStart-10,
                ((2*height)/3)-30,Color.black, true, 17, model);
        task_tree_panel.setLocation(5, 5);
        task_tree_panel.setFocusable(false);
        pane.add(task_tree_panel);

        add_task_panel = new AddTaskPanel("ADD TASK",0, 2, rightColumnWidth,
                ((2*height)/3)-30, Color.blue, true, 16, model);
        add_task_panel.setLocation(rightColumnStart,5);
        add_task_panel.setFocusable(false);
        pane.add(add_task_panel);
        task_tree_panel.setAddTaskPanel(add_task_panel);

        comenced_task_panel = new CommencedTasksPanel("COMMENCED TASKS",0,1,rightColumnStart-50,
                (height/3)-10,Color.blue, true,17, model);
        comenced_task_panel.setLocation(5,height-(height/3)-20);
        comenced_task_panel.setFocusable(false);
        comenced_task_panel.setBackground(Color.white);
        pane.add(comenced_task_panel);
        model.setCommencedTasksPanel(comenced_task_panel);


        completed_task_panel = new CompletedTasksPanel("COMPLETED TASKS",0,1, rightColumnWidth-50,
                (height/3)-10,Color.black, true,17, model);
        completed_task_panel.setLocation(rightColumnStart+50,height-(height/3)-20);
        completed_task_panel.setBackground(Color.white);
        completed_task_panel.setFocusable(false);
        pane.add(completed_task_panel);

        completeTaskLabel = new JLabel("Complete");
        completeTaskLabel.setBounds(this.getWidth()/2-35,
                (5*height/6)+15, 100, 15);
        completeTaskLabel.setBackground(Color.RED);
        completeTaskLabel.setForeground(Color.RED);
        this.add(completeTaskLabel);


        eastFacingArrowForCompletedTasks = new BasicArrowButton(BasicArrowButton.EAST);
        eastFacingArrowForCompletedTasks.setBounds(this.getWidth()/2-26,
                (5*height/6)-30, 50, 40);
        eastFacingArrowForCompletedTasks.setBackground(Color.CYAN);
        eastFacingArrowForCompletedTasks.setEnabled(true);
        eastFacingArrowForCompletedTasks.addActionListener((event)-> {
            Task task = comenced_task_panel.getCommenced();
            if (task == null) {
                return;
            }

            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to mark this Project as complete?", "Completed?", JOptionPane.YES_NO_OPTION);
            if (result != JOptionPane.YES_OPTION) {
                return;

            }

            comenced_task_panel.clear();
            completed_task_panel.addTask(task);
            model.deleteTask(task, (DefaultMutableTreeNode) model.getTaskTreeModel().getRoot());
        });
        this.add(eastFacingArrowForCompletedTasks, BorderLayout.EAST);
    }
}
