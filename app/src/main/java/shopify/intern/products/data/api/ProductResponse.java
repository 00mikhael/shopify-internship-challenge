package shopify.intern.products.data.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import shopify.intern.products.data.database.entities.Product;

/**
 * Created by gravity on 1/4/18.
 */

public class ProductResponse {
    @SerializedName("products")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }
}
