package sheloumov.v.d.ShoeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import sheloumov.v.d.ShoeShop.entity.Item;
import sheloumov.v.d.ShoeShop.service.ItemService;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
