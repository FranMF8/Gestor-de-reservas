/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools.DecisionTree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cejit
 */
public class BinaryTree<T extends Comparable<T>> {
    
    private TreeNode<T> root;
    
    public BinaryTree() {
        this.root = null;
    }
    
    private TreeNode<T> insertRecursively(TreeNode<T> node, T value) {
        
        /*
        Base case
        
        If the actual node value equals null it means
        that we are in a free to insert the new value
        creating a new node and returning it.
        */       
        
        if (node == null) {
            return new TreeNode<>(value);
        }
        
        /*
        Left recursion case
        
        It calls 'insertRecursively' recursively
        on the left subtree of the current node
        as the new node and the same value that
        we want to insert.
        */
        
        if (value.compareTo(node.getValue()) <= 0) {
            
            node.setLeft(insertRecursively(node.getLeft(), value));
            
        } 
        
        /*
        Right recursion case
        
        It calls 'insertRecursively' recursively
        on the right subtree of the current node
        as the new node and the same value that we want to insert
        */
        
        else if (value.compareTo(node.getValue()) > 0) {
            
            node.setRight(insertRecursively(node.getRight(), value));
            
        }
        
        /*
        Finally, after inserting the new node
        in the proper place, we return
        the current node
        */
        
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
    
    public List<T> returnBiggerThan(T value) {
        
        List<T> biggerValues = new ArrayList<>();
        this.returnBiggerThan(this.root, value, biggerValues);
        
        return biggerValues;
    }
    
}
