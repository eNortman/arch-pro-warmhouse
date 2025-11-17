package warmhouse.webapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warmhouse.webapp.dbo.User;
import warmhouse.webapp.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v3/user")
@Tag(name = "user API",
        description = "API для управления данными о пользователях")
class UserController {

    @Autowired
    public UserRepository userRepository;

    @Operation(
            summary = "Получить список всех пользователей",
            description = "Возвращает список всех пользователей в системе."
    )
    @GetMapping(value = "")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Operation(
            summary = "Получить данные пользователя по id"
    )
    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId){

        var useropt =  userRepository.findById(userId);
        return useropt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(
            summary = "Создать пользователя"
    )
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
