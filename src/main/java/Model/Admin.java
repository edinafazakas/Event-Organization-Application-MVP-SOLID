package Model;

import javax.persistence.*;

@Entity
@Table(name = "administrator")
public class Admin{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // other fields and methods

    public Admin(User user) {
        this.user = user;
    }

    public Admin() {

    }

    public Long getIdAdmin() {
        return id;
    }

    public void setIdAdmin(Long idAdmin) {
        this.id = idAdmin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
