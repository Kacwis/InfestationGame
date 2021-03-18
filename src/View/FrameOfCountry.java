package View;

import Objects.Country;
import Utilites.GlobalStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameOfCountry extends JFrame {

    Country country;
    JButton exit;
    JButton upgrades;
    JLabel countryName;
    JLabel countryPopulation ;
    JLabel countryInfectedPopulation;
    JLabel pointsPerCountry;
    JLabel percentageOfInfectedPeople;

    public FrameOfCountry(Country country){
        this.country = country;
        countryName = new JLabel(country.getName());
        upgrades = new JButton("UPGRADES");
        setSize(500,300);
        countryPopulation = new JLabel("POPULATON: " + Long.toString(country.getPopulation()));
        countryInfectedPopulation = new JLabel("INFECTED POPULATION: " + Long.toString(country.getInfectedPopulation()));
        percentageOfInfectedPeople = new JLabel("PERCENTAGE OF INFECTED PEOPLE: " + Double.toString(country.getInfectedPopulation() / country.getPopulation()) + "%");
        if(country.getTreatmentInCountry() != null) {
            pointsPerCountry = new JLabel("POINTS: " + country.getTreatmentInCountry().getPointsForCuredPeople());
            pointsPerCountry.setBounds(300, 60, 100, 30);
            add(pointsPerCountry);
        }
        setLayout(null);
        exit = new JButton("EXIT");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        });
        countryName.setFont(new Font("Arial", 10, 20));
        countryName.setBounds(10, 20, 300, 30);
        countryPopulation.setBounds(10, 60, 200, 20);
        countryInfectedPopulation.setBounds(10, 90, 200, 20);
        percentageOfInfectedPeople.setBounds(10,120,250, 20);
        add(countryName);
        add(countryPopulation);
        add(countryInfectedPopulation);
        add(percentageOfInfectedPeople);
        JInternalFrame upgradesFrame = new UpgradesInternalFrame(country, true,true);
        upgrades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                add(upgradesFrame);
                upgradesFrame.setVisible(true);
                upgradesFrame.setSize(new Dimension(250,200));
                upgradesFrame.setLocation(280, 30);
            }
        });
        upgrades.setBounds(10, 150, 100,20);
        exit.setBounds(250 - 40, 400, 80, 20);
        add(upgrades);
        add(exit);
        setIconImage(country.getFlag());
        getContentPane().setBackground(GlobalStates.menuColor);
        setTitle(country.getName() + "'s informations");
        pack();
        setSize(new Dimension(600, 300));
    }

    @Override
    public void paintComponents(Graphics g) {
        Graphics2D graphic = (Graphics2D) g;
        graphic.drawImage(country.getFlag() , 0,0, this);
        super.paintComponents(g);
    }

}
