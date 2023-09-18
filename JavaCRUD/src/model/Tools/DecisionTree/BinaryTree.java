/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools.DecisionTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Tables.Table;

/**
 *
 * @author cejit
 */
public class BinaryTree<T extends Comparable<T>> {
    
    private TreeNode<T> root;
    private Comparator<T> comparator;
    
    public BinaryTree(Comparator<T> comparador) {
        this.root = null;
        this.comparator = comparador;
    }
    
    private TreeNode<T> insertRecursively(TreeNode<T> node, T value) {

        if (node == null) {
            return new TreeNode<>(value);
        }

        if (this.comparator.compare(value, node.getValue()) <= 0) {
            node.setLeft(insertRecursively(node.getLeft(), value));
        } else {
            node.setRight(insertRecursively(node.getRight(), value));
        }

        return node;
    }
    
    public void insertNode(T value){
        
        this.root = this.insertRecursively(this.root, value);
    }
    
    public void removeNode(T value) {
        this.root = removeRecursively(this.root, value);
    }

    private TreeNode<T> removeRecursively(TreeNode<T> currentNode, T value) {
        if (currentNode == null) {
            return currentNode;
        }

        if (comparator.compare(value, currentNode.getValue()) < 0) {
            
            currentNode.setLeft(removeRecursively(currentNode.getLeft(), value));
            
        } else if (comparator.compare(value, currentNode.getValue()) > 0) {
            
            currentNode.setRight(removeRecursively(currentNode.getRight(), value));
            
        } else {
            
        if (currentNode.getLeft() == null) {
            
            return currentNode.getRight();
            
        } else if (currentNode.getRight() == null) {
            
            return currentNode.getLeft();
            
        }
            currentNode.setValue(findMinValue(currentNode.getRight()));

        
            currentNode.setRight(removeRecursively(currentNode.getRight(), currentNode.getValue()));
        }

        return currentNode;
    }

    private T findMinValue(TreeNode<T> node) {
        T minValue = node.getValue();
        while (node.getLeft() != null) {
            minValue = node.getLeft().getValue();
            node = node.getLeft();
        }
        return minValue;
    }

    private void returnBiggerThan(TreeNode<T> node, int quantity, List<T> biggerValues) {
        if (node == null) {
            return;
        }

        this.returnBiggerThan(node.getRight(), quantity, biggerValues);

        if (node.getValue() instanceof Table) {
            Table table = (Table) node.getValue();
            if (table.getQuantity() >= quantity) {
                biggerValues.add(node.getValue());
            }
        }

        this.returnBiggerThan(node.getLeft(), quantity, biggerValues);
    }
    
    public List<T> returnBiggerThan(int value, Comparator<T> comparador) {
        
        List<T> biggerValues = new ArrayList<>();
        this.returnBiggerThan(this.root, value, biggerValues);
        
        return biggerValues;
    }
    public List<T> returnBiggerThan(int value) {
        
        return returnBiggerThan(value, this.comparator);
    }
    
    public void replaceNodeValue(T oldValue, T newValue) {
        this.root = replaceNodeValueRecursively(this.root, oldValue, newValue);
    }

    private TreeNode<T> replaceNodeValueRecursively(TreeNode<T> node, T oldValue, T newValue) {
        if (node == null) {
            return null;
        }

        if (comparator.compare(oldValue, node.getValue()) == 0) {
            node.setValue(newValue);
        }

        return node;
    }
}
