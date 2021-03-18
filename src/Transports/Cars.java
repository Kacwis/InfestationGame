package Transports;

public class Cars extends Transport {

    final static int capacityPerCar = 5;

    public Cars(String name, int numberOfVechicles) {
        super(name, numberOfVechicles);
        capacityPerDay = capacityPerCar * quantityOfVechicles;
    }

    public static int getCapacityPerCar() {
        return capacityPerCar;
    }
}
