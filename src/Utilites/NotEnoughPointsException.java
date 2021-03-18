package Utilites;

import javax.swing.*;

public class NotEnoughPointsException extends Exception {

    public void err(){
        System.err.println("YOU DON'T HAVE ENOUGH POINTS TO BUY THIS UPGRADE");
        JOptionPane.showMessageDialog(null, "YOU DON'T HAVE ENOUGH POINTS TO BUY THIS UPGRADE");
    }
}
