public class Car implements Rentable {
    private final String brand;
    private final String model;
    private final int year;
    private final String regNumber;
    private final double pricePerDay;
    private boolean available;

    public Car(String brand, String model, int year, String regNumber, double pricePerDay) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.regNumber = regNumber;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getRegNumber() { return regNumber; }

    @Override
    public double getPricePerDay() { return pricePerDay; }

    @Override
    public boolean isAvailable() { return available; }

    @Override
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String getIdentifier() { return regNumber; }

    @Override
    public String getShortInfo() {
        return String.format("%s %s (%d) - Reg: %s - Price/day: %.2f BDT - %s",
                brand, model, year, regNumber, pricePerDay, (available ? "Available" : "Rented"));
    }

    @Override
    public String toString() {
        return getShortInfo();
    }
}
