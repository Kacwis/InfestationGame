package Threads;

import Objects.Country;
import Utilites.GlobalStates;

public class DeathRateInCountry {

    final double maxOfRandom = 0.014;
    final double minOfRandom = 0.01;

    Thread deathRate;
    boolean workingDeathRate;
    long quantityOfDeaths;
    double randomPercentage;
    long counterOfDeaths = 0;

    public DeathRateInCountry(Country country){
        workingDeathRate = (country.infectedPopulation != 0) || (country.population != 0);
        deathRate = new Thread(new Runnable() {
            @Override
            public void run() {
                while(workingDeathRate){
                    try {
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay() * 7);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    randomPercentage = Math.random() * (maxOfRandom - minOfRandom ) + minOfRandom;
                    quantityOfDeaths =(long) (country.getInfectedPopulation() * randomPercentage);
                    country.infectedPopulation = country.getInfectedPopulation() - quantityOfDeaths;
                    country.population = country.getPopulation() - quantityOfDeaths;
                    GlobalStates.INSTANCE.counterOfDeaths = GlobalStates.INSTANCE.counterOfDeaths + quantityOfDeaths;
                    GlobalStates.INSTANCE.allPopulation = GlobalStates.INSTANCE.allPopulation - quantityOfDeaths;
                    GlobalStates.INSTANCE.allInfectedPopulation = GlobalStates.INSTANCE.getAllInfectedPopulation() - quantityOfDeaths;
                    counterOfDeaths = counterOfDeaths + quantityOfDeaths;
                }
            }
        });
        deathRate.start();
    }

    public void stopDeathRate(Country country){
        workingDeathRate = false;
        deathRate = new Thread(new Runnable() {
            @Override
            public void run() {
                while(workingDeathRate){
                    try {
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay() * 7);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    randomPercentage = Math.random() * (maxOfRandom - minOfRandom ) + minOfRandom;
                    quantityOfDeaths =(long) (country.getInfectedPopulation() * randomPercentage);
                    country.infectedPopulation = country.getInfectedPopulation() - quantityOfDeaths;
                    country.population = country.getPopulation() - quantityOfDeaths;
                    GlobalStates.INSTANCE.counterOfDeaths = GlobalStates.INSTANCE.counterOfDeaths + quantityOfDeaths;
                    GlobalStates.INSTANCE.allPopulation = GlobalStates.INSTANCE.allPopulation - quantityOfDeaths;
                    GlobalStates.INSTANCE.allInfectedPopulation = GlobalStates.INSTANCE.getAllInfectedPopulation() - quantityOfDeaths;
                    counterOfDeaths = counterOfDeaths + quantityOfDeaths;
                }
            }
        });
    }
}