/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author cejit
 */
public class ColorHandler {
    private Random randomGenerator;
    private int r, g, b; 
    
    public ColorHandler() {
        randomGenerator = new Random();
    }
    
    public Color createColor(int red, int green, int blue) {
        return new Color(red, green, blue);
    }
    
    public Color generateRandomColor() {
        r = randomGenerator.nextInt(256);
        g = randomGenerator.nextInt(256);
        b = randomGenerator.nextInt(256);
        
        return new Color(r, g, b);
    }
}
