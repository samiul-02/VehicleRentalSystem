import java.util.Scanner;

public class Payment {

    public static String processPaymentInteractive(double requiredAmount) throws InsufficientPaymentException, InvalidPaymentException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select payment method:");
        System.out.println("1. bKash");
        System.out.println("2. Nagad");
        System.out.println("3. DBBL");
        System.out.println("4. Card");
        System.out.print("Choice: ");
        int choice = readInt(sc);
        sc.nextLine();

        String method;
        switch (choice) {
            case 1 -> method = "bKash";
            case 2 -> method = "Nagad";
            case 3 -> method = "DBBL";
            case 4 -> method = "Card";
            default -> throw new InvalidPaymentException("Invalid payment method selected.");
        }

        System.out.print("Enter account/card number: ");
        String acct = sc.nextLine().trim();
        if (acct.isEmpty()) throw new InvalidPaymentException("Account/card number cannot be empty.");

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine().trim();
        if (pin.isEmpty()) throw new InvalidPaymentException("PIN cannot be empty.");

        System.out.printf("Total due: %.2f BDT. Enter payment amount: ", requiredAmount);
        double paid = readDouble(sc);
        sc.nextLine();

        if (paid < requiredAmount) throw new InsufficientPaymentException("Paid amount " + paid + " is less than required " + requiredAmount);

        System.out.printf("Payment of %.2f BDT accepted via %s.\n", paid, method);
        return method;
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) { sc.next(); System.out.print("Please enter a number: "); }
        return sc.nextInt();
    }

    private static double readDouble(Scanner sc) {
        while (!sc.hasNextDouble()) { sc.next(); System.out.print("Please enter a number: "); }
        return sc.nextDouble();
    }
}
