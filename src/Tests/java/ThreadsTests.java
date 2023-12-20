


import Objects.Country;
import Threads.Clock;
import Threads.WorldwideTransporting;
import Utilites.GlobalStates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

public class ThreadsTests {

    @Test
    public void testCheckClock() throws InterruptedException {
        Clock.INSTANCE.startClock(LocalDate.now());
        Thread.sleep(GlobalStates.INSTANCE.getLengthOfDay());
        Assertions.assertEquals(LocalDate.now().plusDays(1), Clock.INSTANCE.getGlobalTime());
    }

    @Test
    public void testWorldWideTransporting(){
        GlobalStates.INSTANCE.getMapOfCountries().get("Poland").setInfectedPopulation(30000);
        int capacity = 30000;
        Assertions.assertEquals((int)(capacity * 0.00005), WorldwideTransporting.INSTANCE.quantityOfInfectedPeople(GlobalStates.INSTANCE.getMapOfCountries().get("Poland"), capacity ));
    }

}
