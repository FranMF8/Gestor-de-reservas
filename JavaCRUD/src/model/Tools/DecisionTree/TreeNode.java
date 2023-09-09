/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools.DecisionTree;

/**
 *
 * @author cejit
 */
public class TreeNode<T> {
    
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> right;
    
    public TreeNode(T valor) {
        this.value = valor;
        this.left = null;
        this.right = null;
    }
    
    ///////////Getters & Setters///////////
    
    /////////////////Value////////////////
    
    public T getValue() {
        return this.value;
    }
    
    public void setValue( T valor) {
        this.value = valor;
    }
    
    /////////////////Left/////////////////
    
    public TreeNode<T> getLeft() {
        return this.left;
    }
    
    public void setLeft(TreeNode<T> newNode) {
        this.left = newNode;
    }
    
    /////////////////Right/////////////////
    
    public TreeNode<T> getRight() {
        return this.right;
    }
    
    public void setRight(TreeNode<T> newNode) {
        this.right = newNode;
    }
}
