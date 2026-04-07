import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
    public static final double BASE_FARE = 3.0;
    private List<String> activeRentals;
    private BikeService bikeService;

    public RentalService(BikeService bikeService) {
        activeRentals = new ArrayList<>();
        this.bikeService = bikeService;
    }

    public void startRental(String bikeId, String userEmail) {
        activeRentals.add(bikeId);
        ERyderLog log = new ERyderLog(
            "LOG-" + System.currentTimeMillis(),
            "Trip started: " + bikeId + " User: " + userEmail,
            LocalDateTime.now()
        );
    }

    public void endRental(String bikeId, RegisteredUsers user) {
        activeRentals.remove(bikeId);
        bikeService.releaseBike(bikeId);
        double finalFare = user.calculateFare(BASE_FARE);
        user.displayUserType();
        System.out.println("Base Fare: " + BASE_FARE);
        System.out.println("Final Fare: " + finalFare);
        ERyderLog log = new ERyderLog(
            "LOG-" + System.currentTimeMillis(),
            "Trip ended: " + bikeId + " Fare: " + finalFare,
            LocalDateTime.now()
        );
    }

    public void viewActiveRentals() {
        System.out.println("Active Rentals: " + activeRentals);
    }
}