package database;

import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database
{
    private static long _nextId;
    private static HashMap<Long, User> _users;

    static {
        _users = new HashMap<>();
        User u = new User();
        u.setFirstName("alden");
        u.setLastName("quimby");
        addUser(u);
    }

    public static User updateUser(Long id, User u) {
        _users.put(id, u);
        u.setId(id);
        return u;
    }

    public static User getUser(Long id) {
        return _users.get(id);
    }

    public static User addUser(User u) {
        u.setId(++_nextId);
        return updateUser(u.getId(), u);
    }

    public static List<User> getUsers() {
        return new ArrayList<>( _users.values());
    }

    public static void deleteUser(Long id) {
        _users.remove(id);
    }
}
