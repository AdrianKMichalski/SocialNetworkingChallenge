package pl.hsbc.codechallenge.socialnetworking.core.domain.users.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Adrian Michalski
 */
public class User {

    private final String name;
    private final Set<User> followedUsers;

    public User(String name, Set<User> followedUsers) {
        this.name = name;
        this.followedUsers = new HashSet<>(followedUsers);
    }

    public User(String name) {
        this.name = name;
        this.followedUsers = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<User> getFollowedUsers() {
        return followedUsers;
    }

    /**
     * @return <tt>true</tt> if specified user was not already followed
     */
    public boolean followUser(User followedUser) {
        return followedUsers.add(followedUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
