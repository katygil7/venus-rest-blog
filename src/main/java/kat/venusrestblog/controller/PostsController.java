package kat.venusrestblog.controller;
import kat.venusrestblog.data.User;
import kat.venusrestblog.data.Post;
import kat.venusrestblog.repository.PostsRepository;
import kat.venusrestblog.repository.UsersRepository;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")

public class PostsController {
    private PostsRepository postsRepository;
    private UsersRepository usersRepository;

    public PostsController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }
@GetMapping (value = "")
public List<Post> fetchPosts (){
    return postsRepository.findAll();
}

    @GetMapping("/{id}")
    public Optional<Post> fetchPostById(@PathVariable long id) {
        return postsRepository.findById(id);
    }

    @PostMapping("")
    public void createPost(@RequestBody Post newPost){
        User author = usersRepository.findById(1L).get();
        newPost.setAuthor(author);
        postsRepository.save(newPost);
    }

    @DeleteMapping("/{id}")
    public void deletePostsById (@PathVariable long id){
        postsRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public void updatePost( @RequestBody Post updatedPost, @PathVariable long id){
        updatedPost.setId(id);
        postsRepository.save(updatedPost);
////    find the post you want to update in the posts list
//        Post post = findPostById(id);
//        if(post == null){
//            System.out.println("Post not found");
//        }else{
//            if (updatedPost.getTitle() != null){
//                post.setTitle(updatedPost.getTitle());
//            }
//            if(updatedPost.getContent() != null){
//                post.setContent(updatedPost.getContent());
//            }
//            return;
//        }
//        throw new RuntimeException("Post not found");
    }
}
