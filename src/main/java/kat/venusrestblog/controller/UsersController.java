package kat.venusrestblog.controller;

import kat.venusrestblog.data.User;
import kat.venusrestblog.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UsersController {
    private UsersRepository usersRepository;
    @GetMapping(value = "")
    public List<User> fetchUsers (){
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> fetchUserById(@PathVariable long id) {
        return usersRepository.findById(id);
    }
    @GetMapping("/me")
    private Optional<User> fetchMe() {
        return usersRepository.findById(1L);
    }

//    @GetMapping("/username/{userName}")
//    private User fetchByUserName(@PathVariable String userName) {
//
//    }
//
//    @GetMapping("/email/{email}")
//    private User fetchByEmail(@PathVariable String email) {
//        User user = findUserByEmail(email);
//        if(user == null) {
//            // what to do if we don't find it
//            throw new RuntimeException("I don't know what I am doing");
//        }
//        return user;
//    }


    @PostMapping("/create")
    public void createUser(@RequestBody User newUser){
        newUser.setCreatedAt(LocalDate.now());
        usersRepository.save(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
        usersRepository.deleteById(id);
    }
    @PutMapping("/{id}")
    public void updateUser( @RequestBody User updatedUser, @PathVariable long id) {
        updatedUser.setId(id);
        usersRepository.save(updatedUser);
    }
    @PutMapping("/{id}/updatePassword")
    private void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword){
        User user = usersRepository.findById(id).get();
//        if(user == null){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User id" + id + "not found");
//        }
//        to compare the old password with saved password
        if (!user.getPassword().equals(oldPassword)){

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"asmcrayy");
        }
//        to validate new password
        if (newPassword.length() < 3){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "new password length must be at least 3 characters");
        }
        user.setPassword(newPassword);
        usersRepository.save(user);
    }
}
