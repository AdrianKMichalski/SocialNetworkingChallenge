package pl.hsbc.codechallenge.socialnetworking.core.domain.users.boundary;

import pl.hsbc.codechallenge.socialnetworking.api.PostDTO;
import pl.hsbc.codechallenge.socialnetworking.api.UserDTO;
import pl.hsbc.codechallenge.socialnetworking.core.domain.posts.boundary.PostMapper;
import pl.hsbc.codechallenge.socialnetworking.core.domain.posts.boundary.PostsRepository;
import pl.hsbc.codechallenge.socialnetworking.core.domain.posts.boundary.PostsResource;
import pl.hsbc.codechallenge.socialnetworking.core.domain.posts.entity.Post;
import pl.hsbc.codechallenge.socialnetworking.core.domain.users.entity.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Adrian Michalski
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

    private static final int MAXIMUM_POST_LENGTH = 140;

    @Inject
    private UsersRepository usersRepository;

    @Inject
    private PostsRepository postsRepository;

    @GET
    public Set<UserDTO> getAllUsers() {
        return usersRepository.getAllUsers().stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toSet());
    }

    @GET
    @Path("{userName}/wall")
    public List<PostDTO> getUsersWall(@PathParam("userName") String userName) {
        List<Post> postsByUser = usersRepository.getUserByName(userName)
                .map(postsRepository::getUsersWallPosts)
                .orElseThrow(() -> new BadRequestException("User " + userName + " not found"));

        return postsByUser.stream()
                .map(PostMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GET
    @Path("{userName}/timeline")
    public List<PostDTO> getUsersTimeline(@PathParam("userName") String userName) {
        User user = getUserOrThrowException(userName);

        return postsRepository.getUsersTimeLinePosts(user).stream()
                .map(PostMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GET
    @Path("{userName}/followed")
    public List<String> getFollowedUserNames(@PathParam("userName") String userName) {
        User user = getUserOrThrowException(userName);

        return user.getFollowedUsers().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    @POST
    @Path("{userName}/post")
    public Response addPost(@PathParam("userName") String userName, PostDTO post) {
        String postBody = Optional.ofNullable(post.getBody())
                .orElseThrow(() -> new BadRequestException("Body in PostDTO is required"));
        if ((postBody.length() > MAXIMUM_POST_LENGTH)) {
            throw new BadRequestException("Post body should have at most " + MAXIMUM_POST_LENGTH + " characters");
        }

        User user = getOrCreateUser(userName);
        Post createdPost = postsRepository.createPost(user, postBody);

        URI postUri = UriBuilder.fromResource(PostsResource.class)
                .path(createdPost.getId().toString())
                .build();

        return Response.created(postUri).build();
    }

    private User getOrCreateUser(String userName) {
        Optional<User> userMaybe = usersRepository.getUserByName(userName);
        return userMaybe.orElseGet(() -> usersRepository.createUser(userName));
    }

    @PUT
    @Path("{userName}/follow/{followedUserName}")
    public Response followUser(@PathParam("userName") String userName,
                               @PathParam("followedUserName") String followedUserName) {
        User followingUser = getUserOrThrowException(userName);
        User followedUser = getUserOrThrowException(followedUserName);

        followingUser.followUser(followedUser);

        return Response.ok().build();
    }

    private User getUserOrThrowException(String userName) {
        return usersRepository.getUserByName(userName)
                .orElseThrow(() -> new BadRequestException("User " + userName + " not found"));
    }

}
