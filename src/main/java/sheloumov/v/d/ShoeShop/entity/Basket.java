package sheloumov.v.d.ShoeShop.entity;

import javax.persistence.*;

@Entity
@Table(name = "basket")
public class Basket {
    @Id
    @SequenceGenerator(name = "basket_sequence", sequenceName = "basket_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_sequence")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
