package sheloumov.v.d.ShoeShop.entity;

import javax.persistence.*;

@Entity
@Table(name = "item_info")
public class ItemInfo {
    @Id
    @SequenceGenerator(name = "item_info_sequence", sequenceName = "item_info_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_info_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item itemId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
