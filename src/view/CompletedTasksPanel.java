package view;

import model.AddTaskModel;
import model.Task;

import javax.swing.*;
import java.awt.*;

public class CompletedTasksPanel extends AbstractCommonComponents{

    public static final int MIN_WIDTH = 480;
    public static final int MIN_HEIGHT = 200;

    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    public CompletedTasksPanel(String completedTaskPanelTitle, int x, int y, int width, int height, Color color, Boolean createBorder, int boundsHeight, AddTaskModel model){
        super(completedTaskPanelTitle,x,y,width, height,color,createBorder,boundsHeight);

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setBounds(x+6, y+23, width-2, height-20);
        taskList.setDragEnabled(false);
        taskList.setBackground(null);
        this.add(taskList);
    }

    public void addTask(Task task) {
        task.completed = true;
        listModel.addElement(task.projectName);
    }
}
