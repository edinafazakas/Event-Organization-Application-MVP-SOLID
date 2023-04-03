package Model;
import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public Client(User user) {
        this.user = user;
    }

    public Client() {

    }

    public Long getIdClient() {
        return id;
    }

    public void setIdClient(Long idClient) {
        this.id = idClient;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
