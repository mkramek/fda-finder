package pl.mkramek.fdafinder.http.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.mkramek.fdafinder.api.model.FinderResponse;
import pl.mkramek.fdafinder.api.model.openfda.OpenFDAResponse;
import pl.mkramek.fdafinder.api.model.openfda.Product;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static pl.mkramek.fdafinder.api.util.OpenFDAUtils.*;

@Service
public class FinderService {

    @Autowired
    private OpenFDAService openFDAService;

    public FinderResponse handleRequest(String manufacturer, String brand, Integer limit) {
        ResponseEntity<OpenFDAResponse> response = openFDAService.getFDAResponse(manufacturer, brand, limit);
        if (response.getStatusCode().is2xxSuccessful()) {
            OpenFDAResponse body = response.getBody();
            if (Objects.isNull(body)) {
                throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Response body is empty");
            } else {
                List<String> manufacturers = combineManufacturers(body);
                Optional<String> manufacturerName = findMatchingManufacturer(manufacturer, manufacturers);
                if (manufacturerName.isPresent()) {
                    List<Product> products = openFDAService.getProductList(response.getBody());
                    return new FinderResponse(manufacturerName.get(), deduplicateProducts(products));
                } else {
                    throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "No correct manufacturer in response");
                }
            }
        } else {
            throw new HttpClientErrorException(response.getStatusCode(), "Error while fetching data");
        }
    }
}
