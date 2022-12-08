package sheloumov.v.d.ShoeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sheloumov.v.d.ShoeShop.entity.FileEntity;


public interface FileRepository extends JpaRepository<FileEntity, String> {
}
