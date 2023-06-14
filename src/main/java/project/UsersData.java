package project;

import java.util.ArrayList;
import java.util.List;

public class UsersData {
    private static UsersData instance;
    private List<User> users = new ArrayList<>();
    private User currentUser;
    private int thisGameScore;

    private UsersData() {
    }

    public static UsersData getInstance() {
        if (instance == null)
            instance = new UsersData();
        return instance;
    }

    public static void setInstance(UsersData instance) {
        UsersData.instance = instance;
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