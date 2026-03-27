public class UserRegistration {
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private String cardNumber;
    private String cardExpiryDate;
    private String cardProvider;
    private String cvv;
    private String userType;

    public UserRegistration() {}

    public UserRegistration(String fullName, String emailAddress, String dateOfBirth,
                            String cardNumber, String cardExpiryDate, String cardProvider,
                            String cvv, String userType) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv;
        this.userType = userType;
    }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getCardExpiryDate() { return cardExpiryDate; }
    public void setCardExpiryDate(String cardExpiryDate) { this.cardExpiryDate = cardExpiryDate; }

    public String getCardProvider() { return cardProvider; }
    public void setCardProvider(String cardProvider) { this.cardProvider = cardProvider; }

    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
}