package sheloumov.v.d.ShoeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sheloumov.v.d.ShoeShop.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
}
