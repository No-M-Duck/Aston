package org.example.input;

public class Bus {

    private final String number;
    private final String model;
    private final int mileage;

    Bus(BusBuilder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public static class BusBuilder {
        private String number;
        private String model;
        private int mileage;

        public BusBuilder number(String number) {
            this.number = number;
            return this;
        }

        public BusBuilder model(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder mileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {

            return new Bus(this);
        }
        @Override
        public String toString() {
            return "Bus{" + "number='" + number + '\'' + ", model=" + model + ", mileage=" + mileage + '}';
        }
    }

}

