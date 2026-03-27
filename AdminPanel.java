import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AdminPanel {
    private ArrayList<RegisteredUsers> registeredUsersList;
    private Scanner scanner;

    public AdminPanel() {
        registeredUsersList = new ArrayList<>();
        scanner = new Scanner(System.in);
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
                    BikeRental br = new BikeRental();
                    br.simulateApplicationInput();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
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
            String emailAddress = scanner.nextLine();

            System.out.print("Date of Birth (YYYY-MM-DD): ");
            String dateOfBirth = scanner.nextLine();

            System.out.print("Card Number: ");
            String cardNumber = scanner.nextLine();

            System.out.print("Card Expiry Date: ");
            String cardExpiryDate = scanner.nextLine();

            System.out.print("Card Provider: ");
            String cardProvider = scanner.nextLine();

            System.out.print("CVV: ");
            String cvv = scanner.nextLine();

            System.out.print("User Type: ");
            String userType = scanner.nextLine();

            String[] lastThreeTrips = new String[3];
            System.out.println("\nEnter last three trips:");

            for (int j = 0; j < 3; j++) {
                System.out.print("Trip " + (j + 1) + " date (YYYY-MM-DD): ");
                String date = scanner.nextLine();

                System.out.print("Source: ");
                String source = scanner.nextLine();

                System.out.print("Destination: ");
                String dest = scanner.nextLine();

                System.out.print("Fare (€): ");
                String fare = scanner.nextLine();

                System.out.print("Feedback: ");
                String feedback = scanner.nextLine();

                StringBuilder sb = new StringBuilder();
                sb.append("Date: ").append(date)
                  .append(", Source: ").append(source)
                  .append(", Destination: ").append(dest)
                  .append(", Fare (€): ").append(fare)
                  .append(", Feedback: ").append(feedback);

                lastThreeTrips[j] = sb.toString();
            }

            RegisteredUsers user = new RegisteredUsers(
                    fullName, emailAddress, dateOfBirth,
                    cardNumber, cardExpiryDate, cardProvider,
                    cvv, userType, lastThreeTrips);

            registeredUsersList.add(user);
            System.out.println("User added successfully.");
        }
    }

    private void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }

        System.out.println("\n--- All Registered Users ---");
        for (RegisteredUsers u : registeredUsersList) {
            System.out.println(u);
        }
    }

    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }

        System.out.print("Enter email to remove: ");
        String email = scanner.nextLine();
        boolean found = false;

        Iterator<RegisteredUsers> it = registeredUsersList.iterator();
        while (it.hasNext()) {
            RegisteredUsers u = it.next();
            if (u.getEmailAddress().equals(email)) {
                it.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("User removed successfully.");
        } else {
            System.out.println("No user found with this email address");
        }
    }

    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }

        System.out.print("Enter email to update: ");
        String email = scanner.nextLine();
        RegisteredUsers target = null;

        for (RegisteredUsers u : registeredUsersList) {
            if (u.getEmailAddress().equals(email)) {
                target = u;
                break;
            }
        }

        if (target == null) {
            System.out.println("No user found with this email address");
            return;
        }

        System.out.println("\nUpdate user info (Press ENTER for no change, 0 for no change on numbers)");

        System.out.print("New full name: ");
        String fn = scanner.nextLine();
        if (!fn.isEmpty()) target.setFullName(fn);

        System.out.print("New date of birth: ");
        String dob = scanner.nextLine();
        if (!dob.isEmpty()) target.setDateOfBirth(dob);

        System.out.print("New card number: ");
        String cn = scanner.nextLine();
        if (!cn.isEmpty() && !cn.equals("0")) target.setCardNumber(cn);

        System.out.print("New card expiry: ");
        String exp = scanner.nextLine();
        if (!exp.isEmpty()) target.setCardExpiryDate(exp);

        System.out.print("New card provider: ");
        String cp = scanner.nextLine();
        if (!cp.isEmpty()) target.setCardProvider(cp);

        System.out.print("New CVV: ");
        String cvv = scanner.nextLine();
        if (!cvv.isEmpty() && !cvv.equals("0")) target.setCvv(cvv);

        System.out.print("New user type: ");
        String ut = scanner.nextLine();
        if (!ut.isEmpty()) target.setUserType(ut);

        System.out.println("User updated successfully.");
    }
}