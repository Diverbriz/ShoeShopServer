package sheloumov.v.d.ShoeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import sheloumov.v.d.ShoeShop.entity.Brand;
import sheloumov.v.d.ShoeShop.exceptions.NotFoundExceptions;
import sheloumov.v.d.ShoeShop.repository.BrandRepository;

import java.util.List;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Brand createBrand(Brand brand){
        if(brandRepository.findFirstByName(brand.getName()) != null){
            Brand brand1 = brandRepository.findFirstByName(brand.getName());
            brand1.setName(brand.getName());
            brandRepository.save(brand1);
            return brand1;
        }
        else {
            brandRepository.save(brand);
            return brand;
        }
    }

    public Brand findOneByName(String name){

        Brand brand = brandRepository.findFirstByName(name);
        if(brand != null){
            return brand;
        }
        else {
            throw  new NotFoundExceptions();
        }
    }

    public List<Brand> getAllBrand(){
        return brandRepository.findAll();
    }

    public Brand deleteBrand(Long id){
       Brand brand = brandRepository.findFirstById(id);
       if(brand != null){
           brandRepository.delete(brand);
           return brand;
       }
       else {
           throw new NotFoundExceptions();
       }
    }
}
