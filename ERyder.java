import java.util.Scanner;

public class ERyder {
    public static final String COMPANY_NAME = "eRyder";
    public static final double BASE_FARE = 1.0;
    public static final double PER_MINUTE_FARE = 0.5;
    private final String LINKED_ACCOUNT;
    private final String LINKED_PHONE_NUMBER;
    int bikeID;
    int batteryLevel;
    boolean isAvailable;
    double kmDriven;
    private int totalUsageInMinutes;
    private double totalFare;
    public ERyder(int bikeID, int batteryLevel, boolean isAvailable, double kmDriven, String linkedAccount, String linkedPhoneNumber) {
        this.bikeID = bikeID;
        this.setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.LINKED_ACCOUNT = linkedAccount;
        this.LINKED_PHONE_NUMBER = linkedPhoneNumber;
    }

    public void ride() {
        if (batteryLevel > 0 && isAvailable) {
            System.out.println("Bike " + bikeID + " is available");
        } else {
            System.out.println("Bike " + bikeID + " is not available");
        }
    }
    public void printBikeDetails() {
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Battery: " + batteryLevel + "%");
        System.out.println("Available: " + isAvailable);
        System.out.println("Total KM: " + kmDriven);
        System.out.println("-------------------");
    }
    public void printRideDetails(int usageInMinutes) {
        this.totalFare = calculateFare(usageInMinutes);
        this.totalUsageInMinutes = usageInMinutes;
        System.out.println("=== Ride Details ===");
        System.out.println("Linked Account: " + LINKED_ACCOUNT);
        System.out.println("Linked Phone Number: " + LINKED_PHONE_NUMBER);
        System.out.println("Bike ID: " + bikeID);
        System.out.println("Total Usage: " + usageInMinutes);
        System.out.println("Total Fare: " + totalFare);
        System.out.println("====================");
    }
    private double calculateFare(int usageInMinutes) {
        return BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
    }
    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel >= 0 && batteryLevel <= 100) {
            this.batteryLevel = batteryLevel;
        } else {
            this.batteryLevel = 0;
        }
    }
    public int getBikeID() { return bikeID; }
    public int getBatteryLevel() { return batteryLevel; }
    public boolean isAvailable() { return isAvailable; }
    public double getKmDriven() { return kmDriven; }
    public void setBikeID(int bikeID) { this.bikeID = bikeID; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
    public void setKmDriven(double kmDriven) { this.kmDriven = kmDriven; }
}
