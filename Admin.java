import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public void displayMenu(RentalService rentalService) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Admin Menu (" + getName() + ") ---");
            System.out.println("1. View all cars");
            System.out.println("2. Add a car");
            System.out.println("3. Remove a car");
            System.out.println("4. View all bookings");
            System.out.println("5. Logout");
            System.out.print("Choice: ");
            choice = readInt(sc);
            sc.nextLine();

            switch (choice) {
                case 1 -> rentalService.listAllCars();
                case 2 -> {
                    System.out.print("Brand: ");
                    String brand = sc.nextLine();
                    System.out.print("Model: ");
                    String model = sc.nextLine();
                    System.out.print("Year: ");
                    int year = readInt(sc); sc.nextLine();
                    System.out.print("Reg number: ");
                    String reg = sc.nextLine();
                    System.out.print("Price per day: ");
                    double price = readDouble(sc); sc.nextLine();

                    rentalService.addCar(new Car(brand, model, year, reg, price));
                    System.out.println("Car added.");
                }
                case 3 -> {
                    System.out.print("Enter reg number to remove: ");
                    String reg = sc.nextLine();
                    boolean removed = rentalService.removeCar(reg);
                    System.out.println(removed ? "Car removed." : "Car not found.");
                }
                case 4 -> {
                    List<Booking> all = rentalService.getAllBookings();
                    if (all.isEmpty()) System.out.println("No bookings yet.");
                    else all.forEach(System.out::println);
                }
                case 5 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    private int readInt(Scanner sc) {
        while (!sc.hasNextInt()) { sc.next(); System.out.print("Please enter a number: "); }
        return sc.nextInt();
    }

    private double readDouble(Scanner sc) {
        while (!sc.hasNextDouble()) { sc.next(); System.out.print("Please enter a number: "); }
        return sc.nextDouble();
    }
}
