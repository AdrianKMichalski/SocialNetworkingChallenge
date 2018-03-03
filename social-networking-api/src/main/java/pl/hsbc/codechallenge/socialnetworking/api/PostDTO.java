package pl.hsbc.codechallenge.socialnetworking.api;

import java.util.UUID;

/**
 * @author Adrian Michalski
 */
public class PostDTO {

    private UUID id;
    private UserDTO user;
    private String body;
    private Long creationDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

}
