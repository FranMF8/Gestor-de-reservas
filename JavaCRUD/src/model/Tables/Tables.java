/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tables;

import java.util.Comparator;
import model.Tools.DecisionTree.BinaryTree;

/**
 *
 * @author cejit
 */
public class Tables {
    
    public Comparator<Table> quantityComparator;
    public BinaryTree<Table> mesas;
    
    public Tables(Comparator<Table> comparador) {
        this.quantityComparator = comparador;
        this.mesas = new BinaryTree<>(this.quantityComparator);
    }
    
    
}

