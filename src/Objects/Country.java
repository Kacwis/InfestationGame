package Objects;

import Threads.DeathRateInCountry;
import Threads.InfestationInCountry;
import Threads.TreatmentInCountry;
import Transports.*;
import Utilites.GlobalStates;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Country implements Comparable<Country> {

    final long borderOfPopulationFor1stLevel = 15000000;
    final long borderOfPopulationFor2stLevel = 2000000;

    String name;
    public long population;
    double x;
    double y;
    public long infectedPopulation;
    ActionInCertainCountry actionInCertainCountry;
    public InfestationInCountry infestationInCountry;
    ArrayList<Country> listOfNeighboringCountries;
    public TreatmentInCountry treatmentInCountry;
    public DeathRateInCountry deathRateInCountry;
    Image flag;
    ImageIcon iconOfFlag;
    String code;


    public Country(String name, long population){
        this.name = name;
        this.population = population;
        infectedPopulation = 0;
        listOfNeighboringCountries = new ArrayList<>();
        pickingLevelOfAdvancement();
    }

    public void pickingLevelOfAdvancement(){
        int quantityOfTrains = (int)((population / (Trains.getCapacityPerTrain()) *  0.05));
        int quantityOfCars = (int) (population / (Cars.getCapacityPerCar()));
        int quantityOfCargoVechicles = (int) ((population / (Cargo.getCapacityPerCargoVechicle()) * 0.1) );
        int quantityOfAirplanes = (int)((population / Airlines.getCapacityPerPlane()) * 0.04 );
        int quantityOfCoaches = (int) ((population / Coaches.getCapacityPerCoach()) * 0.09);

        if(population > borderOfPopulationFor1stLevel) {
            if(population > 50000000)
                quantityOfAirplanes = quantityOfAirplanes / 8000;
            else
                quantityOfAirplanes = quantityOfAirplanes / 4000;
            quantityOfTrains =  quantityOfTrains / 5000;
            quantityOfCargoVechicles = quantityOfCargoVechicles  / 3000;
            quantityOfCars = quantityOfCars / 1000;
            quantityOfCoaches =  (quantityOfCoaches / 6000);

            actionInCertainCountry = new ActionsInCertain1stTypeOfCountry(name, quantityOfTrains, quantityOfAirplanes, quantityOfCars, quantityOfCargoVechicles, quantityOfCoaches);
        }
        else if(population > borderOfPopulationFor2stLevel){
            quantityOfTrains = quantityOfTrains / 1000;
            quantityOfCars = quantityOfCars / 500;
            quantityOfCargoVechicles = quantityOfCargoVechicles / 1000;
            quantityOfCoaches = quantityOfCoaches / 1500;

            actionInCertainCountry = new ActionIn2ndTypeOfCountry(name, quantityOfTrains, quantityOfCars, quantityOfCargoVechicles, quantityOfCoaches);
        }
        else{
            quantityOfCars = quantityOfCars / 50;
            quantityOfCargoVechicles = quantityOfCargoVechicles / 150;
            quantityOfCoaches = quantityOfCoaches / 200;

            actionInCertainCountry = new ActionInCertain3rdTypeOfCountry(name, quantityOfCars, quantityOfCargoVechicles, quantityOfCoaches);
        }
    }

    public long getInfectedPopulation() {
        return infectedPopulation;
    }

    public String getName(){
        return  name;
    }

    public void setInfectedPopulation(long amount){
        this.infectedPopulation =  amount;
    }

    public InfestationInCountry getInfestationInCountry() {
        return infestationInCountry;
    }

    public ActionInCertainCountry getActionInCertainCountry() {
        return actionInCertainCountry;
    }

    public long getPopulation() {
        return population;
    }

    public ArrayList<Country> getListOfNeighboringCountries() {
        return listOfNeighboringCountries;
    }


    public void setCoordinates(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void addPopulation(int quantityOfHealthyPeople, int quantityOfInfectedPeople){
        population = population + quantityOfHealthyPeople;
        infectedPopulation = infectedPopulation + quantityOfInfectedPeople;
    }

    public void removePopulation(int quantityOfHealthyPeople, int quantityOfInfectedPeople){
        population = population - quantityOfHealthyPeople;
        if(infectedPopulation < quantityOfHealthyPeople)
            return;
        infectedPopulation = infectedPopulation - quantityOfInfectedPeople;
    }

    public TreatmentInCountry getTreatmentInCountry() {
        return treatmentInCountry;
    }

    public void setFlag(Image flag) {
        this.flag = flag;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Image getFlag() {
        return flag;
    }

    public void setIconOfFlag(ImageIcon iconOfFlag) {
        this.iconOfFlag = iconOfFlag;
    }

    public ImageIcon getIconOfFlag() {
        return iconOfFlag;
    }

    public DeathRateInCountry getDeathRateInCountry() {
        return deathRateInCountry;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Country country) {
        if(this.name.toCharArray()[0] < country.name.toCharArray()[0])
            return  -1;
        if(this.name.toCharArray()[0] > country.name.toCharArray()[0])
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
