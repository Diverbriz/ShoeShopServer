package sheloumov.v.d.ShoeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import sheloumov.v.d.ShoeShop.entity.BasketItem;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
}
