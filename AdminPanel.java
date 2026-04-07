import java.util.Scanner;

public class AdminPanel {
    private Scanner scanner;
    private UserService userService;
    private BikeRental bikeRental;

    public AdminPanel() {
        scanner = new Scanner(System.in);
        userService = new UserService();
        bikeRental = new BikeRental();
    }

    public void userManagementOptions() {
        while (true) {
            System.out.println("\nWelcome to E-Ryder Administrator Panel.");
            System.out.println("What do you want to do?");
            System.out.println("1. Add New Users");
            System.out.println("2. View Registered Users");
            System.out.println("3. Remove Registered Users");
            System.out.println("4. Update Registered Users");
            System.out.println("5. EXIT");
            System.out.println("6. Demo the Bike Rental System");
            System.out.println("7. View All Bikes Status");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addNewUsers();
                    break;
                case "2":
                    viewRegisteredUsers();
                    break;
                case "3":
                    removeRegisteredUsers();
                    break;
                case "4":
                    updateRegisteredUsers();
                    break;
                case "5":
                    System.out.println("Exiting...");
                    return;
                case "6":
                    bikeRental.simulateApplicationInput();
                    break;
                case "7":
                    viewAllBikesStatus();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void addNewUsers() {
        System.out.print("Enter number of users to add: ");
        int count;
        try {
            count = Integer.parseInt(scanner.nextLine());
            if (count <= 0) {
                System.out.println("Number of users must be positive!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("\nEnter user details:");
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine().trim();
            if (fullName.isEmpty()) {
                System.out.println("Full name cannot be empty!");
                i--;
                continue;
            }
            System.out.print("Email Address: ");
            String email = scanner.nextLine().trim();
            if (email.isEmpty() || !email.contains("@")) {
                System.out.println("Invalid email format! (must contain @)");
                i--;
                continue;
            }
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine().trim();
            if (dob.isEmpty() || !dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
                System.out.println("Invalid DOB format (must be YYYY-MM-DD)!");
                i--;
                continue;
            }
            System.out.print("Card Number: ");
            String card = scanner.nextLine().trim();
            if (card.isEmpty()) {
                System.out.println("Card number cannot be empty!");
                i--;
                continue;
            }
            System.out.print("Card Expiry Date: ");
            String exp = scanner.nextLine().trim();
            System.out.print("Card Provider: ");
            String provider = scanner.nextLine().trim();
            System.out.print("CVV: ");
            String cvv = scanner.nextLine().trim();
            if (cvv.isEmpty() || !cvv.matches("\\d{3,4}")) {
                System.out.println("Invalid CVV (must be 3/4 digits)!");
                i--;
                continue;
            }
            System.out.print("User Type: ");
            String type = scanner.nextLine().trim();
            String[] trips = new String[3];
            System.out.println("\nEnter last three trips:");
            for (int j = 0; j < 3; j++) {
                System.out.print("Trip " + (j+1) + " date: ");
                String d = scanner.nextLine();
                System.out.print("Source: ");
                String s = scanner.nextLine();
                System.out.print("Destination: ");
                String de = scanner.nextLine();
                System.out.print("Fare (€): ");
                String f = scanner.nextLine();
                System.out.print("Feedback: ");
                String fb = scanner.nextLine();
                trips[j] = "Date: "+d+", Source: "+s+", Destination: "+de+", Fare: "+f+", Feedback: "+fb;
            }
            RegisteredUsers createdUser = userService.addNewUser(fullName, email, dob, card, exp, provider, cvv, type, trips);
            System.out.println("User added successfully.");
            bikeRental.simulateApplicationInput(createdUser);
        }
    }

    private void viewRegisteredUsers() {
        if (userService.getAllUsers().isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }
        System.out.println("\n--- All Registered Users ---");
        for (RegisteredUsers u : userService.getAllUsers()) {
            System.out.println(u);
        }
    }

    private void removeRegisteredUsers() {
        if (userService.getAllUsers().isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }
        System.out.print("Enter email to remove: ");
        String email = scanner.nextLine();
        boolean ok = userService.removeUser(email);
        if (ok) {
            System.out.println("User removed successfully.");
        } else {
            System.out.println("No user found with this email address");
        }
    }

    private void updateRegisteredUsers() {
        if (userService.getAllUsers().isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }
        System.out.print("Enter email to update: ");
        String email = scanner.nextLine();
        RegisteredUsers target = userService.findUserByEmail(email);
        if (target == null) {
            System.out.println("No user found with this email address");
            return;
        }
        System.out.println("\nUpdate user info (Press ENTER for no change)");
        System.out.print("New full name: ");
        String fn = scanner.nextLine();
        if (!fn.isEmpty())
            target.setFullName(fn);
        System.out.print("New DOB: ");
        String dob = scanner.nextLine();
        if (!dob.isEmpty())
            target.setDateOfBirth(dob);
        System.out.print("New card: ");
        String cn = scanner.nextLine();
        if (!cn.isEmpty())
            target.setCardNumber(cn);
        System.out.print("New expiry: ");
        String exp = scanner.nextLine();
        if (!exp.isEmpty())
            target.setCardExpiryDate(exp);
        System.out.print("New provider: ");
        String cp = scanner.nextLine();
        if (!cp.isEmpty())
            target.setCardProvider(cp);
        System.out.print("New CVV: ");
        String cvv = scanner.nextLine();
        if (!cvv.isEmpty())
            target.setCvv(cvv);
        System.out.print("New type: ");
        String ut = scanner.nextLine();
        if (!ut.isEmpty())
            target.setUserType(ut);
        System.out.println("\nUpdate last three trips? (y/n): ");
        String updateTrips = scanner.nextLine();
        if (updateTrips.equalsIgnoreCase("y")) {
            String[] newTrips = new String[3];
            System.out.println("Enter new last three trips:");
            for (int j = 0; j < 3; j++) {
                System.out.print("Trip " + (j+1) + " date: ");
                String d = scanner.nextLine();
                System.out.print("Source: ");
                String s = scanner.nextLine();
                System.out.print("Destination: ");
                String de = scanner.nextLine();
                System.out.print("Fare (€): ");
                String f = scanner.nextLine();
                System.out.print("Feedback: ");
                String fb = scanner.nextLine();
                newTrips[j] = "Date: "+d+", Source: "+s+", Destination: "+de+", Fare: "+f+", Feedback: "+fb;
            }
            target.setLastThreeTrips(newTrips);
        }
        System.out.println("User updated successfully.");
    }

    private void viewAllBikesStatus() {
        System.out.println("\n--- All Bikes Status ---");
        if (BikeDatabase.bikes.isEmpty()) {
            System.out.println("No bikes in database!");
            return;
        }
        for (Bike bike : BikeDatabase.bikes) {
            System.out.println(bike);
        }
    }
}