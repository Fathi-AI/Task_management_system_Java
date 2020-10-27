package model;

import view.CommencedTasksPanel;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.text.SimpleDateFormat;
import java.util.*;


public class AddTaskModel {

    private String projectName;
    private String projectDescription;
    private String taskName;
    private String subTask1Value;
    private String subTask2Value;
    private String subTask3Value;
    private String subTask4Value;
    private String subTask5Value;
    private Calendar date;
    private int dueHour;
    private int dueMinutes;
    private int taskImportance;
    private String taskDurationValue;
    DefaultMutableTreeNode projName;
    DefaultTreeModel treeModel;
    DefaultTreeModel dfModel;
    DefaultMutableTreeNode root;

    public ArrayList<Task> newTasks;

    private TaskTreeModel taskTreeModel;
    private CommencedTasksPanel commencedTasksPanel;

    public static final Integer IMPORTANCE_MODEL[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static final String DATE_UNIT[] = {"Day(s)","Week(s)","Month(s)","Year(s)"};
    private Date dateTime;


    public AddTaskModel() {
        newTasks = new ArrayList<>();
        taskTreeModel = new TaskTreeModel();

    }

    public void addTask(Task t)
    {
        newTasks.add(t);

        SortTree();
    }


    public Task getTaskByName(String name) {
        for (Task task : newTasks) {
            if (task.projectName.equals(name)) {
                return task;
            }
        }

        return null;
    }

    public void deleteTask(Task task, TreeNode taskRoot) {
        newTasks.remove(task);
        if (task.commenced) {
            commencedTasksPanel.clear();
        }

        if (newTasks.isEmpty()) {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode)taskTreeModel.getRoot();
            root.removeAllChildren();
            taskTreeModel.fireLastNodeRemoved(taskRoot);
        } else {
            SortTree();
        }
    }

    public void taskUpdated(Task task) {
        SortTree();

        if (task.commenced) {
            commencedTasksPanel.reload();
        }
    }



    public void SortTree(){

//        sort array list by due date

        Collections.sort(newTasks, Comparator.comparing(Task::getImportance).thenComparing(Task::getLatestStartDate));

       // Clear JTree of current Tasks
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)taskTreeModel.getRoot();
        root.removeAllChildren();
//        Pass new tasks into JTree
        for (Task task:newTasks){
            SimpleDateFormat simpleDate = new SimpleDateFormat("dd MMM , yyyy.");
            String myDate = simpleDate.format(task.date);
            DefaultMutableTreeNode projectName = new DefaultMutableTreeNode("Project Name: "+task.projectName);
            DefaultMutableTreeNode parentDescription = new DefaultMutableTreeNode("Project Description: "+task.description);
            DefaultMutableTreeNode parentTaskName = new DefaultMutableTreeNode("Task Name: "+task.taskName);
            DefaultMutableTreeNode taskEstimatedDuration = new DefaultMutableTreeNode("Task Est. Duration: "+ task.estimatedDuration +" "+ task.StringDuration(task.estimatedDurationUnits));
            DefaultMutableTreeNode dueDateTimeAndImportance = new DefaultMutableTreeNode(" Due Date: "+ myDate +" - "+ " Due Time: "+
                    task.hour+":"+task.minutes+" - Importance: "+task.importance+" / 10");
            DefaultMutableTreeNode subTask1 = new DefaultMutableTreeNode("Sub Task 1: "+task.subTask1);
            DefaultMutableTreeNode subTask2 = new DefaultMutableTreeNode("Sub Task 2: "+task.subTask2);
            DefaultMutableTreeNode subTask3 = new DefaultMutableTreeNode("Sub Task 3: "+task.subTask3);
            DefaultMutableTreeNode subTask4 = new DefaultMutableTreeNode("Sub Task 4: "+task.subTask4);
            DefaultMutableTreeNode subTask5 = new DefaultMutableTreeNode("Sub Task 5: "+task.subTask5);
            DefaultMutableTreeNode importance = new DefaultMutableTreeNode("Task Importance : "+task.importance+" / 10");
            DefaultMutableTreeNode status = new DefaultMutableTreeNode("Status: Commenced");
            if (task.commenced) {
                projectName.add(status);
            }
            if (task.completed) {
                status.removeFromParent();
                DefaultMutableTreeNode statusComplete = new DefaultMutableTreeNode("Status: Completed");
                projectName.add(statusComplete);
            }

            root.add(projectName);
            projectName.add(parentDescription);
            projectName.add(taskEstimatedDuration);
            projectName.add(dueDateTimeAndImportance);
            projectName.add(parentTaskName);
            parentTaskName.add(subTask1);

            if(!task.subTask2.isEmpty()){parentTaskName.add(subTask2);}
            if(!task.subTask3.isEmpty()){parentTaskName.add(subTask3);}
            if(!task.subTask4.isEmpty()){parentTaskName.add(subTask4);}
            if(!task.subTask5.isEmpty()){parentTaskName.add(subTask5);}
            taskTreeModel.fireTreeNodeAdded(projectName);
        }


    }




    /*
    public void addTasks(String projectName,
                         String projectDescription,
                         String taskName, String subTask1Value,
                         String subTask2Value,String subTask3Value,
                         String subTask4Value,String subTask5Value,
                         Calendar date,int dueHour,int dueMinutes,
                         int taskImportance,String taskDurationValue){

        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.taskName = taskName;
        this.subTask1Value = subTask1Value;
        this.subTask2Value = subTask2Value;
        this.subTask3Value = subTask3Value;
        this.subTask4Value = subTask4Value;
        this.subTask5Value = subTask5Value;
        this.date = date;
        this.dueHour = dueHour;
        this.dueMinutes = dueMinutes;
        this.taskImportance = taskImportance;
        this.taskDurationValue = taskDurationValue;
        this.taskDurationValue = taskDurationValue;

    }
     */


    public TaskTreeModel getTaskTreeModel() {
        return taskTreeModel;}


    public void setCommencedTasksPanel(CommencedTasksPanel commencedTasksPanel){
        this.commencedTasksPanel = commencedTasksPanel;
    }

    public void commenceTask(Task task) {
        if (commencedTasksPanel.showTask(task)) {
            SortTree();
        }
    }
}
