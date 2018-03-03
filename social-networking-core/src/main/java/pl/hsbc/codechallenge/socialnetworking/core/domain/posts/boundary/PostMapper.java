package pl.hsbc.codechallenge.socialnetworking.core.domain.posts.boundary;

import pl.hsbc.codechallenge.socialnetworking.api.PostDTO;
import pl.hsbc.codechallenge.socialnetworking.core.domain.posts.entity.Post;
import pl.hsbc.codechallenge.socialnetworking.core.domain.users.boundary.UserMapper;

import java.time.ZoneId;

/**
 * @author Adrian Michalski
 */
public class PostMapper {

    public static PostDTO toDTO(Post entity) {
        PostDTO dto = new PostDTO();

        dto.setBody(entity.getBody());
        dto.setCreationDate(entity.getCreationDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        dto.setId(entity.getId());
        dto.setUser(UserMapper.toDTO(entity.getUser()));

        return dto;
    }

}
