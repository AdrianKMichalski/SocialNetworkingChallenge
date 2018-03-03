package pl.hsbc.codechallenge.socialnetworking.core.domain.users.boundary;

import pl.hsbc.codechallenge.socialnetworking.core.domain.users.entity.User;

import java.util.Optional;
import java.util.Set;

/**
 * @author Adrian Michalski
 */
public interface UsersRepository {

    Set<User> getAllUsers();

    Optional<User> getUserByName(String name);

    User createUser(String name);

}
