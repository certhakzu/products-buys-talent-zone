package co.com.sofka.mongo.product;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDocument {
    @Id
    private String id;
    @NotNull
    private String name;
    @NotNull
    private Integer inInventory;
    @NotNull
    private Boolean enabled;
    @NotNull
    private Integer min;
    @NotNull
    private Integer max;
}
