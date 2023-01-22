package pl.mkramek.fdafinder.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.mkramek.fdafinder.api.model.openfda.Product;

import java.io.Serializable;
import java.util.List;

public class FinderResponse implements Serializable {
    private String manufacturer;
    private List<Product> products;

    public String getManufacturer() {
        return manufacturer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public FinderResponse(String manufacturer, List<Product> products) {
        this.manufacturer = manufacturer;
        this.products = products;
    }
}
