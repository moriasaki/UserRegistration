import java.util.ArrayList;
import java.util.Iterator;

public class UserService {
    private ArrayList<RegisteredUsers> registeredUsersList;

    public UserService() {
        registeredUsersList = new ArrayList<>();
    }

    public RegisteredUsers addNewUser(String fullName, String email, String dob,
                                      String card, String exp, String provider, String cvv,
                                      String userType, String[] trips) {
        RegisteredUsers newUser;
        if (userType.equalsIgnoreCase("VIP")) {
            newUser = new VIPUser(fullName, email, dob, card, exp, provider, cvv, userType, trips);
        } else {
            newUser = new RegularUser(fullName, email, dob, card, exp, provider, cvv, userType, trips);
        }
        registeredUsersList.add(newUser);
        return newUser;
    }

    public void addUser(RegisteredUsers user) {
        registeredUsersList.add(user);
    }

    public ArrayList<RegisteredUsers> getAllUsers() {
        return registeredUsersList;
    }

    public boolean removeUser(String email) {
        Iterator<RegisteredUsers> it = registeredUsersList.iterator();
        while (it.hasNext()) {
            RegisteredUsers u = it.next();
            if (u.getEmailAddress().equals(email)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public RegisteredUsers findUserByEmail(String email) {
        for (RegisteredUsers u : registeredUsersList) {
            if (u.getEmailAddress().equals(email)) {
                return u;
            }
        }
        return null;
    }
}