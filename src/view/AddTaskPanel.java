package view;
import model.AddTaskModel;
import model.Task;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilCalendarModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author ganiyuisola
 * @version 20/10/2020
 */
public class AddTaskPanel extends AbstractCommonComponents {

    static final int FIELD_START = 100;
    public static final int MIN_WIDTH = 430;
    public static final int MIN_HEIGHT = 300;
    private JComboBox<String> estimatedTaskDurationDateUnits;

    private JLabel projectName;
    private JTextField projectNameEntry;
    private JLabel taskName;
    private JTextField taskNameEntry;
    private JLabel description;
    private JTextField descriptionEntry;
    private static String projectNameValue;
    private static String projectDescriptionValue;
    private JButton addTaskButton;
    private JButton clearTaskButton;
    private JButton taskUpdateButton;
    private JLabel subTask1;
    private JTextField subTask1NameEntry;
    private JLabel subTask2;
    private JTextField subTask2NameEntry;
    private JLabel subTask3;
    private JTextField subTask3NameEntry;
    private JLabel subTask4;
    private JTextField subTask4NameEntry;
    private JTextField subTask5NameEntry;
    private JLabel subTask5;
    private JLabel taskDueDate;
    private JLabel taskDueTime;
    private JTextField taskDueTimeEntry;
    private JComboBox importanceDropDownComponent;
    JSpinner hour;
    JSpinner minutes;
    private JLabel lowAndMediumPriorityRangeDescription;
    private JLabel taskImportance;
    private JLabel highAndHighestPriotityRangeDescription;
    private JLabel estimatedTaskDuration;
    private JTextField estimatedTaskDurationField;
    private UtilCalendarModel dateModel;
    private JDatePanelImpl datePanel;
    JDatePickerImpl datePicker;

    private static String taskNameValue;
    private static String subTask1Value;
    private static String subTask2Value;
    private static String subTask3Value;
    private static String subTask4Value;
    private static String subTask5Value;
    private static int hourValue;
    private static int minutesValue;
    private static int selectedImportanceNumberValue;
    private static String taskDurationValue;
    private AddTaskModel model;



    //Need AddTaskData model here to use to call the addTask after the event
    public AddTaskPanel(String addTaskPanelTitle, int x, int y, int width, int height, Color color, Boolean createBorder, int boundsHeight, AddTaskModel model) {
        super(addTaskPanelTitle, x, y, width, height, color, createBorder, boundsHeight);
        this.model = model;

        //Project Name and field
        createJLabel(projectName, "Project Name: *", 5, 20, FIELD_START - 2, 15, color);
        projectNameEntry = new JTextField();
        projectNameEntry.setBounds(FIELD_START, 18, 3 * (this.getWidth() - 150) / 4, 20);
        this.add(projectNameEntry);


        //Description and field name.
        createJLabel(description, "Description: *", 5, 45, FIELD_START - 2, 15, color);
        descriptionEntry = new JTextField();
        descriptionEntry.setBounds(FIELD_START, 43, this.getWidth() - 150, 20);
        this.add(descriptionEntry);

        addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(this.getWidth()/4-50, height-42, 100, 40);
        addTaskButton.setEnabled(true);
        addTaskButton.setBackground(Color.WHITE);
        addTaskButton.setForeground(Color.BLUE);
        addTaskButton.addActionListener(this::addTaskEvent);
        this.add(addTaskButton);

        taskUpdateButton = new JButton("Update");
        taskUpdateButton.setBounds(this.getWidth()/2-50, height-42, 100, 40);
        taskUpdateButton.setEnabled(true);
        taskUpdateButton.setBackground(Color.WHITE);
        taskUpdateButton.setForeground(Color.BLUE);
        taskUpdateButton.addActionListener(this::updateTask);
        this.add(taskUpdateButton);


        clearTaskButton = new JButton("Clear Field");
        clearTaskButton.setBounds(3*this.getWidth()/4-50, height-42, 100,40);
        clearTaskButton.setEnabled(true);
        clearTaskButton.setBackground(Color.WHITE);
        clearTaskButton.setForeground(Color.BLUE);
        clearTaskButton.addActionListener((event)-> this.clearField());
        this.add(clearTaskButton);


        //Task Name Label and field name
        createJLabel(taskName, "Task Name: *",5 , 70, FIELD_START - 2, 15, color);
        taskNameEntry = new JTextField();
        taskNameEntry.setBounds(FIELD_START , 68, 3*(this.getWidth()-150)/4, 20);
        this.add(taskNameEntry);
       
        //sub-task 1 (Needs to be made mandatory)
        createJLabel(subTask1, "Sub Task 1: *",5, 95, FIELD_START - 2, 15, color);
        subTask1NameEntry = new JTextField();
        subTask1NameEntry.setBounds(FIELD_START , 93, 3*(this.getWidth()-150)/4, 20);
        this.add(subTask1NameEntry);


        //sub-task 2 (optional)
        createJLabel(subTask2, "Sub Task 2:",5 , 120, FIELD_START - 2, 15, color);
        subTask2NameEntry = new JTextField();
        subTask2NameEntry.setBounds(FIELD_START , 118, 3*(this.getWidth()-150)/4, 20);
        this.add(subTask2NameEntry);


        //sub-task 3 (optional)
        createJLabel(subTask3, "Sub Task 3:",5 , 145, FIELD_START - 2, 15, color);
        subTask3NameEntry = new JTextField();
        subTask3NameEntry.setBounds(FIELD_START , 143, 3*(this.getWidth()-150)/4, 20);
        this.add(subTask3NameEntry);


        //sub-task 4 (optional)
        createJLabel(subTask4, "Sub Task 4:",5 , 170, FIELD_START - 2, 15, color);
        subTask4NameEntry = new JTextField();
        subTask4NameEntry.setBounds(FIELD_START , 168, 3*(this.getWidth()-150)/4, 20);
        this.add(subTask4NameEntry);



        //sub-task 5 (optional)
        createJLabel(subTask5, "Sub Task 5:",5 , 195, FIELD_START - 2, 15, color);
        subTask5NameEntry = new JTextField();
        subTask5NameEntry.setBounds(FIELD_START , 193, 3*(this.getWidth()-150)/4, 20);
        this.add(subTask5NameEntry);

        //Task due date field
        dateModel = new UtilCalendarModel();
        datePanel = new JDatePanelImpl(dateModel);
        datePicker = new JDatePickerImpl(datePanel);
        datePicker.setBounds(FIELD_START, 218, 50+(this.getWidth()-150)/3, 25);
        this.add(datePicker);
        createJLabel(taskDueDate, "Due Date: *",5, 225, FIELD_START -2, 15, color);


        //Task due time field
        createJLabel(taskDueTime, "Due Time: *",130+(this.getWidth()-220)/2, 225, 80 , 15, color);
        hour = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        hour.setBounds(195+(this.getWidth()-220)/2,220,(this.getWidth()-220)/5, 25);
        this.add(hour);
        createJLabel(taskDueTime, ":",195+(this.getWidth()-220)/5+(this.getWidth()-220)/2, 220, 5, 25, color);
        minutes = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
        minutes.setBounds(200+(this.getWidth()-220)/5+(this.getWidth()-220)/2,220,(this.getWidth()-220)/5, 25);
        this.add(minutes);


        //Importance field and combo box.
        createJLabel(taskImportance, "Importance: *",5, 255, FIELD_START - 2, 15, color);
        importanceDropDownComponent = new JComboBox<>(AddTaskModel.IMPORTANCE_MODEL);
        importanceDropDownComponent.setBounds(FIELD_START, 255, (this.getWidth()-120)/3, 20);
        this.add(importanceDropDownComponent);

        createJLabel(lowAndMediumPriorityRangeDescription, "[0 - 3 : LOW]        " +
                        "[4 - 6 : MEDIUM]",
                110+(this.getWidth()-120)/3, 253, 2*(this.getWidth()-120)/3, 15, color);

        createJLabel(highAndHighestPriotityRangeDescription, "[7 - 9 : HIGH]       " +
                        "[10 : HIGHEST]",
                110+(this.getWidth()-120)/3, 268, 2*(this.getWidth()-120)/3, 15, color);


        //Estimated Duration and field name.
        createJLabel(estimatedTaskDuration, "Est. Duration: *",5, 288, FIELD_START-2, 15, color);
        estimatedTaskDurationField = new JTextField();
        estimatedTaskDurationField.setBounds(FIELD_START, 290, (this.getWidth()-220)/2, 20);
        estimatedTaskDurationDateUnits = new JComboBox<>(AddTaskModel.DATE_UNIT);
        estimatedTaskDurationDateUnits.setBounds(120+(this.getWidth()-220)/2, 290, 100, 20);
        this.add(estimatedTaskDurationField);
        this.add(estimatedTaskDurationDateUnits);
    }


    private void addTaskEvent(ActionEvent actionEvent) {
        if (!areFieldsValidated())
            return;

        Calendar date = dateModel.getValue();
        Date newDate = date.getTime();

        int numberofDuration=0;

        if (!estimatedTaskDurationField.getText().trim().isEmpty()){
            numberofDuration = Integer.parseInt(estimatedTaskDurationField.getText());
        }
        else if (estimatedTaskDurationField.getText().trim().isEmpty()) {
            numberofDuration = 0;
        }

        Task newTask = new Task(projectNameEntry.getText(), descriptionEntry.getText(),
                importanceDropDownComponent.getSelectedIndex(),
                (Integer)this.hour.getValue(), (Integer) this.minutes.getValue(),taskNameEntry.getText(), newDate,
                subTask1NameEntry.getText(), subTask2NameEntry.getText(), subTask3NameEntry.getText(),
                subTask4NameEntry.getText(), subTask5NameEntry.getText(), numberofDuration, estimatedTaskDurationDateUnits.getSelectedIndex() );
        model.addTask(newTask);
        clearField();
    }


    private void updateTask(ActionEvent event) {
        if (!areFieldsValidated())
            return;

        Calendar date = dateModel.getValue();
        Date newDate = date.getTime();
        Task task = model.getTaskByName(projectNameEntry.getText());
        if (task == null) {
            JOptionPane.showMessageDialog(this, "Cannot update project name.", "Update Task Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (task.completed) {
            JOptionPane.showMessageDialog(this, "Cannot update completed task.", "Update Task Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to update?", "Update?", JOptionPane.YES_NO_OPTION);
        if (result != JOptionPane.YES_OPTION) {
            return;

        }
        task.description = descriptionEntry.getText();
        task.taskName = taskNameEntry.getText();
        task.subTask1 = subTask1NameEntry.getText();
        task.subTask2 = subTask2NameEntry.getText();
        task.subTask3 = subTask3NameEntry.getText();
        task.subTask4 = subTask4NameEntry.getText();
        task.subTask5 = subTask5NameEntry.getText();
        task.importance = importanceDropDownComponent.getSelectedIndex();
        task.hour = (Integer) this.hour.getValue();
        task.minutes = (Integer) this.minutes.getValue();
        task.estimatedDuration = Integer.parseInt(estimatedTaskDurationField.getText());
        task.estimatedDurationUnits= estimatedTaskDurationDateUnits.getSelectedIndex();
        task.date = new Date();
        model.taskUpdated(task);
        clearField();
    }

    private boolean areFieldsValidated() {
            if (projectNameEntry.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Project Name is missing", "Empty Required Field", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (descriptionEntry.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Description is missing", "Empty Required Field", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (taskNameEntry.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Task Name is missing", "Empty Required Field", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (subTask1NameEntry.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "One Subtask must be entered", "Empty Required Field", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (datePicker.getJFormattedTextField().getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Due Date is missing", "Empty Required Field", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (estimatedTaskDurationField.getText().trim().isEmpty() || estimatedTaskDurationField.getText().contains(" ")){
                JOptionPane.showMessageDialog(this, "Est. Duration is missing", "Empty Required Field", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            boolean isNumeric = estimatedTaskDurationField.getText().chars().allMatch(Character::isDigit);
            if (!isNumeric){
                JOptionPane.showMessageDialog(this, "Est. Duration has to be a number.", "Empty Required Field", JOptionPane.ERROR_MESSAGE);
                return false;
            }


        return true;
    }

    private void clearField()
    {
        projectNameEntry.setText(null);
        descriptionEntry.setText(null);
        taskNameEntry.setText(null);
        subTask1NameEntry.setText(null);
        subTask2NameEntry.setText(null);
        subTask3NameEntry.setText(null);
        subTask4NameEntry.setText(null);
        subTask5NameEntry.setText(null);
        datePicker.getJFormattedTextField().setText(null);
        importanceDropDownComponent.setSelectedItem(0);
        estimatedTaskDurationField.setText(null);
    }

    public void showTask(Task task) {

        projectNameEntry.setText(task.projectName);
        descriptionEntry.setText(task.description);
        taskNameEntry.setText(task.taskName);
        subTask1NameEntry.setText(task.subTask1);
        subTask2NameEntry.setText(task.subTask2);
        subTask3NameEntry.setText(task.subTask3);
        subTask4NameEntry.setText(task.subTask4);
        subTask5NameEntry.setText(task.subTask5);
        datePicker.getJFormattedTextField().setText(task.date.toString());
        hour.setValue(task.hour);
        minutes.setValue(task.minutes);
        importanceDropDownComponent.setSelectedItem(task.importance);
        estimatedTaskDurationField.setText(Integer.toString(task.estimatedDuration));

    }


}



