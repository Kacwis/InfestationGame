package Transports;

public class Cargo extends Transport {

    final static int capacityPerCargoVechicle = 2 ;

    public Cargo(String name, int numberOfVechicles) {
        super(name, numberOfVechicles);
        capacityPerDay = capacityPerCargoVechicle * quantityOfVechicles;
    }

    public static int getCapacityPerCargoVechicle() {
        return capacityPerCargoVechicle;
    }
}
