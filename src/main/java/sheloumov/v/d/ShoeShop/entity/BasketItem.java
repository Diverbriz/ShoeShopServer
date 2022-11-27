package sheloumov.v.d.ShoeShop.entity;

import javax.persistence.*;

@Entity
@Table(name = "basket_item")
public class BasketItem {
    @Id
    @SequenceGenerator(name = "basket_item_sequence", sequenceName = "basket_item_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_item_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basketId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "counter")
    private Long counter;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public Basket getBasketId() {
        return basketId;
    }

    public void setBasketId(Basket basketId) {
        this.basketId = basketId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }
}
