package View;

import Objects.HighScore;
import Utilites.GlobalStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameOfGame extends JFrame {

    public static int widthOfMainFrame = GlobalStates.imageOfmapOfTheWorld.getWidth(null) + 350;
    public static int heightOfMainFrame = GlobalStates.imageOfmapOfTheWorld.getHeight(null);

    JButton exit = new JButton("EXIT");

    public FrameOfGame(){
        setLayout(null);
        MyJPanel myJPanel = new MyJPanel();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GlobalStates.INSTANCE.savingHighScores();
                System.exit(0);
            }
        });
        exit.setBounds(widthOfMainFrame - 100,heightOfMainFrame - 100, 60, 30);
        exit.setForeground(Color.red);
        exit.setVisible(true);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                GlobalStates.INSTANCE.stoppingGame();

            }
        });
        getContentPane().add(exit);
        myJPanel.setBounds(0,0,widthOfMainFrame,heightOfMainFrame);
        add(myJPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AntiPlaque Inc.");
        pack();
        setSize(new Dimension(widthOfMainFrame, heightOfMainFrame));
        setResizable(false);
    }

    public static void winningFrame(){
        JFrame winningFrame = new JFrame();
        winningFrame.setSize(300,300);
        String inputString = JOptionPane.showInputDialog("INSERT YOUR NAME");
        GlobalStates.INSTANCE.getSetOfHighScores().add(new HighScore(inputString, GlobalStates.INSTANCE.pointsForCuredPeopleGlobal, GlobalStates.INSTANCE.getAllInfectedPopulation(), GlobalStates.INSTANCE.counterOfDeaths));
        winningFrame.setVisible(true);
        winningFrame.setLocationRelativeTo(null);
    }
}
