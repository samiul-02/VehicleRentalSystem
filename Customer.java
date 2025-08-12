import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    public Customer(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public void displayMenu(RentalService rentalService) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Customer Menu (" + getName() + ") ---");
            System.out.println("1. View available cars");
            System.out.println("2. Book a car");
            System.out.println("3. View my bookings");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            choice = readInt(sc);
            sc.nextLine();

            switch (choice) {
                case 1 -> rentalService.listAvailableCars();
                case 2 -> {
                    rentalService.listAvailableCars();
                    System.out.print("Enter car reg number to book: ");
                    String reg = sc.nextLine().trim();
                    System.out.print("Enter rental duration in days: ");
                    int days = readInt(sc);
                    sc.nextLine();

                    try {
                        rentalService.bookCar(this, reg, days);
                    } catch (Exception e) {
                        System.out.println("Booking failed: " + e.getMessage());
                    }
                }
                case 3 -> {
                    List<Booking> myBookings = rentalService.getBookingsForUser(this.getEmail());
                    if (myBookings.isEmpty()) System.out.println("No bookings found.");
                    else myBookings.forEach(b -> System.out.println(b));
                }
                case 4 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Please enter a number: ");
        }
        return sc.nextInt();
    }
}
