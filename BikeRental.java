import java.util.Scanner;

public class BikeRental {
    private Scanner scanner;
    private BikeService bikeService;
    private RentalService rentalService;
    private UserRegistration userReg;

    public BikeRental() {
        scanner = new Scanner(System.in);
        bikeService = new BikeService();
        rentalService = new RentalService();
        userReg = new UserRegistration();
    }

    public void simulateApplicationInput() {
        System.out.println("This is the simulation of the e-bike rental process.");

        System.out.print("Are you a registered user? (true/false): ");
        boolean isRegistered = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter your location: ");
        String location = scanner.nextLine();

        System.out.println("Simulating the analysis of the rental request.");
        String bikeID = bikeService.validateLocation(location);
        if (bikeID == null) return;

        if (isRegistered) {
            System.out.println("Welcome back, " + email + "!");
        } else {
            System.out.println("You’re not our registered user. Please consider registering.");
        }

        System.out.println("Simulating e-bike reservation…");
        bikeService.reserveBike(bikeID);
        rentalService.startRental(bikeID, email);
        System.out.println("Reserving the bike with the " + bikeID + ". Please following the on-screen instructions to locate the bike and start your pleasant journey.");

        System.out.println("Displaying the active rentals…");
        rentalService.viewActiveRentals();

        System.out.println("Simulating the end of the trip…");
        rentalService.endRental(bikeID);
        bikeService.releaseBike(bikeID);
        System.out.println("Your trip has ended. Thank you for riding with us.");

        System.out.println("Displaying the active rentals after trip end…");
        rentalService.viewActiveRentals();
    }
}