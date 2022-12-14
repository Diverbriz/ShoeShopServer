package sheloumov.v.d.ShoeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sheloumov.v.d.ShoeShop.entity.Item;
import sheloumov.v.d.ShoeShop.entity.ItemImage;
import sheloumov.v.d.ShoeShop.exceptions.NotFoundExceptions;
import sheloumov.v.d.ShoeShop.repository.ItemImageRepository;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ItemImageService {
    @Value("${upload.file}")
    private String uploadPath;

    private final ItemImageRepository itemImageRepository;

    @Autowired
    public ItemImageService(ItemImageRepository itemImageRepository) {
        this.itemImageRepository = itemImageRepository;
    }

    public ItemImage createItemImage(MultipartFile file) throws IOException {
        ItemImage itemImage = new ItemImage();

        if(file != null){
            Path filepath = Paths.get(uploadPath, file.getOriginalFilename());
            file.transferTo(filepath);
            System.out.println(filepath.getFileName());
            itemImage.setUrl(file.getOriginalFilename());
        }
        itemImageRepository.save(itemImage);
        return itemImage;
    }



//    public ItemImage findOneByName(String name){
//
//        Item item = itemImageRepository.findFirstByName(name);
//        if(item != null){
//            return item;
//        }
//        else {
//            throw  new NotFoundExceptions();
//        }
//    }

    public List<ItemImage> getAllItemImage(){
        return itemImageRepository.findAll();
    }

    public List<ItemImage> getItemImageByItemId(Long itemId){

        return itemImageRepository.findAllByItemId(itemId);
    }

    public boolean deleteAllItemImageById(Long id){
//        ItemImage itemImage = itemImageRepository.findFirstById(id);
//        if(item != null){
//            itemRepository.delete(item);
//            return item;
//        }
//        else {
//            throw new NotFoundExceptions();
//        }
        try {
            itemImageRepository.deleteAllById(id);
            return true;
        }catch (NotFoundExceptions e){
            e.printStackTrace();
            return false;
        }
    }


    public ItemImage findByName(String url) {
        return itemImageRepository.findFirstByUrl(url);
    }


    public ItemImage findFirstById(Long id){

        return itemImageRepository.findById(id).get();
    }
}
