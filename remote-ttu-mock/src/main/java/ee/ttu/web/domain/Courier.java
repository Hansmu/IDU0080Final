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

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="address_id")
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
