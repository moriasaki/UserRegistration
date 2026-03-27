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
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== Registered User =====\n");
        sb.append("Full Name: ").append(getFullName()).append("\n");
        sb.append("Email: ").append(getEmailAddress()).append("\n");
        sb.append("DOB: ").append(getDateOfBirth()).append("\n");
        sb.append("Card: ").append(getCardNumber()).append("\n");
        sb.append("Expiry: ").append(getCardExpiryDate()).append("\n");
        sb.append("Provider: ").append(getCardProvider()).append("\n");
        sb.append("CVV: ").append(getCvv()).append("\n");
        sb.append("Type: ").append(getUserType()).append("\n");
        sb.append("Last three trips:\n");

        if (lastThreeTrips != null) {
            for (String trip : lastThreeTrips) {
                sb.append("- ").append(trip).append("\n");
            }
        }
        return sb.toString();
    }
}