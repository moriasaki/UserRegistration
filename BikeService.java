import java.time.LocalDateTime;

public class BikeService {
    public String validateLocation(String location) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getLocation().equalsIgnoreCase(location) && bike.isAvailable()) {
                System.out.println("A bike is available at the location you requested.");
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");
        return null;
    }
    public boolean reserveBike(String bikeID) {
        if (bikeID == null) return false;
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(false);
                bike.setLastUsedTime(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }
    public void releaseBike(String bikeID) {
        for (Bike bike : BikeDatabase.bikes) {
            if (bike.getBikeID().equals(bikeID)) {
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                break;
            }
        }
    }
}