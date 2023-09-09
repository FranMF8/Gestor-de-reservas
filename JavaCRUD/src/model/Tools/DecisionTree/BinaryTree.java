/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools.DecisionTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    
    private void returnBiggerThan(TreeNode<T> node, T value, List<T> biggerValues) {
        if (node == null) {
            return;
        }
        
        if (node.getValue().compareTo(value) > 0) {
            biggerValues.add(node.getValue());
        }
        
        this.returnBiggerThan(node.getRight(), value, biggerValues);
        
        this.returnBiggerThan(node.getLeft(), value, biggerValues);
    }
    
    public List<T> returnBiggerThan(T value, Comparator<T> comparador) {
        
        List<T> biggerValues = new ArrayList<>();
        this.returnBiggerThan(this.root, value, biggerValues);
        
        return biggerValues;
    }
    public List<T> returnBiggerThan(T value) {
        
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
        } else if (comparator.compare(oldValue, node.getValue()) < 0) {
            node.setLeft(replaceNodeValueRecursively(node.getLeft(), oldValue, newValue));
        } else {
            node.setRight(replaceNodeValueRecursively(node.getRight(), oldValue, newValue));
        }

        return node;
    }
}
