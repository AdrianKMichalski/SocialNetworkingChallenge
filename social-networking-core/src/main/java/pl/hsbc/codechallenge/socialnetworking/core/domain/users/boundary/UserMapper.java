package pl.hsbc.codechallenge.socialnetworking.core.domain.users.boundary;

import pl.hsbc.codechallenge.socialnetworking.api.UserDTO;
import pl.hsbc.codechallenge.socialnetworking.core.domain.users.entity.User;

/**
 * @author Adrian Michalski
 */
public class UserMapper {

    public static UserDTO toDTO(User entity) {
        UserDTO dto = new UserDTO();

        dto.setName(entity.getName());

        return dto;
    }
}
