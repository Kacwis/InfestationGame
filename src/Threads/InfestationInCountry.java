package Threads;

import Objects.Country;
import Utilites.GlobalStates;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;

public class InfestationInCountry {

    boolean isInfestationRunning;
    Thread infestationInCountry;
    BigDecimal formulaOfSpreadingVirus;
    BigDecimal resultOfFormula = new BigDecimal(0);
    double paceOfExpanding;


    public InfestationInCountry(Country country){
        isInfestationRunning = true;
        paceOfExpanding = 5;
        infestationInCountry = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isInfestationRunning){
                    if(country.getInfectedPopulation() < 100)
                        setPaceOfExpanding(100);
                    else if(country.getInfectedPopulation() < 1000)
                        setPaceOfExpanding(5);
                    if(country.getInfectedPopulation() >= country.getPopulation()){
                        isInfestationRunning = false;
                        System.out.println(country.getName() + " HAS BEEN FULLY INFECTED ");
                    }
                    formulaOfSpreadingVirus = quantityOfInfectedPeople(country);
                    BigDecimal paceOfExpandingBigDecimal = new BigDecimal(paceOfExpanding);
                    resultOfFormula = resultOfFormula.multiply(paceOfExpandingBigDecimal);
                    resultOfFormula = resultOfFormula.add(formulaOfSpreadingVirus);
                    if(resultOfFormula.doubleValue() > 500000)
                        paceOfExpanding = 0.9;
                    if(country.getInfectedPopulation() + resultOfFormula.intValue() > country.getPopulation()) {
                        country.setInfectedPopulation(country.getPopulation());
                        continue;
                    }
                    country.infectedPopulation = country.infectedPopulation + resultOfFormula.intValue();
                    GlobalStates.INSTANCE.setAllInfectedPopulation(GlobalStates.INSTANCE.getAllInfectedPopulation() + resultOfFormula.intValue());
                    try{
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        infestationInCountry.start();
    }

    private BigDecimal quantityOfInfectedPeople(Country country){
        BigDecimal result;
        BigDecimal infectedPopulation = new BigDecimal(country.getInfectedPopulation());
        BigDecimal divider = new BigDecimal(country.getPopulation());
        result = infectedPopulation.divide(divider, 8 , RoundingMode.HALF_UP);
        return result;
    }

    public void stopInfestation(Country country){
        isInfestationRunning = false;
        infestationInCountry = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isInfestationRunning){
                    if(country.getInfectedPopulation() < 100)
                        setPaceOfExpanding(100);
                    else if(country.getInfectedPopulation() < 1000)
                        setPaceOfExpanding(5);
                    if(country.getInfectedPopulation() >= country.getPopulation()){
                        isInfestationRunning = false;
                        System.out.println(country.getName() + " HAS BEEN FULLY INFECTED ");
                    }
                    formulaOfSpreadingVirus = quantityOfInfectedPeople(country);
                    BigDecimal paceOfExpandingBigDecimal = new BigDecimal(paceOfExpanding);
                    resultOfFormula = resultOfFormula.multiply(paceOfExpandingBigDecimal);
                    resultOfFormula = resultOfFormula.add(formulaOfSpreadingVirus);
                    if(resultOfFormula.doubleValue() > 500000)
                        paceOfExpanding = 0.9;
                    if(country.getInfectedPopulation() + resultOfFormula.intValue() > country.getPopulation()) {
                        country.setInfectedPopulation(country.getPopulation());
                        continue;
                    }
                    country.infectedPopulation = country.infectedPopulation + resultOfFormula.intValue();
                    GlobalStates.INSTANCE.setAllInfectedPopulation(GlobalStates.INSTANCE.getAllInfectedPopulation() + resultOfFormula.intValue());
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

    public void setPaceOfExpanding(double amount){
        this.paceOfExpanding = amount;
    }

    public double getPaceOfExpanding() {
        return paceOfExpanding;
    }
}
