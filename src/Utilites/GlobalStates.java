package Utilites;

import Objects.*;
import Threads.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

public class GlobalStates {

    HashMap< String,Country> mapOfCountries;
    Map<Country, ArrayList<Country> > graphs;
    TreeMap<String, Country> sortedMapOfcountries;
    ArrayList<Country> listOfCountriesWithAirlines;
    ArrayList<Country> listOfCountriesWithTrainstation;
    ArrayList<HighScore> setOfHighScores = new ArrayList<>();

    public static GlobalStates INSTANCE = new GlobalStates();

    public long allPopulation;
    public long allInfectedPopulation;
    public byte difficultyLevel = 1;
    int counterOfCountries = 0;
    int lengthOfDay = 3000;
    public long pointsForCuredPeopleGlobal = 0;
    public long counterOfDeaths = 0;
    public static Image imageOfmapOfTheWorld;
    public static final Color menuColor = Color.gray;
    public static boolean isItEnd;
    final public int quantityOfFirstInfectedPeople = 5000;
    String informations;

    private GlobalStates(){
        allPopulation = 0;
        allInfectedPopulation = quantityOfFirstInfectedPeople;
        isItEnd = false;
        mapOfCountries = new HashMap<String,Country>();
        graphs = new HashMap<Country, ArrayList<Country>>();
        listOfCountriesWithAirlines = new ArrayList<>();
        listOfCountriesWithTrainstation = new ArrayList<>();
        addAllCountries();
        addAllConnections();
        loadingHighScores();
        sortedMapOfcountries = new TreeMap<String, Country>(mapOfCountries);
        addingCoordinatesToCountries();
        addingCodeOfCountry();
        addingFlagsToCountries();
        creatingGraph();
        creatingListsOfTypesCountry();
        readingImageOfTheWorld();
        setInfromations();
    }

    public void setLengthOfDay(int amount){
        this.lengthOfDay = amount;
    }

    public int getLengthOfDay(){
        return  lengthOfDay;
    }

    public void setDifficultyLevel(byte difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public long getAllInfectedPopulation() {
        return allInfectedPopulation;
    }

    public long getAllPopulation() {
        return allPopulation;
    }

    public HashMap<String, Country> getMapOfCountries() {
        return mapOfCountries;
    }

    void addInfectedPeople(long quantityOfInfectedPeople){
        this.allInfectedPopulation =+ quantityOfInfectedPeople;
    }

    private void readingImageOfTheWorld(){
        try {
            File inputFile = new File("src/mapOfWorld.jpg");
            imageOfmapOfTheWorld = ImageIO.read(inputFile);
            System.out.println(imageOfmapOfTheWorld.getWidth(new ImageObserver() {
                @Override
                public boolean imageUpdate(Image image, int i, int i1, int i2, int i3, int i4) {
                    return false;
                }
            }));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addAllCountries(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("src/population.csv"));

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        try {
            String currentLine = "";
            String[] splitedLine = {""};
            while (currentLine != null){
                currentLine = reader.readLine();
                if(currentLine == null)
                    continue;
                splitedLine = currentLine.split(",");
                if(!splitedLine[2].equals("2018")){
                    continue;
                }
                String nameOfCountry = splitedLine[0];
                Long population = Long.parseLong(splitedLine[3]);
                allPopulation = allPopulation + population.longValue();
                mapOfCountries.put( nameOfCountry, new Country(nameOfCountry, population.longValue()));
                counterOfCountries++;
            }
            reader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        mapOfCountries.get("China").setInfectedPopulation(quantityOfFirstInfectedPeople);
    }

    private void addAllConnections(){
        try {
           BufferedReader reader = new BufferedReader(new FileReader("src/Connections.CSV"));
           String currentLine ="";
           String nameOfCurrentCountry = "";
           String[] splitedLine;
           String nameOfConnectedCountry = "";
           while (currentLine != null){
               currentLine = reader.readLine();
               if(currentLine == null)
                   continue;
               splitedLine = currentLine.split(",");
               nameOfCurrentCountry = splitedLine[1];
               nameOfConnectedCountry = splitedLine[3];
               nameOfCurrentCountry = nameOfCurrentCountry.substring(1, nameOfCurrentCountry.length() - 1);
               nameOfConnectedCountry = nameOfConnectedCountry.substring(1, nameOfConnectedCountry.length() - 1);
               if(!mapOfCountries.containsKey(nameOfCurrentCountry) || !mapOfCountries.containsKey(nameOfConnectedCountry)){
                   continue;
               }
               mapOfCountries.get(nameOfCurrentCountry).getListOfNeighboringCountries().add(mapOfCountries.get(nameOfConnectedCountry));
           }
           reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addingCodeOfCountry(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/countriesCodes.csv"));
            String currentLine ="";
            String nameOfCurrentCountry = "";
            String[] splitedLine;
            String codeOfCountry = "";
            while (currentLine != null){
                currentLine = reader.readLine();
                if(currentLine == null)
                    continue;
                splitedLine = currentLine.split(",");
                nameOfCurrentCountry = splitedLine[0];
                codeOfCountry = splitedLine[1];
                if(!mapOfCountries.containsKey(nameOfCurrentCountry))
                    continue;
                mapOfCountries.get(nameOfCurrentCountry).setCode(codeOfCountry);
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addingFlagsToCountries(){
        for (Country country : mapOfCountries.values()){
            String pathOfImageOfFlag = "src/Flags/" + country.getCode() + "-32.png";
            country.setIconOfFlag(new ImageIcon(pathOfImageOfFlag));
            try {
                Image flag = ImageIO.read(new File(pathOfImageOfFlag));
                country.setFlag(flag);
            }
            catch (Exception e){
                continue;
            }
        }
    }

    public void addingCoordinatesToCountries(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Cordinates.csv"));
            String currentLine = "";
            String nameOfCurrentCountry;
            String[] splitedLine;
            double x;
            double y;
            while(currentLine != null){
                currentLine = reader.readLine();
                if(currentLine == null)
                    continue;
                splitedLine = currentLine.split(";");
                x = Double.parseDouble(splitedLine[1]);
                y = Double.parseDouble(splitedLine[2]);
                nameOfCurrentCountry = splitedLine[3];
                if(!mapOfCountries.containsKey(nameOfCurrentCountry)){
                    continue;
                }
                mapOfCountries.get(nameOfCurrentCountry).setCoordinates(x, y);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void creatingListsOfTypesCountry(){
        for(Country country : mapOfCountries.values()){
            if(country.getActionInCertainCountry().getMapOfTransports().containsKey("AIRLINES")) {
                listOfCountriesWithAirlines.add(country);
            }
            else if(country.getActionInCertainCountry().getMapOfTransports().containsKey("TRAINS")){
                listOfCountriesWithTrainstation.add(country);
            }
        }
    }

    public void creatingGraph(){
        for(Country country : mapOfCountries.values()){
            graphs.put(country, country.getListOfNeighboringCountries());
        }
    }


    public void setInfromations(){
        this.informations = "  THIS IS ANTI - PLAQUE   \n" +
                            "Your goal in this game is to stop virus before all planet become infected.\n" +
                            "You will have few global upgrades at your disposal, but every country will\n" +
                            "have their own upgrades. Good luck and save our planet!";
    }

    public void settingNewGame(){
        allInfectedPopulation = quantityOfFirstInfectedPeople;
        pointsForCuredPeopleGlobal = 0;
        counterOfDeaths = 0;
        isItEnd = false;
        for(Country country : mapOfCountries.values()){
            country.infectedPopulation = 0;
            country.treatmentInCountry = null;
            country.deathRateInCountry = null;
            country.infestationInCountry = null;
        }
        Clock.INSTANCE.startClock(LocalDate.now());
        GlobalInfestation.INSTANCE.startGlobalInfestation();
        WorldwideTransporting.INSTANCE.transportStart();
        GlobalResearchForVaccine.INSTANCE.startingThreadsReasarch();
        Country china = mapOfCountries.get("China");
        china.setInfectedPopulation(quantityOfFirstInfectedPeople);
        setLengthOfDay(5000);
        china.infestationInCountry = new InfestationInCountry(china);
        china.treatmentInCountry = new TreatmentInCountry(china);
        china.deathRateInCountry = new DeathRateInCountry(china);
    }

    public void stoppingGame(){
        Clock.INSTANCE.stopClock();
        System.out.println(Clock.INSTANCE.getClock().getState());
        GlobalInfestation.INSTANCE.stopGlobalInfestation();
        WorldwideTransporting.INSTANCE.stopTransport();
        GlobalResearchForVaccine.INSTANCE.stopThreadReaserch();
        for(Country country : mapOfCountries.values()){
            if(country.getDeathRateInCountry() != null)
                country.getDeathRateInCountry().stopDeathRate(country);
            if(country.getInfestationInCountry() != null)
                country.getInfestationInCountry().stopInfestation(country);
            if(country.getActionInCertainCountry().getResearchOnVaccine() != null)
                country.getActionInCertainCountry().getResearchOnVaccine().stopResearch();
            if(country.getTreatmentInCountry() != null)
                country.getTreatmentInCountry().stopTreatment(country);
        }
    }

    public void savingHighScores(){
        try{
            File serializable = new File("src/highScores.ser");
            FileOutputStream fileOut = new FileOutputStream(serializable);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (HighScore highScore : setOfHighScores){
                out.writeObject(highScore);
            }
            out.close();
            fileOut.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadingHighScores(){
        try{
            FileInputStream fileIn = new FileInputStream("src/highScores.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            while(in.available() > 0){
                setOfHighScores.add((HighScore)in.readObject());
            }
            in.close();
            fileIn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public long getPointsForCuredPeopleGlobal() {
        return pointsForCuredPeopleGlobal;
    }

    public void setAllInfectedPopulation(long allInfectedPopulation) {
        this.allInfectedPopulation = allInfectedPopulation;
    }

    public String getInformations() {
        return informations;
    }

    public ArrayList<HighScore> getSetOfHighScores() {
        return setOfHighScores;
    }

    public Map<Country, ArrayList<Country>> getGraphs() {
        return graphs;
    }

    public TreeMap<String,Country> getSortedMapOfcountries() {
        return sortedMapOfcountries;
    }

    public ArrayList<Country> getListOfCountriesWithAirlines() {
        return listOfCountriesWithAirlines;
    }

    public ArrayList<Country> getListOfCountriesWithTrainstation() {
        return listOfCountriesWithTrainstation;
    }

    public long getCounterOfDeaths() {
        return counterOfDeaths;
    }
}
