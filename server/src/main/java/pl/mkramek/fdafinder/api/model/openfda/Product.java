package pl.mkramek.fdafinder.api.model.openfda;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Product(
        @JsonProperty("brand_name")
        String brandName,
        @JsonProperty("dosage_form")
        String dosageForm,
        @JsonProperty("marketing_status")
        String marketingStatus
) {}
