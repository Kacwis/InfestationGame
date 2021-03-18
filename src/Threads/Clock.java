package Threads;

import Objects.ActionIn2ndTypeOfCountry;
import Objects.ActionInCertain3rdTypeOfCountry;
import Objects.ActionsInCertain1stTypeOfCountry;
import Objects.Country;
import Utilites.GlobalStates;

import java.time.LocalDate;

public class Clock {
    LocalDate globalTime;
    boolean workingClock;
    Thread clock;

    public static Clock INSTANCE = new Clock();

    private Clock(){
        clock = new Thread(new Runnable() {
            @Override
            public void run() {
                while(workingClock){
                    try{
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                    }
                    catch(InterruptedException e){
                        continue;
                    }
                     globalTime = globalTime.plusDays(1);
                }
            }
        });
    }

    public void startClock(LocalDate startingDate){
        this.globalTime = startingDate;
        workingClock = true;
        clock.start();
    }

    public void checkingCriteria(Country country){
        if(country.getActionInCertainCountry().getClass() == ActionsInCertain1stTypeOfCountry.class){
            ((ActionsInCertain1stTypeOfCountry)country.getActionInCertainCountry()).chceckingCriteria(country);
        }
        else if(country.getActionInCertainCountry().getClass() == ActionIn2ndTypeOfCountry.class){
            ((ActionIn2ndTypeOfCountry)country.getActionInCertainCountry()).chceckingCriteria(country);
        }
        else if(country.getActionInCertainCountry().getClass() == ActionInCertain3rdTypeOfCountry.class){
            ((ActionInCertain3rdTypeOfCountry)country.getActionInCertainCountry()).chceckingCriteria(country);
        }
    }

    public void stopClock(){
        this.workingClock = false;
        clock = new Thread(new Runnable() {
            @Override
            public void run() {
                while(workingClock){
                    try{
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                    }
                    catch(InterruptedException e){
                        continue;
                    }
                    globalTime = globalTime.plusDays(1);
                }
            }
        });
    }

    public Thread getClock() {
        return clock;
    }

    public LocalDate getGlobalTime() {
        return globalTime;
    }
}
