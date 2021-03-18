package Threads;

import Objects.ActionIn2ndTypeOfCountry;
import Objects.ActionInCertain3rdTypeOfCountry;
import Objects.ActionsInCertain1stTypeOfCountry;
import Objects.Country;
import Utilites.GlobalStates;

import javax.swing.*;

public class GlobalResearchForVaccine {

    final private int minimumPointsForFindingCure = 200;
    public int counterOfPoints;

    Thread startingResarchInEveryCountry;
    Thread gatheringPoints;
    Thread checkingReadyForVaccine;

    boolean runningStartingResarchInEveryCountry;
    boolean runningGatheringPoints;
    boolean runningCreatingVaccine;
    double percentageOfCompletingVaccine;
    public boolean isVaccineReady;
    double variableForUpgrades = 1;
    int levelOfDifficulty;

    public static GlobalResearchForVaccine INSTANCE = new GlobalResearchForVaccine();


    public GlobalResearchForVaccine(){
        this.levelOfDifficulty = levelOfDifficulty;
        counterOfPoints = 0;
        percentageOfCompletingVaccine = 0;
        runningGatheringPoints = true;
        runningStartingResarchInEveryCountry = true;
        isVaccineReady = false;
        checkingReadyForVaccine = new Thread(new Runnable() {
            @Override
            public void run() {
                while(runningGatheringPoints) {
                    if (GlobalStates.INSTANCE.getAllInfectedPopulation() < 5001) {
                        try {
                            Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    runningGatheringPoints = false;
                    JOptionPane.showMessageDialog(null, "CHINA STARTED RESEARCH ON VACCINE");
                }
                gatheringPoints.start();
            }
        });
        gatheringPoints = new Thread(new Runnable() {
            @Override
            public void run() {
                while(runningCreatingVaccine){
                    try {
                        Thread.sleep((int) (GlobalStates.INSTANCE.getLengthOfDay() * levelOfDifficulty * variableForUpgrades));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counterOfPoints++;
                    percentageOfCompletingVaccine = counterOfPoints/minimumPointsForFindingCure;
                    if(percentageOfCompletingVaccine >= 1) {
                        System.out.println("VACCINE HAS BEEN CREATED");
                        JOptionPane.showMessageDialog(null,"VACCINE HAS BEEN CREATED");
                        runningCreatingVaccine = false;
                        isVaccineReady = true;
                        percentageOfCompletingVaccine = 0;
                        counterOfPoints = 0;
                    }
                }
            }
        });
        startingResarchInEveryCountry = new Thread(new Runnable() {
            @Override
            public void run() {
                int counterOfCountriesThatAlreadyStartedResarching = 0;
                while(runningStartingResarchInEveryCountry){
                counterOfCountriesThatAlreadyStartedResarching++;
                for(Country country : GlobalStates.INSTANCE.getMapOfCountries().values()){
                    if(country.getActionInCertainCountry().getResearchOnVaccine() != null) {
                        counterOfCountriesThatAlreadyStartedResarching++;
                        continue;
                    }
                    checkingTypeOfCountry(country);
                }
                try{
                    Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                if(counterOfCountriesThatAlreadyStartedResarching == GlobalStates.INSTANCE.getMapOfCountries().size())
                    runningStartingResarchInEveryCountry = false;
            }
        }
        });
    }

    void checkingTypeOfCountry(Country country){
        if(country.getActionInCertainCountry().getClass() == ActionsInCertain1stTypeOfCountry.class){
            ((ActionsInCertain1stTypeOfCountry) country.getActionInCertainCountry()).checkingPopulationForStartResarch(country);
        }
        else if(country.getActionInCertainCountry().getClass() == ActionIn2ndTypeOfCountry.class){
            ((ActionIn2ndTypeOfCountry) country.getActionInCertainCountry()).checkingPopulationForStartResarch(country);
        }
        else if(country.getActionInCertainCountry().getClass() == ActionInCertain3rdTypeOfCountry.class){
            ((ActionInCertain3rdTypeOfCountry) country.getActionInCertainCountry()).checkingPopulationForStartResarch(country);
        }
    }

    public void startingThreadsReasarch(){
        checkingReadyForVaccine.start();
        startingResarchInEveryCountry.start();
        levelOfDifficulty = GlobalStates.INSTANCE.difficultyLevel;
        runningGatheringPoints = true;
        runningStartingResarchInEveryCountry = true;
        runningCreatingVaccine = true;
    }

    public void stopThreadReaserch(){
        runningCreatingVaccine = false;
        runningGatheringPoints = false;
        runningStartingResarchInEveryCountry = false;
        checkingReadyForVaccine = new Thread(new Runnable() {
            @Override
            public void run() {
                while(runningGatheringPoints) {
                    if (GlobalStates.INSTANCE.getAllInfectedPopulation() < 5001) {
                        try {
                            Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                    runningGatheringPoints = false;
                    JOptionPane.showMessageDialog(null, "CHINA STARTED RESEARCH ON VACCINE");
                }
                gatheringPoints.start();
            }
        });
        gatheringPoints = new Thread(new Runnable() {
            @Override
            public void run() {
                while(runningCreatingVaccine){
                    try {
                        Thread.sleep((int) (GlobalStates.INSTANCE.getLengthOfDay() * levelOfDifficulty * variableForUpgrades));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("HURRA");
                    counterOfPoints++;
                    System.out.println(counterOfPoints);
                    percentageOfCompletingVaccine = counterOfPoints/minimumPointsForFindingCure;
                    if(percentageOfCompletingVaccine >= 1) {
                        System.out.println("VACCINE HAS BEEN CREATED");
                        JOptionPane.showMessageDialog(null,"VACCINE HAS BEEN CREATED");
                        runningCreatingVaccine = false;
                        isVaccineReady = true;
                        percentageOfCompletingVaccine = 0;
                        counterOfPoints = 0;
                    }
                }
            }
        });
        startingResarchInEveryCountry = new Thread(new Runnable() {
            @Override
            public void run() {
                while(runningGatheringPoints){
                    if(GlobalStates.INSTANCE.getAllInfectedPopulation() < 7000){
                        continue;
                    }
                    runningGatheringPoints = false;
                    JOptionPane.showMessageDialog(null, "CHINA STARTED RESEARCH ON VACCINE");
                }
                System.out.println("CHINA HAS STARTED CREATING VACCINE");
                runningCreatingVaccine = true;
                while(runningCreatingVaccine){
                    try {
                        Thread.sleep((int)(5000 * levelOfDifficulty * variableForUpgrades));
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    counterOfPoints++;
                    percentageOfCompletingVaccine = counterOfPoints/minimumPointsForFindingCure;
                    if(percentageOfCompletingVaccine != 1)
                        continue;
                    System.out.println("VACCINE HAS BEEN CREATED");
                    runningCreatingVaccine = false;
                    isVaccineReady = true;
                    percentageOfCompletingVaccine = 0;
                    counterOfPoints = 0;
                }
            }
        });
    }


    public double getVariableForUpgrades() {
        return variableForUpgrades;
    }

    public void setVariableForUpgrades(double variableForUpgrades) {
        this.variableForUpgrades = variableForUpgrades;
    }

    public double getPercentageOfCompletingVaccine() {
        return percentageOfCompletingVaccine;
    }
}
