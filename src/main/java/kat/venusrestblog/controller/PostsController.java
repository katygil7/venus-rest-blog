package kat.venusrestblog.controller;

import kat.venusrestblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/posts", produces = "application/json")

public class PostsController {
    public List<Post> posts = new ArrayList<>();
    private long nextId = 1;
@GetMapping (value = "")
public List<Post> fetchPosts (){
    return posts;
}

    @GetMapping("/{id}")
    public Post fetchPostsById (@PathVariable long id){
//    search through the list of posts and return the post that matches the given id
        Post post = findPostById(id);
        if(post == null){
            throw new RuntimeException("I don't know what I am doing");
        }else{
            return post;
        }

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
        newPost.setId(nextId);
        nextId++;

        posts.add(newPost);
    }

    @DeleteMapping("/{id}")
    public void deletePostsById (@PathVariable long id){
//    search through the list of posts and delete the post that matches the given id
        Post post = findPostById(id);
        if( post != null){
            posts.remove(post);
            return;
        }
        throw new RuntimeException("I don't know what I am doing");
    }
    @PutMapping("/{id}")
    public void updatePost( @RequestBody Post updatedPost, @PathVariable long id){
//    find the post you want to update in the posts list
        Post post = findPostById(id);
        if(post == null){
            System.out.println("Post not found");
        }else{
            if (updatedPost.getTitle() != null){
                post.setTitle(updatedPost.getTitle());
            }
            if(updatedPost.getContent() != null){
                post.setContent(updatedPost.getContent());
            }
            return;
        }
        throw new RuntimeException("Post not found");
    }
}
