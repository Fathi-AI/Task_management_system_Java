package view;

import model.AddTaskModel;
import model.Task;

import javax.swing.*;
import java.awt.*;

public class CommencedTasksPanel extends AbstractCommonComponents {

    public static final int MIN_WIDTH = 400;
    public static final int MIN_HEIGHT = 200;

    private JLabel projectName;
    private JLabel description;
    private JLabel dueDateTime;
    private JLabel importance;
    private JLabel estDuration;
    private JLabel taskName;
    private JLabel subTask1;
    private JLabel subTask2;
    private JLabel subTask3;
    private JLabel subTask4;
    private JLabel subTask5;

    private Task commenced;

    public CommencedTasksPanel(String commencedTaskPanelTitle, int x, int y, int width, int height, Color color, Boolean createBorder, int boundsHeight, AddTaskModel model){
        super(commencedTaskPanelTitle,x,y,width, height,color,createBorder,boundsHeight);

        projectName = createJLabel(null, "", x, y + boundsHeight, width, 15, color);
        description = createJLabel(null, "", x, y + boundsHeight + 20, width, 15, color);
        dueDateTime = createJLabel(null, "", x, y + boundsHeight + 40, width, 15, color);
        importance = createJLabel(null, "", x, y + boundsHeight + 80, width, 15, color);
        estDuration = createJLabel(null, "", x, y + boundsHeight + 100, width, 15, color);
        taskName = createJLabel(null, "", x, y + boundsHeight + 130, width, 15, color);
        subTask1 = createJLabel(null, "", x, y + boundsHeight + 150, width, 15, color);
        subTask2 = createJLabel(null, "", x, y + boundsHeight + 170, width, 15, color);
        subTask3 = createJLabel(null, "", x, y + boundsHeight + 190, width, 15, color);
        subTask4 = createJLabel(null, "", x, y + boundsHeight + 210, width, 15, color);
        subTask5 = createJLabel(null, "", x, y + boundsHeight + 230, width, 15, color);

    }


    public boolean showTask(Task task) {
        if (commenced != null)
            return false;

        clear();
        this.commenced = task;
        task.commenced = true;

        reload();

        return true;
    }

    public void clear() {
        if (commenced != null) {
            this.commenced.commenced = false;
            this.commenced = null;
        }

        projectName.setText("");
        description.setText("");
        dueDateTime.setText("");
        importance.setText("");
        estDuration.setText("");
        taskName.setText("");
        subTask1.setText("");
        subTask2.setText("");
        subTask3.setText("");
        subTask4.setText("");
        subTask5.setText("");
    }

    public void reload() {
        projectName.setText(this.commenced.projectName);
        description.setText(this.commenced.description);
        dueDateTime.setText(String.format("Due at: %s :: %d:%d", this.commenced.date, this.commenced.hour, this.commenced.minutes));
        importance.setText(String.format("Importance: %d/10", this.commenced.importance));
        estDuration.setText(String.format("Estimated to complete in: %s", this.commenced.estimatedDuration));
        taskName.setText("Task Name: " + this.commenced.taskName);
        subTask1.setText(String.format("- Sub Task 1: %s", this.commenced.subTask1));
        if (!this.commenced.subTask2.isEmpty()) {
            subTask2.setText(String.format("- Sub Task 2: %s", this.commenced.subTask2));
        }
        if (!this.commenced.subTask3.isEmpty()) {
            subTask3.setText(String.format("- Sub Task 3: %s", this.commenced.subTask3));
        }
        if (!this.commenced.subTask4.isEmpty()) {
            subTask4.setText(String.format("- Sub Task 4: %s", this.commenced.subTask4));
        }
        if (!this.commenced.subTask5.isEmpty()) {
            subTask5.setText(String.format("- Sub Task 5: %s", this.commenced.subTask5));
        }
    }

    public Task getCommenced() {
        return commenced;
    }
}
