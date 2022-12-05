package sheloumov.v.d.ShoeShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sheloumov.v.d.ShoeShop.entity.Brand;
import sheloumov.v.d.ShoeShop.entity.Type;
import sheloumov.v.d.ShoeShop.exceptions.NotFoundExceptions;
import sheloumov.v.d.ShoeShop.service.BrandService;
import sheloumov.v.d.ShoeShop.service.TypeService;

import java.util.List;

@RestController
@RequestMapping(value = "api/items")
public class ItemController {
    private final BrandService brandService;

    private final TypeService typeService;

    @Autowired
    public ItemController(BrandService brandService, TypeService typeService) {
        this.brandService = brandService;
        this.typeService = typeService;
    }
    // Brand controller

    @GetMapping("/brand")
    public ResponseEntity<List<Brand>> getAllBrands(){
        return new ResponseEntity<>(brandService.getAllBrand(), HttpStatus.OK);
    }

    @PostMapping("/brand/create")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand){
        return new ResponseEntity<>(brandService.createBrand(brand), HttpStatus.OK);
    }

    @DeleteMapping("/brand/delete/{id}")
    public ResponseEntity<Brand> deleteBrand(@PathVariable Long id){
        try {
            return new ResponseEntity<>(brandService.deleteBrand(id), HttpStatus.OK);
        } catch (NotFoundExceptions e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Brand> findBrandByName(@PathVariable String name){
          try {
              return new ResponseEntity<>(brandService.findOneByName(name), HttpStatus.OK);
          } catch (NotFoundExceptions e){
              return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
          }
    }

    //Type Controller
    @GetMapping("/type")
    public ResponseEntity<List<Type>> getAllTypes(){
        return new ResponseEntity<>(typeService.getAllBrand(), HttpStatus.OK);
    }

    @PostMapping("/type/create")
    public ResponseEntity<Type> createBrand(@RequestBody Type type){
        return new ResponseEntity<>(typeService.createType(type), HttpStatus.OK);
    }

    @DeleteMapping("/type/delete/{id}")
    public ResponseEntity<Type> deleteType(@PathVariable Long id){
        try {
            return new ResponseEntity<>(typeService.deleteBrand(id), HttpStatus.OK);
        } catch (NotFoundExceptions e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
