package ee.ttu.web.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "client_order")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String deliveryAddress;
    private BigDecimal orderCost;

    @ManyToMany
    @JoinTable(name = "order_seller_address",
            joinColumns = @JoinColumn(name = "client_order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "seller_address_id", referencedColumnName = "id")
    )
    private List<Address> addresses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }
}
