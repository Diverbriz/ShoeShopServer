package sheloumov.v.d.ShoeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import sheloumov.v.d.ShoeShop.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findFirstByName(String name);

    Type findFirstById(Long id);
}
