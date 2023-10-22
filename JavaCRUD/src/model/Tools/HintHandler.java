/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Tools;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author cejit
 */
public class HintHandler {
    public void makeHintOnFocus(JTextField field, String toShowMessage) {
        if (field.getText().equals("")) {
            field.setText(toShowMessage);
            field.setForeground(new Color(115, 113, 112));
        }
    }
    
    public void quitHintOnFocus(JTextField field, String toShowMessage) {
        if (field.getText().equals(toShowMessage)) {
            field.setText("");
            field.setForeground(new Color(0, 0, 0));
        }
    }
}
