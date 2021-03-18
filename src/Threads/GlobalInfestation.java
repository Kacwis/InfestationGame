package Threads;

import Objects.ActionsInCertain1stTypeOfCountry;
import Objects.Country;
import Utilites.GlobalStates;
import View.FrameOfGame;

import javax.swing.*;

public class GlobalInfestation {

    public static GlobalInfestation INSTANCE = new GlobalInfestation();

    Thread globalInfestation;
    boolean isGlobalInfestationRunning;

    private GlobalInfestation(){

        globalInfestation = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isGlobalInfestationRunning){
                    int counterOfInfectedCountries = 0;
                    for(Country country : GlobalStates.INSTANCE.getMapOfCountries().values()) {
                        counterOfInfectedCountries = counterOfInfectedCountries + country.getActionInCertainCountry().checkingInfectedPeople(country);
                    }
                    if(counterOfInfectedCountries != 0){
                        JOptionPane.showMessageDialog(null, counterOfInfectedCountries + " new countries with new infections");
                    }
                    if(GlobalStates.INSTANCE.getAllInfectedPopulation() == GlobalStates.INSTANCE.getAllPopulation()){
                        GlobalStates.isItEnd = true;
                        JOptionPane.showMessageDialog(null, "YOU WON");
                        FrameOfGame.winningFrame();
                    }
                    try{
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void startGlobalInfestation(){
        globalInfestation.start();
        isGlobalInfestationRunning = true;
    }

    public void stopGlobalInfestation(){
        isGlobalInfestationRunning = false;
        globalInfestation = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isGlobalInfestationRunning){
                    int counterOfInfectedCountries = 0;
                    for(Country country : GlobalStates.INSTANCE.getMapOfCountries().values()) {
                        counterOfInfectedCountries = counterOfInfectedCountries + country.getActionInCertainCountry().checkingInfectedPeople(country);
                    }
                    if(counterOfInfectedCountries != 0){
                        JOptionPane.showMessageDialog(null, counterOfInfectedCountries + " new countries with new infections");
                    }
                    if(GlobalStates.INSTANCE.getAllInfectedPopulation() == GlobalStates.INSTANCE.getAllPopulation()){
                        GlobalStates.isItEnd = true;
                    }
                    try{
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
