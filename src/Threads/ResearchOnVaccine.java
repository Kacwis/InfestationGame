package Threads;

import Threads.GlobalResearchForVaccine;
import Utilites.GlobalStates;

public class ResearchOnVaccine{

    int levelOfAdvancementOfCountry;
    int pointsOfResearch;
    double counterOf1Point;
    boolean isItrunning;
    long timeOfGainingPoints;
    Thread researching;

    public ResearchOnVaccine(int levelOfAdvancementOfCountry){
        this.levelOfAdvancementOfCountry = levelOfAdvancementOfCountry;
        pointsOfResearch = 0;
        counterOf1Point = 0;
        isItrunning = true;
        adjustingVariables();
        this.researching = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isItrunning){
                    counterOf1Point += 0.2;
                    try{
                        Thread.sleep(timeOfGainingPoints);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    if(counterOf1Point != 1){
                        continue;
                    }
                    pointsOfResearch++;
                    counterOf1Point = 0;
                    addingPointsOfResearch();
                }
            }
        });
    }

    public void adjustingVariables(){
        switch(levelOfAdvancementOfCountry){
            case 1:
                timeOfGainingPoints = 50000 * GlobalStates.INSTANCE.difficultyLevel;
                break;
            case 2:
                timeOfGainingPoints = 100000 * GlobalStates.INSTANCE.difficultyLevel;
                break;
            case 3:
                timeOfGainingPoints = 150000 * GlobalStates.INSTANCE.difficultyLevel;
                break;
        }
    }

    private void addingPointsOfResearch(){
        GlobalResearchForVaccine.INSTANCE.counterOfPoints= GlobalResearchForVaccine.INSTANCE.counterOfPoints + 1;
    }

    public void stopResearch(){
        isItrunning = false;
        researching = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isItrunning){
                    counterOf1Point += 0.2;
                    try{
                        Thread.sleep(timeOfGainingPoints);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    if(counterOf1Point != 1){
                        continue;
                    }
                    pointsOfResearch++;
                    counterOf1Point = 0;
                    addingPointsOfResearch();
                }
            }
        });
    }

    private void stopRunning(){
        this.isItrunning = false;
    }

}
