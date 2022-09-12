package kat.venusrestblog.controller;

import kat.venusrestblog.data.Post;
import kat.venusrestblog.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api/users", produces = "application/json")
public class UsersController {
    private List<User> users = new ArrayList<>();
    private long nextId = 1;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> fetchUsers (){
        return users;
    }

    @GetMapping("{id}")
    public User fetchUsersById (@PathVariable long id){
//    search through the list of posts and return the post that matches the given id
        User user = findUserById(id);
        if(user == null){
            throw new RuntimeException("I don't know what I am doing");
        }
            return user;
    }
    private User findUserById ( long id){
        for (User user:users) {
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User newUser){
        newUser.setId(nextId);
        nextId++;

        users.add(newUser);
    }

    @DeleteMapping("{id}")
    public void deleteUserById (@PathVariable long id){
//    search through the list of posts and delete the post that matches the given id
        User user = findUserById(id);
        if( user != null){
            users.remove(user);
            return;
        }
        throw new RuntimeException("User not found");
    }
    @PutMapping("/{id}")
    public void updateUser( @RequestBody User updatedUser, @PathVariable long id) {
//    find the post you want to update in the posts list
        User user = findUserById(id);
        if (user == null) {
            user = updatedUser;
        } else {
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            return;
        }
        throw new RuntimeException("User not found");
    }
}
