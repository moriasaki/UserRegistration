import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class BikeRental {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;
    private UserRegistration userReg;
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalsList;
    private Scanner scanner;

    public BikeRental() {
        activeRentalsList = new LinkedList<>();
        scanner = new Scanner(System.in);
        userReg = new UserRegistration();
    }

    public void simulateApplicationInput() {
        System.out.println("This is the simulation of the e-bike rental process.");

        System.out.print("Are you a registered user? (true/false): ");
        isRegisteredUser = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter your email address: ");
        emailAddress = scanner.nextLine();

        System.out.print("Enter your location: ");
        location = scanner.nextLine();

        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);

        if (!locationValid) {
            return;
        }

        System.out.println("Simulating e-bike reservation…");
        reserveBike(bikeID);

        System.out.println("Displaying the active rentals…");
        viewActiveRentals();

        System.out.println("Simulating the end of the trip…");
        removeTrip(bikeID);

        System.out.println("Displaying the active rentals after trip end…");
        viewActiveRentals();
    }

    private String analyseRequest(boolean isRegistered, String email, String loc) {
        if (isRegistered) {
            System.out.println("Welcome back, " + email + "!");
        } else {
            System.out.println("You’re not our registered user. Please consider registering.");
            userReg= new UserRegistration();
        }
        return validateLocation(loc);
    }

    private String validateLocation(String location) {
        locationValid = false;
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equalsIgnoreCase(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }

    private void reserveBike(String bikeID) {
        if (bikeID == null) {
            System.out.println("Sorry, we’re unable to reserve a bike at this time. Please try again later.");
            return;
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                tripStartTime = LocalDateTime.now();
                bike.setAvailable(false);
                bike.setLastUsedTime(tripStartTime);

                System.out.println("Reserving the bike with the " + bikeID + ". Please following the on-screen instructions to locate the bike and start your pleasant journey.");

                activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                activeRentalsList.add(activeRental);
                break;
            }
        }
    }

    private void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
            return;
        }
        for (ActiveRental ar : activeRentalsList) {
            System.out.println(ar);
        }
    }

    private void removeTrip(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental ar = iterator.next();
            if (ar.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
        }

        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                System.out.println("Your trip has ended. Thank you for riding with us.");
                break;
            }
        }
    }
}