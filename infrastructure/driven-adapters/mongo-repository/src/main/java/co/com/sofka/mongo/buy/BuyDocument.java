package co.com.sofka.mongo.buy;

import co.com.sofka.model.buy.BuyProducts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class BuyDocument {

    @Id
    private String idBuy;
    private Date date;
    private String idType;
    private String id;
    private String clientName;
    private List<BuyProducts> buyProducts;
}
