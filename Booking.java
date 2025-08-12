public class Booking {
    private static int nextId = 1;
    private final int id;
    private final String userEmail;
    private final String carReg;
    private final int days;
    private final double totalAmount;
    private final String paymentMethod;

    public Booking(String userEmail, String carReg, int days, double totalAmount, String paymentMethod) {
        this.id = nextId++;
        this.userEmail = userEmail;
        this.carReg = carReg;
        this.days = days;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
    }

    public int getId() { return id; }
    public String getUserEmail() { return userEmail; }
    public String getCarReg() { return carReg; }
    public int getDays() { return days; }
    public double getTotalAmount() { return totalAmount; }
    public String getPaymentMethod() { return paymentMethod; }

    @Override
    public String toString() {
        return String.format("Booking#%d | User:%s | Car:%s | Days:%d | Amount:%.2f BDT | Payment:%s",
                id, userEmail, carReg, days, totalAmount, paymentMethod);
    }
}
