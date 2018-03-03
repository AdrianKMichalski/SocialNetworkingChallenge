package pl.hsbc.codechallenge.socialnetworking.core.domain.users.control;

import pl.hsbc.codechallenge.socialnetworking.core.domain.users.entity.User;
import pl.hsbc.codechallenge.socialnetworking.core.domain.users.boundary.UsersRepository;

import javax.ejb.Singleton;
import java.util.*;

/**
 * @author Adrian Michalski
 */
@Singleton
public class InMemoryUsersRepository implements UsersRepository {

    private final Set<User> users = new HashSet<>();

    @Override
    public Set<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> getUserByName(String name) {
        return users.stream()
                .filter(user -> name.equals(user.getName()))
                .findFirst();
    }

    @Override
    public User createUser(String name) {
        User newUser = new User(name);
        if (users.add(newUser)) {
            return newUser;
        } else {
            throw new IllegalArgumentException("User " + name + " already exists");
        }
    }

}
