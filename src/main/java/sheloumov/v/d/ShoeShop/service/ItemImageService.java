package sheloumov.v.d.ShoeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sheloumov.v.d.ShoeShop.entity.Item;
import sheloumov.v.d.ShoeShop.entity.ItemImage;
import sheloumov.v.d.ShoeShop.exceptions.NotFoundExceptions;
import sheloumov.v.d.ShoeShop.repository.ItemImageRepository;

import java.util.List;

@Service
public class ItemImageService {
    private final ItemImageRepository itemImageRepository;

    @Autowired
    public ItemImageService(ItemImageRepository itemImageRepository) {
        this.itemImageRepository = itemImageRepository;
    }

    public ItemImage createItemImage(ItemImage itemImage){
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

    public List<ItemImage> getItemImageByItemId(Item itemId){
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


}
