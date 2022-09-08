package kat.venusrestblog.controller;

import kat.venusrestblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")

public class PostsController {
    public List<Post> posts = new ArrayList<>();
@RequestMapping(value = "", method = RequestMethod.GET)
public List<Post> fetchPosts (){
    return posts;
}

    @GetMapping("{id}")
    public Post fetchPostsById (@PathVariable long id){
//    search through the list of posts and return the post that matches the given id
        Post post = findPostById(id);
        throw new RuntimeException("I don't know what I am doing");
    }
    private Post findPostById ( long id){
        for (Post post:posts) {
            if(post.getId() == id){
                return post;
            }
        }
        return null;
    }

    @PostMapping("")
    public void createPost(@RequestBody Post newPost){
        System.out.println(newPost);
        posts.add(newPost);
    }

    @DeleteMapping("{id}")
    public void deletePostsById (@PathVariable long id){
//    search through the list of posts and delete the post that matches the given id
        Post post = findPostById(id);
        throw new RuntimeException("I don't know what I am doing");
    }
    @PutMapping("/{id}")
    public void updatePost( @RequestBody Post updatedPost, @PathVariable long id){
//    find the post you want to update in the posts list
        Post post = findPostById(id);
        if(post == null){
            post = updatedPost;
        }else{
            if (updatedPost.getTitle() != null){
                post.setTitle(updatedPost.getTitle());
            }
            if(updatedPost.getContent() != null){
                post.setContent(updatedPost.getContent());
            }
        }
    }
}
