package co.com.sofka.model.buy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Buy {

    private String idBuy;
    private Date date;
    private String idType;
    private String id;
    private String clientName;
    private ArrayList<BuyProducts> buyProducts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Buy)) return false;
        Buy buy = (Buy) o;
        return getIdBuy().equals(buy.getIdBuy()) && getDate().equals(buy.getDate()) && getIdType().equals(buy.getIdType()) && getId().equals(buy.getId()) && getClientName().equals(buy.getClientName()) && getBuyProducts().equals(buy.getBuyProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdBuy(), getDate(), getIdType(), getId(), getClientName(), getBuyProducts());
    }
}
