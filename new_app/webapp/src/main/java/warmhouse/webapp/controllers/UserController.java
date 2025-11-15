package warmhouse.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warmhouse.webapp.dbo.User;
import warmhouse.webapp.repository.UserRepository;

@RestController
@RequestMapping("/api/v3/user")
class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId){

        var useropt =  userRepository.findById(userId);
        return useropt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping(value = "")
    public void createUser(@RequestBody UserCreateJson userJson){
        var user = new User();
        user.setName(userJson.name);
        userRepository.save(user);
    }

    public record UserCreateJson(
            String name
    ){}

}
