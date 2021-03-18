package View;

import Objects.Upgrades;
import Utilites.GlobalStates;
import Utilites.NotEnoughPointsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GlobalUpgradesInternalFrame extends JFrame {

    JButton announcingGlobalEpidemic;
    JButton progressingOnVaccine;
    JButton creatingExperimentalMedicine;
    JButton creatingRestrictions;
    JButton ultimateGlobalLockdown;
    boolean [] isItUsed = new boolean[5];

    public GlobalUpgradesInternalFrame() {
        setLayout(new GridLayout(5,1));
        setSize(500,500);
        for (int i = 0;i < 5; i++){
            isItUsed[i] = false;
        }
        getContentPane().setBackground(GlobalStates.menuColor);
        announcingGlobalEpidemic = new JButton("ANNOUNCE IT'S GLOBAL EPIDEMIC");
        announcingGlobalEpidemic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)  {
                if(isItUsed[0]) {
                    System.err.println("YOU ALREADY BOUGHT THIS UPDATE");
                    return;
                }
                try{
                    Upgrades.INSTANCE.tellingWordItsGlobalEpidemic(GlobalStates.INSTANCE.pointsForCuredPeopleGlobal);
                    System.out.println("YOU BOUGHT ANNOUNCEMENT OF GLOBAL EPIDEMIC");
                }
                catch (NotEnoughPointsException e){
                    e.err();
                }
                isItUsed[0] = true;
            }
        });
        getContentPane().add(announcingGlobalEpidemic);
        progressingOnVaccine = new JButton("PROGRESSING ON VACCINE");
        progressingOnVaccine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isItUsed[1]) {
                    System.err.println("YOU ALREADY BOUGHT THIS UPGRADE");
                    return;
                }
                try{
                    Upgrades.INSTANCE.progressOfVaccine(GlobalStates.INSTANCE.pointsForCuredPeopleGlobal);
                    System.out.println("YOU BOUGHT PROGRESSION ON VACCINE");
                }
                catch (NotEnoughPointsException e){
                    e.err();
                }
                isItUsed[1] = true;
            }
        });
        getContentPane().add(progressingOnVaccine);
        creatingExperimentalMedicine = new JButton("CREATE EXPERIMENTAL DRUG");
        creatingExperimentalMedicine.setSize(30,100);
        creatingExperimentalMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isItUsed[2]) {
                    System.err.println("YOU ALREADY BOUGHT THIS UPGRADE");
                    return;
                }
                try{
                    Upgrades.INSTANCE.creatingExperimentalMedicineForVirus(GlobalStates.INSTANCE.pointsForCuredPeopleGlobal);
                    System.out.println("YOU BOUGHT CREATING EXPERIMENTAL DRUG");
                }
                catch (NotEnoughPointsException e){
                    e.err();
                }
                isItUsed[2] = true;
            }
        });
        getContentPane().add(creatingExperimentalMedicine);
        creatingRestrictions = new JButton("CREATE GLOBAL RESTRICTIONS");
        creatingRestrictions.setSize(30,100);
        creatingRestrictions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isItUsed[3]) {
                    System.err.println("YOU ALREADY BOUGHT THIS UPGRADE");
                    return;
                }
                try{
                    Upgrades.INSTANCE.creatingInternationalRestrictionsAboutLockdown(GlobalStates.INSTANCE.pointsForCuredPeopleGlobal);
                    System.out.println("YOU BOUGHT CREATING RESTRICTIONS");
                }
                catch (NotEnoughPointsException e){
                    e.err();
                }
                isItUsed[3] = true;
            }
        });
        getContentPane().add(creatingRestrictions);
        ultimateGlobalLockdown = new JButton("INTRODUCE GLOBAL LOCKDOWN");
        ultimateGlobalLockdown.setSize(30,100);
        ultimateGlobalLockdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isItUsed[4]) {
                    System.err.println("YOU ALREADY BOUGHT THIS UPGRADE");
                    return;
                }
                try{
                    Upgrades.INSTANCE.ultimateFinalLockdown(GlobalStates.INSTANCE.pointsForCuredPeopleGlobal);
                    System.out.println("YOU BOUGHT ULTIMATE LOCKDOWN");
                }
                catch (NotEnoughPointsException e){
                    e.err();
                }
                isItUsed[4] = true;
            }
        });
        getContentPane().add(ultimateGlobalLockdown);
    }
}
