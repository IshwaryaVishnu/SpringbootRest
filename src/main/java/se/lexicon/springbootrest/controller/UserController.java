package se.lexicon.springbootrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.springbootrest.model.dto.UserDto;
import se.lexicon.springbootrest.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    //POST  http://localhost:8080/api/v1/user/
    //request body { username: user, password: 123456, roles: {id: 1, name: ADMIN,...}}
    //@RequestMapping(path = "/", method = RequestMethod.POST)

    @PostMapping("/")
    public ResponseEntity<UserDto> signup(@RequestBody @Valid UserDto dto){
        System.out.println("USERNAME: " + dto.getUsername());
        UserDto serviceResult = userService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResult);
    }


    //GET  http://localhost:8080/api/v1/user/admin
    //   /{username}    search - path variable - GET
    // todo:
    @GetMapping("/{username}")
    public ResponseEntity<Map<String, Object>> findByUsername(@PathVariable("username") @NotEmpty @Size(min = 4, max = 50) String username){
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }



    //   /{username}    disable user - path variable - PUT
    // todo:
    @PutMapping("/disable")
    public ResponseEntity<Void> disableUserByUsername(@RequestParam("username") @NotEmpty @Size(min = 4, max = 50) String username){
        userService.disableUserByUsername(username);
        return ResponseEntity.noContent().build();
    }


}
