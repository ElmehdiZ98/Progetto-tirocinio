package it.paa.interfaces;

import javax.swing.tree.TreeNode;

public interface NestedSetsTreeNode extends TreeNode {


    NestedSetsTreeNode getTopLevel();
    void setTopLevel(NestedSetsTreeNode topLevel);
    int getLeft();
    void setLeft(int left);
}
