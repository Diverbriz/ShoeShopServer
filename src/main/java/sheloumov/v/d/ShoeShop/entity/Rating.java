package sheloumov.v.d.ShoeShop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @SequenceGenerator(name = "rating_sequence", sequenceName = "rating_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_sequence")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<User> userId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item itemId;

    @Column(name = "rate")
    private float rate;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public List<User> getUserId() {
        return userId;
    }

    public void setUserId(List<User> userId) {
        this.userId = userId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
