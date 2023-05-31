package project;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static UserData instance;
    private List<User> users = new ArrayList<>();
    private User currentUser;
    private int thisGameScore;

    private UserData() {
    }

    public static UserData getInstance() {
        if (instance == null)
            instance = new UserData();
        return instance;
    }

    public static void setInstance(UserData instance) {
        UserData.instance = instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public int getThisGameScore() {
        return thisGameScore;
    }

    public void setThisGameScore(int thisGameScore) {
        this.thisGameScore = thisGameScore;
    }
}