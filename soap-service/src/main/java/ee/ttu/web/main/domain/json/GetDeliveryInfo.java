package ee.ttu.web.main.domain.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="courierId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "courierId",
        "orderId"
})
@XmlRootElement(name = "getDeliveryInfo")
public class GetDeliveryInfo {

    protected long courierId;
    protected long orderId;

    /**
     * Gets the value of the courierId property.
     *
     */
    public long getCourierId() {
        return courierId;
    }

    /**
     * Sets the value of the courierId property.
     *
     */
    public void setCourierId(long value) {
        this.courierId = value;
    }

    /**
     * Gets the value of the orderId property.
     *
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     *
     */
    public void setOrderId(long value) {
        this.orderId = value;
    }

}
