package Threads;

import Objects.Country;
import Utilites.GlobalStates;

public class TreatmentInCountry {

    Thread treatmentInCountry;
    boolean workingTreatment;
    int levelOfCountryAdvancement;
    double variableForUpgrades;
    long pointsForCuredPeople;
    double variableWithVaccine = 0.05;

    public TreatmentInCountry(Country country){
        workingTreatment = country.infectedPopulation != 0;
        levelOfCountryAdvancement = Math.abs(country.getActionInCertainCountry().getLevelOfAdvancementOfCountry() - 4);
        if(GlobalStates.INSTANCE.difficultyLevel > 2)
            variableForUpgrades = 0.5;
        else
            variableForUpgrades = 1;
        treatmentInCountry = new Thread(new Runnable() {
            @Override
            public void run() {
                while (workingTreatment){
                    try{
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay() * 7);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if(GlobalResearchForVaccine.INSTANCE.isVaccineReady) {
                        variableWithVaccine = 0.9;
                        if(country.getInfectedPopulation() < 1000)
                            country.setInfectedPopulation(0);
                    }
                    long peopleCured = (long)(country.infectedPopulation * variableWithVaccine * levelOfCountryAdvancement * variableForUpgrades);
                    country.infectedPopulation = country.infectedPopulation - peopleCured;
                    GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.pointsForCuredPeopleGlobal + peopleCured;
                    GlobalStates.INSTANCE.allInfectedPopulation = GlobalStates.INSTANCE.getAllInfectedPopulation() - peopleCured;
                    pointsForCuredPeople = pointsForCuredPeople + peopleCured;
                }
            }
        });
        treatmentInCountry.start();
    }

    public void stopTreatment(Country country){
        workingTreatment = false;
        treatmentInCountry = new Thread(new Runnable() {
            @Override
            public void run() {
                while (workingTreatment){
                    try{
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay() * 7);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    long peopleCured = (long)(country.infectedPopulation * 0.05 * levelOfCountryAdvancement * variableForUpgrades);
                    country.infectedPopulation = country.infectedPopulation - peopleCured;
                    GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.pointsForCuredPeopleGlobal + peopleCured;
                    GlobalStates.INSTANCE.allInfectedPopulation = GlobalStates.INSTANCE.getAllInfectedPopulation() - peopleCured;
                    pointsForCuredPeople = pointsForCuredPeople + peopleCured;
                }
            }
        });
    }

    public void setVariableForUpgrades(double amount){
        this.variableForUpgrades = amount;
    }

    public double getVariableForUpgrades() {
        return variableForUpgrades;
    }

    public long getPointsForCuredPeople() {
        return pointsForCuredPeople;
    }

    public void setPointsForCuredPeople(long pointsForCuredPeople) {
        this.pointsForCuredPeople = pointsForCuredPeople;
    }
}
