package pl.hsbc.codechallenge.socialnetworking.core.domain.posts.entity;

import pl.hsbc.codechallenge.socialnetworking.core.domain.users.entity.User;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Adrian Michalski
 */
public class Post {

    private final UUID id;
    private final User user;
    private final String body;
    private final LocalDateTime creationDate;

    public Post(UUID id, User user, String body, LocalDateTime creationDate) {
        this.id = id;
        this.user = user;
        this.body = body;
        this.creationDate = creationDate;
    }

    public Post(User user, String body) {
        this(UUID.randomUUID(), user, body, LocalDateTime.now());
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
