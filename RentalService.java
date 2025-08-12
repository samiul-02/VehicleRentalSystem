import java.util.ArrayList;
import java.util.List;

public class RentalService {
    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Car> cars = new ArrayList<>();
    private final ArrayList<Booking> bookings = new ArrayList<>();

    public void registerUser(User user) {
        users.add(user);
    }

    public User authenticateUser(String email, String password) throws AuthenticationException {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
                return u;
            }
        }
        throw new AuthenticationException("Invalid email or password.");
    }

    public User getUserByEmail(String email) {
        for (User u : users) if (u.getEmail().equalsIgnoreCase(email)) return u;
        return null;
    }

    public void addCar(Car car) { cars.add(car); }

    public boolean removeCar(String regNumber) {
        return cars.removeIf(c -> c.getRegNumber().equalsIgnoreCase(regNumber));
    }

    public void listAllCars() {
        if (cars.isEmpty()) { System.out.println("No cars in fleet."); return; }
        System.out.println("\n--- All cars ---");
        cars.forEach(c -> System.out.println(c.getShortInfo()));
    }

    public void listAvailableCars() {
        System.out.println("\n--- Available cars ---");
        boolean any = false;
        for (Car c : cars) {
            if (c.isAvailable()) { System.out.println(c.getShortInfo()); any = true; }
        }
        if (!any) System.out.println("No available cars at the moment.");
    }

    public void bookCar(User user, String regNumber, int days)
            throws CarNotFoundException, InvalidPaymentException, InsufficientPaymentException {
        Car target = null;
        for (Car c : cars) {
            if (c.getRegNumber().equalsIgnoreCase(regNumber)) {
                target = c;
                break;
            }
        }
        if (target == null) throw new CarNotFoundException("Car with reg " + regNumber + " not found.");
        if (!target.isAvailable()) throw new CarNotFoundException("Car " + regNumber + " is currently not available.");

        double required = days * target.getPricePerDay();
        System.out.printf("Booking: %s for %d days. Total: %.2f BDT\n", target.getShortInfo(), days, required);

        String paymentMethod = Payment.processPaymentInteractive(required);

        target.setAvailable(false);
        Booking b = new Booking(user.getEmail(), target.getRegNumber(), days, required, paymentMethod);
        bookings.add(b);
        System.out.println("Booking successful: " + b);
    }

    public List<Booking> getBookingsForUser(String userEmail) {
        List<Booking> out = new ArrayList<>();
        for (Booking b : bookings) if (b.getUserEmail().equalsIgnoreCase(userEmail)) out.add(b);
        return out;
    }

    public List<Booking> getAllBookings() { return new ArrayList<>(bookings); }
}
