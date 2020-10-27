package view;

import model.AddTaskModel;
import model.Task;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class TaskTreePanel extends AbstractCommonComponents {

    public static final int MIN_WIDTH = 500;
    public static final int MIN_HEIGHT = 390;
    private JTextField searchField;
    private JScrollPane scrollPane;
    private JButton deleteTaskButton;
    private JButton startTaskButton;
    private JButton searchButton;
    private AddTaskModel model;
    private JTree taskTree;
    private AddTaskPanel addTaskPanel;

    public TaskTreePanel(String taskTreePanelTitle, int x, int y, int width, int height, Color color, Boolean createBorder, int boundsHeight, AddTaskModel model) {
        super(taskTreePanelTitle, x, y, width, height, color, createBorder, boundsHeight);
        this.model = model;



        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        //Add a search field on the task lists.
        searchField = new JTextField();
        searchField.setBounds(2, 20, this.getWidth() - 137, 30);
        this.add(searchField);
        searchField.addActionListener(this::actionPerformed);

        taskTree = new JTree(model.getTaskTreeModel());
        taskTree.setShowsRootHandles(true);
        taskTree.setRootVisible(false);
        scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setView(taskTree);
        scrollPane.setBounds(5, 50, width - 10, height - 93);
        this.add(scrollPane);

        startTaskButton = new JButton("Start Task");
        startTaskButton.setBounds(this.getWidth() / 3 - 50, height - 42, 100, 40);
        startTaskButton.setEnabled(true);
        startTaskButton.setBackground(Color.WHITE);
        startTaskButton.setForeground(Color.BLACK);
        startTaskButton.addActionListener((event)-> {
            if (taskTree.getSelectionPath() == null) {
                return;
            }
            Object[] path = taskTree.getSelectionPath().getPath();
            //[,Project  Name] | [,Project Name, description] | [,Project Name,Task Name, Sub Task]

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path[1];
            String title = (String)node.getUserObject();
            String taskName = title.substring("Project Name: ".length());
            Task task = model.getTaskByName(taskName);

            model.commenceTask(task);
        });
        this.add(startTaskButton);

        deleteTaskButton = new JButton("Delete Task");
        deleteTaskButton.setBounds(2 * this.getWidth() / 3 - 50, height - 42, 120, 40);
        deleteTaskButton.setEnabled(true);
        deleteTaskButton.setBackground(Color.WHITE);
        deleteTaskButton.setForeground(Color.BLACK);
        deleteTaskButton.addActionListener((event)-> {
            if (taskTree.getSelectionPath() == null) {
                return;
            }
            Object[] path = taskTree.getSelectionPath().getPath();
            //[,Project  Name] | [,Project Name, description] | [,Project Name,Task Name, Sub Task]

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path[1];
            String title = (String)node.getUserObject();
            String taskName = title.substring("Project Name: ".length());
            Task task = model.getTaskByName(taskName);


            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?", "Delete?", JOptionPane.YES_NO_OPTION);
            if (result != JOptionPane.YES_OPTION) {
                return;
            }

            if (path.length < 4) {
                // task
                model.deleteTask(task, node);
            } else {
                 // sub task
                String contents = (String) ((DefaultMutableTreeNode) path[3]).getUserObject();
                contents = contents.substring("Sub Task ".length(), "Sub Task ".length() + 1); //Sub Task {num}
                int subtaskNum = Integer.parseInt(contents);

                if (task.removeSubTask(subtaskNum)) {
                    model.deleteTask(task, node);
                } else {
                    model.taskUpdated(task);
                }
            }

        });
        this.add(deleteTaskButton);

        searchButton = new JButton("Search");
        searchButton.setBounds(this.getWidth() - 130, 18, 125, 33);
        searchButton.setEnabled(true);
        searchButton.setForeground(Color.BLACK);
        searchButton.setBackground(Color.BLACK);
        this.add(searchButton);
        searchButton.addActionListener(this::actionPerformed);


        taskTree.getSelectionModel().addTreeSelectionListener((event)-> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath().getPath()[1];
            String title = (String)node.getUserObject();

            String taskName = title.substring("Project Name: ".length());
            Task task = model.getTaskByName(taskName);
            if (task == null) {
                return;
            }

            addTaskPanel.showTask(task);
        });
    }


    public final DefaultMutableTreeNode findNode(String searchString) {

        List<DefaultMutableTreeNode> searchNodes = getSearchNodes((DefaultMutableTreeNode) model.getTaskTreeModel().getRoot());
        DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) taskTree.getLastSelectedPathComponent();

        DefaultMutableTreeNode foundNode = null;
        int bookmark = -1;

        if (currentNode != null) {
            for (int index = 0; index < searchNodes.size(); index++) {
                if (searchNodes.get(index) == currentNode) {
                    bookmark = index;
                    break;
                }
            }
        }

        for (int index = bookmark + 1; index < searchNodes.size(); index++) {
            if (searchNodes.get(index).toString().toLowerCase().contains(searchString.toLowerCase())) {
                foundNode = searchNodes.get(index);
                break;
            }
        }

        if (foundNode == null) {
            for (int index = 0; index <= bookmark; index++) {
                if (searchNodes.get(index).toString().toLowerCase().contains(searchString.toLowerCase())) {
                    foundNode = searchNodes.get(index);
                    break;
                }
            }
        }
        return foundNode;
    }


    private final List<DefaultMutableTreeNode> getSearchNodes(DefaultMutableTreeNode root) {
        List<DefaultMutableTreeNode> searchNodes = new ArrayList<DefaultMutableTreeNode>();

        Enumeration<?> e = root.preorderEnumeration();
        while (e.hasMoreElements()) {
            searchNodes.add((DefaultMutableTreeNode) e.nextElement());
        }
        return searchNodes;
    }

    public void actionPerformed(ActionEvent event) {
        String search = searchField.getText();
        if (search.trim().length() > 0) {
            DefaultMutableTreeNode node = findNode(search);
            if (node != null) {
                TreePath path = new TreePath(node.getPath());
                taskTree.setSelectionPath(path);
                taskTree.scrollPathToVisible(path);
            }
        }
    }


    public void setAddTaskPanel(AddTaskPanel addTaskPanel) {

        this.addTaskPanel = addTaskPanel;
    }
}
