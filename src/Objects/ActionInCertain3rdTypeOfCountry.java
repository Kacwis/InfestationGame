package Objects;

public class ActionInCertain3rdTypeOfCountry extends ActionInCertainCountry{


    public ActionInCertain3rdTypeOfCountry(String nameOfCountry, int quantityOfCars, int quantityOfCargoVechicles, int quantityOfCoaches) {
        super(nameOfCountry, quantityOfCars, quantityOfCargoVechicles, quantityOfCoaches);
        levelOfAdvancementOfCountry = 3;
    }


    public void chceckingCriteria(Country country){
        if(country.infectedPopulation >= 10000)
            closingCoaches();
        else if(country.infectedPopulation >= country.population * 0.2)
            closingCargo();
        else if(country.infectedPopulation >= country.population * 0.4)
            closingTraffic();
        else if(country.infectedPopulation >= country.population * 0.5)
            closingStreets(country);
        else if(country.infectedPopulation > 10000){
            announcementOfPandemia(country);
            country.getInfestationInCountry().setPaceOfExpanding((int) country.getInfestationInCountry().getPaceOfExpanding() / 2);
        }
    }

    public void checkingPopulationForStartResarch(Country country){
        if(country.infectedPopulation <= 10000 || researchOnVaccine != null)
            return;
        startingResearch();
    }
}
