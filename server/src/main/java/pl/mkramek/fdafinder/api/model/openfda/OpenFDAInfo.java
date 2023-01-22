package pl.mkramek.fdafinder.api.model.openfda;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OpenFDAInfo(
        @JsonProperty("manufacturer_name")
        List<String> manufacturers
) {}
