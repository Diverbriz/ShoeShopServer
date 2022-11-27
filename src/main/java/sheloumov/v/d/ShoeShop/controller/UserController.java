package sheloumov.v.d.ShoeShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sheloumov.v.d.ShoeShop.entity.User;
import sheloumov.v.d.ShoeShop.responses.UserResponse;
import sheloumov.v.d.ShoeShop.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{page}")
    public ResponseEntity<UserResponse> getAllPagination(@PathVariable String page){

        UserResponse userResponse = new UserResponse(userService.getAllUsers());
        System.out.println(Arrays.toString(userResponse.getData().toArray()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getOneUser(@PathVariable("id") Long id){
        if (id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userService.findOneUser(id);

        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user.get(), HttpStatus.OK);

    }

    @PostMapping("/create")
    public void createUser(@RequestBody User user){
        System.out.println(user);
        if(user.getRole() == null){
            user.setRole("USER");
        }
        userService.createUser(user);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userService.findOneUser(id);
        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }




//    @GetMapping("/users")
//    public List<User> getAllCountries() {
//        return userService.getAllUsers();
//    }
}
