public abstract class User {
    private final String name;
    private final String email;
    private final String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email.toLowerCase();
        this.password = password;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    protected String getPassword() { return password; }

    public abstract void displayMenu(RentalService rentalService);
}
