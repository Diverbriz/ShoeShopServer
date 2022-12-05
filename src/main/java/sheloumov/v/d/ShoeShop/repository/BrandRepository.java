package sheloumov.v.d.ShoeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import sheloumov.v.d.ShoeShop.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    public Brand findFirstByName(String name);
    Brand findFirstById(Long id);
}
