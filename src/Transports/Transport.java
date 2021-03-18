package Transports;

public class Transport {

        String name;
        int quantityOfVechicles;
        int capacityPerDay;

        public Transport(String name, long quantityOfVechicles) {
            this.name = name;
            this.quantityOfVechicles = (int) (quantityOfVechicles);
        }

        public int getCapacityPerDay(){
            return  capacityPerDay;
        }

    @Override
    public String toString() {
        return  name + " " + capacityPerDay;

    }
}

