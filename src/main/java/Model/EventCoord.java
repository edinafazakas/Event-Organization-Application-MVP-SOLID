package Model;

import Model.User;

import javax.persistence.*;
@Entity
@Table(name = "event_coord")
public class EventCoord{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public EventCoord(User user) {
        this.user = user;
    }

    public EventCoord() {}

    public Long getIdEvent() {
        return id;
    }

    public void setIdEvent(Long idEvent) {
        this.id = idEvent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}