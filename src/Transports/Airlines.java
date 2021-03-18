package Transports;

public class Airlines extends Transport {

    final static int capacityPerPlane = 200;

    public Airlines(String name, int numberOfVechicles) {
        super(name, numberOfVechicles);
        capacityPerDay = capacityPerPlane * quantityOfVechicles;
    }

    public static int getCapacityPerPlane() {
        return capacityPerPlane;
    }
}
