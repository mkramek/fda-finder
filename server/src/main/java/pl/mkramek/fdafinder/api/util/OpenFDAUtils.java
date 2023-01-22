package pl.mkramek.fdafinder.api.util;

import pl.mkramek.fdafinder.api.model.openfda.OpenFDAResponse;
import pl.mkramek.fdafinder.api.model.openfda.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class OpenFDAUtils {
    public static List<String> combineManufacturers(OpenFDAResponse data) {
        List<String> manufacturers = new ArrayList<>();
        data.results().forEach(element -> manufacturers.addAll(element.openFDAInfo().manufacturers()));
        return manufacturers;
    }

    public static Optional<String> findMatchingManufacturer(String phrase, List<String> manufacturers) {
        return manufacturers.stream().filter(m -> m.toLowerCase(Locale.getDefault()).contains(phrase.toLowerCase())).findFirst();
    }

    public static List<Product> deduplicateProducts(List<Product> products) {
        return products.stream().distinct().collect(Collectors.toList());
    }
}
