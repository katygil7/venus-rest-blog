package kat.venusrestblog.controller;

import kat.venusrestblog.data.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UsersController {
    private final List<User> users = new ArrayList<>();
    private long nextId = 1;

    @GetMapping(value = "")
    public List<User> fetchUsers (){
        return users;
    }

    @GetMapping("/{id}")
    public User fetchUsersById (@PathVariable long id){
//    search through the list of posts and return the post that matches the given id
        User user = findUserById(id);
        if(user == null){
            throw new RuntimeException("I don't know what I am doing");
        }
            return user;
    }
    @GetMapping("/me")
    private User fetchMe() {
        return users.get(0);
    }

    @GetMapping("/username/{userName}")
    private User fetchByUserName(@PathVariable String userName) {
        User user = findUserByUserName(userName);
        if(user == null) {
            // what to do if we don't find it
            throw new RuntimeException("I don't know what I am doing");
        }
        return user;
    }

    @GetMapping("/email/{email}")
    private User fetchByEmail(@PathVariable String email) {
        User user = findUserByEmail(email);
        if(user == null) {
            // what to do if we don't find it
            throw new RuntimeException("I don't know what I am doing");
        }
        return user;
    }
    private User findUserByEmail(String email){
        for (User user: users){
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
    private User findUserByUserName(String userName) {
        for (User user:users){
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
//        return null if didn't find anything
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

    @DeleteMapping("/{id}")
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
            System.out.println("User not found");
        } else {
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            return;
        }
        throw new RuntimeException("User not found");
    }
    @PutMapping("/{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
        User user = findUserById(id);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User id" + id + "not found");
        }
//        to compare the old password with saved password
        if (!user.getPassword().equals(oldPassword)){

            throw new RuntimeException("asmcrayy");
        }
//        to validate new password
        if (newPassword.length() < 3){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "new password length must be at least 3 characters");
        }
        user.setPassword(newPassword);
    }
}
