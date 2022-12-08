package sheloumov.v.d.ShoeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sheloumov.v.d.ShoeShop.entity.Brand;
import sheloumov.v.d.ShoeShop.entity.Item;
import sheloumov.v.d.ShoeShop.entity.ItemImage;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
    List<ItemImage> findAllByItemId(Item itemId);
    ItemImage deleteAllById(Long id);
    ItemImage deleteItemImageById(Long id);
}
