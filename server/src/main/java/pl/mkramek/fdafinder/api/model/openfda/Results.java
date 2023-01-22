package pl.mkramek.fdafinder.api.model.openfda;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Results(
        @JsonProperty("products")
        List<Product> products,
        @JsonProperty("openfda")
        OpenFDAInfo openFDAInfo
) {}
