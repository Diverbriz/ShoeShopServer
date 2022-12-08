package sheloumov.v.d.ShoeShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sheloumov.v.d.ShoeShop.entity.Item;
import sheloumov.v.d.ShoeShop.entity.Type;
import sheloumov.v.d.ShoeShop.exceptions.NotFoundExceptions;
import sheloumov.v.d.ShoeShop.repository.ItemRepository;
import sheloumov.v.d.ShoeShop.repository.TypeRepository;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item){
        if(itemRepository.findFirstByName(item.getName()) != null){
            Item type1 = itemRepository.findFirstByName(item.getName());
            type1.setName(item.getName());
            itemRepository.save(type1);
            return type1;
        }
        else {
            itemRepository.save(item);
            return item;
        }
    }

    public Item findOneByName(String name){

        Item item = itemRepository.findFirstByName(name);
        if(item != null){
            return item;
        }
        else {
            throw  new NotFoundExceptions();
        }
    }

    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }

    public Item deleteItem(Long id){
        Item item = itemRepository.findFirstById(id);
        if(item != null){
            itemRepository.delete(item);
            return item;
        }
        else {
            throw new NotFoundExceptions();
        }
    }
}
