/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayer;

import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author tr266
 */


public class SettingsWindow extends JDialog{
    private JLabel label;
    
    public SettingsWindow(JFrame frame){
        super(frame,"Help Window", true);
        setLayout(new FlowLayout());
        
        label = new JLabel("For further information please contact, tr266@cornell.edu");
        add(label);
        
    }
    
}