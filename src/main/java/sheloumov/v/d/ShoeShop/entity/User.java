package sheloumov.v.d.ShoeShop.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Поле не должно быть пустым")
    private String login;
    @Column(nullable = false)
    private String password;

    @Column
    private Roles role;

    public User(){}

    public User(String login, String password, Roles role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if (!(obj instanceof User) ){
            return false;
        }

        User user = (User) obj;

        return Objects.equals(this.id, user.id) && Objects.equals(this.login, user.login)
                && Objects.equals(this.password, user.password);

    }



    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.login, this.password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

