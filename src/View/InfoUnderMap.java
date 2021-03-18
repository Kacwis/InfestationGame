package View;

import Objects.Country;
import Threads.Clock;
import Threads.GlobalResearchForVaccine;
import Utilites.GlobalStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InfoUnderMap extends JPanel {

    JLabel clock = new JLabel();
    JLabel globalPoints = new JLabel();
    JLabel counterOfInfectedPeople = new JLabel();
    JLabel counterOfDeaths = new JLabel();
    final JLabel infectedCountries = new JLabel("INFECTED COUNTRIES");
    JButton checkingCountry = new JButton("CHECK SELECTED COUNTRY");
    Thread checkingAllCounters;
    JPanel infoAboutStates = new JPanel();
    JPanel allButtonsUnderneath = new JPanel();
    JButton allInformations;
    JButton upgradesGlobal;
    JComboBox<Country> comboBoxInfectedCountries = new JComboBox<>();
    JFrame tableOfAllCountries;
    JFrame globalUpgrades;
    final Font fontOfLabels = new Font("Arial", 1, 20);

    final public static int widthInfoUnderMap = FrameOfGame.widthOfMainFrame - GlobalStates.imageOfmapOfTheWorld.getWidth(null);
    final public static int heightInfoUnderMap = FrameOfGame.heightOfMainFrame / 2;

    public InfoUnderMap(){
        setLayout(new BorderLayout());
        infoAboutStates.setBackground(GlobalStates.menuColor);
        infoAboutStates.setLayout(new BoxLayout(infoAboutStates, BoxLayout.Y_AXIS));
        infoAboutStates.setSize(new Dimension(widthInfoUnderMap , 300));
        allButtonsUnderneath.setBackground(GlobalStates.menuColor);
        allButtonsUnderneath.setLayout(new GridLayout(20,2));
        allButtonsUnderneath.setSize(new Dimension(widthInfoUnderMap, 50));
        setSize(widthInfoUnderMap, heightInfoUnderMap);
        counterOfDeaths.setFont(fontOfLabels);
        counterOfInfectedPeople.setFont(fontOfLabels);
        clock.setFont(fontOfLabels);
        globalPoints.setFont(fontOfLabels);
        infoAboutStates.add(clock);
        infoAboutStates.add(globalPoints);
        infoAboutStates.add(counterOfDeaths);
        infoAboutStates.add(counterOfInfectedPeople);
        addingAllButtons();
        add(infoAboutStates, BorderLayout.PAGE_START);
        add(allButtonsUnderneath, BorderLayout.CENTER);
        setBackground(GlobalStates.menuColor);
        checkingAllCounters = new Thread(new Runnable() {
            @Override
            public void run() {
                String quantityOfInfectedPeople;
                String quantityOfDeaths;
                String percentageOfVaccine;
                String globalPointsString;
                while(!GlobalStates.isItEnd){
                    quantityOfInfectedPeople = Long.toString(GlobalStates.INSTANCE.getAllInfectedPopulation());
                    quantityOfDeaths = Long.toString(GlobalStates.INSTANCE.getCounterOfDeaths());
                    percentageOfVaccine = Double.toString(GlobalResearchForVaccine.INSTANCE.getPercentageOfCompletingVaccine());
                    globalPointsString = Long.toString(GlobalStates.INSTANCE.getPointsForCuredPeopleGlobal());
                    clock.setText("DATE  " + Clock.INSTANCE.getGlobalTime().toString());
                    if(counterOfInfectedPeople.getText() == quantityOfInfectedPeople)
                        continue;
                    counterOfInfectedPeople.setText("INFECTED PEOPLE: " + quantityOfInfectedPeople);
                    if(counterOfDeaths.getText() == quantityOfDeaths)
                        continue;
                    counterOfDeaths.setText("DEATHS: " + quantityOfDeaths);
                    globalPoints.setText("POINTS: " + globalPointsString);
                    try{
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        checkingAllCounters.start();
    }

    public void addingAllButtons(){
        allInformations = new JButton("ALL COUNTRIES' INFORMATIONS");
        allInformations.setVisible(true);
        allInformations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tableOfAllCountries = new TableInternalFrame();
                tableOfAllCountries.setSize(500,500);
                tableOfAllCountries.setVisible(true);
                tableOfAllCountries.setLocationRelativeTo(null);
            }
        });
        allButtonsUnderneath.add(allInformations);
        upgradesGlobal = new JButton("UPGRADES");
        upgradesGlobal.setVisible(true);
        upgradesGlobal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                globalUpgrades = new GlobalUpgradesInternalFrame();
                globalUpgrades.pack();
                globalUpgrades.setVisible(true);
                globalUpgrades.setLocationRelativeTo(null);
            }
        });
        allButtonsUnderneath.add(upgradesGlobal);
        infectedCountries.setFont(fontOfLabels);
        allButtonsUnderneath.add(infectedCountries);
        controllingComboBoxOfInfectedCountries();
    }

    public void controllingComboBoxOfInfectedCountries(){
        DefaultComboBoxModel<Country> infectedCountries = new DefaultComboBoxModel<>();
        ArrayList<Country> allCountries = new ArrayList<>(GlobalStates.INSTANCE.getSortedMapOfcountries().values());
        comboBoxInfectedCountries.setVisible(true);
        checkingCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FrameOfCountry frameOfCountry = new FrameOfCountry((Country)comboBoxInfectedCountries.getSelectedItem());
                frameOfCountry.setLocationRelativeTo(null);
                frameOfCountry.setVisible(true);
            }
        });
        allButtonsUnderneath.add(comboBoxInfectedCountries);
        allButtonsUnderneath.add(checkingCountry);
        Thread checkingInfectedCountries = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!GlobalStates.isItEnd){
                    infectedCountries.removeAllElements();
                    for(Country country : allCountries){
                        if(country.getInfectedPopulation() == 0) {
                            continue;
                        }
                        infectedCountries.addElement(country);
                    }
                    comboBoxInfectedCountries.setModel(infectedCountries);
                    try{
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay() * 2);
                    }
                    catch (InterruptedException e){
                        continue;
                    }
                }
            }
        });
        checkingInfectedCountries.start();
    }
}
