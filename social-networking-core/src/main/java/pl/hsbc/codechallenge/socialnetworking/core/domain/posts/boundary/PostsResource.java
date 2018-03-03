package pl.hsbc.codechallenge.socialnetworking.core.domain.posts.boundary;

import com.google.common.base.Preconditions;
import pl.hsbc.codechallenge.socialnetworking.api.PostDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Objects;

/**
 * @author Adrian Michalski
 */
@Path("posts")
@Produces(MediaType.APPLICATION_JSON)
public class PostsResource {

    @Inject
    private PostsRepository postsRepository;

    @GET
    @Path("{postId}")
    public PostDTO getPostById(@PathParam("postId") String postId) {
        return postsRepository.getPostById(postId)
                .map(PostMapper::toDTO)
                .orElseThrow(NotFoundException::new);
    }

}
