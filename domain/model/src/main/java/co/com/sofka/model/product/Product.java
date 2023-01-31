package co.com.sofka.model.product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String id;
    private String name;
    private Integer inInventory;
    private Boolean enabled;
    private Integer min;
    private Integer max;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) && getName().equals(product.getName()) && getInInventory().equals(product.getInInventory()) && getEnabled().equals(product.getEnabled()) && getMin().equals(product.getMin()) && getMax().equals(product.getMax());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getInInventory(), getEnabled(), getMin(), getMax());
    }
}
