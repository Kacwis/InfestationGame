package View;

import Objects.ActionInCertainCountry;
import Objects.Country;
import Objects.Upgrades;
import Utilites.GlobalStates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpgradesInternalFrame extends JInternalFrame {

    JButton creatingAdvertisements;
    JButton creatingFieldHospitals;
    JButton buyingExperimentalDrug;
    JButton gettingHelpFromOtherCountries;
    final int minimumFor1stUpgrade = 10000;
    final int minimumFro2ndUpgrade = 50000;
    final int minimumFor3rdUpgrade = 100000;
    final int minimumFor4thUpgrade = 200000;
    boolean used1stUpgrade = false;
    boolean used2ndUpgrade = false;
    boolean used3rdUpgrade = false;
    boolean used4thUpgrade = false;
    long pointsForCuredPeople;

    public UpgradesInternalFrame(Country country, boolean resizable, boolean closable ){
        super(country.getName() + "'s upgrades",resizable,closable);
        used1stUpgrade = false;
        used2ndUpgrade = false;
        used3rdUpgrade = false;
        used4thUpgrade = false;
        setLayout(new FlowLayout());
        if(country.getTreatmentInCountry() != null)
            pointsForCuredPeople = country.getTreatmentInCountry().getPointsForCuredPeople();

        if(pointsForCuredPeople > minimumFor1stUpgrade && !used1stUpgrade) {
            creatingAdvertisements = new JButton("CREATE ADVERTISEMENTS");
            creatingAdvertisements.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Upgrades.INSTANCE.creatingAdvertisements(country);
                    System.out.println("YOU BOUGHT ADVERTISEMENTS");
                    country.getTreatmentInCountry().setPointsForCuredPeople(country.getTreatmentInCountry().getPointsForCuredPeople() - minimumFor1stUpgrade);
                    GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.getPointsForCuredPeopleGlobal() - minimumFor1stUpgrade;
                    used1stUpgrade = true;
                }
            });
            getContentPane().add(creatingAdvertisements);
        }
        if(pointsForCuredPeople > minimumFro2ndUpgrade && !used2ndUpgrade) {
            creatingFieldHospitals = new JButton("CREATE FIELD HOSPITALS");
            creatingFieldHospitals.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Upgrades.INSTANCE.creatingFieldHospitals(country);
                    System.out.println("YOU BOUGHT FIELD HOSPITALS");
                    country.getTreatmentInCountry().setPointsForCuredPeople(country.getTreatmentInCountry().getPointsForCuredPeople() - minimumFro2ndUpgrade);
                    GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.getPointsForCuredPeopleGlobal() - minimumFro2ndUpgrade;
                    used2ndUpgrade = true;
                }
            });
            getContentPane().add(creatingFieldHospitals);
        }
        if(pointsForCuredPeople > minimumFor3rdUpgrade && !used3rdUpgrade){
            buyingExperimentalDrug = new JButton("BUY EXPERIMENTAL DRUG");
            buyingExperimentalDrug.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Upgrades.INSTANCE.buyingExperimentalDrug(country);
                    System.out.println("YOU BOUGHT EXPERIMENTAL DRUG");
                    country.getTreatmentInCountry().setPointsForCuredPeople(country.getTreatmentInCountry().getPointsForCuredPeople() - minimumFor3rdUpgrade);
                    GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.getPointsForCuredPeopleGlobal() - minimumFor3rdUpgrade;
                    used3rdUpgrade = true;
                }
            });
            getContentPane().add(buyingExperimentalDrug);
        }
        if(pointsForCuredPeople > minimumFor4thUpgrade && !used4thUpgrade){
            gettingHelpFromOtherCountries = new JButton("GET HELP FROM OTHER COUNTRIES");
            gettingHelpFromOtherCountries.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    Upgrades.INSTANCE.gettingHelpFromOtherCountries(country);
                    System.out.println("YOU BOUGHT HELP FROM OTHER COUNTRIES");
                    country.getTreatmentInCountry().setPointsForCuredPeople(country.getTreatmentInCountry().getPointsForCuredPeople() - minimumFor4thUpgrade);
                    GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.getPointsForCuredPeopleGlobal() - minimumFor4thUpgrade;
                    used4thUpgrade = true;
                }
            });
            getContentPane().add(gettingHelpFromOtherCountries);
        }
        pack();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300,200);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(GlobalStates.menuColor);
    }

}
