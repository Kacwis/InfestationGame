package Threads;

import Objects.ActionIn2ndTypeOfCountry;
import Objects.ActionInCertain3rdTypeOfCountry;
import Objects.ActionsInCertain1stTypeOfCountry;
import Objects.Country;
import Utilites.GlobalStates;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class WorldwideTransporting {

    Thread transport;
    boolean isTransportRunning;

    public static WorldwideTransporting INSTANCE = new WorldwideTransporting();

    private WorldwideTransporting(){
        transport = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isTransportRunning){
                    transportOfPlanes();
                    transportOfTrains();
                    transportOfCars();
                    transportOfCoaches();
                    transportOfCargo();
                    try {
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void transportOfPlanes(){
        int planesToCountries = 10;
        ArrayList<Country> countriesForAirlines = creatingListOfCountriesWithCertainTransport("AIRLINES");

        for(Country country : countriesForAirlines){

            if(!country.getActionInCertainCountry().getMapOfTransports().containsKey("AIRLINES"))
                continue;
            int capacityPerCountry = country.getActionInCertainCountry().getMapOfTransports().get("AIRLINES").getCapacityPerDay();
            int sizeOfListAvailableCountries = countriesForAirlines.size();
            int range = sizeOfListAvailableCountries + 1;

            ArrayList<Country> usedCountries = new ArrayList<>();
            int random;
            for (int i = 0; i < planesToCountries; i++) {
                random = (int) Math.random() * range;
                Country usingCountry = countriesForAirlines.get(random);
                if(usedCountries.contains(usingCountry) || country.getName() == usingCountry.getName())
                    continue;
                usedCountries.add(usingCountry);
                int quantityOfInfectedPeople = quantityOfInfectedPeople(country, capacityPerCountry);
                int quantityOfHealthyPeople = capacityPerCountry / planesToCountries - quantityOfInfectedPeople;
                if(!usingCountry.getActionInCertainCountry().getMapOfTransports().containsKey("COACHES") || usingCountry.getName() == country.getName())
                    continue;
                usingCountry.addPopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                country.removePopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
            }
        }
    }

    private void transportOfTrains(){
        ArrayList<Country> countriesForTrains = creatingListOfCountriesWithCertainTransport("TRAINS");
        int trainsToCountries;
        int capacityPerCountry;
        int quantityOfHealthyPeople;
        int quantityOfInfectedPeople;
        for(Country country : countriesForTrains){
            if(!country.getActionInCertainCountry().getMapOfTransports().containsKey("TRAINS"))
                continue;
            capacityPerCountry = country.getActionInCertainCountry().getMapOfTransports().get("TRAINS").getCapacityPerDay();
            if(country.getActionInCertainCountry().getClass() == ActionsInCertain1stTypeOfCountry.class) {
                ArrayList<Country> listOfAvailableCountries = creatingListOfAvailableCountriesOfCountry(country, 30, 40);
                if(listOfAvailableCountries.isEmpty())
                    continue;
                trainsToCountries = listOfAvailableCountries.size();
                quantityOfHealthyPeople = capacityPerCountry / trainsToCountries;
                quantityOfInfectedPeople = quantityOfInfectedPeople(country, capacityPerCountry);
                for(Country destinationCountry : listOfAvailableCountries){
                    if(!destinationCountry.getActionInCertainCountry().getMapOfTransports().containsKey("TRAINS") || destinationCountry.getName() == country.getName())
                        continue;
                    destinationCountry.addPopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                    country.removePopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                }
            }
            else if(country.getActionInCertainCountry().getClass() == ActionIn2ndTypeOfCountry.class){
                ArrayList<Country> listOfAvailableCountries = creatingListOfAvailableCountriesOfCountry(country, 20 , 30);
                trainsToCountries = listOfAvailableCountries.size();
                quantityOfHealthyPeople = capacityPerCountry / trainsToCountries;
                quantityOfInfectedPeople = quantityOfInfectedPeople(country, capacityPerCountry);
                for(Country destinationCountry : listOfAvailableCountries){
                    if(!destinationCountry.getActionInCertainCountry().getMapOfTransports().containsKey("TRAINS") || destinationCountry.getName() == country.getName())
                        continue;
                    destinationCountry.addPopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                    country.removePopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                }
            }
        }
    }

    private void transportOfCars(){
        HashMap< String ,Country> mapOfCountries = GlobalStates.INSTANCE.getMapOfCountries();
        for(Country country : mapOfCountries.values()){
            ArrayList<Country> listOfNeighboringCountries = country.getListOfNeighboringCountries();
            if(!country.getActionInCertainCountry().getMapOfTransports().containsKey("CARS"))
                continue;
            int capacityPerCountry = country.getActionInCertainCountry().getMapOfTransports().get("CARS").getCapacityPerDay();
            if(listOfNeighboringCountries.isEmpty())
                continue;
            int carsToCountries = listOfNeighboringCountries.size();
            int quantityOfHealthyPeople =(int) (capacityPerCountry / carsToCountries);
            int quantityOfInfectedPeople = quantityOfInfectedPeople(country, capacityPerCountry);
            for(Country destinationCountry : listOfNeighboringCountries){
                if(!destinationCountry.getActionInCertainCountry().getMapOfTransports().containsKey("CARS") || destinationCountry.getName() == country.getName())
                    continue;
                destinationCountry.addPopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                country.removePopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
            }
        }
    }

    private void transportOfCoaches(){
        HashMap<String, Country> mapOfCountriesForCoaches = GlobalStates.INSTANCE.getMapOfCountries();
        int coachesToCountries;
        int capacityPerCountry;
        int quantityOfHealthyPeople;
        int quantityOfInfectedPeople;
        for(Country country : mapOfCountriesForCoaches.values()){
            if(!country.getActionInCertainCountry().getMapOfTransports().containsKey("COACHES"))
                continue;
            capacityPerCountry = country.getActionInCertainCountry().getMapOfTransports().get("COACHES").getCapacityPerDay();
            if(country.getActionInCertainCountry().getClass() == ActionsInCertain1stTypeOfCountry.class){
                ArrayList<Country> listOfAvailableCountries = creatingListOfAvailableCountriesOfCountry(country, 30, 40);
                if(listOfAvailableCountries.isEmpty())
                    continue;
                coachesToCountries = listOfAvailableCountries.size();
                quantityOfHealthyPeople = capacityPerCountry / coachesToCountries;
                quantityOfInfectedPeople = quantityOfInfectedPeople(country, capacityPerCountry);
                for(Country destinationCountry : listOfAvailableCountries){
                    if(!destinationCountry.getActionInCertainCountry().getMapOfTransports().containsKey("COACHES") || destinationCountry.getName() == country.getName())
                        continue;
                    destinationCountry.addPopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                    country.removePopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                }
            }
            else if(country.getActionInCertainCountry().getClass() == ActionIn2ndTypeOfCountry.class){
                ArrayList<Country>  listOfAvailableCountries = creatingListOfAvailableCountriesOfCountry(country, 20, 40);
                coachesToCountries = listOfAvailableCountries.size();
                quantityOfHealthyPeople = capacityPerCountry / coachesToCountries;
                quantityOfInfectedPeople = quantityOfInfectedPeople(country, capacityPerCountry);
                for(Country destinationCountry : listOfAvailableCountries){
                    if(!destinationCountry.getActionInCertainCountry().getMapOfTransports().containsKey("COACHES") || destinationCountry.getName() == country.getName())
                        continue;
                    destinationCountry.addPopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                    country.removePopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                }
            }
            else if(country.getActionInCertainCountry().getClass() == ActionInCertain3rdTypeOfCountry.class){
                ArrayList<Country> listOfAvailableCountries = creatingListOfAvailableCountriesOfCountry(country, 10, 20);
                if(listOfAvailableCountries.isEmpty())
                    continue;
                coachesToCountries = listOfAvailableCountries.size();
                quantityOfHealthyPeople =  capacityPerCountry / coachesToCountries;
                quantityOfInfectedPeople = quantityOfInfectedPeople(country, capacityPerCountry);
                for(Country destinationCountry : listOfAvailableCountries){
                    if(!destinationCountry.getActionInCertainCountry().getMapOfTransports().containsKey("COACHES") || destinationCountry.getName() == country.getName())
                        continue;
                    destinationCountry.addPopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                    country.removePopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                }
            }
        }
    }

    private void transportOfCargo(){
        ArrayList<Country> listOfCountries= new ArrayList<>(GlobalStates.INSTANCE.getMapOfCountries().values());
        int cargoVehiclesToCountries  = listOfCountries.size() - 100;
        int capacityPerCountry;
        int quantityOfHealthyPeople;
        int quantityOfInfectedPeople;
        int range;
        int random;
        ArrayList<Country> usedCountries = new ArrayList<>();
        for(Country country : listOfCountries){
            if(country.getActionInCertainCountry().getClass() == ActionsInCertain1stTypeOfCountry.class){
                for(int i = 0; i< cargoVehiclesToCountries ; i++) {
                    range = listOfCountries.size();
                    random = (int) (Math.random() * range);
                    if(listOfCountries.get(random).getName() == country.getName() || usedCountries.contains(listOfCountries.get(random)))
                        continue;
                    usedCountries.add(listOfCountries.get(random));
                    if(!country.getActionInCertainCountry().getMapOfTransports().containsKey("CARGO"))
                        continue;
                    capacityPerCountry = country.getActionInCertainCountry().getMapOfTransports().get("CARGO").getCapacityPerDay();
                    quantityOfHealthyPeople = capacityPerCountry/ cargoVehiclesToCountries;
                    quantityOfInfectedPeople = quantityOfInfectedPeople(country, capacityPerCountry);
                    Country randomCountry = listOfCountries.get(random);
                    if(!randomCountry.getActionInCertainCountry().getMapOfTransports().containsKey("CARGO") || randomCountry.getName() == country.getName())
                        continue;
                    listOfCountries.get(random).addPopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                    country.removePopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                }
            }
            else if(country.getActionInCertainCountry().getClass() == ActionIn2ndTypeOfCountry.class){
                ArrayList<Country> listOfAvailableCountries = creatingListOfAvailableCountriesOfCountry(country, 60, 70);
                if(!country.getActionInCertainCountry().getMapOfTransports().containsKey("CARGO"))
                    continue;
                capacityPerCountry = country.getActionInCertainCountry().getMapOfTransports().get("CARGO").getCapacityPerDay();
                cargoVehiclesToCountries = listOfAvailableCountries.size();
                quantityOfHealthyPeople = capacityPerCountry / cargoVehiclesToCountries;
                quantityOfInfectedPeople = quantityOfInfectedPeople(country, capacityPerCountry);
                for(Country destinationCountry : listOfAvailableCountries){
                    if(!destinationCountry.getActionInCertainCountry().getMapOfTransports().containsKey("COACHES") || destinationCountry.getName() == country.getName())
                        continue;
                    destinationCountry.addPopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                    country.removePopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                }
            }
            else{
                ArrayList<Country> listOfAvailableCountries = creatingListOfAvailableCountriesOfCountry(country, 40, 50);
                if(!country.getActionInCertainCountry().getMapOfTransports().containsKey("CARGO"))
                    continue;
                capacityPerCountry = country.getActionInCertainCountry().getMapOfTransports().get("CARGO").getCapacityPerDay();
                if(listOfAvailableCountries.isEmpty())
                    continue;
                cargoVehiclesToCountries = listOfAvailableCountries.size();
                quantityOfHealthyPeople = capacityPerCountry / cargoVehiclesToCountries;
                quantityOfInfectedPeople = quantityOfInfectedPeople(country, capacityPerCountry);
                for(Country destinationCountry : listOfAvailableCountries){
                    if(!destinationCountry.getActionInCertainCountry().getMapOfTransports().containsKey("CARGO") || destinationCountry.getName() == country.getName())
                        continue;
                    destinationCountry.addPopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                    country.removePopulation(quantityOfHealthyPeople, quantityOfInfectedPeople);
                }
            }
        }
    }


    private int quantityOfInfectedPeople(Country country, int capacityOfVechiclePerDay){
        int result = 0;
        if(country.getInfectedPopulation() > 20000)
            result = (int)(capacityOfVechiclePerDay * 0.00005);
        else if(country.getInfectedPopulation() > 50000)
            result = (int)(capacityOfVechiclePerDay * 0.0015);
        else if(country.getInfectedPopulation() > 100000)
            result  = (int) (capacityOfVechiclePerDay * 0.003);
        return result;
    }

    private ArrayList<Country> creatingListOfAvailableCountriesOfCountry(Country mainCountry, int xRange, int yRange){
        ArrayList<Country> listOfAvailableCountry = new ArrayList<>();
        double rangeXMax =  mainCountry.getX() + xRange;
        double rangeXMin =  mainCountry.getX() - xRange;
        double rangeYMax = mainCountry.getY() + yRange;
        double rangeYMin = mainCountry.getY() - yRange;
        boolean checkingX;
        boolean checkingY;
        for(Country countryBeingChecked : GlobalStates.INSTANCE.getListOfCountriesWithTrainstation()){
            checkingX = countryBeingChecked.getX() < rangeXMax && countryBeingChecked.getX() > rangeXMin;
            checkingY = countryBeingChecked.getY() < rangeYMax && countryBeingChecked.getY() > rangeYMin;
            if(checkingX && checkingY){
                listOfAvailableCountry.add(countryBeingChecked);
            }
        }
        return  listOfAvailableCountry;
    }


    public void transportStart(){
        isTransportRunning = true;
        transport.start();
    }

    public void stopTransport(){
        isTransportRunning = false;
        transport = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isTransportRunning){
                    transportOfPlanes();
                    transportOfTrains();
                    transportOfCars();
                    transportOfCoaches();
                    transportOfCargo();
                    try {
                        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    public ArrayList<Country> creatingListOfCountriesWithCertainTransport(String transport){
        ArrayList<Country> listOfCountriesWithAirlines = new ArrayList<>();
        for(Country country : GlobalStates.INSTANCE.getMapOfCountries().values()){
            if(!country.getActionInCertainCountry().getMapOfTransports().containsKey(transport)) {
                continue;
            }
            listOfCountriesWithAirlines.add(country);
        }
        return  listOfCountriesWithAirlines;
    }

}