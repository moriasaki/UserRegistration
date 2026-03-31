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
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": addNewUsers(); break;
                case "2": viewRegisteredUsers(); break;
                case "3": removeRegisteredUsers(); break;
                case "4": updateRegisteredUsers(); break;
                case "5": System.out.println("Exiting..."); return;
                case "6": bikeRental.simulateApplicationInput(); break;
                default: System.out.println("Invalid choice. Please try again");
            }
        }
    }

    private void addNewUsers() {
        System.out.print("Enter number of users to add: ");
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.println("\nEnter user details:");
            System.out.print("Full Name: ");
            String fullName = scanner.nextLine();
            System.out.print("Email Address: ");
            String email = scanner.nextLine();
            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();
            System.out.print("Card Number: ");
            String card = scanner.nextLine();
            System.out.print("Card Expiry Date: ");
            String exp = scanner.nextLine();
            System.out.print("Card Provider: ");
            String provider = scanner.nextLine();
            System.out.print("CVV: ");
            String cvv = scanner.nextLine();
            System.out.print("User Type: ");
            String type = scanner.nextLine();

            String[] trips = new String[3];
            System.out.println("\nEnter last three trips:");
            for (int j = 0; j < 3; j++) {
                System.out.print("Trip " + (j+1) + " date: "); String d = scanner.nextLine();
                System.out.print("Source: "); String s = scanner.nextLine();
                System.out.print("Destination: "); String de = scanner.nextLine();
                System.out.print("Fare (€): "); String f = scanner.nextLine();
                System.out.print("Feedback: "); String fb = scanner.nextLine();
                trips[j] = "Date: "+d+", Source: "+s+", Destination: "+de+", Fare: "+f+", Feedback: "+fb;
            }
            RegisteredUsers user = new RegisteredUsers(fullName, email, dob, card, exp, provider, cvv, type, trips);
            userService.addUser(user);
            System.out.println("User added successfully.");
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
        System.out.print("New full name: "); String fn = scanner.nextLine(); if (!fn.isEmpty()) target.setFullName(fn);
        System.out.print("New DOB: "); String dob = scanner.nextLine(); if (!dob.isEmpty()) target.setDateOfBirth(dob);
        System.out.print("New card: "); String cn = scanner.nextLine(); if (!cn.isEmpty()) target.setCardNumber(cn);
        System.out.print("New expiry: "); String exp = scanner.nextLine(); if (!exp.isEmpty()) target.setCardExpiryDate(exp);
        System.out.print("New provider: "); String cp = scanner.nextLine(); if (!cp.isEmpty()) target.setCardProvider(cp);
        System.out.print("New CVV: "); String cvv = scanner.nextLine(); if (!cvv.isEmpty()) target.setCvv(cvv);
        System.out.print("New type: "); String ut = scanner.nextLine(); if (!ut.isEmpty()) target.setUserType(ut);
        System.out.println("User updated successfully.");
    }
}