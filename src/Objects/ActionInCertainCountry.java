package Objects;

import Threads.DeathRateInCountry;
import Threads.InfestationInCountry;
import Threads.ResearchOnVaccine;
import Threads.TreatmentInCountry;
import Transports.Cargo;
import Transports.Cars;
import Transports.Coaches;
import Transports.Transport;
import Utilites.Criteria;
import Utilites.GlobalStates;

import javax.swing.*;
import java.util.HashMap;

public class ActionInCertainCountry implements Criteria {

    String nameOfCountry;
    HashMap<String, Transport> mapOfTransports;
    ResearchOnVaccine researchOnVaccine;
    int levelOfAdvancementOfCountry;

    public ActionInCertainCountry(String nameOfCountry, int quantityOfCars, int quantityOfCargoVechicles, int quantityOfCoaches){
        this.nameOfCountry = nameOfCountry;
        mapOfTransports = new HashMap<>();
        mapOfTransports.put("CARS", new Cars("CARS" , quantityOfCars));
        mapOfTransports.put("CARGO", new Cargo("CARGO" , quantityOfCargoVechicles));
        mapOfTransports.put("COACHES", new Coaches("COACHES" , quantityOfCoaches));
    }

    @Override
    public void closingCoaches() {
        if(!mapOfTransports.containsKey("COACHES"))
            return;
        mapOfTransports.remove("COACHES");
        System.out.println("TOUR COACHES IN COUNTRY HAVE BEEN CLOSED");
    }

    @Override
    public void closingCargo() {
        if(!mapOfTransports.containsKey("CARGO"))
            return;
        mapOfTransports.remove("CARGO");
        System.out.println("CARGO TRANSPORT IN COUNTRY HAVE BEEN CLOSED");
    }

    @Override
    public void closingTraffic() {
        if(!mapOfTransports.containsKey("CARS"))
            return;
        mapOfTransports.remove("CARS");
        System.out.println("ALL TRAFFIC IN COUNTRY HAVE BEEN CLOSED");
    }

    @Override
    public void closingStreets(Country country) {
        country.getInfestationInCountry().setPaceOfExpanding(0.1);
        System.out.println("STREETS IN COUNTRY HAVE BEEN CLOSED, NO PEOPLE ARE ALLOWED TO GO OUTSIDE");
    }

    @Override
    public void announcementOfPandemia(Country country) {
        if(country.getInfestationInCountry().getPaceOfExpanding() < 0.3)
            return;
        country.getInfestationInCountry().setPaceOfExpanding(country.getInfestationInCountry().getPaceOfExpanding() - 0.2);
        System.out.println("ITS OFFICIALLY PANDEMIA");
    }

    public int checkingInfectedPeople(Country country){
        if(country.getInfectedPopulation() == 0 || country.getInfestationInCountry() != null || GlobalStates.INSTANCE.getMapOfCountries().get("China").equals(country)){
            return 0;
        }
        country.infestationInCountry = new InfestationInCountry(country);
        country.treatmentInCountry = new TreatmentInCountry(country);
        country.deathRateInCountry = new DeathRateInCountry(country);
        return 1;
    }


    public void startingResearch(){
        researchOnVaccine = new ResearchOnVaccine(levelOfAdvancementOfCountry);
    }

    public ResearchOnVaccine getResearchOnVaccine() {
        return researchOnVaccine;
    }

    public HashMap<String, Transport> getMapOfTransports() {
        return mapOfTransports;
    }

    public int getLevelOfAdvancementOfCountry() {
        return levelOfAdvancementOfCountry;
    }
}
