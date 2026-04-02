import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalService {
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

    public void endRental(String bikeId) {
        activeRentals.remove(bikeId);
        bikeService.releaseBike(bikeId);

        ERyderLog log = new ERyderLog(
            "LOG-" + System.currentTimeMillis(),
            "Trip ended: " + bikeId,
            LocalDateTime.now()
        );
    }

    public void viewActiveRentals() {
        System.out.println("Active Rentals: " + activeRentals);
    }
}