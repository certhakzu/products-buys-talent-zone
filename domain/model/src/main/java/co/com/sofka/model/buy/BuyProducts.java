package co.com.sofka.model.buy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyProducts {
    private String idProduct;
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BuyProducts)) return false;
        BuyProducts that = (BuyProducts) o;
        return getIdProduct().equals(that.getIdProduct()) && getQuantity().equals(that.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProduct(), getQuantity());
    }
}
