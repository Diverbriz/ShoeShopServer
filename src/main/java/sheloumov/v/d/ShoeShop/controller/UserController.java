package sheloumov.v.d.ShoeShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sheloumov.v.d.ShoeShop.entity.Roles;
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
    public ResponseEntity<UserResponse> getAll(){
        List<User> users = userService.getAllUsers();
        if (users != null){
            return new ResponseEntity<>(new UserResponse(users), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserResponse> getAllPagination(@Param(value = "limit") Integer  limit,
                                                             @Param(value = "offset") Integer offset){

        UserResponse userResponse = new UserResponse(userService
                .getUsersOfPages(PageRequest.of(offset, limit)));
//        System.out.println(Arrays.toString(userResponse.getData().toArray()));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
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
    public ResponseEntity createUser(@RequestBody User user){
        System.out.println("user");
        if(user.getRole() == null){
            user.setRole(Roles.USER);
        }
        try{
            userService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity("Непредвиденная ошибка" ,HttpStatus.BAD_REQUEST);
    }



    @DeleteMapping("/delete")
    public ResponseEntity<User> deleteUser(@RequestParam Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userService.findOneUser(id);
        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
//    @GetMapping("/users")
//    public List<User> getAllCountries() {
//        return userService.getAllUsers();
//    }

    @PostMapping("/login")
    public void loginUser(){

    }

    public User userUpdate(User user){
        User user1 = new User();
        user1.setLogin(user.getLogin());
        user1.setPassword(user.getPassword());
        user1.setRole(user.getRole());
        return user1;
    }
}
