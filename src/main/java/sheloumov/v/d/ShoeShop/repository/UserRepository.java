package sheloumov.v.d.ShoeShop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sheloumov.v.d.ShoeShop.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllBy(Pageable pageable);
    User findFirstByEmail(String email);
}
