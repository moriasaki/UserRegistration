import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AdminPanel {
    private ArrayList<RegisteredUsers> userList;
    private Scanner scanner;

    public AdminPanel() {
        userList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void userManagementOptions() {
        while (true) {
            System.out.println("\n===== ERyder User Management System =====");
            System.out.println("1. Add New User");
            System.out.println("2. View All Registered Users");
            System.out.println("3. Delete User (by Email)");
            System.out.println("4. Update User Information (by Email)");
            System.out.println("5. Exit System");
            System.out.print("Please enter your choice: ");

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
                    System.out.println("System exited successfully!");
                    return;
                default:
                    System.out.println("Invalid input, please try again!");
            }
        }
    }

    private void addNewUsers() {
        System.out.print("Enter number of users to add: ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= count; i++) {
            System.out.println("\n----- User " + i + " Information -----");

            System.out.print("Full Name: ");
            String name = scanner.nextLine();

            System.out.print("Email Address: ");
            String email = scanner.nextLine();

            System.out.print("Date of Birth (yyyy-mm-dd): ");
            String birth = scanner.nextLine();

            System.out.print("Card Number: ");
            String card = scanner.nextLine();

            System.out.print("Card Expiry Date (mm/yy): ");
            String expiry = scanner.nextLine();

            System.out.print("Card Provider (Visa/Master): ");
            String provider = scanner.nextLine();

            System.out.print("CVV: ");
            String cvv = scanner.nextLine();

            System.out.print("User Type (user/admin): ");
            String type = scanner.nextLine();

            String[] trips = new String[3];
            System.out.println("Enter last 3 trips (press Enter directly if none):");
            for (int j = 0; j < 3; j++) {
                System.out.print("Trip " + (j + 1) + ": ");
                trips[j] = scanner.nextLine();
            }

            RegisteredUsers user = new RegisteredUsers(name, email, birth, card, expiry,
                    provider, cvv, type, trips);
            userList.add(user);
            System.out.println("User added successfully!");
        }
    }

    private void viewRegisteredUsers() {
        if (userList.isEmpty()) {
            System.out.println("No registered users!");
            return;
        }

        System.out.println("\n===== All Registered Users =====");
        for (RegisteredUsers user : userList) {
            System.out.println(user);
        }
    }

    private void removeRegisteredUsers() {
        if (userList.isEmpty()) {
            System.out.println("No users to delete!");
            return;
        }

        System.out.print("Enter email of user to delete: ");
        String email = scanner.nextLine();
        boolean isRemoved = false;

        Iterator<RegisteredUsers> iterator = userList.iterator();
        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equals(email)) {
                iterator.remove();
                isRemoved = true;
                break;
            }
        }

        if (isRemoved) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User with this email not found!");
        }
    }

    private void updateRegisteredUsers() {
        if (userList.isEmpty()) {
            System.out.println("No users to update!");
            return;
        }

        System.out.print("Enter email of user to update: ");
        String email = scanner.nextLine();
        RegisteredUsers targetUser = null;

        for (RegisteredUsers user : userList) {
            if (user.getEmailAddress().equals(email)) {
                targetUser = user;
                break;
            }
        }

        if (targetUser == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("\n----- Update User Info (Empty input / 0 to keep original value) -----");
        
        System.out.print("New Name (" + targetUser.getFullName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty() && !name.equals("0")) {
            targetUser.setFullName(name);
        }

        System.out.print("New DOB (" + targetUser.getDateOfBirth() + "): ");
        String birth = scanner.nextLine();
        if (!birth.isEmpty() && !birth.equals("0")) {
            targetUser.setDateOfBirth(birth);
        }

        System.out.print("New Card Number (" + targetUser.getCardNumber() + "): ");
        String card = scanner.nextLine();
        if (!card.isEmpty() && !card.equals("0")) {
            targetUser.setCardNumber(card);
        }

        System.out.print("New Card Provider (" + targetUser.getCardProvider() + "): ");
        String provider = scanner.nextLine();
        if (!provider.isEmpty() && !provider.equals("0")) {
            targetUser.setCardProvider(provider);
        }

        System.out.print("New User Type (" + targetUser.getUserType() + "): ");
        String type = scanner.nextLine();
        if (!type.isEmpty() && !type.equals("0")) {
            targetUser.setUserType(type);
        }

        System.out.println("User information updated successfully!");
    }
}