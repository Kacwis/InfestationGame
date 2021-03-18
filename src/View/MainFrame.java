package View;

import Objects.HighScore;
import Utilites.GlobalStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class MainFrame extends JFrame {

    JButton newGame;
    JButton ranking;
    JButton informations;
    JButton exit;
    JFrame difficultyChoice;
    JFrame rankingFrame;
    JFrame informationsFrame;
    JFrame frameOfGame;

    public MainFrame(){
        setSize(300,300);
        setLocationRelativeTo(null);
        setLayout(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                GlobalStates.INSTANCE.savingHighScores();
                System.exit(0);
            }
        });
        newGame = new JButton("NEW GAME");
        newGame.setBounds(70,50 , 140,40);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                difficultyChoice = creatingDifficultyChoiceFrame();
                difficultyChoice.setVisible(true);
                dispose();
            }
        });
        getContentPane().add(newGame);
        ranking = new JButton("RANKING");
        ranking.setBounds(70, 100,140,40);
        ranking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                rankingFrame = creatingRankingFrame();
                rankingFrame.setVisible(true);
            }
        });
        getContentPane().add(ranking);
        informations = new JButton("INFORMATIONS");
        informations.setBounds(70,150, 140, 40);
        informations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                informationsFrame = creatingInformationsFrame();
                informationsFrame.setVisible(true);
            }
        });
        getContentPane().add(informations);
        exit = new JButton("EXIT");
        exit.setBounds(200,200, 70,30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        getContentPane().add(exit);
    }

    public JFrame creatingDifficultyChoiceFrame(){
        JFrame difficultyChoiceFrame = new JFrame();
        JButton easy = new JButton("EASY");
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GlobalStates.INSTANCE.setDifficultyLevel((byte) 1);
                GlobalStates.INSTANCE.settingNewGame();
                frameOfGame = new FrameOfGame();
                frameOfGame.setVisible(true);
                difficultyChoiceFrame.dispose();
            }
        });
        JButton medium = new JButton("MEDIUM");
        medium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GlobalStates.INSTANCE.setDifficultyLevel((byte) 2);
                GlobalStates.INSTANCE.settingNewGame();
                frameOfGame = new FrameOfGame();
                frameOfGame.setVisible(true);
                difficultyChoiceFrame.dispose();
            }
        });
        JButton hard = new JButton("HARD");
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GlobalStates.INSTANCE.setDifficultyLevel((byte) 3);
                GlobalStates.INSTANCE.settingNewGame();
                frameOfGame = new FrameOfGame();
                frameOfGame.setVisible(true);
                difficultyChoiceFrame.dispose();
            }
        });
        difficultyChoiceFrame.getContentPane().add(easy);
        difficultyChoiceFrame.getContentPane().add(medium);
        difficultyChoiceFrame.getContentPane().add(hard);
        difficultyChoiceFrame.setSize(300,300);
        difficultyChoiceFrame.setLayout(new GridLayout(3,1));

        return  difficultyChoiceFrame;
    }

    public JFrame creatingRankingFrame(){
        JFrame ranking = new JFrame();
        ranking.setLayout(null);
        ranking.setSize(300,300);

        JButton exit = new JButton("EXIT");
        JList<HighScore> listOfScores;
        ArrayList<HighScore> setOfAllHighScores = GlobalStates.INSTANCE.getSetOfHighScores();
        Collections.sort(setOfAllHighScores);
        DefaultListModel<HighScore> modelOfHighScores = new DefaultListModel<>();
        for(HighScore highScore : setOfAllHighScores){
            modelOfHighScores.addElement(highScore);
        }
        listOfScores = new JList<>(modelOfHighScores);
        JScrollPane scrollList = new JScrollPane(listOfScores);
        scrollList.setBounds(0,0,300,200);
        scrollList.setVisible(true);
        ranking.getContentPane().add(scrollList);
        return ranking;
    }

    public JFrame creatingInformationsFrame(){
        JFrame informations = new JFrame();
        informations.setSize(500, 400);
        informations.setLocationRelativeTo(null);
        JTextArea informationsArea = new JTextArea(GlobalStates.INSTANCE.getInformations());
        informationsArea.setBounds(0, 1, 400,200);
        informations.getContentPane().add(informationsArea);
        informations.setLayout(null);
        return  informations;
    }

}
