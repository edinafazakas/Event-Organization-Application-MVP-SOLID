package Model;
import javax.persistence.*;

@Entity
@Table(name = "EventE")
public class Events {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "scop", nullable = false)
    private String scop;
    @Column(name = "locatie", nullable = false)
    private String locatie;
    @Column(name = "data", nullable = false)
    private String data;
    @Column(name = "numarPersoane", nullable = false)
    private String numarPersoane;



    public Events(String scop, String locatie, String data, String numarPersoane) {
        this.scop = scop;
        this.locatie = locatie;
        this.numarPersoane = numarPersoane;
        this.data = data;
    }

    public Events() {

    }

    public Long getIdEvent() {
        return id;
    }

    public void setIdEvent(Long idEvent) {
        this.id = idEvent;
    }

    public String getScop() {
        return scop;
    }

    public void setScop(String scop) {
        this.scop = scop;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getNumarPersoane() {
        return numarPersoane;
    }
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setNumarPersoane(String numarPersoane) {
        this.numarPersoane = numarPersoane;
    }
}
