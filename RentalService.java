import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
public class RentalService {
    private LinkedList<ActiveRental> activeRentalsList;

    public RentalService() {
        activeRentalsList = new LinkedList<>();
    }

    public void startRental(String bikeID, String email) {
        LocalDateTime startTime = LocalDateTime.now();
        ActiveRental rental = new ActiveRental(bikeID, email, startTime);
        activeRentalsList.add(rental);
    }

    public void endRental(String bikeID) {
        Iterator<ActiveRental> iterator = activeRentalsList.iterator();
        while (iterator.hasNext()) {
            ActiveRental ar = iterator.next();
            if (ar.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
        }
    }
    public void viewActiveRentals() {
        if (activeRentalsList.isEmpty()) {
            System.out.println("No active rentals at the moment.");
            return;
        }
        for (ActiveRental ar : activeRentalsList) {
            System.out.println(ar);
        }
    }
}