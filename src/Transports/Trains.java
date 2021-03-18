package Transports;

public class Trains extends Transport {

    final static int capacityPerTrain = 50;

    public Trains(String name, int numberOfVechicles) {
        super(name, numberOfVechicles);
        capacityPerDay = quantityOfVechicles * capacityPerTrain;
    }

    public static int getCapacityPerTrain() {
        return capacityPerTrain;
    }
}
