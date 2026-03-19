import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
public class UserRegistration {
    public static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0 / 100;
    public static final double VIP_DISCOUNT_UNDER_18 = 20.0 / 100;
    public static final double VIP_BASE_FEE = 100.0;
    String fullName;
    String emailAddress;
    String dateOfBirth;
    long cardNumber;
    String cardProvider;
    String cardExpiryDate;
    Double feeToCharge;
    int cvv;
    String userType;
    boolean emailValid;
    boolean minorAndBirthday;
    boolean minor;
    boolean ageValid;
    boolean cardNumberValid;
    boolean cardStillValid;
    boolean validCVV;
    private Scanner scanner;
    public UserRegistration() {
        this.scanner = new Scanner(System.in);
        this.emailValid = false;
        this.minorAndBirthday = false;
        this.minor = false;
        this.ageValid = false;
        this.cardNumberValid = false;
        this.cardStillValid = false;
        this.validCVV = false;
    }
   public void registration() {
        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("Here are your two options:");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.println("Please enter your choice (1 or 2):");
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        userType = (choice == 1) ? "Regular User" : "VIP User";
        System.out.println("Please enter your full name:");
        fullName = scanner.nextLine();
        System.out.println("Please enter your email address:");
        emailAddress = scanner.nextLine();
        emailValid = analyseEmail(emailAddress);
        System.out.println("Please enter your date of birth (YYYY-MM-DD):");
        dateOfBirth = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);
        System.out.println("Please enter your card number (only Visa, MasterCard, American Express):");
        cardNumber = scanner.nextLong();
        scanner.nextLine(); 
        cardNumberValid = analyseCardNumber(cardNumber);
        System.out.println("Please enter your card expiry date (MM/YY):");
        cardExpiryDate = scanner.nextLine();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);
        System.out.println("Please enter your card CVV:");
        cvv = scanner.nextInt();
        scanner.nextLine(); 
        validCVV = analyseCVV(cvv);
        finalCheckpoint();
        scanner.close();
    }
    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("Email is valid");
            return true;
        } else {
            System.out.println("Invalid email address. Going back to the start of the registration");
            registration(); 
            return false;
        }
    }
    private boolean analyseAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob, currentDate);
        int age = period.getYears();
        boolean isBirthday = dob.getMonthValue() == currentDate.getMonthValue()
                && dob.getDayOfMonth() == currentDate.getDayOfMonth();
        if (age <= 12 || age > 120) {
            System.out.println("Looks like you are either too young or already dead. Sorry, you can't be our user. Have a nice day");
            System.exit(0);
        }
        if ("VIP User".equals(userType)) {
            if (isBirthday && age <= 18 && age > 12) {
                System.out.println("Happy Birthday!");
                System.out.println("You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                minorAndBirthday = true;
            } else if (!isBirthday && age <= 18 && age > 12) {
                System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                minor = true;
            }
        }
        return true;
    }
    private boolean analyseCardNumber(long cardNum) {
        String cardNumStr = String.valueOf(cardNum);
        int firstTwoDigits = 0;
        int firstFourDigits = 0;
        if (cardNumStr.length() >= 2) {
            firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        }
        if (cardNumStr.length() >= 4) {
            firstFourDigits = Integer.parseInt(cardNumStr.substring(0, 4));
        }

        if ((cardNumStr.length() == 13 || cardNumStr.length() == 15) && cardNumStr.startsWith("4")) {
            cardProvider = "VISA";
            return true;
        }
        else if (cardNumStr.length() == 16 &&
                ((firstTwoDigits >= 51 && firstTwoDigits <= 55) || (firstFourDigits >= 2221 && firstFourDigits <= 2720))) {
            cardProvider = "MasterCard";
            return true;
        }
        else if (cardNumStr.length() == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
            return true;
        }
        else {
            System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.");
            System.out.println("Going back to the start of the registration.");
            registration();
            return false;
        }
    }
    private boolean analyseCardExpiryDate(String expiryDate) {
        int month = Integer.parseInt(expiryDate.substring(0, 2));
        int year = Integer.parseInt(expiryDate.substring(3, 5)) + 2000;
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        if (year > currentYear || (year == currentYear && month >= currentMonth)) {
            System.out.println("The card is still valid");
            return true;
        } else {
            System.out.println("Sorry, your card has expired. Please use a different card.");
            System.out.println("Going back to the start of the registration process…");
            registration(); 
            return false;
        }
    }
    private boolean analyseCVV(int cvvNum) {
        String cvvStr = String.valueOf(cvvNum);
        if (("American Express".equals(cardProvider) && cvvStr.length() == 4)
                || ("VISA".equals(cardProvider) && cvvStr.length() == 3)
                || ("MasterCard".equals(cardProvider) && cvvStr.length() == 3)) {
            System.out.println("Card CVV is valid.");
            return true;
        } else {
            System.out.println("Invalid CVV for the given card.");
            System.out.println("Going back to the start of the registration process.");
            registration(); 
            return false;
        }
    }
    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFees();
        } else {
            System.out.println("Sorry, your registration was unsuccessful due to the following reason(s)");
            if (!emailValid) System.out.println("Invalid email address");
            if (!ageValid) System.out.println("Invalid age");
            if (!cardNumberValid) System.out.println("Invalid card number");
            if (!cardStillValid) System.out.println("Card has expired");
            if (!validCVV) System.out.println("Invalid CVV");
            System.out.println("Going back to the start of the registration process.");
            registration(); 
        }
    }
    private void chargeFees() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY);
        } else if (minor) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18);
        } else {
            feeToCharge = VIP_BASE_FEE;
        }
        String cardNumStr = String.valueOf(cardNumber);
        String lastFourDigits = cardNumStr.substring(cardNumStr.length() - 4);
        System.out.printf("Thank you for your payment.%n");
        System.out.printf("A fee of %.2f has been charged to your card ending with %s%n", feeToCharge, lastFourDigits);
    }
    public String toString() {
        String cardNumStr = String.valueOf(cardNumber);
        String censoredPart = cardNumStr.substring(0, cardNumStr.length() - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumStr.substring(cardNumStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;
        return "Registration successful! Here are your details:\n"
                + "User Type: " + userType + "\n"
                + "Full Name: " + fullName + "\n"
                + "Email Address: " + emailAddress + "\n"
                + "Date of Birth: " + dateOfBirth + "\n"
                + "Card Number: " + censoredNumber + "\n"
                + "Card Provider: " + cardProvider + "\n"
                + "Card Expiry Date: " + cardExpiryDate;
    }
}
