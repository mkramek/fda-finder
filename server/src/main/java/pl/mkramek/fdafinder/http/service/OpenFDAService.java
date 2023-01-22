package pl.mkramek.fdafinder.http.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.mkramek.fdafinder.api.model.openfda.OpenFDAResponse;
import pl.mkramek.fdafinder.api.model.openfda.Product;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class OpenFDAService {

    @Value("${application.http.fda_url}")
    private String fdaUrl;

    @Value("${application.http.default_items_per_search}")
    private int defaultLimit;

    private final RestTemplate template;

    public OpenFDAService(RestTemplateBuilder builder) {
        this.template = builder.build();
    }

    public List<Product> getProductList(@Nullable OpenFDAResponse response) {
        if (Objects.isNull(response)) {
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Response is null");
        } else {
            List<Product> products = new ArrayList<>();
            response.results().forEach(result -> products.addAll(result.products()));
            return products;
        }
    }

    public ResponseEntity<OpenFDAResponse> getFDAResponse(String manufacturerName, String brandName, Integer limit) {
        if (manufacturerName == null || manufacturerName.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Manufacturer cannot be empty");
        } else {
            String link = String.format("%s?limit=%s&search=openfda.manufacturer_name:%s", fdaUrl, limit == null ? defaultLimit : limit, URLEncoder.encode(manufacturerName, StandardCharsets.UTF_8));
            URI targetUri;
            if (brandName == null || brandName.isEmpty()) {
                targetUri = URI.create(link);
            } else {
                targetUri = URI.create(String.format("%s+AND+openfda.brand_name:%s", link, brandName));
            }
            return template.getForEntity(targetUri, OpenFDAResponse.class);
        }
    }
}
