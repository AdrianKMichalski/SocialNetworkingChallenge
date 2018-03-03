package pl.hsbc.codechallenge.socialnetworking.core.domain.posts.control;

import pl.hsbc.codechallenge.socialnetworking.core.domain.posts.boundary.PostsRepository;
import pl.hsbc.codechallenge.socialnetworking.core.domain.posts.entity.Post;
import pl.hsbc.codechallenge.socialnetworking.core.domain.users.entity.User;

import javax.ejb.Singleton;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Adrian Michalski
 */
@Singleton
public class InMemoryPostsRepository implements PostsRepository {

    private final List<Post> posts = new LinkedList<>();

    @Override
    public Optional<Post> getPostById(String postId) {
        return posts.stream()
                .filter(p -> postId.equals(p.getId().toString()))
                .findFirst();
    }

    @Override
    public List<Post> getAllPosts() {
        return posts;
    }

    @Override
    public List<Post> getUsersWallPosts(User user) {
        return posts.stream()
                .filter(post -> user.equals(post.getUser()))
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getUsersTimeLinePosts(User user) {
        Set<User> followedUsers = user.getFollowedUsers();

        return followedUsers.stream()
                .flatMap(followedUser -> getUsersWallPosts(followedUser).stream())
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Post createPost(User user, String body) {
        Post newPost = new Post(user, body);
        posts.add(newPost);
        return newPost;
    }

}
