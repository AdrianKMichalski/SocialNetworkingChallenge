package pl.hsbc.codechallenge.socialnetworking.core.domain.posts.boundary;

import pl.hsbc.codechallenge.socialnetworking.core.domain.posts.entity.Post;
import pl.hsbc.codechallenge.socialnetworking.core.domain.users.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Adrian Michalski
 */
public interface PostsRepository {

    Optional<Post> getPostById(String postId);

    List<Post> getAllPosts();

    List<Post> getUsersWallPosts(User user);

    List<Post> getUsersTimeLinePosts(User user);

    Post createPost(User user, String body);

}
