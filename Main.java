import java.util.Scanner;

 class CarRentalSystem {
    private static final RentalService rentalService = new RentalService();

    public static void main(String[] args) {
        preloadAdminsAndCars();

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== Car Rental System ===");
            System.out.println("1. Register (Customer)");
            System.out.println("2. Login (Customer)");
            System.out.println("3. Login (Admin)");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            choice = readInt(sc);
            sc.nextLine();

            switch (choice) {
                case 1 -> registerCustomer(sc);
                case 2 -> loginCustomer(sc);
                case 3 -> loginAdmin(sc);
                case 4 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 4);

        sc.close();
    }

    private static void preloadAdminsAndCars() {
        rentalService.registerUser(new Admin("Samiul", "samiul@admin.com", "adminpass"));
        rentalService.registerUser(new Admin("Shuvo",  "shuvo@admin.com",  "adminpass"));
        rentalService.registerUser(new Admin("Raisa",  "raisa@admin.com",  "adminpass"));
        rentalService.registerUser(new Admin("Shadeed", "shadeed@admin.com", "adminpass"));

        rentalService.registerUser(new Customer("Default Customer", "cust@demo.com", "custpass"));

        rentalService.addCar(new Car("BMW", "5 Series", 2023, "BMW123", 8000));
        rentalService.addCar(new Car("Tesla", "Model S Plaid", 2024, "TSLA001", 12000));
        rentalService.addCar(new Car("Audi", "A6 Prestige", 2022, "AUDI789", 7500));
    }

    private static void registerCustomer(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();
        System.out.print("Enter password: ");
        String pwd = sc.nextLine().trim();

        if (rentalService.getUserByEmail(email) != null) {
            System.out.println("Email already registered. Try logging in.");
            return;
        }

        rentalService.registerUser(new Customer(name, email, pwd));
        System.out.println("Registration successful — please login.");
    }

    private static void loginCustomer(Scanner sc) {
        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();
        System.out.print("Enter password: ");
        String pwd = sc.nextLine().trim();
        try {
            User u = rentalService.authenticateUser(email, pwd);
            if (u instanceof Customer) {
                u.displayMenu(rentalService);
            } else {
                System.out.println("This account is not a customer account.");
            }
        } catch (AuthenticationException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    private static void loginAdmin(Scanner sc) {
        System.out.print("Enter admin email: ");
        String email = sc.nextLine().trim();
        System.out.print("Enter password: ");
        String pwd = sc.nextLine().trim();
        try {
            User u = rentalService.authenticateUser(email, pwd);
            if (u instanceof Admin) {
                u.displayMenu(rentalService);
            } else {
                System.out.println("This account is not an admin account.");
            }
        } catch (AuthenticationException e) {
            System.out.println("Admin login failed: " + e.getMessage());
        }
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Please enter a number: ");
        }
        return sc.nextInt();
    }
}
