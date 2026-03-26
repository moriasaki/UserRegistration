public class RegisteredUsers extends UserRegistration {
    private String[] lastThreeTrips;

    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth,
                           String cardNumber, String cardExpiryDate, String cardProvider,
                           String cvv, String userType, String[] lastThreeTrips) {
        super(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate,
                cardProvider, cvv, userType);
        this.lastThreeTrips = lastThreeTrips;
    }

    public String[] getLastThreeTrips() {
        return lastThreeTrips;
    }

    public void setLastThreeTrips(String[] lastThreeTrips) {
        this.lastThreeTrips = lastThreeTrips;
    }

    @Override
    public String toString() {
        StringBuilder trips = new StringBuilder();
        if (lastThreeTrips != null) {
            for (String trip : lastThreeTrips) {
                trips.append(trip).append(" | ");
            }
        } else {
            trips.append("No trips");
        }

        return "===== User Info =====\n" +
                "Full Name: " + getFullName() + "\n" +
                "Email: " + getEmailAddress() + "\n" +
                "Date of Birth: " + getDateOfBirth() + "\n" +
                "Card Number: " + getCardNumber() + "\n" +
                "Card Provider: " + getCardProvider() + "\n" +
                "User Type: " + getUserType() + "\n" +
                "Last 3 Trips: " + trips + "\n";
    }
}