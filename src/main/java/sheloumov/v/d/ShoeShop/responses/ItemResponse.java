package sheloumov.v.d.ShoeShop.responses;

import sheloumov.v.d.ShoeShop.entity.Brand;
import sheloumov.v.d.ShoeShop.entity.Item;
import sheloumov.v.d.ShoeShop.entity.ItemImage;
import sheloumov.v.d.ShoeShop.entity.Type;

import java.util.List;

public class ItemResponse {
    private Item item;
    private List<ItemImage> itemImage;
    private Brand brand;
    private Type type;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<ItemImage> getItemImage() {
        return itemImage;
    }

    public void setItemImage(List<ItemImage> itemImage) {
        this.itemImage = itemImage;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
