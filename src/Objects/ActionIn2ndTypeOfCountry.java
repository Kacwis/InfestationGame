package Objects;

import Transports.Trains;

public class ActionIn2ndTypeOfCountry  extends ActionInCertainCountry {


    public ActionIn2ndTypeOfCountry(String nameOfCountry, int quantityOfTrains, int quantityOfCars, int quantityOfCargoVechicles, int quantityOfCoaches) {
        super(nameOfCountry, quantityOfCars, quantityOfCargoVechicles, quantityOfCoaches);
        mapOfTransports.put("TRAINS", new Trains("TRAINS", quantityOfTrains));
        levelOfAdvancementOfCountry = 2;
    }

    public void closingTrainstations() {
        if(!mapOfTransports.containsKey("TRAINS"))
            return;
        mapOfTransports.remove("TRAINS");
        System.out.println("TRAIN STATIONS IN COUNTRY HAVE BEEN CLOSED");
    }


    public void chceckingCriteria(Country country){
        if(country.infectedPopulation >= 100000)
            closingCoaches();
        else if(country.infectedPopulation >= (country.population * 0.1))
            closingTrainstations();
        else if(country.infectedPopulation >= country.population * 0.3)
            closingCargo();
        else if(country.infectedPopulation >= country.population * 0.4)
            closingTraffic();
        else if(country.infectedPopulation >= country.population * 0.5)
            closingStreets(country);
        else if(country.infectedPopulation > 30000){
            announcementOfPandemia(country);
            country.getInfestationInCountry().setPaceOfExpanding((int) country.getInfestationInCountry().getPaceOfExpanding() / 2);
        }
    }

    public void checkingPopulationForStartResarch(Country country){
        if(country.infectedPopulation <= 50000)
            return;
        startingResearch();
    }
}
