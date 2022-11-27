package sheloumov.v.d.ShoeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sheloumov.v.d.ShoeShop.entity.User;
import sheloumov.v.d.ShoeShop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    private List<User> users = new ArrayList<>();
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

//    public Page<User> findAllUsersWithPagination(Pageable pageable){
//       return userRepository.findAll(Sort.by("id").ascending());
//    }

    public void createUser(User user){
            userRepository.save(user);
    }

    public void  deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void updateUser(User user, Long id){

    }

    public Optional<User> findOneUser(Long id){
        return userRepository.findById(id);
    }
}
