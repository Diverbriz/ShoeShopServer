package sheloumov.v.d.ShoeShop.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sheloumov.v.d.ShoeShop.entity.Brand;
import sheloumov.v.d.ShoeShop.entity.Item;
import sheloumov.v.d.ShoeShop.entity.ItemImage;
import sheloumov.v.d.ShoeShop.entity.Type;
import sheloumov.v.d.ShoeShop.exceptions.NotFoundExceptions;
import sheloumov.v.d.ShoeShop.service.BrandService;
import sheloumov.v.d.ShoeShop.service.ItemImageService;
import sheloumov.v.d.ShoeShop.service.ItemService;
import sheloumov.v.d.ShoeShop.service.TypeService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(value = "api/items")
public class ItemController {
    @Value("${upload.file}")
    private String uploadPath;
    private final BrandService brandService;

    private final TypeService typeService;

    private final ItemService itemService;

    private final ItemImageService itemImageService;
    @Autowired
    public ItemController(BrandService brandService, TypeService typeService, ItemService itemService, ItemImageService itemImageService) {
        this.brandService = brandService;
        this.typeService = typeService;
        this.itemService = itemService;
        this.itemImageService = itemImageService;
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

    @GetMapping("brand/{name}")
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
        return new ResponseEntity<>(typeService.getAllType(), HttpStatus.OK);
    }

    @PostMapping("/type/create")
    public ResponseEntity<Type> createBrand(@RequestBody Type type){
        return new ResponseEntity<>(typeService.createType(type), HttpStatus.OK);
    }

    @DeleteMapping("/type/delete/{id}")
    public ResponseEntity<Type> deleteType(@PathVariable Long id){
        try {
            return new ResponseEntity<>(typeService.deleteItem(id), HttpStatus.OK);
        } catch (NotFoundExceptions e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("type/{name}")
    public ResponseEntity<Type> findTypeByName(@PathVariable String name){
        try {
            return new ResponseEntity<>(typeService.findOneByName(name), HttpStatus.OK);
        } catch (NotFoundExceptions e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Item Image controller
    @GetMapping("/item/images")
    public ResponseEntity<List<ItemImage>> getAllItemImages(){
        return new ResponseEntity<>(itemImageService.getAllItemImage(), HttpStatus.OK);
    }



//    @PostMapping("/item/image/create")
//    public ResponseEntity<ItemImage> createItemImage(@RequestBody ItemImage item){
////        try{
////
////            itemImageService.createItemImage(item);
////        }
//
//        return new ResponseEntity<>(itemImageService.createItemImage(item), HttpStatus.OK);
//    }

    @DeleteMapping("/item/image/delete/{id}")
    public ResponseEntity<ItemImage> deleteItemImages(@PathVariable Long id){
        try {
            itemImageService.deleteAllItemImageById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundExceptions e){

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    // Item controller

    @GetMapping("")
    public ResponseEntity<List<Item>> getAllItems(){
        return new ResponseEntity<>(itemService.getAllItem(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        return new ResponseEntity<>(itemService.createItem(item), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long id){
        try {
            return new ResponseEntity<>(itemService.deleteItem(id), HttpStatus.OK);
        } catch (NotFoundExceptions e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<Item> findItemByName(@PathVariable String name){
        try {
            return new ResponseEntity<>(itemService.findOneByName(name), HttpStatus.OK);
        } catch (NotFoundExceptions e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
