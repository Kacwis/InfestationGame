package Transports;

public class Coaches extends Transport {

    final static int capacityPerCoach = 10;

    public Coaches(String name, int numberOfVechicles) {
            super(name, numberOfVechicles);
            capacityPerDay = capacityPerCoach * quantityOfVechicles;
    }

    public static int getCapacityPerCoach() {
        return capacityPerCoach;
    }
}
