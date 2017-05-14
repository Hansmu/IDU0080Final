package ee.ttu.web.main.domain.json;

import java.util.List;

public class CourierJson {
    private Long id;
    private String courierName;
    private int percentFromOrder;
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

    public int getPercentFromOrder() {
        return percentFromOrder;
    }

    public void setPercentFromOrder(int percentFromOrder) {
        this.percentFromOrder = percentFromOrder;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
