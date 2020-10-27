
package model;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class TaskTreeModel implements TreeModel {


    private DefaultMutableTreeNode root;


    private ArrayList<TreeModelListener> treeModelListeners;

    public  TaskTreeModel()
    {
        treeModelListeners = new ArrayList<TreeModelListener>();
        root = new DefaultMutableTreeNode("ROOT");
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((TreeNode) parent).getChildAt(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((TreeNode) parent).getChildCount();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((TreeNode) node).isLeaf();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((TreeNode) parent).getIndex((TreeNode) child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        treeModelListeners.add(l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        treeModelListeners.remove(l);
    }


    public void fireTreeNodeAdded(TreeNode addedTask) {
        ArrayList<TreeNode> path = new ArrayList<>();
        path.add((TreeNode) root);
        for (TreeNode parent = addedTask.getParent();
             parent != (TreeNode) root;
             parent = parent.getParent()) {
            path.add(1,parent);
        }
        final int[] indicies = {addedTask.getParent().getIndex(addedTask)};
        TreeModelEvent e = new TreeModelEvent(this, path.toArray(), indicies,
                new Object[] {addedTask});
        for (TreeModelListener tml : treeModelListeners) {
            tml.treeStructureChanged(e);
        }
    }

    public void fireLastNodeRemoved(TreeNode removed) {
        ArrayList<TreeNode> path = new ArrayList<>();
        path.add((TreeNode) root);

        final int[] indicies = {0};
        TreeModelEvent e = new TreeModelEvent(this, path.toArray(), indicies,
                new Object[] {removed});
        for (TreeModelListener tml : treeModelListeners) {
            tml.treeNodesRemoved(e);
        }
    }
}
