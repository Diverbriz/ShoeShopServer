package sheloumov.v.d.ShoeShop.entity;


import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @SequenceGenerator(name = "item_sequence", sequenceName = "item_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_sequence")
    private Long id;

    private String name;

    private float price;

    private float rating;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type typeId;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brandId;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Type getTypeId() {
        return typeId;
    }

    public void setTypeId(Type typeId) {
        this.typeId = typeId;
    }

    public Brand getBrandId() {
        return brandId;
    }

    public void setBrandId(Brand brandId) {
        this.brandId = brandId;
    }
}
