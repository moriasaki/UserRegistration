import java.time.LocalDateTime;

public class BikeRequest {
    private String userEmail;
    private String location;
    private LocalDateTime requestTime;

    public BikeRequest(String userEmail, String location) {
        this.userEmail = userEmail;
        this.location = location;
        this.requestTime = LocalDateTime.now();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public String toString() {
        return userEmail + " - " + location + " - " + requestTime;
    }
}