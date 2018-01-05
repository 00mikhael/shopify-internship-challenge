package shopify.intern.products.data.api;

import com.google.gson.annotations.SerializedName;

import shopify.intern.products.data.database.entities.Detail;

/**
 * Created by gravity on 1/4/18.
 */

public class DetailResponse {
    @SerializedName("product")
    private Detail detail;

    public Detail getDetail() {
        return detail;
    }
}
