package Objects;

import Transports.*;

public class ActionsInCertain1stTypeOfCountry extends ActionInCertainCountry {



        public ActionsInCertain1stTypeOfCountry(String nameOfCountry, int quantityOfTrains, int quantityOfAirplanes, int quantityOfCars, int quantityOfCargoVechicles, int quantityOfCoaches ){
            super(nameOfCountry, quantityOfCars, quantityOfCargoVechicles, quantityOfCoaches);
            mapOfTransports.put("AIRLINES", new Airlines("AIRLINES" , quantityOfAirplanes));
            mapOfTransports.put("TRAINS", new Trains("TRAINS" , quantityOfTrains));
            levelOfAdvancementOfCountry = 1;
        }

        public void closingAirlines() {
            if(!mapOfTransports.containsKey("AIRLINES"))
                return;
            mapOfTransports.remove("AIRLINES");
            System.out.println("AIRLINES IN COUNTRY HAVE BEEN CLOSED");
        }

        public void closingTrainstations() {
            if(!mapOfTransports.containsKey("TRAINS"))
                return;
            mapOfTransports.remove("TRAINS");
            System.out.println("TRAIN STATIONS IN COUNTRY HAVE BEEN CLOSED");
        }


        public void chceckingCriteria(Country country){
            if(country.infectedPopulation >= country.getPopulation() * 0.2)
                closingAirlines();
            else if(country.infectedPopulation >= country.getPopulation() * 0.08)
                closingCoaches();
            else if(country.infectedPopulation >= (country.population * 0.1))
                closingTrainstations();
            else if(country.infectedPopulation >= country.population * 0.3)
                 closingCargo();
            else if(country.infectedPopulation >= country.population * 0.5)
                closingTraffic();
            else if(country.infectedPopulation >= country.population * 0.6)
                closingStreets(country);
            else if(country.infectedPopulation > 30000){
                announcementOfPandemia(country);
                country.getInfestationInCountry().setPaceOfExpanding((int) country.getInfestationInCountry().getPaceOfExpanding() / 2);
            }
        }

        public void checkingPopulationForStartResarch(Country country){
            if(country.infectedPopulation <= 20000)
                return;
            startingResearch();
        }
}
