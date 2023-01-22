package pl.mkramek.fdafinder.api.model.openfda;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenFDAResponse(
        @JsonProperty("results")
        List<Results> results
) {}
