package ee.ttu.web.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courier")
public class Courier {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String courierName;

    @ManyToMany
    @JoinTable(name = "courier_address",
            joinColumns = @JoinColumn(name = "courier_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "courier_address_id", referencedColumnName = "id")
    )
    private List<Address> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
