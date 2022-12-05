package sheloumov.v.d.ShoeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sheloumov.v.d.ShoeShop.entity.Basket;
import sheloumov.v.d.ShoeShop.entity.User;
import sheloumov.v.d.ShoeShop.exceptions.NotFoundExceptions;
import sheloumov.v.d.ShoeShop.repository.BasketRepository;
import sheloumov.v.d.ShoeShop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BasketRepository basketRepository;

    @Autowired
    public UserService(UserRepository userRepository, BasketRepository basketRepository){
        this.userRepository = userRepository;
        this.basketRepository = basketRepository;
    }

    private List<User> users = new ArrayList<>();
//    @Query(nativeQuery = true, )
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getUsersOfPages(Pageable page){
        return userRepository.findAllBy(page);
    }



//    public Page<User> findAllUsersWithPagination(Pageable pageable){
//       return userRepository.findAll(Sort.by("id").ascending());
//    }

    public void createUser(User user){
            try{
                if(userRepository.findFirstByEmail(user.getEmail()) != null){
                    User userUpdate = userRepository.findFirstByEmail(user.getEmail());
                    System.out.println(userUpdate.toString());
                    userUpdate.setEmail(user.getEmail());
                    //TODO: добавить сброс пароля
                    userUpdate.setRole(user.getRole());
                }
                else {
                    userRepository.save(user);
                    if(findOneUser(user.getId()).isPresent()){
                        Basket basket = new Basket();
                        basket.setUserId(user);
                        basketRepository.save(basket);
                    }
                }
            } catch (NotFoundExceptions e){
                e.printStackTrace();
            }
//        try {
//            userRepository.save(user);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
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
