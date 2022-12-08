package sheloumov.v.d.ShoeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
                if(userRepository.findFirstByLogin(user.getLogin()) != null){
                    User userUpdate = userRepository.findFirstByLogin(user.getLogin());
                    System.out.println(userUpdate.toString());
                    userUpdate.setLogin(user.getLogin());
                    //TODO: добавить сброс пароля

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


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User u = userRepository.getUserByLogin(username);
//        return null;
//    }
}
