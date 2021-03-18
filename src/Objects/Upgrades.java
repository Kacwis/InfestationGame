package Objects;

import Threads.GlobalResearchForVaccine;
import Utilites.GlobalStates;
import Utilites.NotEnoughPointsException;

import java.util.ArrayList;

public class Upgrades {


    final int infectedPeopleDeductedFrom1Upgrade = 5000;
    final int infectedPeopleDeductedFrom2Upgrade = 20000;
    final int infectedPeopleDeductedFrom3Upgrade = 50000;
    final long floorOfPoints1stUpgrade = 100000;
    final long floorOfPoints2ndUpgrade = 500000;
    final long floorOfPoints3rdUpgrade = 2000000;
    final long floorOfPoints4thUpgrade = 5000000;
    final long floorOfPoints5thUpgrade = 10000000;

    public static Upgrades INSTANCE = new Upgrades();

    private Upgrades(){
    }

    //IN COUNTRIES

    public void creatingAdvertisements(Country country){
        if(country.getInfestationInCountry().getPaceOfExpanding() < 0.3) {
            country.getInfestationInCountry().setPaceOfExpanding(0.1);
            return;
        }
        country.getInfestationInCountry().setPaceOfExpanding(country.getInfestationInCountry().getPaceOfExpanding() - 0.2);
    }

    public void creatingFieldHospitals(Country country){
        country.getTreatmentInCountry().setVariableForUpgrades(country.getTreatmentInCountry().getVariableForUpgrades() + 0.8);
        System.out.println("FIELD HOSPITALS HAVE BEEN CREATED IN " + country);
    }

    public void buyingExperimentalDrug(Country country){
        country.getTreatmentInCountry().setVariableForUpgrades(country.getTreatmentInCountry().getVariableForUpgrades() + 0.2);
        if(country.getInfestationInCountry().getPaceOfExpanding() < 0.5) {
            country.getInfestationInCountry().setPaceOfExpanding(0.1);
            return;
        }
        country.getInfestationInCountry().setPaceOfExpanding(country.getInfestationInCountry().getPaceOfExpanding() - 0.4);
        System.out.println("GOVERNMENT OF " + country + " BOUGHT EXPERIMENTAL DRUG FOR VIRUS");
    }

    public void gettingHelpFromOtherCountries(Country country){
        if(country.getInfestationInCountry().getPaceOfExpanding() == 0.1)
            return;
        country.getInfestationInCountry().setPaceOfExpanding(country.getInfestationInCountry().getPaceOfExpanding() / 2);
        ArrayList<Country> list1typeCountries =  GlobalStates.INSTANCE.getListOfCountriesWithAirlines();
        int randomIndex = (int) (Math.random() * list1typeCountries.size() + 1);
        System.out.println(list1typeCountries.get(randomIndex) + " HELPED " + country);
    }
    

    //GLOBAL

    public void tellingWordItsGlobalEpidemic(long points) throws NotEnoughPointsException {
        if(points < floorOfPoints1stUpgrade) {
            throw new NotEnoughPointsException();
        }
        for(Country country : GlobalStates.INSTANCE.getMapOfCountries().values()){
            if(country.getInfestationInCountry() == null)
                continue;
            country.getInfestationInCountry().setPaceOfExpanding(country.getInfestationInCountry().getPaceOfExpanding() - 0.2);
            GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.getPointsForCuredPeopleGlobal() - floorOfPoints1stUpgrade;
        }
    }

    public void progressOfVaccine(long points) throws NotEnoughPointsException{
        if(points < floorOfPoints2ndUpgrade)
            throw new NotEnoughPointsException();
        GlobalResearchForVaccine.INSTANCE.setVariableForUpgrades(GlobalResearchForVaccine.INSTANCE.getVariableForUpgrades() * 2);
        GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.getPointsForCuredPeopleGlobal() - floorOfPoints2ndUpgrade;
    }

    public void creatingExperimentalMedicineForVirus(long points) throws NotEnoughPointsException{
        if(points < floorOfPoints3rdUpgrade)
            throw new NotEnoughPointsException();
        for(Country country : GlobalStates.INSTANCE.getMapOfCountries().values()){
            if(country.getTreatmentInCountry() == null)
                continue;
            country.getTreatmentInCountry().setVariableForUpgrades(country.getTreatmentInCountry().getVariableForUpgrades() + 1);
            GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.getPointsForCuredPeopleGlobal() - floorOfPoints3rdUpgrade;
        }
    }

    public void creatingInternationalRestrictionsAboutLockdown(long points) throws NotEnoughPointsException{
        if(points < floorOfPoints4thUpgrade)
            throw new NotEnoughPointsException();
        for(Country country : GlobalStates.INSTANCE.getMapOfCountries().values()){
            if(country.getInfestationInCountry() == null)
                continue;
            country.getInfestationInCountry().setPaceOfExpanding(country.getInfestationInCountry().getPaceOfExpanding() - 0.5);
            GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.getPointsForCuredPeopleGlobal() - floorOfPoints4thUpgrade;
        }
    }


    public void ultimateFinalLockdown(long points) throws NotEnoughPointsException{
        if(points < floorOfPoints5thUpgrade)
            throw new NotEnoughPointsException();
        for(Country country : GlobalStates.INSTANCE.getMapOfCountries().values()){
            if(country.getInfestationInCountry() == null || country.getTreatmentInCountry() == null){
                continue;
            }
            settingThreadsForUltimateLockdown(country);
            GlobalStates.INSTANCE.pointsForCuredPeopleGlobal = GlobalStates.INSTANCE.getPointsForCuredPeopleGlobal() - floorOfPoints5thUpgrade;
        }
    }

    private void settingThreadsForUltimateLockdown(Country country){
        country.getTreatmentInCountry().setVariableForUpgrades(country.getTreatmentInCountry().getVariableForUpgrades() * 2);
        country.getInfestationInCountry().setPaceOfExpanding(country.getInfestationInCountry().getPaceOfExpanding() / 2);
    }

}